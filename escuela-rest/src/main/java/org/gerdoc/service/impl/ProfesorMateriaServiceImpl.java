package org.gerdoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.gerdoc.dao.MateriaDao;
import org.gerdoc.dao.ProfesorDao;
import org.gerdoc.dao.ProfesorMateriaDao;
import org.gerdoc.exception.DatosInvalidosException;
import org.gerdoc.exception.RecursoNoEncontradoException;
import org.gerdoc.model.Materia;
import org.gerdoc.model.Profesor;
import org.gerdoc.model.ProfesorMateria;
import org.gerdoc.service.ProfesorMateriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorMateriaServiceImpl implements ProfesorMateriaService
{
    private final ProfesorMateriaDao profesorMateriaDao;
    private final ProfesorDao profesorDao;
    private final MateriaDao materiaDao;

    @Override
    public List<ProfesorMateria> findAll( )
    {
        return profesorMateriaDao.findAll( );
    }

    @Override
    public ProfesorMateria findById( Long id )
    {
        return profesorMateriaDao.findById( id )
                .orElseThrow( ( ) -> new RecursoNoEncontradoException( "No existe el registro PROFESOR_MATERIA con ID " + id ) );
    }

    @Override
    public ProfesorMateria save( ProfesorMateria profesorMateria )
    {
        profesorMateria.setId( null );
        completarRelaciones( profesorMateria );
        return profesorMateriaDao.save( profesorMateria );
    }

    @Override
    public ProfesorMateria update( Long id, ProfesorMateria profesorMateria )
    {
        findById( id );
        profesorMateria.setId( id );
        completarRelaciones( profesorMateria );
        return profesorMateriaDao.save( profesorMateria );
    }

    @Override
    public void deleteById( Long id )
    {
        // Nadie hace referencia a PROFESOR_MATERIA, se puede borrar directo
        findById( id );
        profesorMateriaDao.deleteById( id );
    }

    // El JSON trae solo los IDs: { "profesor": { "id": 1 }, "materia": { "id": 2 } }
    private void completarRelaciones( ProfesorMateria profesorMateria )
    {
        Long profesorId = profesorMateria.getProfesor( ).getId( );
        Long materiaId = profesorMateria.getMateria( ).getId( );
        if( profesorId == null || materiaId == null )
        {
            throw new DatosInvalidosException( "Debes indicar profesor.id y materia.id" );
        }
        Profesor profesor = profesorDao.findById( profesorId )
                .orElseThrow( ( ) -> new DatosInvalidosException( "No existe el profesor con ID " + profesorId ) );
        Materia materia = materiaDao.findById( materiaId )
                .orElseThrow( ( ) -> new DatosInvalidosException( "No existe la materia con ID " + materiaId ) );
        profesorMateria.setProfesor( profesor );
        profesorMateria.setMateria( materia );
    }
}
