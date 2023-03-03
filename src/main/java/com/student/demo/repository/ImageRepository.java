package com.student.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.demo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
		
}
