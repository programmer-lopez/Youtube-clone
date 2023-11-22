package com.youtube.clone.exception;

import com.youtube.clone.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class FileManagerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage>handleMaxSizeException(MaxUploadSizeExceededException exc){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Tamaño del video es demaciado grande. Pesa mas de 2 GB."));
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseMessage>handleFileNotFoundException(FileNotFoundException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("El video no se ha encontrado!"));
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseMessage>HandleIOException(IOException exc){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("No se proceso correctamente el video!"));
    }
}
