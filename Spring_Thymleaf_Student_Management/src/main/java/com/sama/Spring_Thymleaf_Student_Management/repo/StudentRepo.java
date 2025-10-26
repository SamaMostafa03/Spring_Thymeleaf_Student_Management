package com.sama.Spring_Thymleaf_Student_Management.repo;


import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByNameContainingOrEmailContaining(String name , String email);
    List<Student> findAllByOrderByIdAsc();

}
//    private List<Student> students = new ArrayList<>(Arrays.asList(
//            new Student(1, "Lina Ali", "lina@example.com", true) ,
//            new Student(2, "Peter Parker", "peter@example.com", false),
//            new Student(3, "Sara Iyad", "sara@example.com", true) ,
//            new Student(4, "Mazen Ahmed", "mazen@example.com", false))
//    );
//
//    public List<Student> viewAllStudents(){
//        return students;
//    }
//
//    public void addStudent(Student student)
//    {
//        students.add(student);
//    }
//
//    public int getStudentsSize()
//    {
//        return students.size();
//    }
//
//    public Student findStudent(int id) {
//        for (Student stu : students) {
//            if (stu.getId() == id) return stu;
//        }
//        return null;
//    }
//
//    public Student deleteStudent(int id) {
//        for (Student stu : students) {
//            if (stu.getId() == id){students.remove(stu); return stu; }
//        }
//        return null;
//    }
//    public void updateStudent(Student student) {
//        for (Student stu : students) {
//            if (stu.getId() == student.getId()){
//                stu.setName(student.getName());
//                stu.setEmail(student.getEmail());
//                stu.setActive(student.isActive());
//            }
//        }
//    }