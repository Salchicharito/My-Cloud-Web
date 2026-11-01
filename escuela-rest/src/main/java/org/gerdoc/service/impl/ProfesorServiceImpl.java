package org.gerdoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.gerdoc.dao.ProfesorDao;
import org.gerdoc.dao.ProfesorMateriaDao;
import org.gerdoc.exception.IntegridadReferencialException;
import org.gerdoc.exception.RecursoNoEncontradoException;
import org.gerdoc.model.Profesor;
import org.gerdoc.service.ProfesorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService
{
    private final ProfesorDao profesorDao;
    private final ProfesorMateriaDao profesorMateriaDao;

    @Override
    public List<Profesor> findAll( )
    {
        return profesorDao.findAll( );
    }

    @Override
    public Profesor findById( Long id )
    {
        return profesorDao.findById( id )
                .orElseThrow( ( ) -> new RecursoNoEncontradoException( "No existe el profesor con ID " + id ) );
    }

    @Override
    public Profesor save( Profesor profesor )
    {
        profesor.setId( null );
        return profesorDao.save( profesor );
    }

    @Override
    public Profesor update( Long id, Profesor profesor )
    {
        findById( id );
        profesor.setId( id );
        return profesorDao.save( profesor );
    }

    @Override
    @Transactional
    public void deleteById( Long id )
    {
        findById( id );
        long asignaciones = profesorMateriaDao.countByProfesorId( id );
        if( asignaciones > 0 )
        {
            throw new IntegridadReferencialException( "No se puede borrar el profesor con ID " + id
                    + ": tiene " + asignaciones + " materia(s) asignada(s). Borra primero esos registros de PROFESOR_MATERIA." );
        }
        profesorDao.deleteById( id );
    }
}
