package org.gerdoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.gerdoc.dao.AlumnoDao;
import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.dao.MateriaDao;
import org.gerdoc.exception.DatosInvalidosException;
import org.gerdoc.exception.RecursoNoEncontradoException;
import org.gerdoc.model.Alumno;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.model.Materia;
import org.gerdoc.service.AlumnoMateriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoMateriaServiceImpl implements AlumnoMateriaService
{
    private final AlumnoMateriaDao alumnoMateriaDao;
    private final AlumnoDao alumnoDao;
    private final MateriaDao materiaDao;

    @Override
    public List<AlumnoMateria> findAll( )
    {
        return alumnoMateriaDao.findAll( );
    }

    @Override
    public AlumnoMateria findById( Long id )
    {
        return alumnoMateriaDao.findById( id )
                .orElseThrow( ( ) -> new RecursoNoEncontradoException( "No existe el registro ALUMNO_MATERIA con ID " + id ) );
    }

    @Override
    public AlumnoMateria save( AlumnoMateria alumnoMateria )
    {
        alumnoMateria.setId( null );
        completarRelaciones( alumnoMateria );
        return alumnoMateriaDao.save( alumnoMateria );
    }

    @Override
    public AlumnoMateria update( Long id, AlumnoMateria alumnoMateria )
    {
        findById( id );
        alumnoMateria.setId( id );
        completarRelaciones( alumnoMateria );
        return alumnoMateriaDao.save( alumnoMateria );
    }

    @Override
    public void deleteById( Long id )
    {
        // Nadie hace referencia a ALUMNO_MATERIA, se puede borrar directo
        findById( id );
        alumnoMateriaDao.deleteById( id );
    }

    // El JSON trae solo los IDs: { "alumno": { "id": 1 }, "materia": { "id": 2 } }
    private void completarRelaciones( AlumnoMateria alumnoMateria )
    {
        Long alumnoId = alumnoMateria.getAlumno( ).getId( );
        Long materiaId = alumnoMateria.getMateria( ).getId( );
        if( alumnoId == null || materiaId == null )
        {
            throw new DatosInvalidosException( "Debes indicar alumno.id y materia.id" );
        }
        Alumno alumno = alumnoDao.findById( alumnoId )
                .orElseThrow( ( ) -> new DatosInvalidosException( "No existe el alumno con ID " + alumnoId ) );
        Materia materia = materiaDao.findById( materiaId )
                .orElseThrow( ( ) -> new DatosInvalidosException( "No existe la materia con ID " + materiaId ) );
        alumnoMateria.setAlumno( alumno );
        alumnoMateria.setMateria( materia );
    }
}
