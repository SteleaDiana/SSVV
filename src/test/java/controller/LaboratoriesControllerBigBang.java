package controller;
import model.Laboratory;
import model.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import repository.FileDataPersistence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class LaboratoriesControllerBigBang {
    @Test
    public void saveStudent() throws Exception {
    }

    private LaboratoriesController ctr;
    private FileDataPersistence repo;

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp(){
        File student_file = null, laboratories_file=null;
        try {
            student_file = folder.newFile("students.txt");
            laboratories_file = folder.newFile("laboratories.txt");
            PrintWriter o = new PrintWriter(student_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert student_file != null;
        assert laboratories_file != null;
        ctr = new LaboratoriesController(student_file.getName(), laboratories_file.getName());
    }

    @Test
    public void integrationTestA() throws Exception {
        Student p = new Student();
        p.setRegNumber("sdie2021");
        p.setName("Georgel Maricel");
        p.setGroup(932);
        Boolean success = ctr.saveStudent(p);
        assertTrue(success);
    }

    @Test
    public void integrationTestB() throws Exception {
        Laboratory laboratory = new Laboratory();
        laboratory.setLaboratoryNumber(10);
        laboratory.setDate(new Date("19-Mar-2012"));
        laboratory.setGrade(4);
        laboratory.setProblemNumber(2);
        laboratory.setStudentRegNumber("sdie2021");


        Boolean success = ctr.saveLaboratory(laboratory);
        assertTrue(success);
    }

    @Test
    public void integrationTestC() throws Exception {

        List<Student> temp = new ArrayList<>();
        Student student_temp = new Student("pgpg1234", "PetrescuGabriela", 123);
        temp.add(student_temp);
        List<Student> s;
        s = ctr.passedStudents();
        assertTrue(assertArrayEquals(temp, s));
    }

    private boolean assertArrayEquals(List<Student> temp, List<Student> p) {
        if (temp.size() != p.size())
            return false;
        return temp.toString().contentEquals(p.toString());

    }

    @Test
    public void bigBangTest() throws Exception {

        integrationTestA();
        integrationTestB();
        integrationTestC();
    }

}