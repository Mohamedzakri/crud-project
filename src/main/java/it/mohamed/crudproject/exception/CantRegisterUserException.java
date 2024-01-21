package it.mohamed.crudproject.exception;

public class CantRegisterUserException extends Exception{
    public CantRegisterUserException(String message){
        super(message);
    }
}
