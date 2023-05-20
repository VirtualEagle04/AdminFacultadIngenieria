package co.edu.unbosque.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
	
	private int contador_cambios = 0; //SE RESETEA CADA 3 CAMBIOS (Agregar, Eliminar, Activar/Desactivar Estudiante)
	private ArrayList<String> valores;
	private DefaultListModel<String> model_temp;
	
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
		vp = new MainWindow();

		agregarLectores();
		
		//ADMIN CONTROL LIST
		DefaultListModel<String> temp_modelo_estudiantes = vp.getAdmincontrol().getModelo();
		temp_modelo_estudiantes.clear();
		for(int i = 0; i < edao.getLista().size(); i++) {
			temp_modelo_estudiantes.addElement(edao.getLista().get(i).getDocumento()+" "+edao.getLista().get(i).getNombres()+" "+edao.getLista().get(i).getApellidos());
		}
		vp.getAdmincontrol().getList_e().setModel(temp_modelo_estudiantes);
			
		//INICIAR LISTAS PAISES/MUNICIPIOS
		ArrayList<String> lista_municipios = FileHandler.cargarDesdeArchivo("municipios.csv");
		vp.getCreationpanel().getModelo_lugar().addAll(lista_municipios);
		vp.getCreationpanel().getLista_lugar().setModel(vp.getCreationpanel().getModelo_lugar());
		
		//CARGAR VALORES
		for (int i = 0; i < edao.getLista().size(); i++) {
			valores.add(edao.getLista().get(i).getDocumento()+" "+edao.getLista().get(i).getNombres()+" "+edao.getLista().get(i).getApellidos());
		}
		
		//CARGAR NOMBRES
		ArrayList<EstudianteDTO> temp_e = new ArrayList<EstudianteDTO>();
		for(EstudianteDTO estudiante : edao.getLista()) {
			temp_e.add(estudiante);
		}
		
		model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
		for(int i = 0; i < edao.getLista().size(); i++) {
			
			for(int j = 0; j < edao.getLista().size() - 1; j++) {
				
				if(temp_e.get(j).getNombres().split(" ")[0].compareTo(temp_e.get(j+1).getNombres().split(" ")[0]) > 0) {
					EstudianteDTO aux = temp_e.get(j);
					temp_e.set(j, temp_e.get(j+1));
					temp_e.set(j+1, aux);
				}
				
			}
		}
		model_temp.clear();
		for(EstudianteDTO estudiante2 : temp_e) {
			model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
		}
		
		valores.clear();
		for(int i = 0; i < model_temp.size(); i++) {
			valores.add(model_temp.getElementAt(i));
		}
		
		model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
	}
	
	public void remodelar() {
		ArrayList<EstudianteDTO> temp_e = new ArrayList<EstudianteDTO>();
		for(EstudianteDTO estudiante : edao.getLista()) {
			temp_e.add(estudiante);
		}
		
		String tipo = (String) vp.getAdmincontrol().getCampotipo().getSelectedItem();
		if(tipo.contains("Nombre")) {
			vp.getAdmincontrol().getSort().setVisible(true);
			vp.getAdmincontrol().getSort().setEnabled(true);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			for(int i = 0; i < edao.getLista().size(); i++) {
				
				for(int j = 0; j < edao.getLista().size() - 1; j++) {
					
					if(temp_e.get(j).getNombres().split(" ")[0].compareTo(temp_e.get(j+1).getNombres().split(" ")[0]) > 0) {
						EstudianteDTO aux = temp_e.get(j);
						temp_e.set(j, temp_e.get(j+1));
						temp_e.set(j+1, aux);
					}
					
				}
			}
			model_temp.clear();
			for(EstudianteDTO estudiante2 : temp_e) {
				model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
			}
			
			valores.clear();
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
			
		}
		else if(tipo.contains("Apellido")) {
			vp.getAdmincontrol().getSort().setVisible(true);
			vp.getAdmincontrol().getSort().setEnabled(true);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			for(int i = 0; i < edao.getLista().size(); i++) {
				
				for(int j = 0; j < edao.getLista().size() - 1; j++) {
					
					if(temp_e.get(j).getApellidos().split(" ")[0].compareTo(temp_e.get(j+1).getApellidos().split(" ")[0]) > 0) {
						EstudianteDTO aux = temp_e.get(j);
						temp_e.set(j, temp_e.get(j+1));
						temp_e.set(j+1, aux);
					}
					
				}
			}
			model_temp.clear();
			for(EstudianteDTO estudiante2 : temp_e) {
				model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
			}
			
			valores.clear();
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
		}
		else if(tipo.contains("Documento")) {
			vp.getAdmincontrol().getSort().setVisible(true);
			vp.getAdmincontrol().getSort().setEnabled(true);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			for(int i = 0; i < edao.getLista().size(); i++) {
				
				for(int j = 0; j < edao.getLista().size() - 1; j++) {
					
					if(temp_e.get(j).getDocumento() > temp_e.get(j+1).getDocumento()) {
						EstudianteDTO aux = temp_e.get(j);
						temp_e.set(j, temp_e.get(j+1));
						temp_e.set(j+1, aux);
					}
					
				}
			}
			model_temp.clear();
			for(EstudianteDTO estudiante2 : temp_e) {
				model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
			}
			
			valores.clear();
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
		}
		else if(tipo.contains("Activo")) {
			vp.getAdmincontrol().getSort().setVisible(false);
			vp.getAdmincontrol().getSort().setEnabled(false);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			model_temp.clear();
			for(EstudianteDTO estudiante : edao.getLista()) {
				if(estudiante.isEsta_activo()) {
					model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
				}
			}
			
			valores.clear();
			
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
		}else if(tipo.contains("Inactivo")) {
			vp.getAdmincontrol().getSort().setVisible(false);
			vp.getAdmincontrol().getSort().setEnabled(false);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			model_temp.clear();
			for(EstudianteDTO estudiante : edao.getLista()) {
				if(estudiante.isEsta_activo() == false) {
					model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
				}
			}
			
			valores.clear();
			
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
		}else if(tipo.contains("Top 3 Más Estudiantes")) {
			vp.getAdmincontrol().getSort().setVisible(false);
			vp.getAdmincontrol().getSort().setEnabled(false);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
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
						model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
						break;
					}
				}
				
			}
			
			valores.clear();
			
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
		}else if(tipo.contains("Top 3 Más Nacionales")) {
			vp.getAdmincontrol().getSort().setVisible(false);
			vp.getAdmincontrol().getSort().setEnabled(false);
			vp.getAdmincontrol().getSort().setSelected(false);
			
			model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
			model_temp.clear();
			
			HashMap<String, Integer> map = MTC.topNacionales(edao.getLista());
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
						model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
						break;
					}
				}
				
			}
			
			valores.clear();
			
			for(int i = 0; i < model_temp.size(); i++) {
				valores.add(model_temp.getElementAt(i));
			}
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
		
		vp.getAdmincontrol().getBack3().addActionListener(this);
		vp.getAdmincontrol().getBack3().setActionCommand("back_admin_control");
		
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

		vp.getAdminpanel().getMostrar_clave().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					vp.getAdminpanel().getClave().setEchoChar((char) 0);
					vp.getAdminpanel().getMostrar_clave().setIcon(new ImageIcon("src/Assets/Ojoabierto.png"));
				}else {
					vp.getAdminpanel().getClave().setEchoChar('•');
					vp.getAdminpanel().getMostrar_clave().setIcon(new ImageIcon("src/Assets/Ojocerrado.png"));
				}
			}
		});
		
		//PANEL ADMIN CONTROL
		vp.getAdmincontrol().getFilter().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) { filtrarEstudiantes(); }
			
			@Override
			public void removeUpdate(DocumentEvent e) { filtrarEstudiantes(); }
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		
		vp.getAdmincontrol().getCampotipo().addActionListener(this);
		vp.getAdmincontrol().getCampotipo().setActionCommand("campo_tipo");
		
		vp.getAdmincontrol().getGeneratepdf().addActionListener(this);
		vp.getAdmincontrol().getGeneratepdf().setActionCommand("generatePDF");
		
		vp.getAdmincontrol().getGenerate().addActionListener(this);
		vp.getAdmincontrol().getGenerate().setActionCommand("generate");
		
		vp.getAdmincontrol().getActualpdf().addActionListener(this);
		vp.getAdmincontrol().getActualpdf().setActionCommand("actualPDF");
		
		vp.getAdmincontrol().getDelete().addActionListener(this);
		vp.getAdmincontrol().getDelete().setActionCommand("eliminar_estudiante");
		
		vp.getAdmincontrol().getActivate().addActionListener(this);
		vp.getAdmincontrol().getActivate().setActionCommand("activar_estudiante");
		
		vp.getAdmincontrol().getList_e().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for(EstudianteDTO estudiante : edao.getLista()) {
					try {
						long id = Long.parseLong(vp.getAdmincontrol().getList_e().getSelectedValue().split(" ")[0]);
						if(id == estudiante.getDocumento()){
							vp.getAdmincontrol().getArea1().setText(estudiante.toString());
							
							break;
						} 
					} catch (NullPointerException e1) {
						
					}
				}
			}
		});
		
		vp.getAdmincontrol().getList_pdf().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String fecha_temp = vp.getAdmincontrol().getList_pdf().getSelectedValue();
				SimpleDateFormat sdf1 = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
				
				for(PersistenciaEstudiantesDTO lista_individual : pdao.getLista_pdfs()) {
					if(sdf1.format(lista_individual.getFecha_generacion()).equalsIgnoreCase(fecha_temp)) {
						lista_temp = lista_individual;
						break;
					}
				}
				
			}
		});
		
		vp.getAdmincontrol().getSort().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				ArrayList<EstudianteDTO> temp_e = new ArrayList<EstudianteDTO>();
				for(EstudianteDTO estudiante : edao.getLista()) {
					temp_e.add(estudiante);
				}
				
				String tipo = (String) vp.getAdmincontrol().getCampotipo().getSelectedItem();
				if(e.getStateChange() == ItemEvent.SELECTED) {
					vp.getAdmincontrol().getSort().setIcon(new ImageIcon("src/Assets/ArrowD.png"));
					//DESCENDENTE
					
					if(tipo.contains("Nombre")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getNombres().split(" ")[0].compareTo(temp_e.get(j+1).getNombres().split(" ")[0]) < 0) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}else if(tipo.contains("Apellido")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getApellidos().split(" ")[0].compareTo(temp_e.get(j+1).getApellidos().split(" ")[0]) < 0) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}else if(tipo.contains("Documento")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getDocumento() < temp_e.get(j+1).getDocumento()) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}
					
				}else {
					vp.getAdmincontrol().getSort().setIcon(new ImageIcon("src/Assets/ArrowUP.png"));
					//ASCENDENTE
					
					if(tipo.contains("Nombre")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getNombres().split(" ")[0].compareTo(temp_e.get(j+1).getNombres().split(" ")[0]) > 0) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}else if(tipo.contains("Apellido")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getApellidos().split(" ")[0].compareTo(temp_e.get(j+1).getApellidos().split(" ")[0]) > 0) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}else if(tipo.contains("Documento")) {
						
						for(int i = 0; i < edao.getLista().size(); i++) {
							
							for(int j = 0; j < edao.getLista().size() - 1; j++) {
								
								if(temp_e.get(j).getDocumento() > temp_e.get(j+1).getDocumento()) {
									EstudianteDTO aux = temp_e.get(j);
									temp_e.set(j, temp_e.get(j+1));
									temp_e.set(j+1, aux);
								}
								
							}
						}
						model_temp.clear();
						for(EstudianteDTO estudiante2 : temp_e) {
							model_temp.addElement(estudiante2.getDocumento()+" "+estudiante2.getNombres()+" "+estudiante2.getApellidos());
						}
						
						valores.clear();
						for(int i = 0; i < model_temp.size(); i++) {
							valores.add(model_temp.getElementAt(i));
						}
						
					}
					
				}
				
				
			}
		});
	}
	
	public void filtrarEstudiantes() {
		String texto_filtro = vp.getAdmincontrol().getFilter().getText();
		filtrarModelo(model_temp, texto_filtro);
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
	
	public void verificarCambios() {
		
		if(contador_cambios == 3) {
			pdao.crear(new PersistenciaEstudiantesDTO(edao.getLista()));
			contador_cambios = 0;
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
			
			vp.getCreationpanel().getDocumento().setText("");
			vp.getCreationpanel().getNombre().setText("");
			vp.getCreationpanel().getApellido().setText("");
			vp.getCreationpanel().getCorreo().setText("");
			vp.getCreationpanel().getFecha().setText("");
			vp.getCreationpanel().getPaises().setText("");
			vp.getCreationpanel().getPrograma().setSelectedIndex(0);
			vp.getCreationpanel().getGenero().setSelectedIndex(0);
			vp.getCreationpanel().getNacional().setSelectedIndex(0);
			vp.getCreationpanel().getJornada().setSelectedIndex(0);
			
		}
		case "back_activation":{
			
			vp.setVisible(true);
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			
			vp.getActivationpanel().getUser().setText("");
			vp.getActivationpanel().getCode().setText("");
		}
		case "back_admin":{
			
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			
			vp.setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			
			vp.getAdminpanel().getUser2().setText("");
			vp.getAdminpanel().getClave().setText("");
			break;
		}
		case "back_admin_control":{
			
			vp.setVisible(true);
			vp.getAdmin().setVisible(true);
			vp.getStudents().setVisible(true);
			vp.getActivation().setVisible(true);
			vp.getAdminpanel().setVisible(false);
			vp.getActivationpanel().setVisible(false);
			vp.getAdmincontrol().setVisible(false);
			vp.getCreationpanel().setVisible(false);
			
			vp.getAdmincontrol().getFilter().setText("");
			vp.getAdmincontrol().getCampotipo().setSelectedIndex(0);
			vp.getAdmincontrol().getList_e().clearSelection();
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

			documento_temp = Long.parseLong(vp.getCreationpanel().getDocumento().getText()); //TODO La cedula unicamente debe contener digitos, y su logitud es de 10.
			nombres_temp = vp.getCreationpanel().getNombre().getText(); //TODO Los nombres no pueden contener caracteres invalidos.
			apellidos_temp = vp.getCreationpanel().getApellido().getText(); //TODO Los apellidos no pueden contener caracteres invalidos.
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
			usuario_temp = usuario_temp.toLowerCase();
			correo_temp = vp.getCreationpanel().getCorreo().getText(); //TODO El correo ingresado debe tener un formato valido de correo y dominio.
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fecha_temp = sdf.parse(fecha_string_temp );
			} catch (ParseException e1) {}
			
			programa_temp = (String) vp.getCreationpanel().getPrograma().getSelectedItem();
			jornada_temp = (String) vp.getCreationpanel().getJornada().getSelectedItem();
			lugar_temp = (String) vp.getCreationpanel().getPaises().getText();
			fechaR_temp = new Date();
			origen_temp = (String) vp.getCreationpanel().getNacional().getSelectedItem();
			
			if(origen_temp.contains("Nacional")) {
				lugar_temp = "(Colombia/"+lugar_temp+")";
			}
			
			//TODO Si faltan datos, o estan mal ingresados, JOptionPane con el error, de lo contrario, crear estudiante.
			edao.crear(new EstudianteDTO(documento_temp, nombres_temp, apellidos_temp, genero_temp, usuario_temp, correo_temp, fecha_temp, false, programa_temp, jornada_temp, lugar_temp, fechaR_temp, origen_temp));
			valores.add(documento_temp + " "+nombres_temp+" "+apellidos_temp);
			remodelar();
			contador_cambios++;
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
			
			vp.getCreationpanel().getDocumento().setText("");
			vp.getCreationpanel().getNombre().setText("");
			vp.getCreationpanel().getApellido().setText("");
			vp.getCreationpanel().getCorreo().setText("");
			vp.getCreationpanel().getFecha().setText("");
			vp.getCreationpanel().getPaises().setText("");
			vp.getCreationpanel().getPrograma().setSelectedIndex(0);
			vp.getCreationpanel().getGenero().setSelectedIndex(0);
			vp.getCreationpanel().getNacional().setSelectedIndex(0);
			vp.getCreationpanel().getJornada().setSelectedIndex(0);
			
			
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
			
			activacion_usuario = vp.getActivationpanel().getUser().getText(); //TODO Debe contener texto para verificar. 
			activacion_codigo = vp.getActivationpanel().getCode().getText(); //TODO Debe contener texto para verificar.
			
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
			StringBuilder sb = new StringBuilder();
			for(char caracter : vp.getAdminpanel().getClave().getPassword()) {
				sb.append(caracter);
			}
			admin_clave = sb.toString();
			
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
					vp.getAdmincontrol().setVisible(true);
					vp.getAdminpanel().getUser2().setText("");
					vp.getAdminpanel().getClave().setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos. \nVuelva a intentarlo.", "Inicio Sesión Administradores", JOptionPane.WARNING_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos. \nVuelva a intentarlo.", "Inicio Sesión Administradores", JOptionPane.WARNING_MESSAGE);
			}

			break;
		}
		case "campo_tipo": {
			vp.getAdmincontrol().getFilter().setText("");
			remodelar();
			
			break;
		}
		
		case "eliminar_estudiante": {
			int index = 0;
			long id = 0;
			id = Long.parseLong(vp.getAdmincontrol().getList_e().getSelectedValue().split(" ")[0]); //TODO si vp.getAdmincontrol().getList_e().getSelectedValue() == null, el usuario no ha seleccionado de la lista.
			for(int i = 0; i < edao.getLista().size(); i++) {
				if(id == edao.getLista().get(i).getDocumento()){
					index = i;
					break;
				}
			}
			String usuario_temp = edao.getLista().get(index).getUsuario();
			int res = JOptionPane.showConfirmDialog(null, "Desea eliminar al estudiante con Documento: \n"+id, "Eliminar Estudiante", JOptionPane.YES_NO_CANCEL_OPTION);

			valores.clear();
			for(EstudianteDTO estudiante : edao.getLista()) {
				valores.add(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
			}
			if(res==0) {
				if(edao.eliminar(index)) {
					valores.remove(index);
					remodelar();
					contador_cambios++;
					for(int i = 0; i < uuidDAO.getLista().size(); i++) {
						if(uuidDAO.getLista().get(i).getUsuario().equals(usuario_temp)) {
							uuidDAO.eliminar(i);
							break;
						}
					}
					vp.getAdmincontrol().getFilter().setText("");
					vp.getAdmincontrol().getArea1().setText("");
					vp.getAdmincontrol().getList_e().clearSelection();
					JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nha sido eliminado correctamente.", "Eliminacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nno ha podido ser eliminado.", "Eliminacion Erronea", JOptionPane.ERROR_MESSAGE);
				}
			}
			remodelar();
			
			
			break;
		}
		case "activar_estudiante": {
			int index = 0;
			long id = 0;
			EstudianteDTO ea = null;
			for(int i = 0; i < edao.getLista().size(); i++) {
				id = Long.parseLong(vp.getAdmincontrol().getList_e().getSelectedValue().split(" ")[0]); //TODO si vp.getAdmincontrol().getList_e().getSelectedValue() == null, el usuario no ha seleccionado de la lista.
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
						remodelar();
						contador_cambios++;
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
						remodelar();
						contador_cambios++;
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nha sido ACTIVADO correctamente.", "Activación Exitosa", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Estudiante con Documento: \n"+id+"\nno ha podido ser ACTIVADO.", "Activación Erronea", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if(vp.getAdmincontrol().getCampotipo().getSelectedItem().equals("Inactivo")) {
				model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
				model_temp.clear();
				for(EstudianteDTO estudiante : edao.getLista()) {
					if(estudiante.isEsta_activo() == false) {
						model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
					}
				}
				
				valores.clear();
				
				for(int i = 0; i < model_temp.size(); i++) {
					valores.add(model_temp.getElementAt(i));
				}
			}else if(vp.getAdmincontrol().getCampotipo().getSelectedItem().equals("Activo")) {
				model_temp = (DefaultListModel<String>) vp.getAdmincontrol().getList_e().getModel();
				model_temp.clear();
				for(EstudianteDTO estudiante : edao.getLista()) {
					if(estudiante.isEsta_activo()) {
						model_temp.addElement(estudiante.getDocumento()+" "+estudiante.getNombres()+" "+estudiante.getApellidos());
					}
				}
				
				valores.clear();
				
				for(int i = 0; i < model_temp.size(); i++) {
					valores.add(model_temp.getElementAt(i));
				}
			}
			
			vp.getAdmincontrol().getFilter().setText("");
			vp.getAdmincontrol().getArea1().setText("");
			vp.getAdmincontrol().getList_e().clearSelection();
			break;
		}
		case "actualPDF": {
			
			pdao.crear(new PersistenciaEstudiantesDTO(edao.getLista()));
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
			
			vp.getAdmincontrol().getPanel_pdfs().setVisible(true);
			
			DefaultListModel<String> temp_modelo_pdf = vp.getAdmincontrol().getModelo2();
			temp_modelo_pdf.clear();
			for(int i = 0; i < pdao.getLista_pdfs().size(); i++) {
				temp_modelo_pdf.addElement(pdao.getLista_pdfs().get(i).toString());
			}
			vp.getAdmincontrol().getList_pdf().setModel(temp_modelo_pdf);
			
			
			break;
		}
		case "generate":{
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
			FileHandler.crearPdf("Estadisticas"+sdf.format(lista_temp.getFecha_generacion())+".pdf", FileHandler.crearGraficas(lista_temp.getLista_individual())); //TODO Si lista_temp == null, el usuario no ha seleccionado ningun pdf de la lista.
			if(!Desktop.isDesktopSupported()) {
				
			}else {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(new File("src/co/edu/unbosque/model/persistance/Estadisticas"+sdf.format(lista_temp.getFecha_generacion())+".pdf"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			vp.getAdmincontrol().getPanel_pdfs().setVisible(false);
			
			break;
		}

		default:
			break;
		}
		
	}
}
