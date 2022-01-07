package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.response.ErroResponse;
import br.com.uniciv.minhastarefas.exception.TarefaStatusException;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //Classe generica para tratamento de erro
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class) //Trata os exception de not found
    @ResponseStatus(HttpStatus.NOT_FOUND) // Devolve status para essa tratativa
    public ErroResponse entityNotFoundHandler(EntityNotFoundException ex) {

        return new ErroResponse("Recurso não encontrado");
    }

    @ExceptionHandler(TarefaStatusException.class) //Trata os exception de not foundtiva
    public ResponseEntity<?> alterarStatusTarefaHandler(TarefaStatusException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withTitle("Método não permitido")
                        .withDetail("Você não pode realizar está operação : " + ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErroResponse> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> new ErroResponse(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(BadCredentialsException.class) //Trata os exception de credencial invalida
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // Devolve status para essa tratativa
    public ErroResponse entityBadCredentialHandler(BadCredentialsException ex) {

        return new ErroResponse("Nome de Usuário e/ou senha inválidos");
    }
}
