package com.mk.mortalKombat.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.mk.mortalKombat.model.NivelPoder;
import com.mk.mortalKombat.model.Personaje;

public class PersonajeServiceTest {

@Test
public void testCrearPersonaje() {
        Personaje p = new Personaje("Scorpion", 100, NivelPoder.ALTO, List.of("Spear", "Teleport Punch"));
        assertEquals("Scorpion", p.getNombre());
        assertEquals(100, p.getSaludMaxima());
        assertEquals(NivelPoder.ALTO, p.getNivelPoder());
        assertTrue(p.getMovimientosEspeciales().contains("Spear"));
}


@Test
public void testAgregarYBuscarPorId() {
    PersonajeService service = new PersonajeService();
    Personaje p = service.agregar("Sub-Zero", 90, NivelPoder.MEDIO, List.of("Freeze", "Slide"));
    assertNotNull(service.buscarPorId(p.getId()));
}

@Test
public void testFiltrarNivelMedioOAlto() {
    PersonajeService service = new PersonajeService();
    service.agregar("Baraka", 80, NivelPoder.BAJO, List.of("Blade Swipe"));
    service.agregar("Raiden", 110, NivelPoder.ALTO, List.of("Lightning"));
    service.agregar("Sonya", 95, NivelPoder.MEDIO, List.of("Drone Drop"));

    List<Personaje> filtrados = service.filtrarNivelMedioOAlto();
    assertEquals(2, filtrados.size());
}

@Test
public void testBuscarPorNombre() {
    PersonajeService service = new PersonajeService();
    service.agregar("Kano", 90, NivelPoder.MEDIO, List.of("Eye Laser"));
    service.agregar("Kabal", 100, NivelPoder.MEDIO, List.of("Tornado Spin"));
    
    List<Personaje> resultado = service.buscarPorNombre("ka");
    assertEquals(2, resultado.size()); // ambos contienen "ka" ignorando mayúsculas
}

@Test
public void testOrdenarPorSaludDescendente() {
    PersonajeService service = new PersonajeService();
    service.agregar("Mileena", 90, NivelPoder.ALTO, List.of("Sai Throw"));
    service.agregar("Jade", 105, NivelPoder.MEDIO, List.of("Boomerang"));
    service.agregar("Shang Tsung", 95, NivelPoder.ALTO, List.of("Soul Steal"));

    List<Personaje> ordenados = service.ordenarPorSaludDescendente();
    assertEquals("Jade", ordenados.get(0).getNombre());
    assertEquals("Shang Tsung", ordenados.get(1).getNombre());
    assertEquals("Mileena", ordenados.get(2).getNombre());
}


}
