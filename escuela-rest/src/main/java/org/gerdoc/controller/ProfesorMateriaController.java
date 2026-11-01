package org.gerdoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gerdoc.model.ProfesorMateria;
import org.gerdoc.service.ProfesorMateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/profesor-materias" )
@RequiredArgsConstructor
public class ProfesorMateriaController
{
    private final ProfesorMateriaService profesorMateriaService;

    @GetMapping
    public List<ProfesorMateria> findAll( )
    {
        return profesorMateriaService.findAll( );
    }

    @GetMapping( "/{id}" )
    public ProfesorMateria findById( @PathVariable Long id )
    {
        return profesorMateriaService.findById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public ProfesorMateria save( @Valid @RequestBody ProfesorMateria profesorMateria )
    {
        return profesorMateriaService.save( profesorMateria );
    }

    @PutMapping( "/{id}" )
    public ProfesorMateria update( @PathVariable Long id, @Valid @RequestBody ProfesorMateria profesorMateria )
    {
        return profesorMateriaService.update( id, profesorMateria );
    }

    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById( @PathVariable Long id )
    {
        profesorMateriaService.deleteById( id );
    }
}
