package org.gerdoc.dao.impl;

import org.gerdoc.dao.AlumnoMateriaDao;
import org.gerdoc.model.AlumnoMateria;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AlumnoMateriaDaoImpl implements AlumnoMateriaDao
{
    private Session session;

    public AlumnoMateriaDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<AlumnoMateria> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<AlumnoMateria> criteria =
                builder.createQuery(AlumnoMateria.class);
        criteria.from(AlumnoMateria.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public AlumnoMateria findById(Long id)
    {
        return session.get( AlumnoMateria.class, id );
    }

    @Override
    public AlumnoMateria save(AlumnoMateria alumnoMateria)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( alumnoMateria );
            transaction.commit( );
            return alumnoMateria;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if( transaction != null && transaction.isActive( ) )
            {
                transaction.rollback( );
            }
            session.clear( );
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id)
    {
        Transaction transaction = null;
        try
        {
            AlumnoMateria alumnoMateria = findById( id );
            if( alumnoMateria == null )
            {
                return false;
            }
            transaction = session.beginTransaction();
            session.delete( alumnoMateria );
            transaction.commit( );
            return true;
        }
        catch (Exception e)
        {
            // Falla si otro registro todavía lo referencia (llave foránea)
            e.printStackTrace();
            if( transaction != null && transaction.isActive( ) )
            {
                transaction.rollback( );
            }
            session.clear( );
            return false;
        }
    }

    @Override
    public AlumnoMateria update(AlumnoMateria alumnoMateria)
    {
        Transaction transaction = null;
        AlumnoMateria updatedAlumnoMateria = null;
        try
        {
            transaction = session.beginTransaction();
            updatedAlumnoMateria = (AlumnoMateria) session.merge( alumnoMateria );
            transaction.commit( );
            return updatedAlumnoMateria;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if( transaction != null && transaction.isActive( ) )
            {
                transaction.rollback( );
            }
            session.clear( );
            return null;
        }
    }
}
