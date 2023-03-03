package com.student.demo.studentService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.student.demo.entity.ImageUtil;
import com.student.demo.entity.Image;
import com.student.demo.entity.Response;
import com.student.demo.entity.Student;
import com.student.demo.entity.TokenResponse;
import com.student.demo.repository.ImageRepository;
import com.student.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

	@Autowired
	private ImageRepository productImageRepository;
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private StudentRepository studentRepository;

		@Override
	public ResponseEntity<Response> addStudent(Student student) throws FileNotFoundException {
		Response response = new Response();
		String phone = student.getPhone();
		String Aadharno = student.getAadharno();
		if (studentRepository.existsByPhoneAndAadharno(phone, Aadharno)) {
			response.setCode(400);
			response.setMessage("Student Already Exists!!!");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setCode(200);
			response.setMessage("Student Added Successfully!!!");
			studentRepository.save(student);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> editStudent(Student student) {
		Response response = new Response();
		int id = student.getId();
		if (studentRepository.existsById(id) == false) {
			response.setCode(404);
			response.setMessage("Student Not Found!!!");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setCode(200);
			response.setMessage("Student Updated Successfully!!!");
			studentRepository.save(student);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> deleteStudent(Student student) {
		Response response = new Response();
		int id = student.getId();
		if (studentRepository.existsById(id) == false) {
			response.setCode(404);
			response.setMessage("Student Not Found!!!");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			studentRepository.deleteById(id);
			response.setCode(200);
			response.setMessage("Student Deleted Successfully!!!");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> findAll = studentRepository.findAll();
		return findAll;
	}

	@Override
	public ResponseEntity<?> filterStudent(String firstname, String phone, String aadharno) {

		Response response = new Response();
		if (studentRepository.findAllByFirstnameLikeAndPhoneLikeAndAadharnoLike("%" + firstname + "%",
				"%" + phone + "%", "%" + aadharno + "%").isEmpty()) {
			response.setCode(404);
			response.setMessage("Student Not Found!!!");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			List<Student> student1 = studentRepository.findAllByFirstnameLikeAndPhoneLikeAndAadharnoLike(
					"%" + firstname + "%", "%" + phone + "%", "%" + aadharno + "%");
			return new ResponseEntity<List<Student>>(student1, HttpStatus.OK);
		}
	}

	public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
		Student student = studentRepository.findByPhone(phoneNo);
		return new org.springframework.security.core.userdetails.User((student.getPhone()), "", new ArrayList<>());
	}

	@Override
	public ResponseEntity<?> loginStudent(Student student) {
		TokenResponse tmessage = new TokenResponse();
		Response response = new Response();
		String phone = student.getPhone();
		String token = "Error";
		if (studentRepository.existsByPhone(phone) == false) {
			response.setCode(400);
			response.setMessage("Invalid Credential!!!");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			final UserDetails userDetails = loadUserByUsername(String.valueOf(student.getPhone()));
			token = jwtUtil.generateToken(userDetails);
			System.out.println(token);
			tmessage.setMessage("Student login Successfully!!!");
			tmessage.setToken(token);
			return new ResponseEntity<TokenResponse>(tmessage, HttpStatus.OK);
		}
	}

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filePath = path + File.separator + fileName1;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullpath);
		return is;
	}


	
	public Image uploadImage(MultipartFile file) throws IOException {
		Image pImage = new Image();
		pImage.setName(file.getOriginalFilename());
		pImage.setType(file.getContentType());
		pImage.setImageData(ImageUtil.compressImage(file.getBytes()));
		return productImageRepository.save(pImage);
	}
	
	public byte[] downloadImage(Long id){
        Optional<Image> imageData = productImageRepository.findById(id);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

}
