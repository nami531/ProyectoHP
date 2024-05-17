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
public class Mago extends Persona {
    private String tipoEscoba; 
    private Casa casa;

    public Mago(String tipoEscoba, String nombre, LocalDate fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.tipoEscoba = tipoEscoba;
    }

    @Override
    public boolean esMayorEdad() {
        return super.calcularEdad() >= 17; 
    }
    
    @Override
    public String toString() {
        return "Mago{" + "nombre=" + getNombre() + ", fechaNacimiento=" + getFechaNacimiento() +  " tipoEscoba=" + tipoEscoba + ", casa=" + casa + '}';
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public String getTipoEscoba() {
        return tipoEscoba;
    }

    
    
    
    
    
}
