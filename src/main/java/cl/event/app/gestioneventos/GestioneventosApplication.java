package cl.event.app.gestioneventos;

import cl.event.app.gestioneventos.model.Reserva;
import cl.event.app.gestioneventos.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioneventosApplication implements CommandLineRunner {

	@Autowired
	private ReservasService reservasService;

	public static void main(String[] args) {
		SpringApplication.run(GestioneventosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			///Insertar datos de prueba Reserva
			Reserva reserva = new Reserva();
			reserva.setNombres("Gian");
			reserva.setApellidos("Parra");
			reserva.setCorreo("gian@mail.com");
			reserva.setTelefono("123456789");
			reserva.setFecha("2024-10-10");
			reserva.setHora("10:00");
			reserva.setLugar("Santiago");
			reserva.setHabitacion("Habitacion 1");
			reserva.setTipo("Matrimonial");
			reserva.setEstado("Pendiente");

			reservasService.agregarReserva(reserva);


			Reserva reserva2 = new Reserva();
			reserva2.setNombres("Juan");
			reserva2.setApellidos("Perez");
			reserva2.setCorreo("juan@mail.com");
			reserva2.setTelefono("987654321");
			reserva2.setFecha("2024-10-10");
			reserva2.setHora("10:00");
			reserva2.setLugar("Santiago");
			reserva2.setHabitacion("Habitacion 2");
			reserva2.setTipo("Simple");
			reserva2.setEstado("Pendiente");

			reservasService.agregarReserva(reserva2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
