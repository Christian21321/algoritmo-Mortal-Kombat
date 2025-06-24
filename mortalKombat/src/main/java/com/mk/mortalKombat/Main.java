package com.mk.mortalKombat;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.mk.mortalKombat.model.NivelPoder;
import com.mk.mortalKombat.model.Personaje;
import com.mk.mortalKombat.service.PersonajeService;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PersonajeService service = new PersonajeService();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> agregarPersonaje();
                case 2 -> editarPersonaje();
                case 3 -> eliminarPersonaje();
                case 4 -> listarTodos();
                case 5 -> filtrarPorNivel();
                case 6 -> buscarPorNombre();
                case 7 -> ordenarPorSalud();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("""
                \n--- Mortal Kombat CLI ---
                1. Agregar personaje
                2. Editar personaje
                3. Eliminar personaje
                4. Listar todos los personajes
                5. Filtrar por nivel MEDIO o ALTO
                6. Buscar por nombre
                7. Ordenar por salud descendente
                0. Salir
                Selecciona una opción:""");
    }

    private static void agregarPersonaje() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Salud máxima: ");
        int salud = Integer.parseInt(scanner.nextLine());

        NivelPoder nivel = leerNivelPoder();

        System.out.print("Movimientos especiales (separados por coma): ");
        List<String> movimientos = Arrays.stream(scanner.nextLine().split(","))
                                         .map(String::trim)
                                         .toList();

        Personaje p = service.agregar(nombre, salud, nivel, movimientos);
        System.out.println("Personaje agregado con ID: " + p.getId());
    }

    private static void editarPersonaje() {
        System.out.print("ID del personaje a editar: ");
        long id = Long.parseLong(scanner.nextLine());

        Personaje existente = service.buscarPorId(id);
        if (existente == null) {
            System.out.println("No se encontró el personaje.");
            return;
        }

        System.out.print("Nuevo nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva salud máxima [" + existente.getSaludMaxima() + "]: ");
        int salud = Integer.parseInt(scanner.nextLine());

        NivelPoder nivel = leerNivelPoder();

        System.out.print("Nuevos movimientos (separados por coma): ");
        List<String> movimientos = Arrays.stream(scanner.nextLine().split(","))
                                         .map(String::trim)
                                         .toList();

        service.editar(id, nombre, salud, nivel, movimientos);
        System.out.println("Personaje actualizado.");
    }

    private static void eliminarPersonaje() {
        System.out.print("ID del personaje a eliminar: ");
        long id = Long.parseLong(scanner.nextLine());
        service.eliminar(id);
        System.out.println("Personaje eliminado.");
    }

    private static void listarTodos() {
        List<Personaje> lista = service.listarTodos();
        lista.forEach(Main::imprimirPersonaje);
    }

    private static void filtrarPorNivel() {
        List<Personaje> filtrados = service.filtrarNivelMedioOAlto();
        filtrados.forEach(Main::imprimirPersonaje);
    }

    private static void buscarPorNombre() {
        System.out.print("Subcadena a buscar en nombre: ");
        String subcadena = scanner.nextLine();
        List<Personaje> resultados = service.buscarPorNombre(subcadena);
        resultados.forEach(Main::imprimirPersonaje);
    }

    private static void ordenarPorSalud() {
        List<Personaje> ordenados = service.ordenarPorSaludDescendente();
        ordenados.forEach(Main::imprimirPersonaje);
    }

    private static NivelPoder leerNivelPoder() {
        System.out.print("Nivel de poder (BAJO, MEDIO, ALTO): ");
        return NivelPoder.valueOf(scanner.nextLine().toUpperCase());
    }

    private static void imprimirPersonaje(Personaje p) {
        System.out.println("ID: " + p.getId() +
                " | Nombre: " + p.getNombre() +
                " | Salud: " + p.getSaludMaxima() +
                " | Nivel: " + p.getNivelPoder() +
                " | Movimientos: " + String.join(", ", p.getMovimientosEspeciales()));
    }
}
