package cl.event.app.gestioneventos.controller;

import cl.event.app.gestioneventos.model.Reserva;
import cl.event.app.gestioneventos.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin("*")
public class ReservaController {
    private final ReservasService reservasService;

    @Autowired
    public ReservaController(ReservasService reservasService) {
        this.reservasService = reservasService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Reserva>> obtenerReservas() {
        return ResponseEntity.ok(reservasService.obtenerReservas());
    }

    @GetMapping("/id/{reservaId}")
    public ResponseEntity<Reserva> obtenerReservaPorId(Long reservaId) {
        return ResponseEntity.ok(reservasService.obtenerReserva(reservaId));
    }

    @PostMapping("/")
    public ResponseEntity<Reserva> guardarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservasService.agregarReserva(reserva));
    }

    @PutMapping("/")
    public ResponseEntity<Reserva> actualizarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservasService.actualizarReserva(reserva));
    }

    @DeleteMapping("/{reservaId}")
    public ResponseEntity<ResponseEntity.BodyBuilder> eliminarReservaPorId(@PathVariable("reservaId") Long reservaId) {
        reservasService.eliminarReserva(reservaId);
        return ResponseEntity.ok().build();
    }

}
