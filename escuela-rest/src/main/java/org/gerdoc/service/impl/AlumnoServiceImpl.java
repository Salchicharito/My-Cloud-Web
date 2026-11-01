package org.gerdoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.gerdoc.dao.AlumnoDao;
import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.exception.IntegridadReferencialException;
import org.gerdoc.exception.RecursoNoEncontradoException;
import org.gerdoc.model.Alumno;
import org.gerdoc.service.AlumnoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService
{
    private final AlumnoDao alumnoDao;
    private final AlumnoMateriaDao alumnoMateriaDao;

    @Override
    public List<Alumno> findAll( )
    {
        return alumnoDao.findAll( );
    }

    @Override
    public Alumno findById( Long id )
    {
        return alumnoDao.findById( id )
                .orElseThrow( ( ) -> new RecursoNoEncontradoException( "No existe el alumno con ID " + id ) );
    }

    @Override
    public Alumno save( Alumno alumno )
    {
        alumno.setId( null );
        return alumnoDao.save( alumno );
    }

    @Override
    public Alumno update( Long id, Alumno alumno )
    {
        findById( id );
        alumno.setId( id );
        return alumnoDao.save( alumno );
    }

    @Override
    @Transactional
    public void deleteById( Long id )
    {
        findById( id );
        long inscripciones = alumnoMateriaDao.countByAlumnoId( id );
        if( inscripciones > 0 )
        {
            throw new IntegridadReferencialException( "No se puede borrar el alumno con ID " + id
                    + ": está inscrito en " + inscripciones + " materia(s). Borra primero esos registros de ALUMNO_MATERIA." );
        }
        alumnoDao.deleteById( id );
    }
}
