package com.example.Muitos_Para_Muitos.SecurityService;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.Muitos_Para_Muitos.Data.UserDetailsData;
import com.example.Muitos_Para_Muitos.Models.Student;
import com.example.Muitos_Para_Muitos.Repository.StudentRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final StudentRepository studentRepository;
	

	public UserDetailsServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Student> student = studentRepository.findStudentByEmail(username);
		
		if(student.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserDetailsData(student);
	}

}
