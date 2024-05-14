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

    public Mago(String tipoEscoba, Casa casa, String nombre, LocalDate fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.tipoEscoba = tipoEscoba;
        this.casa = casa;
    }

    @Override
    public boolean esMayorEdad() {
        return super.calcularEdad() >= 17; 
    }
    
    @Override
    public String toString() {
        return super.toString() + "Mago{" + "tipoEscoba=" + tipoEscoba + ", casa=" + casa + '}';
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    
    
    
    
    
}
