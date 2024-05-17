/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hogwarts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*; 
import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hogwarts";
        String usuario = "root";
        String contrasenha = "password";
        DAO dao = new DAO(url, usuario);
        Hogwarts hogwarts = new Hogwarts();

        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
       
            System.out.println("Qué quieres hacer: ");
            System.out.println("1. Crear Alumno");
            System.out.println("2. Listar Alumnos por Casa");
            System.out.println("3. Cambiar Creencia en Magia de un Muggle");
            System.out.println("4. Eliminar Profesor");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 ->
                    crearAlumno(scanner, dao);

                case 2->
                    listarAlumnosPorCasa(scanner, dao);

                case 3->
                    cambiarCreenciaMuggle(scanner,dao);

                case 4->
                    eliminarProfesor(scanner,dao);
                case 5->
                    seguir = false;
                default->
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }

}
    private static void crearAlumno(Scanner scanner, DAO dao) {
            System.out.println("Ingrese nombre del alumno: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese fecha de nacimiento (yyyy-MM-dd): ");
            String fechaNac = scanner.nextLine();
            LocalDate fechaNacimiento = LocalDate.parse(fechaNac, DateTimeFormatter.ISO_DATE);
            System.out.println("Ingrese tipo de escoba: ");
            String tipoEscoba = scanner.nextLine();
            System.out.println("Ingrese casa (GRYFFINDOR, HUFFLEPUFF, RAVENCLAW, SLYTHERIN): ");
            String c = scanner.nextLine();
            Casa casa = Casa.valueOf(c.toUpperCase());

            Mago mago = new Mago(tipoEscoba , nombre, fechaNacimiento);
            mago.setCasa(casa);

            try {
                if (dao.crearAlumno(mago)) {
                    System.out.println("Alumno creado exitosamente.");
                } else {
                    System.out.println("Error al crear el alumno.");
                }
            } catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            }
        }

    private static void listarAlumnosPorCasa(Scanner scanner, DAO dao) {
        System.out.println("Ingresa la casa (GRYFFINDOR, HUFFLEPUFF, RAVENCLAW, SLYTHERIN): ");
        String casaStr = scanner.nextLine();
        Casa casa = Casa.valueOf(casaStr.toUpperCase());

        try {
            ArrayList<Persona> alumnos = dao.devolverAlumnosCasa(casa);
            System.out.println("Alumnos de la casa " + casa + ":");
            for (Persona alumno : alumnos) {
                System.out.println(alumno);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    private static void cambiarCreenciaMuggle(Scanner scanner, DAO dao) {
        System.out.print("Ingresa nombre del Muggle: ");
        String nombre = scanner.nextLine();

        try {
            Muggle muggle = dao.devolverMuggle(nombre); 
            if (dao.cambiarMuggleCreeEnMagia(muggle)) {
                System.out.println("Estado de creencia en magia actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el estado de creencia en magia.");
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    private static void eliminarProfesor(Scanner scanner, DAO dao) {
        System.out.print("Ingrese nombre del Profesor: ");
        String nombre = scanner.nextLine();

        try {
            Profesor profesor = dao.devolverProfesor(nombre); 
            if (dao.eliminarProfesor(profesor)) {
                System.out.println("Profesor eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el Profesor.");
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
    
    
}
