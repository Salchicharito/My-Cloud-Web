package org.gerdoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gerdoc.model.Alumno;
import org.gerdoc.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/alumnos" )
@RequiredArgsConstructor
public class AlumnoController
{
    private final AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> findAll( )
    {
        return alumnoService.findAll( );
    }

    @GetMapping( "/{id}" )
    public Alumno findById( @PathVariable Long id )
    {
        return alumnoService.findById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Alumno save( @Valid @RequestBody Alumno alumno )
    {
        return alumnoService.save( alumno );
    }

    @PutMapping( "/{id}" )
    public Alumno update( @PathVariable Long id, @Valid @RequestBody Alumno alumno )
    {
        return alumnoService.update( id, alumno );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById( @PathVariable Long id )
    {
        alumnoService.deleteById( id );
    }
}
