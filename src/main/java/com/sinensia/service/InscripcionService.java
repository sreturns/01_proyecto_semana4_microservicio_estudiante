package com.sinensia.service;

import java.util.List;

import com.sinensia.model.Inscripcion;

public interface InscripcionService {
    
    List<Inscripcion> getAll();

    List<Inscripcion> save(Inscripcion inscripcion);

    List<Inscripcion> getByCourse(String nombreCurso);

}
