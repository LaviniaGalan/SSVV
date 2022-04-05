package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
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
import java.util.Date;

public class ServiceIntegrationTest {

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

        studentXMLRepo = new StudentXMLRepo("src/test/java/fisiere_integration_test/Studenti.xml");
        temaXMLRepo = new TemaXMLRepo("src/test/java/fisiere_integration_test/Teme.xml");

        notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        notaXMLRepo = new NotaXMLRepo("src/test/java/fisiere_integration_test/Note.xml");

        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    }

    public void setUpStudentFile(){
        try {
            String filename = "src/test/java/fisiere_integration_test/Studenti.xml";
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);

            root.appendChild(studentXMLRepo.createElementfromEntity(document, new Student("1", "Ion Popescu", 10, "ionpop@gmail.com", "Maria Ionescu")));

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

    public void setUpTemeFile(){
        try {
            String filename = "src/test/java/fisiere_integration_test/Teme.xml";
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);

            root.appendChild(temaXMLRepo.createElementfromEntity(document, new Tema("1", "file repo", 3, 1)));

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

    public void setUpNotaFile(){
        try {
            String filename = "src/test/java/fisiere_integration_test/Note.xml";
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


    @After
    public void clearFile(){
        setUpStudentFile();
        setUpTemeFile();
        setUpNotaFile();
    }

    @Test
    public void testAddStudent() {
        Student stud = new Student("2", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");
        try{
            service.addStudent(stud);
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testAddAssignment(){
        Tema tema = new Tema("2", "tema", 6, 3);
        try {
            service.addTema(tema);
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testAddNota(){
        Nota nota = new Nota("1", "1", "1", 9.0, LocalDate.now());
        try {
            service.addNota(nota, "feedback");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testIntegration(){
        Student stud = new Student("2", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");
        Tema tema = new Tema("2", "tema", 6, 3);
        Nota nota = new Nota("1", "1", "1", 9.0, LocalDate.now());

        try{
            service.addStudent(stud);
            service.addTema(tema);
            service.addNota(nota, "feedback");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }
}
