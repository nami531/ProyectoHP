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
    SLYTHERIN, 
    GRYFFINDOR(ArrayList<Mago> integrantes),
    HUFFLEPUFF(ArrayList<Mago> integrantes),
    RAVENCLAW(ArrayList<Mago> integrantes),
    SLYTHERIN(ArrayList<Mago> integrantes); 
    
    public ArrayList<Mago> integrantes; 
    
    Casa(){
        
    }
    
    Casa(ArrayList<Mago> integrantes){
        this.integrantes = integrantes; 
    }
    
}
