package com.ribera.gimnasio.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ribera.gimnasio.dto.ActividadDto;
import com.ribera.gimnasio.dto.ClaseDto;
import com.ribera.gimnasio.dto.Mensaje;
import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.enums.CategoriaActividad;
import com.ribera.gimnasio.enums.DificultadActividad;
import com.ribera.gimnasio.service.IActividadService;
import com.ribera.gimnasio.service.IClaseService;

@RestController
@RequestMapping("/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {

	@Autowired
	private IActividadService actividadService;
	@Autowired
	private IClaseService claseService;
	@Autowired
    private ModelMapper modelMapper;
	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<ActividadDto> getActividades() throws NoSuchElementException {
		return actividadService.getActividades().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ActividadDto getActividadesById(@PathVariable Long id )throws NoSuchElementException {
		System.out.println("prueba"+id);
		return this.convertToDto(actividadService.getActividadById(id)); 
	}
	@GetMapping("/nombre/{nombre}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ActividadDto getActividadesByNombre(@PathVariable String nombre )throws NoSuchElementException {
		
		return this.convertToDto(actividadService.getActividadByNombre(nombre));
	}
	@GetMapping("/categorias")
	@PreAuthorize("hasRole('ROLE_USER')")
	public CategoriaActividad[] getCategorias() throws NoSuchElementException {
		return CategoriaActividad.values();
	}
	@GetMapping("/dificultades")
	@PreAuthorize("hasRole('ROLE_USER')")
	public DificultadActividad[] getDificultades() throws NoSuchElementException {
		return DificultadActividad.values();
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> create(@Valid @RequestBody Actividad actividad, BindingResult bindingResult) 
			{
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
		}
		if(actividadService.existsByNombre(actividad.getNombre())) {
			return new ResponseEntity<>(new Mensaje("Ya existe una actividad con este nombre "),HttpStatus.BAD_REQUEST);
		}
		System.out.println("probando");
		System.out.println(actividad);
		actividadService.save(actividad);
		return new ResponseEntity<>(new Mensaje("Actividad creada"),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Actividad actividad,  BindingResult bindingResult)
			throws NoSuchElementException,ServletRequestBindingException, Exception {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"),HttpStatus.BAD_REQUEST);
		}
		Actividad actividadaux = actividadService.getActividadById(id);
		actividadaux.setNombre(actividad.getNombre());
		actividadaux.setCategoria(actividad.getCategoria());
		actividadaux.setDificultad(actividad.getDificultad());
		actividadaux.setDuracion(actividad.getDuracion());
		actividadaux.setNumMaxAsistentes(actividad.getNumMaxAsistentes());
		actividadService.save(actividadaux);
		return new ResponseEntity<>(new Mensaje("Actividad actualizada"),HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) throws NoSuchElementException , ConstraintViolationException ,Exception{
		Actividad actividad = actividadService.getActividadById(id);
		if(claseService.getClasesFuturasByActividad(actividad).isEmpty()) {
			claseService.deleteByActividad(actividad);
		}
		actividadService.delete(id);
		return new ResponseEntity<>(new Mensaje("Actividad eliminada"),HttpStatus.OK);
	}
	 private ActividadDto convertToDto(Actividad actividad) {
	        ActividadDto actividadDto = modelMapper.map(actividad, ActividadDto.class);
	      
	   
	        return actividadDto;
	    }
}

