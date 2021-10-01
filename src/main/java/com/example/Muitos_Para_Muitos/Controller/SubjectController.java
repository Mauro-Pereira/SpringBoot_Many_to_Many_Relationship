package com.example.Muitos_Para_Muitos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Muitos_Para_Muitos.Exception.ValueAlreadyExistsException;
import com.example.Muitos_Para_Muitos.Exception.ValueNotExists;
import com.example.Muitos_Para_Muitos.Models.Subject;
import com.example.Muitos_Para_Muitos.Service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/subject")
@Api(tags = "subject") //swagger
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/addSubject")
    @ApiOperation(value = "This method add subjects.")
	public ResponseEntity<?> addSubject(@RequestBody Subject subject) throws ValueAlreadyExistsException, ValueNotExists{
		
		try {
			
			return new ResponseEntity<>(this.subjectService.saveSubject(subject),HttpStatus.CREATED);
			
		}catch(ValueNotExists ex) {
			
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
			
		}catch(ValueAlreadyExistsException ex) {
			
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/listSubject")
    @ApiOperation(value = "This method is used to get the subjects.")
	public ResponseEntity<?> listSubject(){
		return new ResponseEntity<>(this.subjectService.listSubject(),HttpStatus.OK);
	}
	
	@GetMapping("/listStudentInSubject/{idSubject}")
	public ResponseEntity<?> listStudentInSubject(@PathVariable Long idSubject){
		
		return new ResponseEntity<>(this.subjectService.listStudentInSubject(idSubject),HttpStatus.OK);
		
	}
	
}
