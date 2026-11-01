package org.gerdoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gerdoc.model.Materia;
import org.gerdoc.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/materias" )
@RequiredArgsConstructor
public class MateriaController
{
    private final MateriaService materiaService;

    @GetMapping
    public List<Materia> findAll( )
    {
        return materiaService.findAll( );
    }

    @GetMapping( "/{id}" )
    public Materia findById( @PathVariable Long id )
    {
        return materiaService.findById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Materia save( @Valid @RequestBody Materia materia )
    {
        return materiaService.save( materia );
    }

    @PutMapping( "/{id}" )
    public Materia update( @PathVariable Long id, @Valid @RequestBody Materia materia )
    {
        return materiaService.update( id, materia );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById( @PathVariable Long id )
    {
        materiaService.deleteById( id );
    }
}
