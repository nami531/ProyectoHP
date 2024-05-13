/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author a21nadiami
 */
public class Hogwarts {
    private ArrayList<Persona> alumnos; 
    private HashMap<Profesor, String> profMateria; 
    
    public boolean anhadirAlumno(Mago alumno){
        Casa casa = casaAleatoria(); 
    }
    
    private Casa casaAleatoria(){
        Casa[] valoresPosibles = Casa.values(); 
        return valoresPosibles[Random.nextInt(valoresPosibles.length)]; 
    }
}
