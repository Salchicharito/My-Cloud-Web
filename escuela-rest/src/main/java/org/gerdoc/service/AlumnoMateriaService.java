package org.gerdoc.service;

import org.gerdoc.model.AlumnoMateria;

import java.util.List;

public interface AlumnoMateriaService
{
    List<AlumnoMateria> findAll( );
    AlumnoMateria findById( Long id );
    AlumnoMateria save( AlumnoMateria alumnoMateria );
    AlumnoMateria update( Long id, AlumnoMateria alumnoMateria );
    void deleteById( Long id );
}
