package com.example.Muitos_Para_Muitos.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Muitos_Para_Muitos.Models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Student findStudentById(Long id);
	Optional<Student> findStudentByEmail(String email);

}
