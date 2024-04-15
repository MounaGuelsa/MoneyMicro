package org.money.utilisateurmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UtilisateurNotFound extends RuntimeException{
    public UtilisateurNotFound(String resourceName,String fieldValue){
        super(String.format("%s not found with the given input data %s : '%s'",resourceName,fieldValue));
    }
}