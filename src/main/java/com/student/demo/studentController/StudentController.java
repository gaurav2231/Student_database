package com.student.demo.studentController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.student.demo.entity.Response;
import com.student.demo.entity.Student;
import com.student.demo.repository.StudentRepository;
import com.student.demo.studentService.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Value("${project.image}")
	private String path;

	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/addStudent")
	public ResponseEntity<?> addStudent(@Validated @RequestBody Student student) throws FileNotFoundException {
		return studentService.addStudent(student);
	}

	@PostMapping("/uploadFile/{id}")
	public ResponseEntity<Student> uploadFile(@RequestParam("image") MultipartFile image, @PathVariable Integer id)
			throws IOException {
		Student student = this.studentRepository.getById(id);
		String fileName = this.studentService.uploadImage(path, image);
		student.setImage(fileName);
		Student save = studentRepository.save(student);
		return new ResponseEntity<Student>(save, HttpStatus.OK);
	}

	@GetMapping(value = "/downloadFile/{image}")
	public void downloadFile(@PathVariable String image, HttpServletResponse response) throws IOException {
		InputStream resource = this.studentService.getResource(path, image);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	@PostMapping("/loginStudent")
	public ResponseEntity<?> loginStudent(@RequestBody Student student) {
		return studentService.loginStudent(student);
	}

	@PutMapping("/editStudent")
	public ResponseEntity<?> editStudent(@RequestBody Student student) {
		return studentService.editStudent(student);
	}

	@DeleteMapping("/deleteStudent")
	public ResponseEntity<?> deleteStudent(@RequestBody Student student) {
		return studentService.deleteStudent(student);
	}

	@GetMapping("/getAllStudent")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/filterStudent")
	public ResponseEntity<Object> filterStudent(@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "aadharno", required = false) String aadharno) {
		return (ResponseEntity<Object>) studentService.filterStudent(firstname, phone, aadharno);
	}

	@PostMapping("/uploadfile")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		Response response = new Response();
		response.setCode(200);
		response.setMessage("file Uploaded Successfully!!!");
		studentService.uploadImage(file);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/downloadfile/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
		byte[] image = studentService.downloadImage(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}
}
