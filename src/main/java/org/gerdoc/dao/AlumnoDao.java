package org.gerdoc.dao;

import org.gerdoc.model.Alumno;

import java.util.List;

public interface AlumnoDao
{
    List<Alumno> findAll( );
    Alumno findById(Long id);
    Alumno save(Alumno alumno);
    boolean deleteById(Long id);
    Alumno update(Alumno alumno);
}
