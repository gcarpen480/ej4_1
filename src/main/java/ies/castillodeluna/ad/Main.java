package ies.castillodeluna.ad;

import java.util.Map;

import ies.castillodeluna.ad.backend.CrearConexion;
import ies.castillodeluna.ad.ui.UI;

public class Main {
    public static void main(String[] args) {
        try {
            
            System.out.println("Iniciando aplicación....");

            Map<String, Object> opciones = CrearConexion.opciones();
            System.out.println("Configuración obtenida, iniciando UI...");
            
            UI ui = new UI(opciones);
            ui.menu();
            
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}