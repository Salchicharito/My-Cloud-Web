package org.gerdoc.dao;

import org.gerdoc.model.ProfesorMateria;

import java.util.List;

public interface ProfesorMateriaDao
{
    List<ProfesorMateria> findAll( );
    ProfesorMateria findById(Long id);
    ProfesorMateria save(ProfesorMateria profesorMateria);
    boolean deleteById(Long id);
    ProfesorMateria update(ProfesorMateria profesorMateria);
}
