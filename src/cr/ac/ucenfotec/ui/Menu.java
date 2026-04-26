package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.logic.*;
import cr.ac.ucenfotec.tl.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Menu {

    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static void mostrarMenu() throws IOException, SQLException, ClassNotFoundException {
        verificarModerador();
        int opcion;
        do {
            System.out.println("\n--Plataforma de Subastas--");
            System.out.println("1. Registro de usuarios");
            System.out.println("2. Listado de usuarios");
            System.out.println("3. Creacion de subastas");
            System.out.println("4. Listado de subastas");
            System.out.println("5. Creacion de ofertas");
            System.out.println("6. Listado de ofertas");
            System.out.println("7. Objetos ofrecidos en la plataforma");
            System.out.println("8. Adjudicacion de subastas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    Controlador.registrarUsuario();
                    break;
                case 2:
                    Controlador.listarUsuarios();
                    break;
                case 3:
                    Controlador.crearSubasta();
                    break;
                case 4:
                    Controlador.listarSubastas();
                    break;
                case 5:
                    Controlador.crearOferta();
                    break;
                case 6:
                    Controlador.listarOfertas();
                    break;
                case 7:
                    Controlador.listarObjetosEnPlataforma();
                    break;
                case 8:
                    Controlador.adjudicarSubasta();
                    break;
                case 0:
                    System.out.println("Saliendo del menu...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void verificarModerador() throws IOException, SQLException, ClassNotFoundException {
        if (!GestorModerador.existeModerador()) {
            System.out.println("\n=== REGISTRO OBLIGATORIO DEL MODERADOR ===");
            System.out.println("No existe un moderador registrado. Debe registrar uno para continuar.\n");
            Controlador.registrarModerador();
        }
    }

    private static int leerEntero() throws IOException {
        while (true) {
            try {
                return Integer.parseInt(entrada.readLine());
            } catch (NumberFormatException e) {
                System.out.print("Error: Ingrese un numero valido: ");
            }
        }
    }
}