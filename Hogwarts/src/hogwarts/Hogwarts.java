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
        alumno.setCasa(casa);
        if (!(alumnos.contains(alumno))){
            alumnos.add(alumno); 
            return true; 
        } else {
            return false; 
        }
    }
    
    public boolean graduar(Mago alumno){
        if (alumnos.contains(alumno)){
            alumnos.remove(alumno); 
            return true; 
        } else {
            return false; 
        }
        
    }
    
    /*Comprobar si ya esta asignado*/
    public boolean asignarProfesor(String materia, Profesor profe){
        profMateria.put(profe, materia); 
        return false;
        
    }
    
    private Casa casaAleatoria(){
        Casa[] valoresPosibles = Casa.values(); 
        Random r = new Random(); 
        int valorAleatorio = r.nextInt(valoresPosibles.length);
        return valoresPosibles[valorAleatorio]; 
    }
    
    private void listarProfesores(){
        for (Profesor profe : profMateria.keySet() ) {
            profMateria[profe]; 
        }
    }
}
