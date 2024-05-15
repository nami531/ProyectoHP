/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.time.LocalDate;

/**
 *
 * @author a21nadiami
 */
public abstract class Persona {
    private String nombre; 
    private LocalDate fechaNacimiento; 

    public Persona(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public abstract boolean esMayorEdad(); 
    
    protected int calcularEdad(){
        return LocalDate.now().getYear() - fechaNacimiento.getYear();  
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    
    
}
