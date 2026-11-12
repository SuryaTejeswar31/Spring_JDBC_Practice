package org.example.jdbcpractice;

import org.example.jdbcpractice.modelClass.Student;
import org.example.jdbcpractice.repository.StudentRepository;

public class JdbcPracticeApplication {
    public static void main(String[] args) {
        StudentRepository repository=new StudentRepository();
        //repository.updateStudent(new Student("Raju","raju@gmail.com",23),2);
        repository.getAllStudents();
    }
}
