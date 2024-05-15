/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.sql.*;

import java.sql.Date;
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
    public boolean crearAlumno(Persona alumno) throws SQLException {
        String sql = """
                    INSERT INTO persona(nombre, fecha_nacimiento) VALUES (?, ?);
                    SET FOREIGN_KEY_CHECKS = 0;
                    INSERT INTO mago(persona_id, tipo_escoba, casa_id) VALUES (
                        (SELECT AUTO_INCREMENT AS last_ai
                         FROM INFORMATION_SCHEMA.TABLES
                         WHERE TABLE_SCHEMA = 'hogwarts' AND TABLE_NAME = 'persona'),
                         ?, 
                         (SELECT id FROM casa WHERE nombre = ?)
                    );
                    INSERT INTO alumno(mago_id) VALUES (
                        SELECT AUTO_INCREMENT AS last_ai
                        FROM INFORMATION_SCHEMA.TABLES
                        WHERE TABLE_SCHEMA = 'hogwarts' AND TABLE_NAME = 'mago'
                    );
                    SET FOREIGN_KEY_CHECKS = 1;
                    """; 
        
//        String bdInicialmente = "SET FOREIGN_KEY_CHECKS = 1;";  
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); 
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            if (alumno instanceof Mago mago){
                sentencia.setString(1, mago.getNombre());
                sentencia.setDate(2, Date.valueOf(mago.getFechaNacimiento()));
                sentencia.setString(3, mago.getTipoEscoba());
                sentencia.setString(4, (mago.getCasa().name()));
            }                      
            
            int filasAfectadas = sentencia.executeUpdate(sql); 
            return filasAfectadas > 0; 
        }
        
    }

    @Override
    public ArrayList<Persona> devolverAlumnosCasa(Casa casa) throws SQLException {
        String sql = """
                     SELECT m.tipo_escoba, c.nombre as casa ,p.nombre, p.fecha_nacimiento 
                     FROM alumno as a
                     join mago as m 
                     	on a.mago_id = m.id
                     join casa as c 
                     	on m.casa_id = c.id
                     left join persona as p 
                     	on m.persona_id = p.id and 
                     where c.nombre = ?""" ; 
        
        ArrayList<Persona> alumnosCasa = new ArrayList<>(); 
        
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); 
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, casa.name());
            
            ResultSet resultado = sentencia.executeQuery(); 
            while (resultado.next()){
                Mago m = new Mago(resultado.getString("tipo_escoba"), Casa.valueOf(resultado.getString("casa")), resultado.getString("nombre"), (resultado.getDate("fecha_nacimiento")).toLocalDate()); 
                alumnosCasa.add(m); 
            }
        } return alumnosCasa;
    }

    @Override
    public boolean cambiarMuggleCreeEnMagia(Muggle alumno) throws SQLException{
        String sql = "UPDATE muggle" +
                     "SET cree_en_magia = 1" +
                     "WHERE persona_id = (select id from persona where nombre = ?)";
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); 
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, alumno.getNombre()); 
            
            int filasAfectadas = sentencia.executeUpdate(); 
            
            return filasAfectadas > 0; 
        }
    }

    @Override
    public boolean eliminarProfesor(Mago profesor) throws SQLException{
        String sql = "DELETE from profesor where nombre = ?"; 
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); 
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, profesor.getNombre()); 
            
            int filasAfectadas = sentencia.executeUpdate(); 
            
            return filasAfectadas > 0; 
        }
    }
    
    
    
}
