package co.edu.unbosque.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

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

public class Controller implements ActionListener{

	private EstudianteDAO edao;
	private UUIDUsuarioDAO uuidDAO;
	private AdminDAO adao;
	private PersistenciaEstudiantesDAO pdao;
	private MainWindow vp;
	private int contador_cambios = 0; //SE RESETEA CADA X CAMBIOS

	private Scanner sc;
	
	private long documento_temp;
	private String nombres_temp, apellidos_temp, correo_temp, fechaNacimiento_temp, usuario_temp, programa_temp, jornada_temp, lugar_temp, origen_temp;
	private Date fecha_temp, fechaR_temp;
	private char genero_temp;

	public Controller() {
		edao = new EstudianteDAO();
		uuidDAO = new UUIDUsuarioDAO();
		adao = new AdminDAO();
		pdao = new PersistenciaEstudiantesDAO();
		sc = new Scanner(System.in);
		vp = new MainWindow();
		
		agregarLectores();
	}

	public void agregarLectores() {
		
		//MENU INICIAL
		vp.getStudents().addActionListener(this);
		vp.getStudents().setActionCommand("Student_registration");
		
		vp.getActivation().addActionListener(this);
		vp.getActivation().setActionCommand("Activation");
		
		vp.getAdmin().addActionListener(this);
		vp.getAdmin().setActionCommand("Admin");

		//BOTONES VOLVER
		vp.getCreationpanel().getVolver().addActionListener(this);
		vp.getCreationpanel().getVolver().setActionCommand("back_creation");
		
		vp.getActivationpanel().getBack().addActionListener(this);
		vp.getActivationpanel().getBack().setActionCommand("back_activation");
		
		vp.getAdminpanel().getBack2().addActionListener(this);
		vp.getAdminpanel().getBack2().setActionCommand("back_admin");
		
		vp.getAdmincontroll().getBack3().addActionListener(this);
		vp.getAdmincontroll().getBack3().setActionCommand("back_admin_controll");
		
		//PANEL REGISTRO
		vp.getCreationpanel().getCalendar().addActionListener(this);
		vp.getCreationpanel().getCalendar().setActionCommand("abrir_calendario");
		
		vp.getCreationpanel().getAgregar().addActionListener(this);
		vp.getCreationpanel().getAgregar().setActionCommand("agregar");
		
		vp.getCreationpanel().getConfirmar_fecha().addActionListener(this);
		vp.getCreationpanel().getConfirmar_fecha().setActionCommand("confirmar_fecha");
		
		vp.getCreationpanel().getPrograma().addActionListener(this);
		vp.getCreationpanel().getPrograma().setActionCommand("cambio_programa");
		
		vp.getCreationpanel().getNacional().addActionListener(this);
		vp.getCreationpanel().getNacional().setActionCommand("cambio_origen");
		
		//PANEL ADMIN
		vp.getAdminpanel().getJoin().addActionListener(this);
		vp.getAdminpanel().getJoin().setActionCommand("ingresar");
		
		vp.getAdmincontroll().getGeneratepdf().addActionListener(this);
		vp.getAdmincontroll().getGeneratepdf().setActionCommand("generatePDF");
		
		vp.getAdmincontroll().getGenerate().addActionListener(this);
		vp.getAdmincontroll().getGenerate().setActionCommand("generate");
	}
	
