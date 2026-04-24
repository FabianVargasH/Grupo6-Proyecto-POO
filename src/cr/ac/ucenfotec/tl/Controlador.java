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
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //  USUARIOS

    public static void registrarModerador() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n--- Registro del Moderador ---");
        String[] datos = leerDatosUsuario();
        LocalDate fechaNac = leerFechaDesdeString(datos[2]);
        if (fechaNac == null) { System.out.println("Fecha invalida."); return; }
        System.out.println(GestorModerador.registrarModerador(datos[0], datos[1], fechaNac, datos[3], datos[4]));
    }

    public static void registrarUsuario() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\n--- Registro de usuario ---");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");
        System.out.print("Seleccione tipo: ");
        int tipo = leerEntero();
        String[] datos = leerDatosUsuario();
        LocalDate fechaNac = leerFechaDesdeString(datos[2]);
        if (fechaNac == null) { System.out.println("Fecha invalida."); return; }
        System.out.print("Puntuacion: ");
        double puntuacion = leerDecimal();
        System.out.print("Direccion: ");
        String direccion = entrada.readLine();

        if (tipo == 1) {
            System.out.println(GestorVendedor.registrarVendedor(
                    datos[0], datos[1], fechaNac, datos[3], datos[4], puntuacion, direccion));
        } else if (tipo == 2) {
            String resultado = GestorColeccionista.registrarColeccionista(
                    datos[0], datos[1], fechaNac, datos[3], datos[4], puntuacion, direccion);
            System.out.println(resultado);
            if (resultado.contains("adecuadamente")) {
                System.out.print("Desea agregar intereses? (s/n): ");
                if (entrada.readLine().equalsIgnoreCase("s")) {
                    System.out.print("Cuantos intereses desea agregar: ");
                    int cantidad = leerEntero();
                    for (int i = 0; i < cantidad; i++) {
                        System.out.print("Interes " + (i + 1) + ": ");
                        System.out.println(GestorColeccionista.agregarInteres(datos[1], entrada.readLine()));
                    }
                }
            }
        } else {
            System.out.println("Tipo de usuario invalido.");
        }
    }

    public static void listarUsuarios() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n--- Listado de usuarios ---");
        System.out.println("\n-- Vendedores --");
        GestorVendedor.listarVendedores();
        System.out.println("\n-- Coleccionistas --");
        GestorColeccionista.listarColeccionistas();
        System.out.println("\n-- Moderador --");
        GestorModerador.listarModerador();
    }

    //  SUBASTAS

    public static void crearSubasta(ArrayList<Usuario> usuarios)
            throws IOException, SQLException, ClassNotFoundException {
        if (usuarios.isEmpty()) { System.out.println("No hay usuarios registrados."); return; }
        System.out.println("\n--- Creacion de subasta ---");
        System.out.println("Seleccione el creador:");
        for (int i = 0; i < usuarios.size(); i++)
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombreCompleto() +
                    " (" + usuarios.get(i).getTipoUsuario() + ")");
        System.out.print("Opcion: ");
        int index = leerEntero() - 1;
        if (index < 0 || index >= usuarios.size()) { System.out.println("Opcion invalida."); return; }
        Usuario creador = usuarios.get(index);

        System.out.print("Precio minimo: ");
        double precioMinimo = leerDecimal();
        System.out.print("Fecha de creacion (dd/MM/yyyy): ");
        LocalDate fechaCreacion = leerFecha();
        System.out.print("Fecha de cierre (dd/MM/yyyy): ");
        LocalDate fechaCierre = leerFecha();
        System.out.print("Estado: ");
        String estado = entrada.readLine();

        ArrayList<Objetos> objetos = new ArrayList<>();
        if (creador.getTipoUsuario().equals("COLECCIONISTA")) {
            Coleccionista col = (Coleccionista) creador;
            ArrayList<Objetos> propios = col.getObjetosPropiedad();
            if (propios.isEmpty()) { System.out.println("El coleccionista no tiene objetos registrados."); return; }
            System.out.println("\nObjetos disponibles:");
            for (int i = 0; i < propios.size(); i++)
                System.out.println((i + 1) + ". " + propios.get(i).getNombre());
            System.out.print("Cuantos objetos desea agregar: ");
            int cantidad = leerEntero();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Seleccione objeto " + (i + 1) + ": ");
                int idx = leerEntero() - 1;
                if (idx >= 0 && idx < propios.size()) objetos.add(propios.get(idx));
            }
        } else {
            System.out.print("Cuantos objetos desea agregar: ");
            int cantidad = leerEntero();
            for (int i = 0; i < cantidad; i++) {
                System.out.println("\n-- Objeto " + (i + 1) + " --");
                System.out.print("Nombre: "); String nombre = entrada.readLine();
                System.out.print("Descripcion: "); String descripcion = entrada.readLine();
                System.out.print("Estado (nuevo/usado/antiguo sin abrir): "); String estadoObj = entrada.readLine();
                System.out.print("Fecha de compra (dd/MM/yyyy): "); LocalDate fechaCompra = leerFecha();
                objetos.add(new Objetos(nombre, descripcion, estadoObj, fechaCompra));
            }
        }
        System.out.println(GestorSubasta.crearSubasta(creador, precioMinimo, fechaCreacion, fechaCierre, estado, objetos));
    }

    public static void listarSubastas(ArrayList<Subasta> subastas) {
        if (subastas.isEmpty()) { System.out.println("No hay subastas registradas."); return; }
        System.out.println("\n--- Listado de subastas ---");
        for (Subasta s : subastas) System.out.println(s.toString());
    }

    //  OFERTAS

    public static void crearOferta(ArrayList<Usuario> usuarios, ArrayList<Subasta> subastas)
            throws IOException, SQLException, ClassNotFoundException {
        if (subastas.isEmpty() || usuarios.isEmpty()) {
            System.out.println("Debe haber subastas y usuarios registrados."); return;
        }
        System.out.println("\n--- Creacion de oferta ---");
        System.out.println("Seleccione la subasta:");
        for (int i = 0; i < subastas.size(); i++)
            System.out.println((i + 1) + ". Creador: " + subastas.get(i).getCreador().getNombreCompleto() +
                    " | Estado: " + subastas.get(i).getEstado());
        System.out.print("Opcion: ");
        int idxSubasta = leerEntero() - 1;
        if (idxSubasta < 0 || idxSubasta >= subastas.size()) { System.out.println("Opcion invalida."); return; }
        Subasta subasta = subastas.get(idxSubasta);

        System.out.println("Seleccione el oferente:");
        for (int i = 0; i < usuarios.size(); i++)
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombreCompleto() +
                    " (" + usuarios.get(i).getTipoUsuario() + ")");
        System.out.print("Opcion: ");
        int idxOferente = leerEntero() - 1;
        if (idxOferente < 0 || idxOferente >= usuarios.size()) { System.out.println("Opcion invalida."); return; }
        Usuario oferente = usuarios.get(idxOferente);

        System.out.print("Precio ofertado: ");
        double precio = leerDecimal();
        System.out.println(GestorOferta.registrarOferta(subasta.getId(), oferente.getIdentificacion(), precio));
    }

    public static void listarOfertas(ArrayList<Subasta> subastas) {
        boolean hayOfertas = false;
        System.out.println("\n--- Listado de ofertas ---");
        for (int i = 0; i < subastas.size(); i++) {
            ArrayList<Oferta> ofertas = subastas.get(i).getOferta();
            if (!ofertas.isEmpty()) {
                hayOfertas = true;
                System.out.println("\nSubasta " + (i + 1) + " | Creador: " +
                        subastas.get(i).getCreador().getNombreCompleto() +
                        " | Estado: " + subastas.get(i).getEstado());
                for (Oferta o : ofertas) System.out.println(o.toString());
            }
        }
        if (!hayOfertas) System.out.println("No hay ofertas registradas.");
    }

    //  OBJETOS Y ADJUDICACIÓN

    public static void listarObjetosEnPlataforma(ArrayList<Subasta> subastas) {
        boolean hayObjetos = false;
        System.out.println("\n--- Objetos ofrecidos en la plataforma ---");
        for (Subasta subasta : subastas) {
            if (subasta.getEstado().startsWith("Adjudicada")) {
                System.out.println("\n[" + subasta.getEstado() + "]");
                System.out.println("Creador: " + subasta.getCreador().getNombreCompleto() +
                        " (" + subasta.getCreador().getTipoUsuario() + ")");
                hayObjetos = true;
            } else {
                ArrayList<Objetos> objetos = subasta.getObjetos();
                if (!objetos.isEmpty()) {
                    hayObjetos = true;
                    System.out.println("\nSubastado por: " + subasta.getCreador().getNombreCompleto() +
                            " (" + subasta.getCreador().getTipoUsuario() + ")");
                    for (Objetos obj : objetos)
                        System.out.println("  - " + obj.getNombre() + " | Estado: " + obj.getEstado() +
                                " | Precio minimo: " + subasta.getPrecioMinimo());
                }
            }
        }
        if (!hayObjetos) System.out.println("No hay objetos ofrecidos actualmente en la plataforma.");
    }

    public static void adjudicarSubasta(ArrayList<Subasta> subastas, ArrayList<Usuario> usuarios)
            throws IOException, SQLException, ClassNotFoundException {
        if (subastas.isEmpty()) { System.out.println("No hay subastas registradas."); return; }
        System.out.println("\n--- Adjudicacion de subasta ---");
        System.out.println("Seleccione la subasta a adjudicar:");
        for (int i = 0; i < subastas.size(); i++)
            System.out.println((i + 1) + ". Creador: " + subastas.get(i).getCreador().getNombreCompleto() +
                    " | Estado: " + subastas.get(i).getEstado());
        System.out.print("Opcion: ");
        int idx = leerEntero() - 1;
        if (idx < 0 || idx >= subastas.size()) { System.out.println("Opcion invalida."); return; }
        System.out.println(GestorOrdenAdjudicacion.adjudicarSubasta(
                subastas.get(idx).getId(), subastas.get(idx), usuarios));
    }

    //  MÉTODOS AUXILIARES

    private static String[] leerDatosUsuario() throws IOException {
        System.out.print("Nombre completo: "); String nombre = entrada.readLine();
        System.out.print("Identificacion: "); String id = entrada.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): "); String fecha = entrada.readLine();
        System.out.print("Contrasena: "); String contrasena = entrada.readLine();
        System.out.print("Correo electronico: "); String correo = entrada.readLine();
        return new String[]{nombre, id, fecha, contrasena, correo};
    }

    private static LocalDate leerFechaDesdeString(String fecha) {
        try { return LocalDate.parse(fecha, formatter); }
        catch (DateTimeParseException e) { return null; }
    }

    private static int leerEntero() throws IOException {
        while (true) {
            try { return Integer.parseInt(entrada.readLine()); }
            catch (NumberFormatException e) { System.out.print("Error: Ingrese un numero valido: "); }
        }
    }

    private static double leerDecimal() throws IOException {
        while (true) {
            try { return Double.parseDouble(entrada.readLine()); }
            catch (NumberFormatException e) { System.out.print("Error: Ingrese un numero valido: "); }
        }
    }

    private static LocalDate leerFecha() throws IOException {
        while (true) {
            try { return LocalDate.parse(entrada.readLine(), formatter); }
            catch (DateTimeParseException e) { System.out.print("Error: Formato invalido (dd/MM/yyyy): "); }
        }
    }
}
