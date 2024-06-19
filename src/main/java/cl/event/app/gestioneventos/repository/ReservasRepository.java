package cl.event.app.gestioneventos.repository;


import cl.event.app.gestioneventos.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
}
