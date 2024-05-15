/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package hogwarts;

import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public enum Casa {
    GRYFFINDOR,
    HUFFLEPUFF,
    RAVENCLAW,
    SLYTHERIN; 
    
    private ArrayList<Mago> integrantes = new ArrayList<>(); 
    
    Casa(){}
    
    public void anhadirIntegrante(Mago alumno){
        integrantes.add(alumno); 
    }
    
    public ArrayList<Mago> getIntegrantes(){
        return integrantes; 
    }
    
}
