package com.example.Muitos_Para_Muitos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Muitos_Para_Muitos.Models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Subject findSubjectById(Long id);
}
