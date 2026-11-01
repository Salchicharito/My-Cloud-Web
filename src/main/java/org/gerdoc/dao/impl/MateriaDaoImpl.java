package org.gerdoc.dao.impl;

import org.gerdoc.dao.MateriaDao;
import org.gerdoc.model.Materia;
import org.gerdoc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MateriaDaoImpl implements MateriaDao
{
    private Session session;

    public MateriaDaoImpl( )
    {
        session = HibernateUtil
                .getSessionFactory( )
                .openSession( );
    }

    @Override
    public List<Materia> findAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Materia> criteria =
                builder.createQuery(Materia.class);
        criteria.from(Materia.class);
        return session
                .createQuery(criteria)
                .getResultList();
    }

    @Override
    public Materia findById(Long id)
    {
        return session.get( Materia.class, id );
    }

    @Override
    public Materia save(Materia materia)
    {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            session.save( materia );
            transaction.commit( );
            return materia;
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
            Materia materia = findById( id );
            if( materia == null )
            {
                return false;
            }
            transaction = session.beginTransaction();
            session.delete( materia );
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
    public Materia update(Materia materia)
    {
        Transaction transaction = null;
        Materia updatedMateria = null;
        try
        {
            transaction = session.beginTransaction();
            updatedMateria = (Materia) session.merge( materia );
            transaction.commit( );
            return updatedMateria;
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
