package com.ribera.gimnasio.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ribera.gimnasio.dto.Monitor;
import com.ribera.gimnasio.entity.Actividad;
import com.ribera.gimnasio.entity.Clase;
import com.ribera.gimnasio.security.entity.Usuario;

public interface IClaseRepository extends CrudRepository<Clase, Long> {

	//public Optional<Clase> findByNombre(String nombre);
	public List<Clase> findByUsuarios(Usuario usuario);
	public List<Clase> findByMonitor(Usuario usuario);
	@Query("select c from Clase c where c.fechaClase > :fechaClase or (c.fechaClase= :fechaClase and c.horaFin >= :horaFin)")
	public List<Clase> findByFechaClaseAndHoraInicioAfter(@Param("fechaClase") Date fechaClase, @Param("horaFin") Time horaInicio);
	@Query("select c from Clase c where (c.fechaClase > :fechaClase or (c.fechaClase= :fechaClase and c.horaFin >= :horaFin)) and c.monitor=:monitor")
	public List<Clase> findByMonitorWithFechaClaseAndHoraInicioAfter(@Param("fechaClase") Date fechaClase, @Param("horaFin") Time horaInicio,
			@Param("monitor") Usuario usuario);
	@Query("select c from Clase c where (c.fechaClase > :fechaClase or (c.fechaClase= :fechaClase and c.horaFin >= :horaFin)) and c.actividad=:actividad")
	public List<Clase> findByActividadWithFechaClaseAndHoraInicioAfter(@Param("fechaClase") Date fechaClase, @Param("horaFin") Time horaInicio,
			@Param("actividad") Actividad actividad);
	@Query("select c from Clase c where c.fechaClase <= :fechaClase and c.horaFin < :horaFin")
	public List<Clase> findByFechaClaseAndHoraInicioBefore(@Param("fechaClase") Date fechaClase, @Param("horaFin") Time horaInicio);
	@Query("select c from Clase c where (c.fechaClase  = :fechaClase and c.monitor = :monitor and ((c.horaInicio >= :horaInicio and c.horaInicio < :horaFin) or (c.horaFin >= :horaInicio and c.horaFin < :horaFin)))")
	public List<Clase>  findByMonitorBetween(@Param("fechaClase") Date fechaClase, @Param("horaInicio") Time horaInicio, @Param("horaFin") Time HoraFin, @Param("monitor") Usuario monitor);
	@Query("select c from Clase c where (c.fechaClase  = :fechaClase and ((c.horaInicio >= :horaInicio and c.horaInicio < :horaFin) or (c.horaFin >= :horaInicio and c.horaFin < :horaFin)))")
	public List<Clase> findAllBetween(@Param("fechaClase") Date fechaClase, @Param("horaInicio") Time horaInicio, @Param("horaFin") Time HoraFin);
	public void deleteByActividad(Actividad actividad);
	public void deleteByMonitor(Usuario usuario);
}
