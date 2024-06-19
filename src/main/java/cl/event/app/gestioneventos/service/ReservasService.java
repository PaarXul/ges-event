package cl.event.app.gestioneventos.service;

import cl.event.app.gestioneventos.model.Reserva;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservasService {

    Reserva agregarReserva(Reserva reserva);
    Reserva actualizarReserva(Reserva reserva);
    void eliminarReserva(Long id);
    Reserva obtenerReserva(Long id);
    List<Reserva> obtenerReservas();
}
