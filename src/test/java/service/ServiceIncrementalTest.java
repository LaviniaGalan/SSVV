package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ServiceIncrementalTest {
    StudentValidator studentValidator;
    TemaValidator temaValidator;
    NotaValidator notaValidator;

    StudentXMLRepo studentXMLRepo;
    TemaXMLRepo temaXMLRepo;
    NotaXMLRepo notaXMLRepo;

    Service service;

    @Before
    public void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();

        studentXMLRepo = new StudentXMLRepo("src/test/java/fisiere_test/Studenti.xml");
        temaXMLRepo = new TemaXMLRepo("src/test/java/fisiere_test/Teme.xml");

        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        notaXMLRepo = new NotaXMLRepo("src/test/java/fisiere_test/Note.xml");

        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    }

    @After
    public void clearFilesAfterTest(){
        clearFile("src/test/java/fisiere_test/Studenti.xml");
        clearFile("src/test/java/fisiere_test/Teme.xml");
        clearFile("src/test/java/fisiere_test/Note.xml");
    }

    public void clearFile(String filename){
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);

            //write Document to file
            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(filename));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testAddStudent() {
        Student stud = new Student("1", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");
        try{
            service.addStudent(stud);
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testAddStudent_AddAssignment(){
        Student stud = new Student("1", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");
        Tema tema = new Tema("1", "tema", 6, 1);
        try {
            service.addStudent(stud);
            service.addTema(tema);
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testAddStudent_AddAssignment_AddNota(){
        Student stud = new Student("1", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");
        Tema tema = new Tema("1", "tema", 6, 1);
        Nota nota = new Nota("1", "1", "1", 9.0, LocalDate.now());
        try {
            service.addStudent(stud);
            service.addTema(tema);
            service.addNota(nota, "feedback");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
