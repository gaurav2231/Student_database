package com.student.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.demo.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
