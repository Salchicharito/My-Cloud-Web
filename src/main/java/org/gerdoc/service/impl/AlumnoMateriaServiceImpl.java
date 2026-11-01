package org.gerdoc.service.impl;

import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.dao.impl.AlumnoMateriaDaoImpl;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.service.AlumnoMateriaService;

import java.util.List;

public class AlumnoMateriaServiceImpl implements AlumnoMateriaService
{
    private AlumnoMateriaDao alumnoMateriaDao;

    public AlumnoMateriaServiceImpl( )
    {
        this.alumnoMateriaDao = new AlumnoMateriaDaoImpl( );
    }

    @Override
    public List<AlumnoMateria> findAll()
    {
        return alumnoMateriaDao.findAll( );
    }

    @Override
    public AlumnoMateria findById(Long id)
    {
        return alumnoMateriaDao.findById( id );
    }

    @Override
    public boolean save(AlumnoMateria alumnoMateria)
    {
        return alumnoMateriaDao.save( alumnoMateria ) != null;
    }

    @Override
    public boolean deleteById(Long id)
    {
        return alumnoMateriaDao.deleteById( id );
    }

    @Override
    public boolean update(AlumnoMateria alumnoMateria)
    {
        return alumnoMateriaDao.update( alumnoMateria ) != null;
    }
}
