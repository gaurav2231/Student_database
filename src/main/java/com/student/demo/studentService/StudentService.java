package com.student.demo.studentService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.student.demo.entity.Image;
import com.student.	demo.entity.Response;
import com.student.demo.entity.Student;

public interface StudentService {

	ResponseEntity<Response> addStudent(Student student) throws FileNotFoundException;

	ResponseEntity<?> editStudent(Student student);

	ResponseEntity<?> deleteStudent(Student student);

	List<Student> getAllStudent();

	ResponseEntity<?> loginStudent(Student student);

	ResponseEntity<?> filterStudent(String firstname, String phone, String aadharno);

    String uploadImage(String path, MultipartFile file) throws IOException;
    
    InputStream getResource(String path, String fileName) throws FileNotFoundException;

	Image uploadImage(MultipartFile file)throws IOException ;

	byte[] downloadImage(Long id);
}
