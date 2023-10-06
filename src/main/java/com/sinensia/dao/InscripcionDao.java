package com.sinensia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.model.Inscripcion;

/**
 * Extendemos de JpaRepository para acceder a los Crud
 *
 * @see com.sinensia.service.InscripcionService
 * @see com.sinensia.service.InscripcionServiceImpl
 */
public interface InscripcionDao extends JpaRepository<Inscripcion, Integer> {

}
