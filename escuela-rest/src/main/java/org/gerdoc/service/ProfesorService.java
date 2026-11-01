package org.gerdoc.service;

import org.gerdoc.model.Profesor;

import java.util.List;

public interface ProfesorService
{
    List<Profesor> findAll( );
    Profesor findById( Long id );
    Profesor save( Profesor profesor );
    Profesor update( Long id, Profesor profesor );
    void deleteById( Long id );
}
