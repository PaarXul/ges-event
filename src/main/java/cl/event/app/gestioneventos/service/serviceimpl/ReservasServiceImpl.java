package cl.event.app.gestioneventos.service.serviceimpl;


import cl.event.app.gestioneventos.model.Reserva;
import cl.event.app.gestioneventos.repository.ReservasRepository;
import cl.event.app.gestioneventos.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservasServiceImpl implements ReservasService {

    private final ReservasRepository reservasRepository;

    @Autowired
    public ReservasServiceImpl(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }

    @Override
    public Reserva agregarReserva(Reserva reserva) {
        return reservasRepository.save(reserva);
    }


    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        Reserva reservaActual = reservasRepository.findById(reserva.getId()).orElse(null);
        if (reservaActual != null) {
            reservaActual.setNombres(reserva.getNombres());
            reservaActual.setApellidos(reserva.getApellidos());
            reservaActual.setCorreo(reserva.getCorreo());
            reservaActual.setTelefono(reserva.getTelefono());
            reservaActual.setFecha(reserva.getFecha());
            reservaActual.setHora(reserva.getHora());
            reservaActual.setLugar(reserva.getLugar());
            reservaActual.setHabitacion(reserva.getHabitacion());
            reservaActual.setTipo(reserva.getTipo());
            reservaActual.setEstado(reserva.getEstado());
            return reservasRepository.save(reservaActual);
        }
        return null;
    }

    @Override
    public void eliminarReserva(Long id) {
        reservasRepository.deleteById(id);

    }

    @Override
    public Reserva obtenerReserva(Long id) {

        return reservasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reserva> obtenerReservas() {
        return reservasRepository.findAll();

    }
}
