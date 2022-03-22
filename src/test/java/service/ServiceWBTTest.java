package service;

import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Test;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

public class ServiceWBTTest {

    @Test
    public void tc1_addAssignment_path1() {
        Tema tema = new Tema(null, "descriere", 1, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assert.fail();
        }
        catch (ValidationException e){
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void tc2_addAssignment_path2() {
        Tema tema = new Tema("id1", "", 1, 3);

        String filename = "src/test/java/fisiere_test/Teme.xml";
        TemaValidator temaValidator = new TemaValidator();

        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filename);

        Service service = new Service(null, null, temaXMLRepo, temaValidator, null, null);

        try{
            service.addTema(tema);
            Assert.fail();
        }
        catch (ValidationException e){
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

}
