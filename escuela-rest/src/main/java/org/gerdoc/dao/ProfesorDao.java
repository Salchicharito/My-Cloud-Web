package org.gerdoc.dao;

import org.gerdoc.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorDao extends JpaRepository<Profesor, Long>
{
}
