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

    public static boolean validateName(String name){
        if(!name.matches("[a-zA-Z]+[\\s]?[a-zA-Z]+")) {
            System.out.println("Wrong Format!");
            return false;
        }
        return true;
    }

    public static boolean validateGroup(int group){
        if(group >= 1000 || group<= 0){
            return false;
        }
        return true;
    }

    public static boolean validateStudent(Student student) {
        return validateRegNb(student.getRegNumber())&&validateName(student.getName())&&validateGroup(student.getGroup());
    }

    public static boolean validateLaboratory(Laboratory laboratory) {
        return validateRegNb(laboratory.getStudentRegNumber()) && validateGrade(laboratory.getGrade())&& validateLabNb(laboratory.getLaboratoryNumber())&&validatePbNb(laboratory.getProblemNumber())&& validateDateBefore(laboratory.getDate());
    }

    public static boolean validateDateBefore(Date labDate){
        Date date = new Date();
        if(date.before(labDate)) {
            return false;
        }
        return true;
    }

    public static boolean validateLabNb(int lab){
        if (lab<1){
            System.out.println("The lab number should be > 1!");
            return false;
        }
        return true;
    }

    public static boolean validatePbNb(int pb){
        if(pb > 10 || pb < 1) {
            System.out.println("The problem number should be between 1 and 10!");
            return false;
        }
        return true;
    }

    public static boolean validateGrade(float grade) {
        if(grade >= 1 && grade <= 10) {
            return true;
        }
        return false;
    }
} 