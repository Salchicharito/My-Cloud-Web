package org.gerdoc.service;

import org.gerdoc.model.AlumnoMateria;

import java.util.List;

public interface AlumnoMateriaService
{
    List<AlumnoMateria> findAll( );
    AlumnoMateria findById( Long id );
    boolean save( AlumnoMateria alumnoMateria );
    boolean deleteById( Long id );
    boolean update( AlumnoMateria alumnoMateria );
}
