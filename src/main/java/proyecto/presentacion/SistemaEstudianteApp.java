package proyecto.presentacion;

import proyecto.Dao.EstudianteDao;
import proyecto.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudianteApp {
    public static void main(String[] args) {

        var salir = false;
        var consola = new Scanner(System.in);

        //Se crea una instancia clase servicio

        var estudianteDao = new EstudianteDao();

        while (!salir){

            try{

                mostrarMenu();
                salir =ejecutarOpciones(consola, estudianteDao);

            }catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar operacion: " +
                        e.getMessage());
            }

        }//fin de while
    }//main

    private static void mostrarMenu(){
        System.out.println("""
                    
                    *** Sistema de Estudiantes *** 
                    1. Listar Estudiantes
                    2. Buscar Estudiante
                    3. Agregar Estudiante
                    4. Modificar Estudiante
                    5. Eliminar Estudiante
                    6. Salir                     
                    
                    Elige una opción: 
                    """);
    }//mostrar nenu

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDao estudianteDao){
        var opcion =Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 ->{
                //listar estudiantes
                System.out.println("Listado de estudiiantes...");
                var estudiantes = estudianteDao.listarEstudiantes();
                estudiantes.forEach(System.out::println);

            }

            case 2-> {
                //buscar estudiane por id
                System.out.println("Introduce el id_estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if (encontrado){

                    System.out.println("Estudiante encontrado: " + estudiante);

                }else {

                    System.out.println("Estudiante No encontrado: " + estudiante);

                }

            }

            case 3 ->{//Agregar estudiante
                System.out.println("Agregar Estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();

                //objeto estudiante

                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);

                if (agregado){
                    System.out.println("Estudiante agregado: " + estudiante);
                }else {
                    System.out.println("Estudiante NO agregado: " + estudiante);
                }
            }

            case 4 ->{//modificar
                System.out.println("Modificar Estudiante: ");

                System.out.println("Id Estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();

                //crear el objeto estudiante a modificar

                var estudiante = new Estudiante(idEstudiante, nombre,apellido,telefono,email);

                var modificado = estudianteDao.modificarEstudiante(estudiante);

                if (modificado){
                    System.out.println("Estudiante modificado: " + estudiante);
                }else {
                    System.out.println("Estudiante NO modificado: " + estudiante);
                }
            }
            case 5->{
                //eliminar estudiante

                System.out.println("Eliminar Estudiante: ");
                System.out.println("Id Estudiante: ");

                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminarEstudiante(estudiante);

                if (eliminado){
                    System.out.println("Estudiante Eliminado: " + estudiante);
                }else {
                    System.out.println("Estudiante NO eliminado " + estudiante);
                }

            }

            case 6 ->{
                //Salir
                System.out.println("Hasta Pronto! ");
                salir = true;

            }
            default -> System.out.println("Opcion no proporcionado");
            
        }
        return salir;
    }//ejecutar opciones

}