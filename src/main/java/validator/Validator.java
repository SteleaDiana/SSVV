package validator;

import model.Laboratory;
import model.Student;

import java.util.Date;

public class Validator {

    public static boolean validateRegNb(String regNb){
        if(!regNb.matches("[a-zA-Z]{4}[\\d]{4}")){
            System.out.println("Wrong Format!");
            return false;
        }
        return true;
    }

    public static boolean validateStudent(Student student) {

        if (!student.getName().matches("[a-zA-Z]+[\\s]?[a-zA-Z]+")) {
            System.out.println("Wrong Format!");
            return false;
        }
        if(student.getGroup() >= 1000 || student.getGroup() <= 0){
            return false;
        }
        return validateRegNb(student.getRegNumber());
    }

    public static boolean validateLaboratory(Laboratory laboratory) {
        if(laboratory.getLaboratoryNumber() < 1) {
            System.out.println("The lab number should be > 1!");
            return false;
        }
        if(laboratory.getProblemNumber() > 10 || laboratory.getProblemNumber() < 1) {
            System.out.println("The problem number should be between 1 and 10!");
            return false;
        }
        Date date = new Date();
        if(date.before(laboratory.getDate())) {
            return false;
        }
        return validateRegNb(laboratory.getStudentRegNumber()) && validateGrade(laboratory.getGrade());
    }

    public static boolean validateGrade(float grade) {
        if(grade >= 1 && grade <= 10) {
            return true;
        }
        return false;
    }
} 