	public void cargarModelos() {
		
		DefaultListModel<String> temp_modelo_estudiantes = vp.getAdmincontroll().getModelo();
		temp_modelo_estudiantes.clear();
		for(int i = 0; i < edao.getLista().size(); i++) {
			temp_modelo_estudiantes.addElement(edao.getLista().get(i).getDocumento()+" "+edao.getLista().get(i).getNombres());
		}
		vp.getAdmincontroll().getList_e().setModel(temp_modelo_estudiantes);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Student_registration":{
			
			vp.getCreationpanel().setVisible(true);
			vp.getActivationpanel().setVisible(false);
			vp.getAdminpanel().setVisible(false);
			break;
		}
		case "Activation":{
			
			vp.getActivationpanel().setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "Admin":{
			
			vp.getAdminpanel().setVisible(true);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "back_creation":{
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
		}
		case "back_activation":{
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
		}
		case "back_admin":{
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "back_admin_controll":{
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getAdmincontroll().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "abrir_calendario": {
			
			vp.getCreationpanel().getMcalend().setVisible(true);
			
			break;
		}
		case "confirmar_fecha": {
			
			vp.getCreationpanel().getMcalend().setVisible(false);
			
			if(vp.getCreationpanel().getCalendario().getCalendar() != null) {
				
				Calendar fecha_seleccionada = vp.getCreationpanel().getCalendario().getCalendar();

				int dia = fecha_seleccionada.get(Calendar.DAY_OF_MONTH);
				int mes = fecha_seleccionada.get(Calendar.MONTH) + 1;
				int anio = fecha_seleccionada.get(Calendar.YEAR);

				LocalDate fecha_nacimiento = LocalDate.of(anio, mes, dia);
				LocalDate fecha_actual = LocalDate.now();

				Period periodo = Period.between(fecha_nacimiento, fecha_actual);

				int dias = periodo.getDays();
				int meses = periodo.getMonths();
				int anios = periodo.getYears();

				int dias_totales = anios * 365 + meses * 30 + dias;

				for (int i = fecha_nacimiento.getYear(); i <= fecha_actual.getYear(); i++) {

					if (Year.of(i).isLeap()) {

						LocalDate primer_dia_anio = LocalDate.of(i, mes, dia);
						if (primer_dia_anio.isAfter(fecha_nacimiento) || primer_dia_anio.isEqual(fecha_nacimiento)
								&& (primer_dia_anio.isBefore(fecha_actual) || primer_dia_anio.isEqual(fecha_actual))) {

							dias_totales++;

						}
					}

				}

				int meses_totales = (anios * 12) + meses;

				fechaNacimiento_temp = dia + "/" + mes + "/" + anio;

				vp.getCreationpanel().getFecha().setText(fechaNacimiento_temp);
			}
			break;
		}
		case "agregar": {
			
			documento_temp = Long.parseLong(vp.getCreationpanel().getDocumento().getText());
			nombres_temp = vp.getCreationpanel().getNombre().getText();
			apellidos_temp = vp.getCreationpanel().getApellido().getText();
			correo_temp = vp.getCreationpanel().getCorreo().getText();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fecha_temp = sdf.parse(fechaNacimiento_temp);
			} catch (ParseException e1) {
			}
			String generoString = (String) vp.getCreationpanel().getGenero().getSelectedItem();
			genero_temp = generoString.charAt(0);
			
			jornada_temp = (String) vp.getCreationpanel().getJornada().getSelectedItem();
			
			
			
			
			
			break;
		}
		case "cambio_programa": {
			
			programa_temp = (String) vp.getCreationpanel().getPrograma().getSelectedItem();
			
			String[] diurno = {"Diurno"};
			String[] diurno_nocturno = {"Diurno", "Nocturno"};
			
			if(programa_temp.contains("sistemas") || programa_temp.contains("Electrónica")) {
				vp.getCreationpanel().getJornada().setModel(new DefaultComboBoxModel<>(diurno_nocturno));
				
			}else {
				vp.getCreationpanel().getJornada().setModel(new DefaultComboBoxModel<>(diurno));
			}
			
			break;
		}
		case "cambio_origen": {
			
			origen_temp = (String) vp.getCreationpanel().getNacional().getSelectedItem();
			
			
			
			break;
		}
		
		
		case "ingresar":{
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getAdmincontroll().setVisible(true);
			
			break;
		}
		case "generatePDF":{
			
			vp.getAdmincontroll().getPanel_pdfs().setVisible(true);
			
			break;
		}
		case "generate":{
			vp.getAdmincontroll().getPanel_pdfs().setVisible(false);
			break;
		}

		default:
			break;
		}
		
	}
	
	
	public void run() throws AddressException, MessagingException {
		
//		pdao.crear(new PersistenciaEstudiantesDTO(edao.getLista()));
//		System.out.println(pdao.mostrarPDFs());
//		int seleccion = sc.nextInt();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
//		FileHandler.crearPdf("Estadisticas"+sdf.format(pdao.getLista_pdfs().get(seleccion).getFecha_generacion())+".pdf", FileHandler.crearGraficas(pdao.getLista_pdfs().get(seleccion).getLista_individual()));
		
		
//		System.exit(0);
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
