package org.gerdoc.service.impl;

import org.gerdoc.dao.AlumnoDao;
import org.gerdoc.dao.impl.AlumnoDaoImpl;
import org.gerdoc.model.Alumno;
import org.gerdoc.service.AlumnoService;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService
{
    private AlumnoDao alumnoDao;

    public AlumnoServiceImpl( )
    {
        this.alumnoDao = new AlumnoDaoImpl( );
    }

    @Override
    public List<Alumno> findAll()
    {
        return alumnoDao.findAll( );
    }

    @Override
    public Alumno findById(Long id)
    {
        return alumnoDao.findById( id );
    }

    @Override
    public boolean save(Alumno alumno)
    {
        return alumnoDao.save( alumno ) != null;
    }

    @Override
    public boolean deleteById(Long id)
    {
        return alumnoDao.deleteById( id );
    }

    @Override
    public boolean update(Alumno alumno)
    {
        return alumnoDao.update( alumno ) != null;
    }
}
