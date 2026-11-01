package org.gerdoc.dao;

import org.gerdoc.model.Profesor;

import java.util.List;

public interface ProfesorDao
{
    List<Profesor> findAll( );
    Profesor findById(Long id);
    Profesor save(Profesor profesor);
    boolean deleteById(Long id);
    Profesor update(Profesor profesor);
}
