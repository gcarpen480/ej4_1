package ies.castillodeluna.ad;

import java.util.Map;

import ies.castillodeluna.ad.backend.CrearConexion;
import ies.castillodeluna.ad.ui.UI;

public class Main {
    public static void main(String[] args) {
        
        Map<String , Object> opciones = CrearConexion.opciones();
        UI ui = new UI(opciones);
        ui.menu();

    }
}