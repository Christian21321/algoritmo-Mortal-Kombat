package com.mk.mortalKombat.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mk.mortalKombat.model.Personaje;

public class PersonajeRepository {
    private final Map<Long, Personaje> personajes = new HashMap<>();

    public Personaje guardar(Personaje p) {
        personajes.put(p.getId(), p);
        return p;
    }

    public Personaje obtenerPorId(Long id) {
        return personajes.get(id);
    }

    public void eliminar(Long id) {
        personajes.remove(id);
    }

    public Collection<Personaje> obtenerTodos() {
        return personajes.values();
    }
}
