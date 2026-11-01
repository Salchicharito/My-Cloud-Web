package org.gerdoc.service.impl;

import org.gerdoc.dao.MateriaDao;
import org.gerdoc.dao.impl.MateriaDaoImpl;
import org.gerdoc.model.Materia;
import org.gerdoc.service.MateriaService;

import java.util.List;

public class MateriaServiceImpl implements MateriaService
{
    private MateriaDao materiaDao;

    public MateriaServiceImpl( )
    {
        this.materiaDao = new MateriaDaoImpl( );
    }

    @Override
    public List<Materia> findAll()
    {
        return materiaDao.findAll( );
    }

    @Override
    public Materia findById(Long id)
    {
        return materiaDao.findById( id );
    }

    @Override
    public boolean save(Materia materia)
    {
        return materiaDao.save( materia ) != null;
    }

    @Override
    public boolean deleteById(Long id)
    {
        return materiaDao.deleteById( id );
    }

    @Override
    public boolean update(Materia materia)
    {
        return materiaDao.update( materia ) != null;
    }
}
