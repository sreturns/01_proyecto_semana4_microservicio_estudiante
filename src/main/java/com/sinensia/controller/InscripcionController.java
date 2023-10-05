package com.sinensia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.exceptions.InscripcionNotFoundException;
import com.sinensia.model.Inscripcion;
import com.sinensia.service.InscripcionService;

@RestController
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @GetMapping(value = "inscripcion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inscripcion>> getAll() {
        try {
            List<Inscripcion> list = service.getAll();

            if (list == null) {
                throw new InscripcionNotFoundException("Lista vacia");
            }
            return ResponseEntity.ok(list);

        } catch (InscripcionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "inscripcion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inscripcion>> save(@RequestBody Inscripcion inscripcion) {
        try {
            if (inscripcion.getIdCurso() < 0 || inscripcion.getCalificaciones() < 0
                    || inscripcion.getNombreEstudiante().isEmpty() || inscripcion.getEdad() < 0) {
                throw new IllegalArgumentException("Parametro no valido");

            }
            return ResponseEntity.ok(service.save(inscripcion));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "inscripcion/{nombreCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inscripcion>> getListByCurso(@PathVariable("nombreCurso") String nombreCurso) {
        try {
            if (nombreCurso == null || nombreCurso.isEmpty()) {
                throw new IllegalArgumentException();
            }
            return ResponseEntity.ok(service.getByCourse(nombreCurso));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
