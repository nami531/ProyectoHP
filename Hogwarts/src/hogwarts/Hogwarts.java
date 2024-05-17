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

    public Hogwarts() {
        this.alumnos = new ArrayList<>();
        this.profMateria = new HashMap<>(); 
    }
    
    
    public boolean anhadirAlumno(Mago alumno){
        Casa casa = casaAleatoria(); 
        alumno.setCasa(casa);
        if (!(alumnos.contains(alumno))){
            alumnos.add(alumno); 
            casa.anhadirIntegrante(alumno);
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
            System.out.println("Nombre: " + profe.getNombre() + " Materia: " + profMateria.get(profe) ); 
        }
    }

    public ArrayList<Mago> alumnadoCasa(Casa casa){
        ArrayList<Mago> listaAlumnado = new ArrayList<>(); 
        for (Mago alumno : casa.getIntegrantes()) {
            if (alumno instanceof Mago && alumno.getCasa().equals(casa)){
                listaAlumnado.add(alumno); 
            }
        }
        return listaAlumnado; 
    }

    public ArrayList<Persona> getAlumnos() {
        return alumnos;
    }

    public HashMap<Profesor, String> getProfMateria() {
        return profMateria;
    }
    
    
}
