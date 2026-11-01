package org.gerdoc.dao;

import org.gerdoc.model.AlumnoMateria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoMateriaDao extends JpaRepository<AlumnoMateria, Long>
{
    // Se usan antes de borrar un alumno o una materia (integridad referencial)
    long countByAlumnoId( Long alumnoId );
    long countByMateriaId( Long materiaId );
}
