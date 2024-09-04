package cl.event.app.gestioneventos.crear;

import cl.event.app.gestioneventos.controller.ReservaController;
import cl.event.app.gestioneventos.model.Reserva;
import cl.event.app.gestioneventos.service.ReservasService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestReservaCrear {

    @Mock
    private ReservasService reservasService;

    @InjectMocks
    private ReservaController reservaController;

    @Test
    void contextLoads() {
    }


    @Test
    void crearReservas(){
        Reserva reserva = new Reserva();
        when(reservasService.agregarReserva(reserva)).thenReturn(reserva);

        ResponseEntity<Reserva> response = reservaController.guardarReserva(reserva);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reserva, response.getBody());

    }

}