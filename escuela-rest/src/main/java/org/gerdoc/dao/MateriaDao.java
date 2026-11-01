package org.gerdoc.dao;

import org.gerdoc.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaDao extends JpaRepository<Materia, Long>
{
}
