package org.gerdoc.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Convierte los errores en respuestas JSON con el código HTTP correcto.
 */
@RestControllerAdvice
public class ManejadorErrores
{
    @ExceptionHandler( RecursoNoEncontradoException.class )
    public ResponseEntity<Map<String, Object>> noEncontrado( RecursoNoEncontradoException e )
    {
        return respuesta( HttpStatus.NOT_FOUND, e.getMessage( ) );
    }

    @ExceptionHandler( IntegridadReferencialException.class )
    public ResponseEntity<Map<String, Object>> integridad( IntegridadReferencialException e )
    {
        return respuesta( HttpStatus.CONFLICT, e.getMessage( ) );
    }

    // Respaldo: cualquier llave foránea o UNIQUE que rechace la base de datos
    @ExceptionHandler( DataIntegrityViolationException.class )
    public ResponseEntity<Map<String, Object>> integridadBaseDatos( DataIntegrityViolationException e )
    {
        return respuesta( HttpStatus.CONFLICT,
                "La base de datos rechazó la operación por una restricción de integridad (registro duplicado o relacionado)." );
    }

    @ExceptionHandler( DatosInvalidosException.class )
    public ResponseEntity<Map<String, Object>> datosInvalidos( DatosInvalidosException e )
    {
        return respuesta( HttpStatus.BAD_REQUEST, e.getMessage( ) );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Map<String, Object>> validacion( MethodArgumentNotValidException e )
    {
        String mensaje = e.getBindingResult( ).getFieldErrors( ).stream( )
                .map( error -> error.getField( ) + ": " + error.getDefaultMessage( ) )
                .collect( Collectors.joining( ", " ) );
        return respuesta( HttpStatus.BAD_REQUEST, mensaje );
    }

    @ExceptionHandler( HttpMessageNotReadableException.class )
    public ResponseEntity<Map<String, Object>> jsonInvalido( HttpMessageNotReadableException e )
    {
        return respuesta( HttpStatus.BAD_REQUEST, "El cuerpo de la petición no es un JSON válido." );
    }

    private ResponseEntity<Map<String, Object>> respuesta( HttpStatus estado, String mensaje )
    {
        Map<String, Object> cuerpo = new LinkedHashMap<>( );
        cuerpo.put( "fecha", LocalDateTime.now( ).toString( ) );
        cuerpo.put( "estado", estado.value( ) );
        cuerpo.put( "error", estado.getReasonPhrase( ) );
        cuerpo.put( "mensaje", mensaje );
        return ResponseEntity.status( estado ).body( cuerpo );
    }
}
