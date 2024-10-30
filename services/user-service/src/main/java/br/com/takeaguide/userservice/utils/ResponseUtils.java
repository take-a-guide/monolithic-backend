package br.com.takeaguide.userservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import br.com.takeaguide.userservice.dtos.ResponseObject;

public class ResponseUtils {

    private static final Gson gson = new Gson();

    public static <T> ResponseEntity<T> formatResponse(HttpStatus httpStatus, T responseObject){

        return ResponseEntity
            .status(httpStatus)
            .body(
                responseObject
            )
        ;

    }

    public static ResponseEntity<String> formatMensagem(HttpStatus httpStatus, Object mensagem){

        return ResponseEntity
            .status(httpStatus)
            .body(
                gson.toJson(mensagem)
            )
        ;

    }

    public static ResponseEntity<String> formatErrorMessage(HttpStatus httpStatus, ResponseObject responseObject){

        return ResponseEntity
            .status(httpStatus)
            .body(
                gson.toJson(responseObject)
            )
        ;

    }
    
}
