package cl.event.app.gestioneventos.listar;

import cl.event.app.gestioneventos.controller.ReservaController;
import cl.event.app.gestioneventos.model.Reserva;
import cl.event.app.gestioneventos.service.ReservasService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestReservaObtener {

    @Mock
    private ReservasService reservasService;

    @InjectMocks
    private ReservaController reservaController;

    @Test
    void contextLoads() {
    }

    @Test
    void obtenerReservasReturnsListOfReservas() {
        List<Reserva> reservas = List.of(new Reserva(), new Reserva());
        when(reservasService.obtenerReservas()).thenReturn(reservas);

        ResponseEntity<List<Reserva>> response = reservaController.obtenerReservas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
    }

    @Test
    void obtenerReservasReturnsEmptyListWhenNoReservas() {
        when(reservasService.obtenerReservas()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Reserva>> response = reservaController.obtenerReservas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }


}