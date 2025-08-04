package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException ex){

        var erros = ex.getFieldErrors().stream().map(DadosErroValidacao::new).toList();

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){

            this(erro.getField(),erro.getDefaultMessage());

        }
}
}
