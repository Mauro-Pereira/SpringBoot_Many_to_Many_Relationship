package com.example.Muitos_Para_Muitos.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Muitos_Para_Muitos.Models.Student;

public class UserDetailsData implements UserDetails{
	
	
	private final Optional<Student> student;
	
	

	public UserDetailsData(Optional<Student> student) {
		super();
		this.student = student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return student.orElse(new Student()).getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return student.orElse(new Student()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
