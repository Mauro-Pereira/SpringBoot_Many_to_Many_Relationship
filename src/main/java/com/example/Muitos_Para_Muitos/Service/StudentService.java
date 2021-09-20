package com.example.Muitos_Para_Muitos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Muitos_Para_Muitos.Exception.ValueAlreadyExistsException;
import com.example.Muitos_Para_Muitos.Exception.ValueNotExists;
import com.example.Muitos_Para_Muitos.Models.Student;
import com.example.Muitos_Para_Muitos.Models.Subject;
import com.example.Muitos_Para_Muitos.Repository.StudentRepository;
import com.example.Muitos_Para_Muitos.Repository.SubjectRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	
	public Student saveStudent(Student student) throws ValueAlreadyExistsException, ValueNotExists{
		
		if(student == null) {
			throw new ValueNotExists();
		}
		
		Student newStudent = this.studentRepo.findStudentById(student.getId());
		
		if(newStudent != null){
			throw new ValueAlreadyExistsException();
		}
		
		student.setPassword(encoder.encode(student.getPassword()));
		
		return this.studentRepo.save(student);
		
	}
	
	
	public Student updateStudent(Student student) throws ValueNotExists{
		
		Student newStudent = this.studentRepo.findStudentById(student.getId());
		
		if(student == null || newStudent == null) {
			throw new ValueNotExists();
		}
		
		
		
		newStudent.setEmail(student.getEmail());
		newStudent.setName(student.getName());
		
		return this.studentRepo.save(newStudent);
	}
	
	
	public List<Student> listStudent(){
		return this.studentRepo.findAll();
	}
	
	
	public String makeRegisterInSubject(Long idSubject, Long idStudent) throws ValueNotExists{
		
		Student newStudent = this.studentRepo.findStudentById(idStudent);
		Subject newSubject = this.subjectRepo.findSubjectById(idSubject);
		
		if(newStudent == null || newSubject == null) {
			throw new ValueNotExists();
		}
		
		newStudent.getSubjectList().add(newSubject);
		newSubject.getStudentList().add(newStudent);
		this.studentRepo.save(newStudent);
		this.subjectRepo.save(newSubject);
		
		String message = "student successfully registered";
		return message;
		
		
	}
	
	
	public String deleteStudent(Long idStudent) throws ValueNotExists{
		
		Student newStudent = this.studentRepo.findStudentById(idStudent);
		
		if(newStudent == null) {
			throw new ValueNotExists();
		}
		
		this.studentRepo.deleteById(newStudent.getId());
		
		String message = "Student was deleted with successfull";
		
		return message;

	}
	
	public List<Subject> mySubjects(Long idStudent){
		return this.studentRepo.findById(idStudent).get().getSubjectList();
	}
	
}


