package org.gerdoc.service;

import org.gerdoc.model.ProfesorMateria;

import java.util.List;

public interface ProfesorMateriaService
{
    List<ProfesorMateria> findAll( );
    ProfesorMateria findById( Long id );
    ProfesorMateria save( ProfesorMateria profesorMateria );
    ProfesorMateria update( Long id, ProfesorMateria profesorMateria );
    void deleteById( Long id );
}
