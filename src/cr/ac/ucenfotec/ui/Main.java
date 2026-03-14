package cr.ac.ucenfotec.ui;
import java.util.ArrayList;
import cr.ac.ucenfotec.bl.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Subasta> subastas = new ArrayList<>();
        Menu menu = new Menu();
        menu.mostrarMenu(usuarios, subastas);
    }
}