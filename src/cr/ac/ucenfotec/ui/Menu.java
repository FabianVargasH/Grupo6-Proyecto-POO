package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Menu {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void mostrarMenu(ArrayList<Usuario>usuarios, ArrayList<Subasta>subastas, ArrayList<OrdenAdjudicacion> ordenes)throws IOException{
        verificarModerador(usuarios);

        int opcion;
        do{
            System.out.println("\n--Plataforma de Subastas--");
            System.out.println("1. Registro de usuarios");
            System.out.println("2. Listado de usuarios");
            System.out.println("3. Creacion de subastas");
            System.out.println("4. Listado de subastas");
            System.out.println("5. Creación de ofertas");
            System.out.println("6. Listado de ofertas");
            System.out.println("7. Adjudicacion de subastas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                opcion = -1;
                continue;
            }

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
                case 7:
                    adjudicarSubasta(subastas, ordenes);
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

    private void verificarModerador(ArrayList<Usuario> usuarios) throws IOException{
        boolean existeModerador = false;
        for(Usuario usuario : usuarios){
            if(usuario.getTipoUsuario().equals("MODERADOR")){
                existeModerador = true;
                break;
            }
        }
        if(!existeModerador){
            System.out.println("\n=== REGISTRO OBLIGATORIO DEL MODERADOR ===");
            System.out.println("No existe un moderador registrado en el sistema.");
            System.out.println("Debe registrar un moderador para acceder a la plataforma.\n");
            registrarModerador(usuarios);
        }
    }

    private void registrarModerador(ArrayList<Usuario> usuarios) throws IOException{
        System.out.println("\n--Registro del Moderador--");
        System.out.print("Nombre completo: ");
        String nombre = br.readLine();
        System.out.print("Identificacion: ");
        String id = br.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac;
        try {
            fechaNac = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy.");
            return;
        }
        System.out.print("Contrasena: ");
        String contrasena = br.readLine();
        System.out.print("Correo electronico: ");
        String correo = br.readLine();
        Moderador moderador = new Moderador(nombre, id, fechaNac, contrasena, correo);
        if(moderador.calcularEdad() < 18){
            System.out.println("El moderador debe ser mayor de 18 años");
            return;
        }
        usuarios.add(moderador);
        System.out.println("\nModerador registrado exitosamente.");
    }

    private void registrarUsuario(ArrayList<Usuario> usuarios) throws IOException {
        System.out.println("\n--Registro de usuario--");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");
        System.out.print("Seleccione tipo: ");
        int tipo;
        try {
            tipo = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }
        System.out.print("Nombre completo: ");
        String nombre = br.readLine();
        System.out.print("Identificacion: ");
        String id = br.readLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        LocalDate fechaNac;
        try {
            fechaNac = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy.");
            return;
        }
        System.out.print("Contrasena: ");
        String contrasena = br.readLine();
        System.out.print("Correo electronico: ");
        String correo = br.readLine();
        if (tipo == 1) {
            Vendedor vendedor = new Vendedor(nombre, id, fechaNac, contrasena, correo, 0, "");
            if(vendedor.calcularEdad() < 18){
                System.out.println("El vendedor debe ser mayor de 18 años");
                return;
            }
            System.out.print("Puntuacion: ");
            double puntuacion;
            try {
                puntuacion = Double.parseDouble(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: La puntuación debe ser un número decimal.");
                return;
            }
            System.out.print("Direccion: ");
            String direccion = br.readLine();
            vendedor.setPuntuacion(puntuacion);
            vendedor.setDireccion(direccion);
            usuarios.add(vendedor);
            System.out.println("Vendedor registrado de manera exitosa");
        } else if (tipo == 2) {
            Coleccionista coleccionista = new Coleccionista(nombre, id, fechaNac, contrasena, correo, 0, "");
            if(coleccionista.calcularEdad() < 18){
                System.out.println("El coleccionista debe ser mayor de 18 años");
                return;
            }
            System.out.print("Puntuacion: ");
            double puntuacion;
            try {
                puntuacion = Double.parseDouble(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: La puntuación debe ser un número decimal.");
                return;
            }
            System.out.print("Direccion: ");
            String direccion = br.readLine();
            coleccionista.setPuntuacion(puntuacion);
            coleccionista.setDireccion(direccion);
            usuarios.add(coleccionista);
            System.out.println("Coleccionista registrado de manera exitosa");
        }
    }

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
        int indexCreador;
        try {
            indexCreador = Integer.parseInt(br.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }
        if(indexCreador < 0 || indexCreador >= usuarios.size()){
            System.out.println("Opcion invalida");
            return;
        }
        Usuario creador = usuarios.get(indexCreador);
        if(creador.getTipoUsuario().equals("MODERADOR")){
            System.out.println("El moderador no puede crear subastas");
            return;
        }
        System.out.print("Precio minimo: ");
        double precioMinimo;
        try {
            precioMinimo = Double.parseDouble(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: El precio mínimo debe ser un número decimal.");
            return;
        }
        System.out.print("Fecha de creacion (dd/MM/yyyy): ");
        LocalDate fechaCreacion;
        try {
            fechaCreacion = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy.");
            return;
        }
        System.out.print("Fecha de cierre (dd/MM/yyyy): ");
        LocalDate fechaCierre;
        try {
            fechaCierre = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy.");
            return;
        }
        System.out.print("Estado: ");
        String estado = br.readLine();
        Subasta subasta = new Subasta(creador, precioMinimo, fechaCreacion, fechaCierre, estado);
        if(creador.getTipoUsuario().equals("COLECCIONISTA")){
            Coleccionista coleccionista = (Coleccionista) creador;
            ArrayList<Objetos> objetosPropiedad = coleccionista.getObjetosPropiedad();
            if(objetosPropiedad.isEmpty()){
                System.out.println("El coleccionista no tiene objetos en su colección");
                return;
            }
            System.out.println("\nObjetos disponibles del coleccionista:");
            for(int i = 0; i < objetosPropiedad.size(); i++){
                System.out.println((i+1) + ". " + objetosPropiedad.get(i).getNombre());
            }
            System.out.print("Cuantos objetos desea agregar a la subasta: ");
            int cantidad;
            try {
                cantidad = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                return;
            }
            for(int i = 0; i < cantidad; i++){
                System.out.print("Seleccione el objeto " + (i+1) + ": ");
                int indexObjeto;
                try {
                    indexObjeto = Integer.parseInt(br.readLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número entero válido.");
                    i--;
                    continue;
                }
                if(indexObjeto >= 0 && indexObjeto < objetosPropiedad.size()){
                    subasta.agregarObjeto(objetosPropiedad.get(indexObjeto));
                } else {
                    System.out.println("Opcion invalida");
                    i--;
                }
            }
        } else if(creador.getTipoUsuario().equals("VENDEDOR")){
            System.out.println("Los vendedores aun no pueden agregar objetos a las subastas");
            return;
        }
        if(subasta.getObjetos().isEmpty()){
            System.out.println("No se puede crear una subasta sin objetos");
            return;
        }
        subastas.add(subasta);
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
        int indexSubasta;
        try {
            indexSubasta = Integer.parseInt(br.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }
        if (indexSubasta < 0 || indexSubasta >= subastas.size()) {
            System.out.println("Opcion invalida");
            return;
        }
        System.out.println("Seleccione el oferente:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombreCompleto() + " (" + usuarios.get(i).getTipoUsuario() + ")");
        }
        System.out.print("Opcion: ");
        int indexOferente;
        try {
            indexOferente = Integer.parseInt(br.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }
        if (indexOferente < 0 || indexOferente >= usuarios.size()) {
            System.out.println("Opcion invalida");
            return;
        }
        Usuario oferente = usuarios.get(indexOferente);
        Subasta subasta = subastas.get(indexSubasta);
        if (!oferente.getTipoUsuario().equals("COLECCIONISTA")) {
            System.out.println("Solo un coleccionista puede realizar ofertas.");
            return;
        }
        if(subasta.getCreador().getIdentificacion().equals(oferente.getIdentificacion())){
            System.out.println("Un coleccionista no puede ofertar en una subasta creada por el mismo.");
            return;
        }
        System.out.print("Precio ofertado: ");
        double precio;
        try {
            precio = Double.parseDouble(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: El precio ofertado debe ser un número decimal.");
            return;
        }
        Oferta oferta = new Oferta(oferente.getNombreCompleto(), oferente.getPuntuacion(), precio);
        subasta.agregarOferta(oferta);
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

    private void adjudicarSubasta(ArrayList<Subasta> subastas, ArrayList<OrdenAdjudicacion> ordenes) throws IOException{
        if(subastas.isEmpty()){
            System.out.println("No hay subastas registradas");
            return;
        }
        System.out.println("\n--Adjudicacion de Subasta--");
        System.out.println("Seleccione la subasta a adjudicar:");
        for(int i = 0; i < subastas.size(); i++){
            ArrayList<Oferta> ofertas = subastas.get(i).getOferta();
            System.out.println((i+1) + ". Creador: " + subastas.get(i).getCreador().getNombreCompleto());
            if(ofertas.isEmpty()){
                System.out.println("   Sin ofertas");
            } else {
                System.out.println("   Mejor oferta: " + ofertas.get(0).getPrecioOfertado());
            }
        }
        System.out.print("Opcion: ");
        int indexSubasta;
        try {
            indexSubasta = Integer.parseInt(br.readLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            return;
        }
        if(indexSubasta < 0 || indexSubasta >= subastas.size()){
            System.out.println("Opcion invalida");
            return;
        }
        Subasta subasta = subastas.get(indexSubasta);
        ArrayList<Oferta> ofertas = subasta.getOferta();
        if(ofertas.isEmpty()){
            System.out.println("No hay ofertas en esta subasta");
            return;
        }
        Oferta mejorOferta = ofertas.get(0);
        for(Oferta oferta : ofertas){
            if(oferta.getPrecioOfertado() > mejorOferta.getPrecioOfertado()){
                mejorOferta = oferta;
            }
        }
        OrdenAdjudicacion orden = new OrdenAdjudicacion(mejorOferta.getNombreOferente(), LocalDate.now(), mejorOferta.getPrecioOfertado());
        ordenes.add(orden);
        System.out.println("\nSubasta adjudicada exitosamente");
        System.out.println(orden.toString());
    }
}
