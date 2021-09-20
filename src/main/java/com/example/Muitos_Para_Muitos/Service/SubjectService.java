package com.example.Muitos_Para_Muitos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Muitos_Para_Muitos.Exception.ValueAlreadyExistsException;
import com.example.Muitos_Para_Muitos.Exception.ValueNotExists;
import com.example.Muitos_Para_Muitos.Models.Student;
import com.example.Muitos_Para_Muitos.Models.Subject;
import com.example.Muitos_Para_Muitos.Repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	public Subject saveSubject(Subject subject) throws ValueAlreadyExistsException, ValueNotExists{
		
		if(subject == null) {
			throw new ValueNotExists();
		}
		
		Subject newSubject = this.subjectRepo.findSubjectById(subject.getId());
		
		if(newSubject != null){
			throw new ValueAlreadyExistsException();
		}
		
		return this.subjectRepo.save(subject);
		
	}
	
	
	public List<Subject> listSubject(){
		return this.subjectRepo.findAll();
	}
	
	public List<Student> listStudentInSubject(Long idSubject){
		
		return this.subjectRepo.findById(idSubject).get().getStudentList();
		
	}

}
