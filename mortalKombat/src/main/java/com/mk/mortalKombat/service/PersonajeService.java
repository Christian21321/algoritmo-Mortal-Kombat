package com.mk.mortalKombat.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mk.mortalKombat.model.NivelPoder;
import com.mk.mortalKombat.model.Personaje;
import com.mk.mortalKombat.repository.PersonajeRepository;

public class PersonajeService {

    private final PersonajeRepository repo = new PersonajeRepository();

    public Personaje agregar(String nombre, int saludMaxima, NivelPoder nivel, List<String> movimientos) {
        return repo.guardar(new Personaje(nombre, saludMaxima, nivel, movimientos));
    }

    public Personaje buscarPorId(Long id) {
        return repo.obtenerPorId(id);
    }

    public void eliminar(Long id) {
        repo.eliminar(id);
    }

    public List<Personaje> listarTodos() {
        return new ArrayList<>(repo.obtenerTodos());
    }

    public List<Personaje> filtrarNivelMedioOAlto() {
        return repo.obtenerTodos().stream()
                .filter(p -> p.getNivelPoder() != NivelPoder.BAJO)
                .collect(Collectors.toList());
    }

    public List<Personaje> buscarPorNombre(String subcadena) {
        return repo.obtenerTodos().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(subcadena.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Personaje> ordenarPorSaludDescendente() {
        return repo.obtenerTodos().stream()
                .sorted(Comparator.comparingInt(Personaje::getSaludMaxima).reversed())
                .collect(Collectors.toList());
    }

    public Personaje editar(Long id, String nombre, int salud, NivelPoder nivel, List<String> movimientos) {
        Personaje p = repo.obtenerPorId(id);
        if (p != null) {
            p.setNombre(nombre);
            p.setSaludMaxima(salud);
            p.setNivelPoder(nivel);
            p.setMovimientosEspeciales(movimientos);
        }
        return p;
    }
}
