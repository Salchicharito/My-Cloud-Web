package org.gerdoc.service;

import org.gerdoc.model.ProfesorMateria;

import java.util.List;

public interface ProfesorMateriaService
{
    List<ProfesorMateria> findAll( );
    ProfesorMateria findById( Long id );
    boolean save( ProfesorMateria profesorMateria );
    boolean deleteById( Long id );
    boolean update( ProfesorMateria profesorMateria );
}
