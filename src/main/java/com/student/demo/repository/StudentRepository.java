package com.student.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByFirstnameOrPhoneOrAadharnoLike(String firstname, String phone, String Aadharno);

	Student findByPhone(String phone);

	boolean existsByPhone(String phone);

	boolean existsByPhoneAndAadharno(String phone, String aadharno);

	Student findByFirstnameContaining(String firstname);

	@Query(value = "SELECT * FROM student s WHERE s.first_name like ?1 or  s.phone_no like ?2 or s.aadhar_no like ?3", nativeQuery = true)
	List<Student> findAllByFirstnameLikeAndPhoneLikeAndAadharnoLike(String firstname, String phone, String aadharno);

//	boolean existsByFirstnameLikeAndPhoneLikeAndAadharnoLike(String string, String string2, String string3);

}
