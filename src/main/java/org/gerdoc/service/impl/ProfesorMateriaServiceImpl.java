package org.gerdoc.service.impl;

import org.gerdoc.dao.ProfesorMateriaDao;
import org.gerdoc.dao.impl.ProfesorMateriaDaoImpl;
import org.gerdoc.model.ProfesorMateria;
import org.gerdoc.service.ProfesorMateriaService;

import java.util.List;

public class ProfesorMateriaServiceImpl implements ProfesorMateriaService
{
    private ProfesorMateriaDao profesorMateriaDao;

    public ProfesorMateriaServiceImpl( )
    {
        this.profesorMateriaDao = new ProfesorMateriaDaoImpl( );
    }

    @Override
    public List<ProfesorMateria> findAll()
    {
        return profesorMateriaDao.findAll( );
    }

    @Override
    public ProfesorMateria findById(Long id)
    {
        return profesorMateriaDao.findById( id );
    }

    @Override
    public boolean save(ProfesorMateria profesorMateria)
    {
        return profesorMateriaDao.save( profesorMateria ) != null;
    }

    @Override
    public boolean deleteById(Long id)
    {
        return profesorMateriaDao.deleteById( id );
    }

    @Override
    public boolean update(ProfesorMateria profesorMateria)
    {
        return profesorMateriaDao.update( profesorMateria ) != null;
    }
}
