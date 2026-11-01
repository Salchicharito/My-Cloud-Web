package org.gerdoc.service;

import org.gerdoc.model.Materia;

import java.util.List;

public interface MateriaService
{
    List<Materia> findAll( );
    Materia findById( Long id );
    boolean save( Materia materia );
    boolean deleteById( Long id );
    boolean update( Materia materia );
}
