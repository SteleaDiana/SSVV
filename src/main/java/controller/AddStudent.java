package controller;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Student;

public class AddStudent extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AddStudent( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AddStudent.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void addSuccessfull() {
        LaboratoriesController controller = new LaboratoriesController("students.txt", "laboratories.txt");
        Student student = new Student("sdie2021", "diana stelea", 936);
        Boolean success = controller.saveStudent(student);
        assertTrue(success);
        controller.saveStudent(student);
    }

    public void addUnsuccessfull() {
        LaboratoriesController controller = new LaboratoriesController("students.txt", "laboratories.txt");
        Student student = new Student("12", "diana stelea", 936);
        Boolean success = controller.saveStudent(student);
        assertTrue(success);
    }
}