package service;

import domain.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.StudentXMLRepo;
import validation.StudentValidator;
import validation.ValidationException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ServiceTest {

    @After
    public void clearFile(){
        try {
            String filename = "src/test/java/fisiere_test/Studenti.xml";
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

    //TC 1
    @Test
    public void testAddStudent_NullId() {
        Student stud = new Student(null, "test", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            Assert.fail();
        }
        catch (ValidationException e){
            Assert.assertTrue(true);
        }
    }

    //TC 2
    @Test
    public void testAddStudent_IdEmptyString() {
        Student stud = new Student("", "test", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 3
    @Test
    public void testAddStudent_ExistentId(){
        Student stud1 = new Student("1", "test", 100, "email@domain.com", "Teacher");
        Student stud2 = new Student("1", "test1", 100, "email1@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);
        service.addStudent(stud1);

        try{
            service.addStudent(stud2);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 4
    @Test
    public void testAddStudent_NullName(){
        Student stud = new Student("1", null, 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 5
    @Test
    public void testAddStudent_NameEmptyString(){
        Student stud = new Student("1", "", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 6
    @Test
    public void testAddStudent_NameOnlyInvalidCharacters(){
        Student stud = new Student("1", "1234_28!7", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }


    //TC 7
    @Test
    public void testAddStudent_NameInvalidCharactersAtBeginning(){
        Student stud = new Student("1", "1Nume", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 8
    @Test
    public void testAddStudent_NameInvalidCharactersAtEnd(){
        Student stud = new Student("1", "Nume1", 100, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 9
    @Test
    public void testAddStudent_GroupEquals0(){
        Student stud = new Student("1", "Nume Corect", 0, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }


    //TC 10
    @Test
    public void testAddStudent_GroupEqualsMinus1(){
        Student stud = new Student("1", "Nume Corect", -1, "email@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 11 (group = MAXINT + 1), TC 12 (group = null) - not possible

    //TC 13
    @Test
    public void testAddStudent_EmailNull(){
        Student stud = new Student("1", "Nume Corect", 1, null, "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 14
    @Test
    public void testAddStudent_EmailEmptyString(){
        Student stud = new Student("1", "Nume Corect", 1, "", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 15
    @Test
    public void testAddStudent_EmailInvalidFormat_ATMissing(){
        Student stud = new Student("1", "Nume Corect", 1, "p.ion", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 16
    @Test
    public void testAddStudent_EmailInvalidFormat_ATBeginning(){
        Student stud = new Student("1", "Nume Corect", 1, "@domain.com", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 17
    @Test
    public void testAddStudent_EmailInvalidFormat_ATEnd(){
        Student stud = new Student("1", "Nume Corect", 1, "adresa@", "Teacher");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 18
    @Test
    public void testAddStudent_TeacherNull(){
        Student stud = new Student("1", "Nume corect", 100, "email@domain.com", null);

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }


    //TC 19
    @Test
    public void testAddStudent_TeacherOnlyInvalidCharacters(){
        Student stud = new Student("1", "Nume corect", 100, "email@domain.com", "1234_28!7");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }


    //TC 20
    @Test
    public void testAddStudent_TeacherInvalidCharactersAtBeginning(){
        Student stud = new Student("1", "Nume corect", 100, "email@domain.com", "_Nume");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 21
    @Test
    public void testAddStudent_TeacherInvalidCharactersAtEnd(){
        Student stud = new Student("1", "Nume corect", 100, "email@domain.com", "Nume_");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }

    //TC 22
    @Test
    public void testAddStudent_TeacherEmptyString(){
        Student stud = new Student("1", "Nume corect", 100, "email@domain.com", "");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(false);
        }
        catch (ValidationException e){
            assert(true);
        }
    }


    //TC 23
    @Test
    public void testAddStudent_AllAttributesValid_GroupEquals1() {
        // all valid
        Student stud = new Student("2", "Popescu Ion", 1, "p.ion@gmail.com", "Ionescu Maria");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(true);
        }
        catch (Exception e){
            assert(false);
        }
    }

    //TC 24
    @Test
    public void testAddStudent_AllAttributesValid_GroupEqualsMaxintMinus1() {
        // all valid
        Student stud = new Student("2", "Popescu Ion", Integer.MAX_VALUE - 1, "p.ion@gmail.com", "Ionescu Maria");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(true);
        }
        catch (Exception e){
            assert(false);
        }
    }

    //TC 25
    @Test
    public void testAddStudent_AllAttributesValid_GroupEqualsMaxint() {
        // all valid
        Student stud = new Student("2", "Popescu Ion", Integer.MAX_VALUE, "p.ion@gmail.com", "Ionescu Maria");

        String filenameStudent = "src/test/java/fisiere_test/Studenti.xml";
        StudentValidator studentValidator = new StudentValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);

        Service service = new Service(studentXMLRepository, studentValidator, null, null, null, null);

        try{
            service.addStudent(stud);
            assert(true);
        }
        catch (Exception e){
            assert(false);
        }
    }

}
