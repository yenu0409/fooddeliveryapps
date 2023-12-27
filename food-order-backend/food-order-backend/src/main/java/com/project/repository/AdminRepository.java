package com.project.repository;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin,Integer>{

	Optional<Admin> findByAdminEmailIdAndAdminPassword(String emailId, String password);

}
