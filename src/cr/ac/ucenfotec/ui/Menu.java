package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Menu {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void mostrarMenu(ArrayList<Usuario>usuarios, ArrayList<Subasta>subastas)throws IOException{
        int opcion;
        do{
            System.out.println("\n--Plataforma de Subastas--");
            System.out.println("1. Registro de usuarios");
            System.out.println("2. Listado de usuarios");
            System.out.println("3. Creacion de subastas");
            System.out.println("4. Listado de subastas");
            System.out.println("5. Creación de ofertas");
            System.out.println("6. Listado de ofertas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion){
                case 1:
                    registrarUsuario(usuarios);
                    break;
                case 2:
                    listarUsuarios(usuarios);
                    break;
                case 3:
                    crearSubasta(usuarios, subastas);
                    break;
                case 4:
                    listarSubastas(subastas);
                    break;
                case 5:
                    crearOferta(usuarios,subastas);
                    break;
                case 6:
                    listarOfertas(subastas);
                    break;
                case 0:
                    System.out.println("Saliendo del menu..");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while(opcion != 0);
    }

    //Metodo para registrar un usuario
    private void registrarUsuario(ArrayList<Usuario> usuarios) throws IOException {
        System.out.println("\n--Registro de usuario--");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");
        System.out.println("3. Moderador");
        System.out.print("Seleccione tipo: ");
        int tipo = Integer.parseInt(br.readLine());

        System.out.print("Nombre completo: ");
        String nombre = br.readLine();
        System.out.print("Identificacion: ");
        String id = br.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Contrasena: ");
        String contrasena = br.readLine();
        System.out.print("Correo electronico: ");
        String correo = br.readLine();

        if (tipo == 1) {
            System.out.print("Puntuacion: ");
            double puntuacion = Double.parseDouble(br.readLine());
            System.out.print("Direccion: ");
            String direccion = br.readLine();
            usuarios.add(new Vendedor(nombre, id, fechaNac, contrasena, correo, puntuacion, direccion));
        } else if (tipo == 2) {
            System.out.print("Puntuacion: ");
            double puntuacion = Double.parseDouble(br.readLine());
            System.out.print("Direccion: ");
            String direccion = br.readLine();
            usuarios.add(new Coleccionista(nombre, id, fechaNac, contrasena, correo, puntuacion, direccion));
        } else {
            usuarios.add(new Moderador(nombre, id, fechaNac, contrasena, correo));
        }
        System.out.println("\nUsuario registrado de manera exitosa");
    }

    //Metodo para listar los usuarios registrados
    private void listarUsuarios(ArrayList<Usuario>usuarios){
        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("\n--Listado de usuarios--");
        for (int i = 0; i<usuarios.size();i++){
            System.out.println(usuarios.get(i).toString());
        }
    }

    //Metodo para crear subastas
    private void crearSubasta(ArrayList<Usuario> usuarios, ArrayList<Subasta>subastas)throws IOException{
        if(usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados");
            return;
        }
        System.out.println("\n--Creacion de subasta--");
        System.out.println("Seleccione el creador:");
        for (int i = 0; i<usuarios.size(); i++){
            System.out.println((i+1) + ". " + usuarios.get(i).getNombreCompleto() + " (" + usuarios.get(i).getTipoUsuario()+ ")");
        }
        System.out.print("Opcion: ");
        int indexCreador = Integer.parseInt(br.readLine()) - 1;
        if(indexCreador < 0 || indexCreador >= usuarios.size()){
            System.out.println("Opcion invalida");
            return;
        }
        Usuario creador = usuarios.get(indexCreador);

        System.out.print("Precio minimo: ");
        double precioMinimo = Double.parseDouble(br.readLine());
        System.out.print("Fecha de creacion (dd/MM/yyyy): ");
        LocalDate fechaCreacion = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Fecha de cierre (dd/MM/yyyy): ");
        LocalDate fechaCierre = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Estado: ");
        String estado = br.readLine();

        subastas.add(new Subasta(creador, precioMinimo, fechaCreacion, fechaCierre, estado));
        System.out.println("\nSubasta creada de manera exitosa");
    }

    private void listarSubastas(ArrayList<Subasta>subastas){
        if(subastas.isEmpty()){
            System.out.println("No hay subastas registradas.");
            return;
        }
        System.out.println("\n--Listado de Subastas--");
        for (int i = 0; i< subastas.size(); i++){
            System.out.println(subastas.get(i).toString());
        }
    }

    private void crearOferta(ArrayList<Usuario> usuarios, ArrayList<Subasta> subastas) throws IOException {
        if (subastas.isEmpty()) {
            System.out.println("No hay subastas registradas");
            return;
        }
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("\n--Creacion de oferta--");
        System.out.println("Seleccione la subasta:");
        for (int i = 0; i < subastas.size(); i++) {
            System.out.println((i + 1) + ". Creador: " + subastas.get(i).getCreador().getNombreCompleto() + "\nPrecio minimo: " + subastas.get(i).getPrecioMinimo());
        }
        System.out.print("Opcion: ");
        int indexSubasta = Integer.parseInt(br.readLine()) - 1;

        if (indexSubasta < 0 || indexSubasta >= subastas.size()) {
            System.out.println("Opcion invalida");
            return;
        }
        System.out.println("Seleccione el oferente:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombreCompleto() + " (" + usuarios.get(i).getTipoUsuario() + ")");
        }
        System.out.print("Opcion: ");
        int indexOferente = Integer.parseInt(br.readLine()) - 1;

        if (indexOferente < 0 || indexOferente >= usuarios.size()) {
            System.out.println("Opcion invalida");
            return;
        }
        Usuario oferente = usuarios.get(indexOferente);
        if (!oferente.getTipoUsuario().equals("COLECCIONISTA")) {
            System.out.println("Solo un coleccionista puede realizar ofertas.");
            return;
        }

        System.out.print("Precio ofertado: ");
        double precio = Double.parseDouble(br.readLine());
        Oferta oferta = new Oferta(oferente.getNombreCompleto(), oferente.getPuntuacion(), precio);
        subastas.get(indexSubasta).agregarOferta(oferta);
        System.out.println("\nOferta registrada de manera exitosa");
    }

    private void listarOfertas(ArrayList<Subasta> subastas){
        boolean hayOfertas = false;
        System.out.println("\n--Listado de ofertas--");
        for (int i = 0; i<subastas.size(); i++){
            ArrayList<Oferta> ofertas = subastas.get(i).getOferta();
            if(!ofertas.isEmpty()){
                hayOfertas = true;
                System.out.println("\nSubasta " + (i+1) + "\nCreador: " + subastas.get(i).getCreador().getNombreCompleto());
                for (int j = 0; j < ofertas.size(); j++){
                    System.out.println(ofertas.get(j).toString());
                }
            }
        }
        if(!hayOfertas){
            System.out.println("No hay ofertas registradas");
        }
    }
}