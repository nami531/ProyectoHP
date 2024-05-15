/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hogwarts;

import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public interface InterfazDAO {
    public boolean crearAlumno(Persona alumno); 
    public ArrayList<Persona> devolverAlumnosCasa(Casa casa); 
    public boolean CambiarMuggleCreeEnMagia(Muggle alumno); 
    public boolean EliminarProfesor(Mago profesor); 
}
