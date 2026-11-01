package org.gerdoc.service.impl;

import org.gerdoc.dao.ProfesorDao;
import org.gerdoc.dao.impl.ProfesorDaoImpl;
import org.gerdoc.model.Profesor;
import org.gerdoc.service.ProfesorService;

import java.util.List;

public class ProfesorServiceImpl implements ProfesorService
{
    private ProfesorDao profesorDao;

    public ProfesorServiceImpl( )
    {
        this.profesorDao = new ProfesorDaoImpl( );
    }

    @Override
    public List<Profesor> findAll()
    {
        return profesorDao.findAll( );
    }

    @Override
    public Profesor findById(Long id)
    {
        return profesorDao.findById( id );
    }

    @Override
    public boolean save(Profesor profesor)
    {
        return profesorDao.save( profesor ) != null;
    }

    @Override
    public boolean deleteById(Long id)
    {
        return profesorDao.deleteById( id );
    }

    @Override
    public boolean update(Profesor profesor)
    {
        return profesorDao.update( profesor ) != null;
    }
}
