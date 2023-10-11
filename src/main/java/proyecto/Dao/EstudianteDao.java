package proyecto.Dao;

import jdk.jfr.Percentage;
import proyecto.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static proyecto.conexion.Conexion.getConexion;

//Dao - Data Access Object

public class EstudianteDao {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "select * from estudiante order by id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());

        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion:" + e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "Select * from estudiante where id_estudiante = ? ";
        try{

            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;

            }

        }catch(Exception e ){
            System.out.println("Ocurrio un error al buscar estudiante" + e.getMessage());

        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexion :" + e.getMessage());

            }

        }
        return  false;

    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "Insert into estudiante(nombre,apellido, telefono, email)"+
                "values(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.execute();
            return true;


        }catch (Exception e){
            System.out.println("Error al agregar estudiante: " +e.getMessage());

        }
        finally {
            try{
                con.close();
            }catch (Exception e ){
                System.out.println("Error al cerrar conexion: " + e.getMessage());

            }
        }
        return false;

    }

    public static void main(String[] args) {
        var estudianteDao = new EstudianteDao();
        //agragar
        var nuevoEstudiante = new Estudiante("Carlos","Lara","5578465522","c@mail.com");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado){
            System.out.println("Estudiante aregado: " + nuevoEstudiante);
        }else {
            System.out.println("No se agrego el estudiiante: " + nuevoEstudiante);

        }


        //Listar los estudiiantes
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        /*
        //buscar
        var estudiante1 = new Estudiante(2);
        System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if (encontrado){
            System.out.println("Estudiante encontrado: " + estudiante1);
        }else {
            System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());

        }
*/
    }

}
