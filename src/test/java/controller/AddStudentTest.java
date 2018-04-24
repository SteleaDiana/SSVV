package controller;
import model.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import repository.FileDataPersistence;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AddStudentTest {
    @Test
    public void saveStudent() throws Exception {
    }

    private LaboratoriesController ctr;

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp(){
        File student_file = null, laboratories_file=null;
        try {
            try {
                student_file = folder.newFile("students.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            laboratories_file = folder.newFile("laboratories.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        assert student_file != null;
        assert laboratories_file != null;
        FileDataPersistence rep1 = new FileDataPersistence(student_file.getName());
        FileDataPersistence rep2 = new FileDataPersistence(laboratories_file.getName());
        ctr = new LaboratoriesController(student_file.getName(), laboratories_file.getName());
    }

    @Test
    public void addStudentWithSuccess() throws Exception {
        Student p = new Student();
        p.setRegNumber("sdie2021");
        p.setName("Georgel Maricel");
        p.setGroup(932);
        Boolean success = ctr.saveStudent(p);
        assertTrue(success);
    }

    @Test
    public void addStudentWithWrongRegNb() throws Exception {
        Student p = new Student();
        p.setRegNumber("12");
        p.setName("Georgel Maricel");
        p.setGroup(932);
        Boolean success = ctr.saveStudent(p);
        assertFalse(success);
    }

    @Test
    public void addStudentWithoutFName() throws Exception {
        Student p = new Student();
        p.setRegNumber("12");
        p.setName("Georgel");
        p.setGroup(932);

        Boolean success = ctr.saveStudent(p);
        assertFalse(success);
    }

    @Test
    public void addStudentWithWrongGroup() throws Exception {
        Student p = new Student();
        p.setRegNumber("sdie2021");
        p.setName("Georgel Marian");
        p.setGroup(9222);

        Boolean success = ctr.saveStudent(p);
        assertFalse(success);
    }

    @Test
    public void addStudentWithEmptyName() throws Exception {
        Student p = new Student();
        p.setRegNumber("sdie2021");
        p.setName("");
        p.setGroup(932);

        Boolean success = ctr.saveStudent(p);
        assertFalse(success);
    }

    @Test
    public void addStudentWithoutRegNb() throws Exception {
        Student p = new Student();
        p.setRegNumber("");
        p.setName("Georgel Marian");
        p.setGroup(932);

        Boolean success = ctr.saveStudent(p);
        assertFalse(success);
    }

}