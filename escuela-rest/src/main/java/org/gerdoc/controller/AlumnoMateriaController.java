package org.gerdoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.service.AlumnoMateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/alumno-materias" )
@RequiredArgsConstructor
public class AlumnoMateriaController
{
    private final AlumnoMateriaService alumnoMateriaService;

    @GetMapping
    public List<AlumnoMateria> findAll( )
    {
        return alumnoMateriaService.findAll( );
    }

    @GetMapping( "/{id}" )
    public AlumnoMateria findById( @PathVariable Long id )
    {
        return alumnoMateriaService.findById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public AlumnoMateria save( @Valid @RequestBody AlumnoMateria alumnoMateria )
    {
        return alumnoMateriaService.save( alumnoMateria );
    }

    @PutMapping( "/{id}" )
    public AlumnoMateria update( @PathVariable Long id, @Valid @RequestBody AlumnoMateria alumnoMateria )
    {
        return alumnoMateriaService.update( id, alumnoMateria );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById( @PathVariable Long id )
    {
        alumnoMateriaService.deleteById( id );
    }
}
