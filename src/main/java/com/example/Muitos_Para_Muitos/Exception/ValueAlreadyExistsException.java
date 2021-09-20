package com.example.Muitos_Para_Muitos.Exception;

public class ValueAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	@Override
    public String getMessage(){
        return "Value Already Exists";
    }

}
