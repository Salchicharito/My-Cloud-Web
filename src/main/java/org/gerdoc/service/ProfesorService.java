package org.gerdoc.service;

import org.gerdoc.model.Profesor;

import java.util.List;

public interface ProfesorService
{
    List<Profesor> findAll( );
    Profesor findById( Long id );
    boolean save( Profesor profesor );
    boolean deleteById( Long id );
    boolean update( Profesor profesor );
}
