package org.example.jdbcpractice.repository;

import org.example.jdbcpractice.modelClass.Student;

import java.sql.*;

public class StudentRepository {
    String url="jdbc:mysql://localhost:3306/student_jdbc_practice";
    String user="root";
    String password="Surya@2008";
    public void createStudent(Student student){
        String sql= """
INSERT INTO student(name,email,age)VALUES(?,?,?)
""";
        try(Connection connection= DriverManager.getConnection(url,user,password);PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());
            int result=preparedStatement.executeUpdate();
            if(result==1)System.out.println("Student created successfully");
        }
        catch(SQLException ex){
            System.out.println("Exception occured in creating student record");
            ex.getStackTrace();
        }
    }
    public void updateStudent(Student student,int id){
        String sql= """
                UPDATE student SET name=?,email=?,age=? where id=?
                """;
        try(Connection connection=DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.setInt(4,id);
            int result=preparedStatement.executeUpdate();
            if(result==1)System.out.println("Student Updated successfully");
        }
        catch(SQLException ex){
            System.out.println("Exception occured in updating student record");
        }
    }
    public void getStudentById(int id){
        String sql= """
                SELECT id,name,email,age FROM student WHERE id=?
                """;
        try(Connection connection= DriverManager.getConnection(url,user,password);PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet set=preparedStatement.executeQuery(sql);
            try{
                if(set.next()){
                    Student student=mapToStudent(set);
                    System.out.println(student);
                }
            }
            catch (SQLException ex){}
        }
        catch(SQLException ex) {
            System.out.println("Exception occured in updating student record");
            ex.getStackTrace();
        }
    }
    public void getAllStudents(){
        String sql= """
                SELECT id,name,email,age FROM student
                """;
        try(Connection connection=DriverManager.getConnection(url,user,password);PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            ResultSet resultSet=preparedStatement.executeQuery(sql);
            try{
                while(resultSet.next()){
                    Student student=mapToStudent(resultSet);
                    System.out.println(student);
                }
            }
            catch (SQLException ex){}
        }
        catch(SQLException ex){}
    }
    public Student mapToStudent(ResultSet set) throws SQLException {
        Student student=new Student();
        student.setId(set.getInt("id"));
        student.setName(set.getString("name"));
        student.setEmail(set.getString("email"));
        student.setAge(set.getInt("age"));
        return student;
    }
}
