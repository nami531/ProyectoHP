/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hogwarts;

import java.sql.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author a21nadiami
 */
public class DAO implements InterfazDAO {

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
        this.contrasenha = "";
    }

    @Override
    public boolean crearAlumno(Persona alumno) throws SQLException {
        String insertarPersona = "INSERT INTO Persona(nombre, fecha_nacimiento) VALUES (?, ?);";
        String insertarMago = "INSERT INTO Mago(persona_id, tipo_escoba, casa_id) VALUES (?, ?, (SELECT id FROM Casa WHERE nombre = ?));";
        String insertarAlumno = "INSERT INTO Alumno(mago_id) VALUES (?);";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha)) {
            conexion.setAutoCommit(false);

            try (PreparedStatement sentenciaPersona = conexion.prepareStatement(insertarPersona, Statement.RETURN_GENERATED_KEYS); PreparedStatement sentenciaMago = conexion.prepareStatement(insertarMago, Statement.RETURN_GENERATED_KEYS); PreparedStatement sentenciaAlumno = conexion.prepareStatement(insertarAlumno)) {

                if (alumno instanceof Mago mago) {
                    // Insertar en Persona
                    sentenciaPersona.setString(1, mago.getNombre());
                    sentenciaPersona.setDate(2, Date.valueOf(mago.getFechaNacimiento()));
                    sentenciaPersona.executeUpdate();

                    // Obtener el ID generado para Persona
                    ResultSet rsPersona = sentenciaPersona.getGeneratedKeys();
                    if (rsPersona.next()) {
                        int personaId = rsPersona.getInt(1);

                        // Insertar en Mago
                        sentenciaMago.setInt(1, personaId);
                        sentenciaMago.setString(2, mago.getTipoEscoba());
                        sentenciaMago.setString(3, mago.getCasa().toString());
                        sentenciaMago.executeUpdate();

                        // Obtener el ID generado para Mago
                        ResultSet rsMago = sentenciaMago.getGeneratedKeys();
                        if (rsMago.next()) {
                            int magoId = rsMago.getInt(1);

                            // Insertar en Alumno
                            sentenciaAlumno.setInt(1, magoId);
                            int filasAfectadas = sentenciaAlumno.executeUpdate();

                            conexion.commit();
                            return filasAfectadas > 0;
                        }
                    }
                }
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Persona> devolverAlumnosCasa(Casa casa) throws SQLException {
        String sql = """
                     SELECT m.tipo_escoba, c.nombre as casa ,p.nombre, p.fecha_nacimiento 
                        FROM Alumno as a
                        join Mago as m 
                              on a.mago_id = m.id
                        join Casa as c 
                              on m.casa_id = c.id
                        left join Persona as p 
                              on m.persona_id = p.id  
                     where c.nombre = ?""";

        ArrayList<Persona> alumnosCasa = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, casa.name());

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Mago m = new Mago(resultado.getString("tipo_escoba"), resultado.getString("nombre"), (resultado.getDate("fecha_nacimiento")).toLocalDate());
                m.setCasa(Casa.valueOf(resultado.getString("casa")));
                alumnosCasa.add(m);
            }
        }
        return alumnosCasa;
    }

    @Override
    public boolean cambiarMuggleCreeEnMagia(Muggle alumno) throws SQLException {
        String sql = """ 
                     UPDATE Muggle
                     SET cree_en_magia = 1
                     WHERE persona_id = (select id from Persona where nombre = ?) """;
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, alumno.getNombre());

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;
        }
    }

    @Override
    public boolean eliminarProfesor(Mago profesor) throws SQLException {
        String getMagoIdSQL = "SELECT m.id FROM Mago m JOIN Persona as p ON m.persona_id = p.id WHERE p.nombre = ?";
        String deleteProfesorSQL = "DELETE FROM Profesor WHERE mago_id = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); PreparedStatement getMagoIdStmt = conexion.prepareStatement(getMagoIdSQL); PreparedStatement deleteProfesorStmt = conexion.prepareStatement(deleteProfesorSQL)) {

            // Obtener el ID del mago basado en el nombre del profesor
            getMagoIdStmt.setString(1, profesor.getNombre());
            try (ResultSet rs = getMagoIdStmt.executeQuery()) {
                if (rs.next()) {
                    int magoId = rs.getInt(1);

                    // Eliminar el profesor usando el ID del mago
                    deleteProfesorStmt.setInt(1, magoId);
                    int filasAfectadas = deleteProfesorStmt.executeUpdate();

                    return filasAfectadas > 0;
                } else {
                    return false; // No se encontró el profesor con ese nombre
                }
            }
        }
    }

    @Override
    public Muggle devolverMuggle(String nombre) throws SQLException {
        Muggle muggle;
        String sql = "select p.nombre, p.fecha_nacimiento from Persona as P join Muggle as m on m.persona_id = p.id where p.nombre = ? ";
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            LocalDate fecha_nac = null; 
            while (resultado.next()){
                fecha_nac = resultado.getDate("fecha_nacimiento").toLocalDate();
            }
            muggle = new Muggle(nombre, fecha_nac);
        }
        return muggle;
    }

    @Override
    public Profesor devolverProfesor(String nombre) throws SQLException {
        Profesor profesor;
        String sql = "select per.nombre, per.fecha_nacimiento, m.tipo_escoba from Persona as per join Mago as m on m.persona_id = per.id join Profesor as p on p.mago_id = m.id where per.nombre = ?";
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha); PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, nombre);
            
            ResultSet resultado = sentencia.executeQuery();
            LocalDate fecha_nac = null; 
            String tipoEscoba = null; 
            while (resultado.next()){
                fecha_nac = resultado.getDate("fecha_nacimiento").toLocalDate();
                tipoEscoba = resultado.getString("tipo_escoba");
            }
            
            profesor = new Profesor(tipoEscoba, nombre, fecha_nac);
        }
        return profesor;
    }

}
