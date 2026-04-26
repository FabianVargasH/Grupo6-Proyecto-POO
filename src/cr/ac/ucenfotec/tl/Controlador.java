package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Controlador {
    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Metodo para registrar el tipo usuario Moderador
    public static void registrarModerador() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro del Moderador --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.readLine();
        System.out.print("Identificacion: ");
        String id = entrada.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac = null;
        try {
            fechaNac = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }
        System.out.print("Contrasena: ");
        String contrasena = entrada.readLine();
        System.out.print("Correo electronico: ");
        String correo = entrada.readLine();

        System.out.println(GestorModerador.registrarModerador(nombre, id, fechaNac, contrasena, correo));
    }

    //Metodo registrar vendedor
    public static void registrarVendedor() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro de Vendedor --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.readLine();
        System.out.print("Identificacion: ");
        String id = entrada.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac = null;
        try {
            fechaNac = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }
        System.out.print("Contrasena: ");
        String contrasena = entrada.readLine();
        System.out.print("Correo electronico: ");
        String correo = entrada.readLine();
        System.out.print("Puntuacion: ");
        double puntuacion = Double.parseDouble(entrada.readLine());
        System.out.print("Direccion: ");
        String direccion = entrada.readLine();

        System.out.println(GestorVendedor.registrarVendedor(nombre, id, fechaNac, contrasena, correo, puntuacion, direccion));
    }
    //Metodo para registrar coleccionista
    public static void registrarColeccionista() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro de Coleccionista --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.readLine();
        System.out.print("Identificacion: ");
        String id = entrada.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac = null;
        try {
            fechaNac = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }
        System.out.print("Contrasena: ");
        String contrasena = entrada.readLine();
        System.out.print("Correo electronico: ");
        String correo = entrada.readLine();
        System.out.print("Puntuacion: ");
        double puntuacion = Double.parseDouble(entrada.readLine());
        System.out.print("Direccion: ");
        String direccion = entrada.readLine();

        System.out.println(GestorColeccionista.registrarColeccionista(nombre, id, fechaNac, contrasena, correo, puntuacion, direccion));
    }

    //Metodo para imprmir el menu de registro de usuario
    public static void registrarUsuario() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Registro de Usuario --");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");
        System.out.print("Seleccione tipo: ");
        int tipo = Integer.parseInt(entrada.readLine());

        if (tipo == 1) {
            registrarVendedor();
        } else if (tipo == 2) {
            registrarColeccionista();
        } else {
            System.out.println("Tipo de usuario invalido.");
        }
    }

    //Metodo para listar los usuarios registrados en la base de datos
    public static void listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-- Listado de Vendedores --");
        GestorVendedor.listarVendedores();
        System.out.println("\n-- Listado de Coleccionistas --");
        GestorColeccionista.listarColeccionistas();
        System.out.println("\n-- Moderador --");
        GestorModerador.listarModerador();
    }

    //Metodo para crear subasta
    public static void crearSubasta() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Creacion de Subasta --");

        System.out.println("\n-- Vendedores disponibles --");
        GestorVendedor.listarVendedores();
        System.out.println("\n-- Coleccionistas disponibles --");
        GestorColeccionista.listarColeccionistas();

        System.out.print("\nIdentificacion del creador: ");
        String idCreador = entrada.readLine().trim();

        Vendedor vendedor = GestorVendedor.buscarVendedor(idCreador);
        Coleccionista coleccionista = null;
        Usuario creador = null;

        if (vendedor != null) {
            creador = vendedor;
        } else {
            coleccionista = GestorColeccionista.buscarColeccionista(idCreador);
            creador = coleccionista;
        }

        if (creador == null) {
            System.out.println("No se encontro un usuario con esa identificacion.");
            return;
        }

        System.out.print("Precio minimo: ");
        double precioMinimo = Double.parseDouble(entrada.readLine());

        System.out.print("Fecha de creacion (dd/MM/yyyy): ");
        LocalDate fechaCreacion = null;
        try {
            fechaCreacion = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        System.out.print("Fecha de cierre (dd/MM/yyyy): ");
        LocalDate fechaCierre = null;
        try {
            fechaCierre = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        System.out.print("Estado: ");
        String estado = entrada.readLine();
        System.out.print("Cuantos objetos desea agregar a la subasta: ");
        int cantidad = Integer.parseInt(entrada.readLine());

        ArrayList<Objetos> objetos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n-- Objeto " + (i + 1) + " --");
            System.out.print("Nombre: ");
            String nombre = entrada.readLine();
            System.out.print("Descripcion: ");
            String descripcion = entrada.readLine();
            System.out.print("Estado (nuevo/usado/antiguo sin abrir): ");
            String estadoObj = entrada.readLine();
            System.out.print("Fecha de compra (dd/MM/yyyy): ");
            LocalDate fechaCompra = null;
            try {
                fechaCompra = LocalDate.parse(entrada.readLine(), FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto.");
                return;
            }
            objetos.add(new Objetos(nombre, descripcion, estadoObj, fechaCompra));
        }

        System.out.println(GestorSubasta.crearSubasta(creador, precioMinimo, fechaCreacion, fechaCierre, estado, objetos));
    }

    //Metodo para listar todas las subastas creadas
    public static void listarSubastas() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-- Listado de Subastas --");
        GestorSubasta.listarSubastas();
    }

    //Metodo para crear una oferta a una subasta
    public static void crearOferta() throws IOException, SQLException, ClassNotFoundException {
        boolean ofertaValida = false;

        while (!ofertaValida) {
            System.out.println("\n-- Creacion de Oferta --");

            System.out.print("ID de la subasta: ");
            int idSubasta = Integer.parseInt(entrada.readLine());

            System.out.print("Identificacion del oferente: ");
            String idOferente = entrada.readLine();

            System.out.print("Precio ofertado: ");
            double precio = Double.parseDouble(entrada.readLine());

            try {
                String resultado = GestorOferta.registrarOferta(idSubasta, idOferente, precio);
                System.out.println(resultado);

                if (resultado.contains("exitosamente")) {
                    ofertaValida = true;
                } else {
                    System.out.println("Error: " + resultado);
                    System.out.print("¿Desea intentar con otro oferente? (s/n): ");
                    String continuar = entrada.readLine();
                    if (!continuar.equalsIgnoreCase("s")) {
                        ofertaValida = true;
                    }
                }
            } catch (SQLException e) {
                String mensaje = e.getMessage();
                if (mensaje.contains("vendedor no puede realizar ofertas")) {
                    System.out.println("Error: Un vendedor no puede realizar ofertas.");
                } else if (mensaje.contains("Solo los coleccionistas pueden realizar ofertas")) {
                    System.out.println("Error: Solo los coleccionistas pueden realizar ofertas.");
                } else if (mensaje.contains("Un coleccionista no puede ofertar en una subasta creada por el mismo")) {
                    System.out.println("Error: Un coleccionista no puede ofertar en su propia subasta.");
                } else {
                    System.out.println("Error inesperado: " + mensaje);
                }

                System.out.print("¿Desea intentar con otro oferente? (s/n): ");
                String continuar = entrada.readLine();
                if (!continuar.equalsIgnoreCase("s")) {
                    ofertaValida = true;
                }
            }
        }
    }

    //Metodo para listar ofertas creadas para subastas especificas
    public static void listarOfertas() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Listado de Ofertas --");
        System.out.print("ID de la subasta: ");
        int idSubasta = Integer.parseInt(entrada.readLine());
        GestorOferta.listarOfertasPorSubasta(idSubasta);
    }

    // Metoodo para listar los objetos que estan en la plataforma (Los subastados)
    public static void listarObjetosEnPlataforma() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-- Objetos ofrecidos en la plataforma --");
        GestorObjeto.listarObjetosEnPlataforma();
    }

    // Metodo para poder adjudicar una subasta
    public static void adjudicarSubasta() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n-- Adjudicacion de Subasta --");
        System.out.print("ID de la subasta a adjudicar: ");
        int idSubasta = Integer.parseInt(entrada.readLine());

        double mejorPrecio = GestorOferta.obtenerMejorOferta(idSubasta);
        if (mejorPrecio == 0) {
            System.out.println("No hay ofertas en esta subasta. No se puede adjudicar.");
            return;
        }

        String ganadorId = GestorOferta.obtenerGanadorSubasta(idSubasta);
        if (ganadorId == null) {
            System.out.println("No se encontro el ganador de la subasta.");
            return;
        }

        System.out.println("Mejor oferta: " + mejorPrecio + " por " + ganadorId);

        System.out.print("¿Está seguro que desea adjudicar esta subasta? (S/N): ");
        String confirmacion = entrada.readLine();

        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada");
            return;
        }

        System.out.println(GestorOrdenAdjudicacion.adjudicarSubasta(idSubasta, ganadorId, mejorPrecio));
    }
}