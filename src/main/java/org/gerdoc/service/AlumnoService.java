package org.gerdoc.service;

import org.gerdoc.model.Alumno;

import java.util.List;

public interface AlumnoService
{
    List<Alumno> findAll( );
    Alumno findById( Long id );
    boolean save( Alumno alumno );
    boolean deleteById( Long id );
    boolean update( Alumno alumno );
}
