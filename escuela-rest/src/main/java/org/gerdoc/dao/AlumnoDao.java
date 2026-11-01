package org.gerdoc.dao;

import org.gerdoc.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoDao extends JpaRepository<Alumno, Long>
{
}
