package com.ribera.gimnasio.controllers;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.internal.util.compare.CalendarComparator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.ribera.gimnasio.dto.ClaseCalendarDto;
import com.ribera.gimnasio.dto.ClaseDto;
import com.ribera.gimnasio.dto.Mensaje;
import com.ribera.gimnasio.dto.NuevaClase;
import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.security.entity.Usuario;
import com.ribera.gimnasio.security.service.AuthenticatedEmpService;
import com.ribera.gimnasio.security.service.UsuarioService;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.service.IActividadService;
import com.ribera.gimnasio.service.IClaseService;

@RestController
@RequestMapping("/clases")
@CrossOrigin(origins = "*")
public class ClaseController {

	@Autowired
	IClaseService claseService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	IActividadService actividadService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AuthenticatedEmpService authenticatedEmpService;

	@GetMapping("/futuras")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<ClaseDto> getClasesFuturas() throws NoSuchElementException {
		return claseService.getClasesFuturas().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ClaseDto getClasesById(@PathVariable Long id) throws NoSuchElementException, java.text.ParseException {
		return this.convertToDto(claseService.getClaseById(id));
	}

	@GetMapping("/pasadas")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public List<ClaseDto> getClasesPasadas() throws NoSuchElementException {
		try {
			return claseService.getClasesPasadas().stream().map(this::convertToDto).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/user/{username}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<ClaseDto> getClasesByUserName(@PathVariable String username) throws Exception {
		return claseService.getByUser(usuarioService.getByNombreUsuario(username).get()).stream()
				.map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/monitor/{username}")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public List<ClaseDto> getClasesByMonitor(@PathVariable String username) throws Exception {
		return claseService.getByMonitor(usuarioService.getByNombreUsuario(username).get()).stream()
				.map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/calendarDTO/{username}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<ClaseCalendarDto> getClasesCalendarDto(@PathVariable String username) {
		Usuario usuario = usuarioService.getByNombreUsuario(username).get();
		List<Clase> clases = claseService.getAll();

		return clases.stream().map(t -> this.convertToCalendarDto(t, usuario)).collect(Collectors.toList());

	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public ResponseEntity<?> create(@Valid @RequestBody NuevaClase nuevaClase, BindingResult bindingResult)
			throws ServletRequestBindingException, Exception {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		}
		System.out.print(nuevaClase);
		Usuario usu = usuarioService.getById(nuevaClase.getMonitor());
		Actividad actividad = actividadService.getActividadById(nuevaClase.getActividad());
		Clase clase = new Clase();
		clase.setActividad(actividad);
		clase.setMonitor(usu);
		clase.setFechaClase(new Date(nuevaClase.getFechaClase().getTime()));
		clase.setHoraInicio(new Time(nuevaClase.getHoraInicio().getTime()));
		clase.setHoraFin(clase.getAddSubtractTime(clase.getHoraInicio(), clase.getActividad().getDuracion()));
		List<Clase> clasesMonitor = claseService.getClasesByMonitorBetweenDates(clase.getFechaClase(), clase.getHoraInicio(), clase.getHoraFin(), usu);
		clasesMonitor.forEach(t -> System.out.println(t) );
		if (!clasesMonitor.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Ya da una clase en esas horas"),HttpStatus.BAD_REQUEST);
		}
			/* if(claseLista.getFechaClase().toString().equals(clase.getFechaClase().toString()) &&
						(((claseLista.getHoraInicio().after(clase.getHoraInicio()) || claseLista.getHoraInicio().equals(clase.getHoraInicio())) && claseLista.getHoraInicio().before(clase.getHoraFin())) 
								|| ((claseLista.getHoraFin().after(clase.getHoraInicio()) || claseLista.getHoraFin().equals(clase.getHoraInicio())) && claseLista.getHoraFin().before(clase.getHoraFin()))
								)
						) {*/
				//	
				//}
		// }
	
		claseService.save(clase);
		
		return new ResponseEntity<>(new Mensaje("Clase creada"), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')"+"|| (hasRole('ROLE_EMP') && @authenticatedEmpService.isMonitor(#id))")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody NuevaClase clase,
			BindingResult bindingResult) throws NoSuchElementException, ServletRequestBindingException, Exception {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		}
		System.out.println(id);
		Clase claseaux = claseService.getClaseById(id);
		if(clase.getMonitor() != null) {
			List<Clase> clasesMonitor = claseService.getClasesByMonitorBetweenDates(new Date(clase.getFechaClase().getTime()), new Time(clase.getHoraInicio().getTime()), clase.getAddSubtractTime(new Time(clase.getHoraInicio().getTime()), actividadService.getActividadById(clase.getActividad()).getDuracion()),usuarioService.getById( clase.getMonitor()));
			if (!clasesMonitor.isEmpty()) {
				return new ResponseEntity<>(new Mensaje("Ya da una clase en esas horas"),HttpStatus.BAD_REQUEST);
			}
			claseaux.setMonitor(usuarioService.getById(clase.getMonitor()));
		}else {
			List<Clase> clasesMonitor = claseService.getClasesByMonitorBetweenDates(new Date(clase.getFechaClase().getTime()), new Time(clase.getHoraInicio().getTime()), clase.getAddSubtractTime(new Time(clase.getHoraInicio().getTime()), actividadService.getActividadById(clase.getActividad()).getDuracion()),claseaux.getMonitor());
			System.out.println("probando");
			if (!clasesMonitor.isEmpty()) {
				return new ResponseEntity<>(new Mensaje("Ya da una clase en esas horas"),HttpStatus.BAD_REQUEST);
			}
		}
			
		claseaux.setActividad(actividadService.getActividadById(clase.getActividad()));
		claseaux.setFechaClase(new Date(clase.getFechaClase().getTime()));
		claseaux.setHoraInicio(new Time(clase.getHoraInicio().getTime()));
		claseaux.setHoraFin(
				claseaux.getAddSubtractTime(claseaux.getHoraInicio(), claseaux.getActividad().getDuracion()));
		
		
			claseService.save(claseaux);
		
		
		return new ResponseEntity<>(new Mensaje("Clase actualizada"), HttpStatus.CREATED);
	}

	@PutMapping("/asistentes/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> updateAsistentes(@PathVariable Long id, @Valid @RequestBody String username,
			BindingResult bindingResult) throws NoSuchElementException, ServletRequestBindingException, Exception {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		}
		Clase clase = claseService.getClaseById(id);
		
		Set<Usuario> usuarios = clase.getUsuarios();
		Usuario usu = usuarioService.getByNombreUsuario(username).get();
		if (usuarios.contains(usu)) {
			usuarios.remove(usu);
			clase.setUsuarios(usuarios);
			clase.quitarAsistente();
		} else {
			List<Clase> clases = claseService.getClasesBetweenDates(clase.getFechaClase(), clase.getHoraInicio(), clase.getHoraFin());
			clases = clases.stream().filter(t -> t.getUsuarios().contains(usu) ).collect(Collectors.toList());
			if(!clases.isEmpty()) {
				return new ResponseEntity<>(new Mensaje("Ya asistes a una clase en esas horas"), HttpStatus.BAD_REQUEST);
			}
			List<Clase> clasesImp = claseService.getClasesByMonitorBetweenDates(clase.getFechaClase(), clase.getHoraInicio(), clase.getHoraFin(), usu);
			if(!clasesImp.isEmpty()) {
				return new ResponseEntity<>(new Mensaje("No pudes asistir porque estas impartiendo una clase "), HttpStatus.BAD_REQUEST);
			}
			usuarios.add(usu);
			clase.setUsuarios(usuarios);
			clase.anadirAsistente();
		}

		claseService.save(clase);

		return new ResponseEntity<>(new Mensaje("Clase actualizada"), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_EMP')")
	public ResponseEntity<?> delete(@PathVariable Long id) throws NoSuchElementException, ConstraintViolationException, Exception {
		claseService.delete(id);
		return new ResponseEntity<>(new Mensaje("Clase eliminada"), HttpStatus.OK);
	}

	private ClaseDto convertToDto(Clase clase) {
		ClaseDto claseDto = modelMapper.map(clase, ClaseDto.class);
		claseDto.setMonitor(clase.getMonitor().getNombre());
	
		// SimpleDateFormat h = new SimpleDateFormat();
		// claseDto.setHoraFin( new java.util.Date(clase.getHoraFin().getTime()));

		// claseDto.setHoraFin(new java.util.Date(clase.getHoraFin().getTime()));

		return claseDto;
	}

	private ClaseCalendarDto convertToCalendarDto (Clase clase,Usuario usu) {
    	ClaseCalendarDto claseDto= modelMapper.map(clase, ClaseCalendarDto.class);
    	claseDto.setTitle(clase.getActividad().getNombre());
    	claseDto.setStart(clase.getFechaClase(), clase.getHoraInicio());
    	claseDto.setEndStr(clase.getFechaClase(), clase.getHoraFin());
    	claseDto.setBackgroundColor("lightgreen");
    	claseDto.setTextColor("black");
    	if(clase.getMonitor().getId() == usu.getId()) {
    		claseDto.setBackgroundColor("#EBEB04");
    		
    	}else if(clase.getUsuarios().contains(usu)) {
    		claseDto.setBackgroundColor("lightblue");
    	}else if(clase.getNumAsistentes() == clase.getActividad().getNumMaxAsistentes()) {
    		claseDto.setBackgroundColor("#EB3B0B");   		
    	}
    	return claseDto;
    }

	private Clase convertToEntity(ClaseDto claseDto) throws ParseException {
		Clase clase = modelMapper.map(claseDto, Clase.class);

		if (claseDto.getId() != null) {
			Clase oldClase = claseService.getClaseById(claseDto.getId());
			// clase.setRedditID(oldClase.getRedditID());
			// clase.setSent(oldClase.isSent());
		}
		return clase;
	}
}
