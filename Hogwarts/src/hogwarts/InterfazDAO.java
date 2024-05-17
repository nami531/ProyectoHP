/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hogwarts;

import java.sql.*; 
import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public interface InterfazDAO{
    public boolean crearAlumno(Persona alumno) throws SQLException; 
    public ArrayList<Persona> devolverAlumnosCasa(Casa casa) throws SQLException; 
    public boolean cambiarMuggleCreeEnMagia(Muggle alumno) throws SQLException; 
    public boolean eliminarProfesor(Mago profesor) throws SQLException; 
    public Persona devolverMuggle(String nombre) throws SQLException; 
    public Profesor devolverProfesor(String nombre) throws SQLException;
}
