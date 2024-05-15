/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

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
        return true;         
    }
    
    private Casa casaAleatoria(){
        Casa[] valoresPosibles = Casa.values(); 
        Random r = new Random(); 
        int valorAleatorio = r.nextInt(valoresPosibles.length);
        return valoresPosibles[valorAleatorio]; 
    }
    
    public void listarProfesores(){
        for (Profesor profe : profMateria.keySet() ) {
            System.out.println("Nombre: " + profe + " Materia: " + profMateria.get(profe) ); 
        }
    }
    
    /*Esto es si solo nos pidiese los alumnos actuales*/
    public ArrayList<Persona> alumnadoCasa(Casa casa){
        ArrayList<Persona> listaAlumnado = new ArrayList<>(); 
        for (Persona alumno : alumnos) {
            /*Importante el operador bit a bit &&; Primero se evalúua que el alumno
            es un mago, si esto es verdadero, continuamos y comprobamos que la casa
            del alumno coincida o no con la que pasamos*/
            if (alumno instanceof Mago && ((Mago)alumno).getCasa().equals(casa)){
                listaAlumnado.add(alumno); 
            }
        }
        return listaAlumnado; 
    }
    
}
