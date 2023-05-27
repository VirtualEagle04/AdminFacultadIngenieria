package co.edu.unbosque.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import co.edu.unbosque.test.controller.ControllerTest;
import co.edu.unbosque.test.model.AdminDAOTest;
import co.edu.unbosque.test.model.EstudianteDAOTest;
import co.edu.unbosque.test.model.PersistenciaEstudiantesDAOTest;
import co.edu.unbosque.test.model.UUIDUsuarioDAOTest;
import co.edu.unbosque.test.persistance.FileHandlerTest;
import co.edu.unbosque.test.util.MTCTest;
import co.edu.unbosque.test.util.MailSenderTest;

@Suite
@SelectClasses({ControllerTest.class, AdminDAOTest.class, EstudianteDAOTest.class, PersistenciaEstudiantesDAOTest.class, UUIDUsuarioDAOTest.class, FileHandlerTest.class, MailSenderTest.class, MTCTest.class})
public class TestManager {
	
	
	
}
