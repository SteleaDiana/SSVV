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
import java.util.Date;

import static org.junit.Assert.*;

public class LaboratoriesControllerTest {
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

    @Test
    public void addLaboratoryWithSucces() throws Exception {

        Boolean success = ctr.saveLaboratory(getMockedLab());
        assertTrue(success);
    }

    @Test
    public void addLaboratoryWithWrongLabNumber() throws Exception {
        Laboratory laboratory = getMockedLab();
        laboratory.setLaboratoryNumber(0);
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addLaboratoryWithWrongStudentRegNumber() throws Exception {
        Laboratory laboratory = getMockedLab();
        laboratory.setStudentRegNumber("sdie");
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addLaboratoryWithWrongProblemNb() throws Exception {
        Laboratory laboratory = getMockedLab();
        laboratory.setProblemNumber(20);
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addLaboratoryWithWrongGrade1() throws Exception {
        Laboratory laboratory = getMockedLab();
        laboratory.setGrade(20);
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addLaboratoryWithWrongGrade2() throws Exception {
        Laboratory laboratory = getMockedLab();
        laboratory.setGrade(0);
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addLaboratoryWithWrongDate() throws Exception {
        Laboratory laboratory = getMockedLab();
        Date date = new Date("18-Dec-2019");
        laboratory.setDate(date);
        Boolean success = ctr.saveLaboratory(laboratory);
        assertFalse(success);
    }

    @Test
    public void addGradeWithSucces() throws Exception {
        Boolean success = ctr.addGrade("sdie2021", 3, 10);
        assertTrue(success);
    }

    @Test
    public void addGradeWithError() throws Exception {
        Boolean success = ctr.addGrade("sdie2021", 4, 12);
        assertFalse(success);
    }

    @Test
    public void getStudentsTest() throws Exception{
        assertTrue(ctr.passedStudents().size()>0);
    }

    public Laboratory getMockedLab(){
        Laboratory laboratory = new Laboratory();
        laboratory.setLaboratoryNumber(1);
        laboratory.setGrade(2);
        laboratory.setDate(new Date("12-Apr-2018"));
        laboratory.setProblemNumber(7);
        laboratory.setStudentRegNumber("sdie2021");
        return laboratory;
    }

}