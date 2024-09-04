package cl.event.app.gestioneventos.suitess;

import cl.event.app.gestioneventos.crear.TestReservaCrear;
import cl.event.app.gestioneventos.editar.TestReservaEditar;
import cl.event.app.gestioneventos.eliminar.TestReservaEliminar;
import cl.event.app.gestioneventos.listar.TestReservaObtener;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestReservaEditar.class,
        TestReservaCrear.class,
        TestReservaEliminar.class,
        TestReservaObtener.class,
})
public class Alltests {

}