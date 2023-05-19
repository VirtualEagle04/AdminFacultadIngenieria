package co.edu.unbosque.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private PersistenciaEstudiantesDTO lista_temp;
	
	
	private int contador_cambios = 0; //SE RESETEA CADA X CAMBIOS
	private ArrayList<String> valores;
	
	private Scanner sc;
	
	private long documento_temp;
	private String nombres_temp, apellidos_temp, correo_temp, usuario_temp, programa_temp, jornada_temp, lugar_temp, origen_temp, fecha_string_temp ;
	private Date fecha_temp, fechaR_temp;
	private char genero_temp;
	private String activacion_usuario, activacion_codigo;
	private String admin_usuario, admin_clave;

	public Controller() {
		edao = new EstudianteDAO();
		uuidDAO = new UUIDUsuarioDAO();
		adao = new AdminDAO();
		pdao = new PersistenciaEstudiantesDAO();
		valores = new ArrayList<>();
		sc = new Scanner(System.in);
		vp = new MainWindow();
		
		agregarLectores();
		cargarModelos();
		cargarValores();
	}
	
	public void cargarModelos() {
		
		DefaultListModel<String> temp_modelo_estudiantes = vp.getAdmincontroll().getModelo();
		temp_modelo_estudiantes.clear();
		for(int i = 0; i < edao.getLista().size(); i++) {
			temp_modelo_estudiantes.addElement(edao.getLista().get(i).getDocumento()+" - "+edao.getLista().get(i).getNombres()+" "+edao.getLista().get(i).getApellidos());
		}
		vp.getAdmincontroll().getList_e().setModel(temp_modelo_estudiantes);
				
		ArrayList<String> lista_municipios = FileHandler.cargarDesdeArchivo("municipios.csv");
		vp.getCreationpanel().getModelo_lugar().addAll(lista_municipios);
		vp.getCreationpanel().getLista_lugar().setModel(vp.getCreationpanel().getModelo_lugar());
		
	}
	
	public void cargarValores() {
		for (int i = 0; i < edao.getLista().size(); i++) {
			valores.add(edao.getLista().get(i).getDocumento()+" - "+edao.getLista().get(i).getNombres()+" "+edao.getLista().get(i).getApellidos());
		}
	}
	
	public void reescribirModelos() {
		vp.getAdmincontroll().getModelo().clear();
		for (int i = 0; i < valores.size(); i++) {
			vp.getAdmincontroll().getModelo().addElement(valores.get(i));
		}
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
		
		vp.getCreationpanel().getBpais().addActionListener(this);
		vp.getCreationpanel().getBpais().setActionCommand("mostrar_lista");
		
		vp.getCreationpanel().getConfirmar().addActionListener(this);
		vp.getCreationpanel().getConfirmar().setActionCommand("confirmar_lugar_nacimiento");
		
		//PANEL ACTIVACION
		vp.getActivationpanel().getActivate().addActionListener(this);
		vp.getActivationpanel().getActivate().setActionCommand("activar");
		
		
		//PANEL ADMIN
		vp.getAdminpanel().getJoin().addActionListener(this);
		vp.getAdminpanel().getJoin().setActionCommand("ingresar");
		
		//PANEL ADMIN CONTROL
		vp.getAdmincontroll().getFilter().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) { filtrarEstudiantes(); }
			
			@Override
			public void removeUpdate(DocumentEvent e) { filtrarEstudiantes(); }
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		vp.getAdmincontroll().getCampotipo().addActionListener(this);
		vp.getAdmincontroll().getCampotipo().setActionCommand("campo_tipo");
		
		vp.getAdmincontroll().getGeneratepdf().addActionListener(this);
		vp.getAdmincontroll().getGeneratepdf().setActionCommand("generatePDF");
		
		vp.getAdmincontroll().getGenerate().addActionListener(this);
		vp.getAdmincontroll().getGenerate().setActionCommand("generate");
		
		vp.getAdmincontroll().getActualpdf().addActionListener(this);
		vp.getAdmincontroll().getActualpdf().setActionCommand("actualPDF");
		
		vp.getAdmincontroll().getDelete().addActionListener(this);
		vp.getAdmincontroll().getDelete().setActionCommand("eliminar_estudiante");
		
		vp.getAdmincontroll().getActivate().addActionListener(this);
		vp.getAdmincontroll().getActivate().setActionCommand("activar_estudiante");
		
		vp.getAdmincontroll().getList_e().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for(EstudianteDTO estudiante : edao.getLista()) {
					long id = Long.parseLong(vp.getAdmincontroll().getList_e().getSelectedValue().split(" ")[0]);
					if(id == estudiante.getDocumento()){
						vp.getAdmincontroll().getArea1().setText(estudiante.toString());
						
						break;
					}
				}
			}
		});
		
		vp.getAdmincontroll().getList_pdf().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String fecha_temp = vp.getAdmincontroll().getList_pdf().getSelectedValue();
				SimpleDateFormat sdf1 = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
				
				for(PersistenciaEstudiantesDTO lista_individual : pdao.getLista_pdfs()) {
					if(sdf1.format(lista_individual.getFecha_generacion()).equalsIgnoreCase(fecha_temp)) {
						lista_temp = lista_individual;
						break;
					}
				}
				
			}
		});
	}
	
	public void filtrarEstudiantes() {
		String texto_filtro = vp.getAdmincontroll().getFilter().getText();
		filtrarModelo((DefaultListModel<String>) vp.getAdmincontroll().getList_e().getModel(), texto_filtro);
	}
	
	public void filtrarModelo(DefaultListModel<String> modelo, String texto_filtro) {
		modelo.clear();
		for(int i = 0; i < valores.size(); i++) {
			String s = valores.get(i);
			if(s.toLowerCase().contains(texto_filtro.toLowerCase())) {
				modelo.addElement(s);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Student_registration":{
			
			vp.getAdmin().setVisible(false);
			vp.getStudents().setVisible(false);
			vp.getActivation().setVisible(false);
			
			vp.getCreationpanel().setVisible(true);
			vp.getActivationpanel().setVisible(false);
			vp.getAdminpanel().setVisible(false);
			break;
		}
		case "Activation":{
			
			vp.getAdmin().setVisible(false);
			vp.getStudents().setVisible(false);
			vp.getActivation().setVisible(false);
			
			vp.getActivationpanel().setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "Admin":{
			
			vp.getAdmin().setVisible(false);
			vp.getStudents().setVisible(false);
			vp.getActivation().setVisible(false);
			
			vp.getAdminpanel().setVisible(true);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "back_creation":{
			
			vp.setVisible(true);
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
		}
		case "back_activation":{
			
			vp.setVisible(true);
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
		}
		case "back_admin":{
			
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "back_admin_controll":{
			
			vp.setVisible(true);
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getAdmincontroll().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			break;
		}
		case "abrir_calendario": {
			
			vp.getCreationpanel().getMcalend().setVisible(true);
			vp.getCreationpanel().getJornada().setVisible(false);
			vp.getCreationpanel().getBpais().setVisible(false);
			vp.getCreationpanel().getAgregar().setVisible(false);
			
			break;
		}
		case "confirmar_fecha": {
			
			vp.getCreationpanel().getMcalend().setVisible(false);
			vp.getCreationpanel().getJornada().setVisible(true);
			vp.getCreationpanel().getBpais().setVisible(true);
			vp.getCreationpanel().getAgregar().setVisible(true);
			
			if(vp.getCreationpanel().getCalendario().getCalendar() != null) {
				
				Calendar fecha_seleccionada = vp.getCreationpanel().getCalendario().getCalendar();

				int dia = fecha_seleccionada.get(Calendar.DAY_OF_MONTH);
				int mes = fecha_seleccionada.get(Calendar.MONTH) + 1;
				int anio = fecha_seleccionada.get(Calendar.YEAR);

				fecha_string_temp = dia + "/" + mes + "/" + anio;
				vp.getCreationpanel().getFecha().setText(fecha_string_temp );
			}
			break;
		}
		case "agregar": {
			
			documento_temp = Long.parseLong(vp.getCreationpanel().getDocumento().getText());
			nombres_temp = vp.getCreationpanel().getNombre().getText();
			apellidos_temp = vp.getCreationpanel().getApellido().getText();
			String generoString = (String) vp.getCreationpanel().getGenero().getSelectedItem();
			genero_temp = generoString.charAt(0);
			
			// CREACION DE USUARIO
			usuario_temp = "";
			if (nombres_temp.split(" ").length > 1) {
				usuario_temp = nombres_temp.split(" ")[0].charAt(0) + "" + nombres_temp.split(" ")[1].charAt(0)
						+ apellidos_temp.split(" ")[0];
			} else {
				usuario_temp = nombres_temp.charAt(0) + apellidos_temp.split(" ")[0] + apellidos_temp.split(" ")[1].charAt(0);
			}
			usuario_temp = usuario_temp.toLowerCase(); //MOSTRAR USUARIO CON JOptionPane
			correo_temp = vp.getCreationpanel().getCorreo().getText();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fecha_temp = sdf.parse(fecha_string_temp );
			} catch (ParseException e1) {}
			
			programa_temp = (String) vp.getCreationpanel().getPrograma().getSelectedItem();
			jornada_temp = (String) vp.getCreationpanel().getJornada().getSelectedItem();
			lugar_temp = (String) vp.getCreationpanel().getPaises().getText();
			fechaR_temp = new Date();
			origen_temp = (String) vp.getCreationpanel().getNacional().getSelectedItem();
			
			edao.crear(new EstudianteDTO(documento_temp, nombres_temp, apellidos_temp, genero_temp, usuario_temp, correo_temp, fecha_temp, false, programa_temp, jornada_temp, lugar_temp, fechaR_temp, origen_temp));
			valores.add(documento_temp + " - "+nombres_temp+" "+apellidos_temp);
			reescribirModelos();
			JOptionPane.showMessageDialog(null, "Informacion de alta importancia:\nSu usuario es: "+usuario_temp+"\nGuardelo y recuerdelo.", "Usuario", JOptionPane.WARNING_MESSAGE);
			
			// ENVIAR CORREO DE CONFIRMACION
			String codigo = "";
			try {
				codigo = MailSender.createAuthEmail(correo_temp);
			} catch (AddressException e1) {
				e1.printStackTrace();
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}

				try {
					if(MailSender.sendEmail()) {
						JOptionPane.showMessageDialog(null, "Revise su correo e ingrese el Codigo de Confirmacion.", "Registro de Estudiantes", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Error en el envio del correo.", "Registro de Estudiantes", JOptionPane.ERROR_MESSAGE);
					}
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}

			// ALMACENAR USUARIO-CODIGO
			uuidDAO.crear(new UUIDUsuarioDTO(usuario_temp, codigo));
			
			
			break;
		}
		case "cambio_programa": {
			
			programa_temp = (String) vp.getCreationpanel().getPrograma().getSelectedItem();
			
			String[] diurno = {"Diurno"};
			String[] diurno_nocturno = {"Diurno", "Nocturno"};
			
			if(programa_temp.contains("Sistemas") || programa_temp.contains("Electrónica")) {
				vp.getCreationpanel().getJornada().setModel(new DefaultComboBoxModel<>(diurno_nocturno));
				
			}else {
				vp.getCreationpanel().getJornada().setModel(new DefaultComboBoxModel<>(diurno));
			}
			
			break;
		}
		case "cambio_origen": {
			
			origen_temp = (String) vp.getCreationpanel().getNacional().getSelectedItem();
			
			ArrayList<String> lista_municipios = FileHandler.cargarDesdeArchivo("municipios.csv");
			ArrayList<String> lista_paises = FileHandler.cargarDesdeArchivo("paises.csv");
			
			if(origen_temp.contains("Nacional")) {
				vp.getCreationpanel().getModelo_lugar().clear();
				vp.getCreationpanel().getModelo_lugar().addAll(lista_municipios);
			}else {
				vp.getCreationpanel().getModelo_lugar().clear();
				vp.getCreationpanel().getModelo_lugar().addAll(lista_paises);
			}
			vp.getCreationpanel().getLista_lugar().setModel(vp.getCreationpanel().getModelo_lugar());
			
			
			break;
		}
		case "activar": {
			
			activacion_usuario = vp.getActivationpanel().getUser().getText();
			activacion_codigo = vp.getActivationpanel().getCode().getText();
			
			// VERIFICAR LA EXISTENCIA DEL USUARIO
			boolean existe = false;
			EstudianteDTO estudiante_verificar = null;
			for (EstudianteDTO estudiante : edao.getLista()) {
				if (estudiante.getUsuario().equalsIgnoreCase(activacion_usuario)) {
					existe = true;
					estudiante_verificar = estudiante;
					break;
				}	
			}
			if (existe) {
				// VERIFICAR SI ESTA ACTIVO
				if (estudiante_verificar.isEsta_activo()) {
					JOptionPane.showMessageDialog(null, "El estudiante esta ACTIVO.", "Activacion de Estudiante", JOptionPane.INFORMATION_MESSAGE);
				} else { // NO ESTA ACTIVO
					UUIDUsuarioDTO usuario_verificar = null;
					for (UUIDUsuarioDTO uuid_usuario : uuidDAO.getLista()) {
						if (uuid_usuario.getUsuario().equalsIgnoreCase(activacion_usuario)) {
							usuario_verificar = uuid_usuario;
							break;
						}
					}
					if (usuario_verificar.getUuid().equalsIgnoreCase(activacion_codigo)) {
						JOptionPane.showMessageDialog(null, "El codigo es correcto. El estudiante ahora esta ACTIVO.", "Activacion de Estudiante", JOptionPane.INFORMATION_MESSAGE);
						estudiante_verificar.setEsta_activo(true);
						edao.escribirArchivo();
					} else {
						JOptionPane.showMessageDialog(null, "El codigo es incorrecto. Vuelva a intentarlo.", "Activacion de Estudiante", JOptionPane.WARNING_MESSAGE);
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "El usuario: " + activacion_usuario + " no existe como Estudiante.\nRegistre un estudiante primero.", "Activacion de Estudiante", JOptionPane.QUESTION_MESSAGE);
			}
			
			
			break;
		}
		case "mostrar_lista":{
			
			vp.getCreationpanel().getFondolista().setVisible(true);
			vp.getCreationpanel().getConfirmar_fecha().setVisible(false);
			vp.getCreationpanel().getJornada().setVisible(false);
			vp.getCreationpanel().getAgregar().setVisible(false);
			
			break;
		}
		case "confirmar_lugar_nacimiento":{
			
			vp.getCreationpanel().getFondolista().setVisible(false);
			vp.getCreationpanel().getConfirmar_fecha().setVisible(true);
			vp.getCreationpanel().getJornada().setVisible(true);
			vp.getCreationpanel().getAgregar().setVisible(true);
			
			vp.getCreationpanel().getPaises().setText(vp.getCreationpanel().getLista_lugar().getSelectedValue());

			break;
		}
		case "ingresar":{
			admin_usuario = vp.getAdminpanel().getUser2().getText();
			admin_clave = vp.getAdminpanel().getPassword().getText();
			
			boolean existe = false;
			AdminDTO admin_verificacion = null;
			for (AdminDTO admin : adao.getLista()) {
				if (admin.getUsuario_admin().equals(admin_usuario)) {
					admin_verificacion = admin;
					existe = true;
					break;
				}
			}
			if(existe) {
				if (admin_verificacion.getContrasena_admin().equals(admin_clave)) {
					vp.setVisible(true);
					vp.getAdminpanel().setVisible(false);
					vp.getActivationpanel().setVisible(false);
					vp.getAdmincontroll().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos. \nVuelva a intentarlo.", "Inicio Sesión Administradores", JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos. \nVuelva a intentarlo.", "Inicio Sesión Administradores", JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case "campo_tipo": {
			
			String tipo = (String) vp.getAdmincontroll().getCampotipo().getSelectedItem();
			if(tipo.contains("Activo")) {
				DefaultListModel<String> model_temp = (DefaultListModel<String>) vp.getAdmincontroll().getList_e().getModel();
				model_temp.clear();
				for(EstudianteDTO estudiante : edao.getLista()) {
					if(estudiante.isEsta_activo()) {
						model_temp.addElement(estudiante.getDocumento()+" - "+estudiante.getNombres()+" "+estudiante.getApellidos());
					}
				}
			}else if(tipo.contains("Inactivo")) {
				DefaultListModel<String> model_temp = (DefaultListModel<String>) vp.getAdmincontroll().getList_e().getModel();
				model_temp.clear();
				for(EstudianteDTO estudiante : edao.getLista()) {
					if(estudiante.isEsta_activo() == false) {
						model_temp.addElement(estudiante.getDocumento()+" - "+estudiante.getNombres()+" "+estudiante.getApellidos());
					}
				}
			}else if(tipo.contains("Top3 mas estudiantes")) {
				DefaultListModel<String> model_temp = (DefaultListModel<String>) vp.getAdmincontroll().getList_e().getModel();
				model_temp.clear();
				
				HashMap<String, Integer> map = MTC.modaPrograma(edao.getLista());
				ArrayList<HashMap.Entry<String, Integer>> res = new ArrayList<>();
				
				for(HashMap.Entry<String, Integer> pareja : map.entrySet()) {
					
					res.add(pareja);
					
				}
				//BUBBLESORT
				for (int i = 0; i < res.size(); i++) {
					for(int j = 0; j < res.size()-1; j++) {
						if(res.get(j).getValue() < res.get(j+1).getValue()) {
							HashMap.Entry<String, Integer> aux = res.get(j);
							res.set(j, res.get(j+1));
							res.set(j+1, aux);
						}
					}
				}
				String[] top = new String[3];
				top[0] = res.get(0).getKey();
				top[1] = res.get(1).getKey();
				top[2] = res.get(2).getKey();
				
				for(EstudianteDTO estudiante : edao.getLista()) {
					
					for(String programa : top) {
						if(estudiante.getPrograma().equalsIgnoreCase(programa)) {
							model_temp.addElement(estudiante.getDocumento()+" - "+estudiante.getNombres()+" "+estudiante.getApellidos());
							break;
						}
					}
					
				}
			}else if(tipo.contains("Top3 mas colombianos")) {
				
			}
			
			break;
		}
		case "eliminar_estudiante": {
			int index = 0;
			long id = 0;
			for(int i = 0; i < edao.getLista().size(); i++) {
				id = Long.parseLong(vp.getAdmincontroll().getList_e().getSelectedValue().split(" ")[0]);
				if(id == edao.getLista().get(i).getDocumento()){
					index = i;
					break;
				}
			}
			int res = JOptionPane.showConfirmDialog(null, "Desea eliminar al estudiante con Documento: \n"+id, "Eliminar Estudiante", JOptionPane.YES_NO_CANCEL_OPTION);
			if(res==0) {
				if(edao.eliminar(index)) {
					valores.remove(index);
					reescribirModelos();
					JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nha sido eliminado correctamente.", "Eliminacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nno ha podido ser eliminado.", "Eliminacion Erronea", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			
			break;
		}
		case "activar_estudiante": {
			int index = 0;
			long id = 0;
			EstudianteDTO ea = null;
			for(int i = 0; i < edao.getLista().size(); i++) {
				id = Long.parseLong(vp.getAdmincontroll().getList_e().getSelectedValue().split(" ")[0]);
				if(id == edao.getLista().get(i).getDocumento()){
					index = i;
					ea = edao.getLista().get(i);
					break;
				}
			}
			if(ea.isEsta_activo()) { //ACTIVO -> INACTIVO
				int res = JOptionPane.showConfirmDialog(null, "Desea INACTIVAR al estudiante con Documento: \n"+id, "Inactivación Estudiante", JOptionPane.YES_NO_CANCEL_OPTION);
				if(res==0) {
					if(edao.actualizar(index, new EstudianteDTO(ea.getDocumento(), ea.getNombres(), ea.getApellidos(), ea.getGenero(), ea.getUsuario(), ea.getCorreo(), ea.getFecha_nacimiento(), false, ea.getPrograma(), ea.getJornada(), ea.getLugar_nacimiento(), ea.getFecha_registro(), ea.getNacional_extranjero()))) {
						reescribirModelos();
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nha sido INACTIVADO correctamente.", "Inactivación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nno ha podido ser INACTIVADO.", "Inactivacion Erronea", JOptionPane.ERROR_MESSAGE);
					}
				}
			}else { //INACTIVO -> ACTIVO
				int res = JOptionPane.showConfirmDialog(null, "Desea ACTIVAR al estudiante con Documento: \n"+id, "Activación Estudiante", JOptionPane.YES_NO_CANCEL_OPTION);
				if(res==0) {
					if(edao.actualizar(index, new EstudianteDTO(ea.getDocumento(), ea.getNombres(), ea.getApellidos(), ea.getGenero(), ea.getUsuario(), ea.getCorreo(), ea.getFecha_nacimiento(), true, ea.getPrograma(), ea.getJornada(), ea.getLugar_nacimiento(), ea.getFecha_registro(), ea.getNacional_extranjero()))) {
						reescribirModelos();
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nha sido ACTIVADO correctamente.", "Activación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nno ha podido ser ACTIVADO.", "Activación Erronea", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			break;
		}
		case "actualPDF": {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
			FileHandler.crearPdf("Estadisticas"+sdf.format(new Date())+".pdf", FileHandler.crearGraficas(edao.getLista()));
			File pdf = new File("src/co/edu/unbosque/model/persistance/Estadisticas"+sdf.format(new Date())+".pdf");
			if(!Desktop.isDesktopSupported()) {
				
			}else {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(pdf);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			break;
		}
		
		case "generatePDF":{
			
			vp.getAdmincontroll().getPanel_pdfs().setVisible(true);
			
			DefaultListModel<String> temp_modelo_pdf = vp.getAdmincontroll().getModelo2();
			temp_modelo_pdf.clear();
			for(int i = 0; i < pdao.getLista_pdfs().size(); i++) {
				temp_modelo_pdf.addElement(pdao.getLista_pdfs().get(i).toString());
			}
			vp.getAdmincontroll().getList_pdf().setModel(temp_modelo_pdf);
			
			
			break;
		}
		case "generate":{
			vp.getAdmincontroll().getPanel_pdfs().setVisible(false);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
			FileHandler.crearPdf("Estadisticas"+sdf.format(lista_temp.getFecha_generacion())+".pdf", FileHandler.crearGraficas(lista_temp.getLista_individual()));
			if(!Desktop.isDesktopSupported()) {
				
			}else {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(new File("src/co/edu/unbosque/model/persistance/Estadisticas"+sdf.format(lista_temp.getFecha_generacion())+".pdf"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			break;
		}

		default:
			break;
		}
		
	}
	
	
	public void run() throws AddressException, MessagingException {
		
//		pdao.crear(new PersistenciaEstudiantesDTO(edao.getLista()));
		
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
