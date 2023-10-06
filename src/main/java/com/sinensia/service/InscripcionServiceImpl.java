package com.sinensia.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sinensia.dao.InscripcionDao;
import com.sinensia.dto.CursoDto;
import com.sinensia.exceptions.CursoNotFoundException;
import com.sinensia.exceptions.CursoOutOfPlaces;
import com.sinensia.model.Inscripcion;

/**
 * Nuestra clase @Service
 * 
 * @author Sergio
 * @see com.sinensia.service.InscripcionService
 * @see com.sinensia.model.Inscripcion
 * @see com.sinensia.controller.InscripcionController
 */
@Service
public class InscripcionServiceImpl implements InscripcionService {

	/**
	 * Inyectamos nuestro RestTemplate definido en la clase de configuracion
	 */
	@Autowired
	RestTemplate template;

	/**
	 * Inyectamos el dao que extiende de JpaRepository para poder acceder a metodos
	 * Crud
	 */
	@Autowired
	private InscripcionDao dao;

	private final String URL_CONNECTION = "http://localhost:8080/curso";

	/**
	 * 
	 * @return List
	 */
	@Override
	public List<Inscripcion> getAll() {
		return dao.findAll();
	}

	/**
	 * 
	 * @param inscripcion
	 * @return List
	 */
	@Override
	public List<Inscripcion> save(Inscripcion inscripcion) {
		List<CursoDto> listCursoDto = Arrays.asList(template.getForObject(URL_CONNECTION, CursoDto[].class));

		try {
			CursoDto cursoDto = listCursoDto.stream().filter(c -> c.getIdCurso() == inscripcion.getIdCurso())
					.findFirst().orElseThrow(() -> new CursoNotFoundException("Curso no encontrado"));

			if (cursoDto.getPlazas() <= 0) {
				throw new CursoOutOfPlaces("No quedan plazas para este curso");
			}
			Inscripcion toSaveInscripcion = new Inscripcion(inscripcion.getNombreEstudiante(), inscripcion.getEdad(),
					cursoDto.getIdCurso(), inscripcion.getCalificaciones());

			dao.save(toSaveInscripcion);

			int totalPlazas = cursoDto.getPlazas() - 1;
			cursoDto.setPlazas(totalPlazas);

			template.put(URL_CONNECTION, cursoDto);

		} catch (CursoNotFoundException | CursoOutOfPlaces e) {
			System.out.println("Error " + e);
		}
		return dao.findAll();
	}

	/**
	 * 
	 * @param nombreCurso
	 * @return List
	 */
	@Override
	public List<Inscripcion> getByCourse(String nombreCurso) {
		List<CursoDto> listCursoDto = Arrays.asList(template.getForObject(URL_CONNECTION, CursoDto[].class));

		CursoDto cursoDto = listCursoDto.stream().filter(c -> c.getNombre().equals(nombreCurso)).findFirst()
				.orElseThrow(() -> new CursoNotFoundException("Curso no encontrado"));

		List<Inscripcion> listInscripciones = dao.findAll();

		return listInscripciones.stream().filter(i -> i.getIdCurso() == cursoDto.getIdCurso())
				.collect(Collectors.toList());
	}

}
