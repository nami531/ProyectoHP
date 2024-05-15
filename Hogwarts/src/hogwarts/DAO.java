/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public class DAO implements InterfazDAO{
    private String url; 
    private String usuario; 
    private String contrasenha; 

    public DAO(String url, String usuario, String contrasenha) {
        this.url = url;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
    }

    public DAO(String url, String usuario) {
        this.url = url;
        this.usuario = usuario;
    }

    @Override
    public boolean crearAlumno(Persona alumno) {
        String sql = "INSERT INTO persona VALUES (?,?); "
                + "INSERT INTO mago VALUES (ID/*SOLUCIONAR*/, ?, ? );"
                + "INSERT INTO alumno VALUES (ID, ID_PERSONA)"; 
        
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); 
                PreparedStatement sentencia = conexion.prepareStatement(sql)){
            SET 
        }
    }

    @Override
    public ArrayList<Persona> devolverAlumnosCasa(Casa casa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean CambiarMuggleCreeEnMagia(Muggle alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean EliminarProfesor(Mago profesor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
