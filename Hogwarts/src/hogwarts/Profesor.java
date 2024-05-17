/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author a21nadiami
 */
public class Profesor extends Mago implements Docente{
    private static String[] frases = {"No es bueno dejarse arrastrar por los sueños y olvidar vivir", "La valentía siempre es la mejor opción", "La felicidad se puede encontrar incluso en los momentos más oscuros, si uno recuerda encender la luz", "Por el bien común", "Son nuestras elecciones las que muestran quienes somos realmente, mucho más que nuestras habilidades"};

    public Profesor(String tipoEscoba, String nombre, LocalDate fechaNacimiento) {
        super(tipoEscoba, nombre, fechaNacimiento);
    }
    
    @Override
    public void imponerDisciplina() {
        System.out.println("Silencio Totalis");
    }

    @Override
    public void explicar() {
        Random r = new Random(); 
        int frase = r.nextInt(frases.length - 1); 
        System.out.println(frases[frase]);
    }
    
    
}
