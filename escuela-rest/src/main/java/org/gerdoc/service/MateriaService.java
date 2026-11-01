package org.gerdoc.service;

import org.gerdoc.model.Materia;

import java.util.List;

public interface MateriaService
{
    List<Materia> findAll( );
    Materia findById( Long id );
    Materia save( Materia materia );
    Materia update( Long id, Materia materia );
    void deleteById( Long id );
}
