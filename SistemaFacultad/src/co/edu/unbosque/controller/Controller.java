package co.edu.unbosque.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import co.edu.unbosque.model.AdminDAO;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.EstudianteDAO;
import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.PersistenciaEstudiantesDAO;
import co.edu.unbosque.model.PersistenciaEstudiantesDTO;
import co.edu.unbosque.model.UUIDUsuarioDAO;
import co.edu.unbosque.model.UUIDUsuarioDTO;
import co.edu.unbosque.model.persistance.FileHandler;
import co.edu.unbosque.util.MTC;
import co.edu.unbosque.util.MailSender;
import co.edu.unbosque.view.MainWindow;

public class Controller {

	private EstudianteDAO edao;
	private UUIDUsuarioDAO uuidDAO;
	private AdminDAO adao;
	private PersistenciaEstudiantesDAO pdao;
	private MainWindow vp;
	private int contador_cambios = 0; //SE RESETEA CADA X CAMBIOS

	private Scanner sc;

	public Controller() {
		edao = new EstudianteDAO();
		uuidDAO = new UUIDUsuarioDAO();
		adao = new AdminDAO();
		pdao = new PersistenciaEstudiantesDAO();
		sc = new Scanner(System.in);
		vp = new MainWindow();
	}

	public void run() throws AddressException, MessagingException {
		
//		pdao.crear(new PersistenciaEstudiantesDTO(edao.getLista()));
		System.out.println(pdao.mostrarPDFs());
		int seleccion = sc.nextInt();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
		FileHandler.crearPdf("Estadisticas"+sdf.format(pdao.getLista_pdfs().get(seleccion).getFecha_generacion())+".pdf", FileHandler.crearGraficas(pdao.getLista_pdfs().get(seleccion).getLista_individual()));
		
		
		System.exit(0);
		//UNICAMENTE PARA TESTEOS: ELIMINAR DESPUES DE IMPLEMENTARLO A LA VISTA
		ppal: while(true) {
			System.out.println("1) Crear Estudiante\n2) Activar Estudiante\n3) Listados (Estudiantes, UUID, Admins)\n4) Inicio Sesion Admins\n5) Salir");
			int op = sc.nextInt();
			sc.nextLine();
			switch (op) {
			// CREAR ESTUDIANTE
			case 1: {
				System.out.println("Documento: ");
				long documento = sc.nextLong();
				sc.nextLine();
				System.out.println("Nombres: ");
				String nombres = sc.nextLine();
				System.out.println("Apellidos: ");
				String apellidos = sc.nextLine();
				System.out.println("Genero (M/F): ");
				char genero = sc.next().charAt(0);
				sc.nextLine();
				System.out.println("Correo: ");
				String correo = sc.nextLine(); // FILTAR POR CORREOS CORRECTOS Y VALIDOS (REGEX)
				Date fecha_nacimiento = new Date(); // PEDIR FECHA VIA JCALENDAR
				System.out.println("Programa Academico: ");
				String programa = sc.nextLine();
				System.out.println("Jornada Academica: ");
				String jornada = sc.nextLine();
				System.out.println("Lugar de Nacimiento: ");
				String lugar_nacimiento = sc.nextLine();
				Date fecha_registro = new Date();
				System.out.println("Nacional/Extranjero: ");
				String nacional_extranjero = sc.nextLine();

				// CREACION DE USUARIO
				String usuario = "";
				if (nombres.split(" ").length > 1) {
					usuario = nombres.split(" ")[0].charAt(0) + "" + nombres.split(" ")[1].charAt(0)
							+ apellidos.split(" ")[0];
				} else {
					usuario = nombres.charAt(0) + apellidos.split(" ")[0] + apellidos.split(" ")[1].charAt(0);
				}
				usuario = usuario.toLowerCase();

				// CREACION DE ESTUDIANTE
				edao.crear(new EstudianteDTO(documento, nombres, apellidos, genero, usuario, correo, fecha_nacimiento,
						false, programa, jornada, lugar_nacimiento, fecha_registro, nacional_extranjero));

				// ENVIAR CORREO DE CONFIRMACION
				String codigo = MailSender.createAuthEmail(correo);

//					if(MailSender.sendEmail()) {
//						System.out.println("Revise su correo e ingrese el Codigo de Confirmacion.");
//					}else {
//						System.out.println("Error en el envio del correo.");
//					}

				// ALMACENAR USUARIO-CODIGO
				uuidDAO.crear(new UUIDUsuarioDTO(usuario, codigo));

				break;
			}
			// ACTIVACION ESTUDIANTE
			case 2: {
				System.out.println("Ingrese el usuario: ");
				String usuario = sc.nextLine();

				// VERIFICAR LA EXISTENCIA DEL USUARIO
				boolean existe = false;
				EstudianteDTO estudiante_verificar = null;
				for (EstudianteDTO estudiante : edao.getLista()) {
					if (estudiante.getUsuario().equalsIgnoreCase(usuario)) {
						existe = true;
						estudiante_verificar = estudiante;
						break;
					}
				}
				if (existe) {
					// VERIFICAR SI ESTA ACTIVO
					if (estudiante_verificar.isEsta_activo()) {
						System.out.println("El estudiante esta ACTIVO.");
					} else { // NO ESTA ACTIVO
						System.out.println("Ingrese el codigo de confirmacion: ");
						String codigo_verificar = sc.nextLine();

						UUIDUsuarioDTO usuario_verificar = null;
						for (UUIDUsuarioDTO uuid_usuario : uuidDAO.getLista()) {
							if (uuid_usuario.getUsuario().equalsIgnoreCase(usuario)) {
								usuario_verificar = uuid_usuario;
								break;
							}
						}
						if (usuario_verificar.getUuid().equalsIgnoreCase(codigo_verificar)) {
							System.out.println("El codigo es correcto. El estudiante ahora esta ACTIVO.");
							estudiante_verificar.setEsta_activo(true);
							edao.escribirArchivo();
						} else {
							System.out.println("El codigo es incorrecto.");
						}

					}
				} else {
					System.out.println(
							"El usuario: " + usuario + " no existe como Estudiante.\nRegistre un estudiante primero.");
				}

				break;
			}
			// LISTADOS
			case 3: {
				listados: while (true) {
					System.out.println("1) Estudiantes\n2) UUIDs\n3) Administradores\n4) Volver");
					int list = sc.nextInt();
					sc.nextLine();
					switch (list) {
					case 1: {
						System.out.println(edao.mostrarTodo());
						break;
					}
					case 2: {
						System.out.println(uuidDAO.mostrarTodo());
						break;
					}
					case 3: {
						// SE DEBE IMPLEMENTAR QUE SE OCULTEN LAS CONTRASENAS CON * DEPENDIENDO DE LA
						// CANTIDAD DE LETRAS
						System.out.println(adao.mostrarTodo());
						break;
					}
					case 4: {
						break listados;
					}
					}

				}
				break;
			}
			// INICIO SESION ADMINS
			case 4: {
				System.out.println("------Inicio de Sesion------");
				System.out.print("Usuario: ");
				String inicio_usuario = sc.nextLine();
				System.out.print("Contraseña: ");
				String inicio_clave = sc.nextLine();

				boolean existe = false;
				AdminDTO admin_verificacion = null;
				for (AdminDTO admin : adao.getLista()) {
					if (admin.getUsuario_admin().equals(inicio_usuario)) {
						admin_verificacion = admin;
						existe = true;
						break;
					}
				}
				if (existe) {
					if (admin_verificacion.getContrasena_admin().equals(inicio_clave)) {
						System.out.println("Inicio de Sesion correcto.");
					} else {
						System.out.println("Inicio de Sesion incorrecto.");
					}
				} else {
					System.out.println("El usuario no existe. Desea registrarse? (Si/No): ");
					boolean registro = sc.nextLine().equalsIgnoreCase("si") ? true : false;
					if (registro) {
						System.out.print("Usuario: ");
						String registro_usuario = sc.nextLine();
						System.out.print("Contraseña: ");
						String registro_contraseña = sc.nextLine();

						// SE PODRIA IMPLEMENTAR LA FUNCIONALIDAD DE QUE LOS OTROS ADMINS DEBEN
						// CONFIRMAR QUE SE QUIERE CREAR OTRO ADMIN, POR MEDIO DE CORREO

						adao.crear(new AdminDTO(registro_usuario, registro_contraseña));
						System.out.println("Administrador creado.");
					} else {
						break;
					}
				}

				break;
			}
			// SALIR
			case 5: {

				break ppal;
			}

			}
		}
		System.err.println("Fin del Programa");

	}

}
