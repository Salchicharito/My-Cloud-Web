package org.gerdoc.dao;

import org.gerdoc.model.ProfesorMateria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorMateriaDao extends JpaRepository<ProfesorMateria, Long>
{
    // Se usan antes de borrar un profesor o una materia (integridad referencial)
    long countByProfesorId( Long profesorId );
    long countByMateriaId( Long materiaId );
}
