package ies.castillodeluna.ad.ui;

import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.ZonaEnvio;

public class MetodosUI {
    
    public void guardarCliente(Conexion conexion , ObtenerDatos datos){

        int id = datos.guardarIDCliente();
        String nombre = datos.guardarNombreCliente();
        String correo = datos.guardarCorreoCliente();
        String telefono = datos.guardarTelefonoCliente();

        int id_zona = 1;

        try {
            
            ZonaEnvio zona = conexion.getZonaEnvio().get(id_zona).orElse(null);

            Cliente cliente = new Cliente(id, nombre, correo, telefono, zona);
            conexion.getCliente().insert(cliente);
            
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    public void listaClientes(Conexion conexion){

        try {
            conexion.getCliente().get().forEach(client -> System.out.println(client));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
