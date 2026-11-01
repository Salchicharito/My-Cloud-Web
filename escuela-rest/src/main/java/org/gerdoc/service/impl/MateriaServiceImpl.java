package org.gerdoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.dao.MateriaDao;
import org.gerdoc.dao.ProfesorMateriaDao;
import org.gerdoc.exception.IntegridadReferencialException;
import org.gerdoc.exception.RecursoNoEncontradoException;
import org.gerdoc.model.Materia;
import org.gerdoc.service.MateriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaServiceImpl implements MateriaService
{
    private final MateriaDao materiaDao;
    private final AlumnoMateriaDao alumnoMateriaDao;
    private final ProfesorMateriaDao profesorMateriaDao;

    @Override
    public List<Materia> findAll( )
    {
        return materiaDao.findAll( );
    }

    @Override
    public Materia findById( Long id )
    {
        return materiaDao.findById( id )
                .orElseThrow( ( ) -> new RecursoNoEncontradoException( "No existe la materia con ID " + id ) );
    }

    @Override
    public Materia save( Materia materia )
    {
        materia.setId( null );
        return materiaDao.save( materia );
    }

    @Override
    public Materia update( Long id, Materia materia )
    {
        findById( id );
        materia.setId( id );
        return materiaDao.save( materia );
    }

    @Override
    @Transactional
    public void deleteById( Long id )
    {
        findById( id );
        long inscripciones = alumnoMateriaDao.countByMateriaId( id );
        long asignaciones = profesorMateriaDao.countByMateriaId( id );
        if( inscripciones > 0 || asignaciones > 0 )
        {
            throw new IntegridadReferencialException( "No se puede borrar la materia con ID " + id
                    + ": tiene " + inscripciones + " alumno(s) inscrito(s) y " + asignaciones
                    + " profesor(es) asignado(s). Borra primero esos registros de ALUMNO_MATERIA y PROFESOR_MATERIA." );
        }
        materiaDao.deleteById( id );
    }
}
