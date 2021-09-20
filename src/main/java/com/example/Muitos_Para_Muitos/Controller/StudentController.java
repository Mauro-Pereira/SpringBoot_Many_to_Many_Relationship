package com.example.Muitos_Para_Muitos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Muitos_Para_Muitos.Exception.ValueAlreadyExistsException;
import com.example.Muitos_Para_Muitos.Exception.ValueNotExists;
import com.example.Muitos_Para_Muitos.Models.Student;
import com.example.Muitos_Para_Muitos.Service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/student")
@Api(tags = "student") //for swagger
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/listAllStudent")
    @ApiOperation(value = "This method list all student.")
	public ResponseEntity<?> listAllStudent(){
		
		return new ResponseEntity<>(this.studentService.listStudent(),HttpStatus.OK);
		
	}
	
	@PostMapping("/addStudent")
    @ApiOperation(value = "This method add students.")
	public ResponseEntity<?> addStudent(@RequestBody Student student) throws ValueAlreadyExistsException, ValueNotExists{
		
		try {
			
			return new ResponseEntity<>(this.studentService.saveStudent(student),HttpStatus.CREATED);
			
		}catch(ValueNotExists ex) {
			
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			
		}catch(ValueAlreadyExistsException ex) {
			
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/makeRegisterInSubject/{idSubject}/{idStudent}")
    @ApiOperation(value = "This method make student registration in subject.")
	public ResponseEntity<?> makeRegisterInSubject(@PathVariable Long idSubject,@PathVariable Long idStudent ) throws ValueNotExists{
		
		try {
			
			return new ResponseEntity<>(this.studentService.makeRegisterInSubject(idSubject, idStudent), HttpStatus.OK);
			
		}catch(ValueNotExists ex) {
			
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
				
		}

	}
	
	@DeleteMapping("deleteStudent/{idStudent}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long idStudent) throws ValueNotExists{
		try {
			
			return new ResponseEntity<>(this.studentService.deleteStudent(idStudent),HttpStatus.OK);
			
		}catch(ValueNotExists ex) {
			
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/mySubjects/{idStudent}")
	public ResponseEntity<?> mySubjects(@PathVariable Long idStudent){
		return new ResponseEntity<>(this.studentService.mySubjects(idStudent), HttpStatus.OK);
	}
	
}
