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
public class Muggle extends Persona {
    
    private boolean creeEnMagia; 

    public Muggle(String nombre, LocalDate fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.creeEnMagia = false; 
    }

    public Muggle(boolean creeEnMagia, String nombre, LocalDate fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.creeEnMagia = creeEnMagia;
    }

    @Override
    public String toString() {
        return super.toString() +  "Muggle{" + "creeEnMagia=" + creeEnMagia + '}';
    }
    /*Duda: Debe tener un método toString() para mostrar toda la información, como Persona y como Muggle.*/

    @Override
    public boolean esMayorEdad() {
        return super.calcularEdad() > 18; 
    }
    
}
