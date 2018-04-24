package repository;

import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDataPersistence {
    private String file;

    public FileDataPersistence(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void saveStudent(Student student) {
        try {
            //if(studentExists(student)){

            //}else{
                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(student.toString() + "\n");
                writer.close();
            //}
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveLaboratory(Laboratory laboratory) {
        BufferedWriter writter;
        try {
            writter = new BufferedWriter(new FileWriter(file, true));
            writter.write(laboratory.toString() + "\n");
            writter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addGrade(String studentRegNb, String labNumber, float grade) throws IOException, NumberFormatException, ParseException {
        File fileA = new File(file); //1
        File fileB = new File("temp"); //1

        BufferedReader reader = new BufferedReader(new FileReader(fileA)); //1
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileB)); //1

        String line; //1

        while ((line = reader.readLine()) != null) { //2
            String[] temp = line.split(" "); //3
            String fileLabNumber = temp[0]; //3
            String fileStudentNumber = temp[4]; //3
            if (fileLabNumber.equals(labNumber) && fileStudentNumber.equals(studentRegNb)) { //4
                Laboratory laboratory = new Laboratory(
                        Integer.valueOf(temp[0]), temp[1],
                        Integer.valueOf(temp[2]), temp[4]); //5
                laboratory.setGrade(grade); //5
                writer.write(laboratory.toString() + "\n"); //5
            } else {
                writer.write(line + "\n"); //6
            }
        }
        writer.close(); //7
        reader.close(); //7

        fileA.delete(); //7
        fileB.renameTo(fileA); //7
    }

    public Map<String, List<Laboratory>> getLaboratoryMap()
            throws NumberFormatException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<String, List<Laboratory>> laboratoryMap = new HashMap<String, List<Laboratory>>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]),
                    temp[1], Integer.valueOf(temp[2]), Float.valueOf(temp[3]),
                    temp[4]);
            if (laboratoryMap.get(laboratory.getStudentRegNumber()) == null) {
                List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(),
                        laboratoryList);
            } else {
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(
                        laboratory);
            }
        }
        return laboratoryMap;
    }

    public List<Student> getStudentsList() throws NumberFormatException,
            IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Student> allStudentsList = new ArrayList<Student>();

        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Student student = new Student(temp[0], temp[1] + temp[2], Integer.valueOf(temp[3]));
            allStudentsList.add(student);
        }

        return allStudentsList;
    }

    public boolean studentExists(Student student) throws IOException {
        for(Student s: getStudentsList()){
            if(s.getRegNumber() == student.getRegNumber()){
                return true;
            }
        }
        return false;
    }

} 