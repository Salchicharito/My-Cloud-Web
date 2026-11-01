package org.gerdoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gerdoc.model.Profesor;
import org.gerdoc.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/profesores" )
@RequiredArgsConstructor
public class ProfesorController
{
    private final ProfesorService profesorService;

    @GetMapping
    public List<Profesor> findAll( )
    {
        return profesorService.findAll( );
    }

    @GetMapping( "/{id}" )
    public Profesor findById( @PathVariable Long id )
    {
        return profesorService.findById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Profesor save( @Valid @RequestBody Profesor profesor )
    {
        return profesorService.save( profesor );
    }

    @PutMapping( "/{id}" )
    public Profesor update( @PathVariable Long id, @Valid @RequestBody Profesor profesor )
    {
        return profesorService.update( id, profesor );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById( @PathVariable Long id )
    {
        profesorService.deleteById( id );
    }
}
