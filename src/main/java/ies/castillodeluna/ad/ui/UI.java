package ies.castillodeluna.ad.ui;

import java.util.Map;
import java.util.Scanner;

import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.backend.Factory;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Clase que implementa la interfaz de usuario del sistema
 */
public class UI {
    
    /** 
     * Conexión a la base de datos
    */
    private Conexion conexion;

    /**
     * Objeto para obtener datos del usuario
     */
    private ObtenerDatos datos;

    /**
     * Constructor que inicializa la UI con las opciones de conexión
     * @param opciones Mapa con las opciones de configuración para la conexión
     */
    public UI(Map<String,Object> opciones) {
        this.conexion = Factory.crearConexion(opciones);
        this.datos = new ObtenerDatos();
    }

    public void menu(){

        Scanner sc = new Scanner(System.in);
        boolean salir = true;

        while (salir) {
            
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar pedido");
            System.out.println("3. Listar clientes");

            int seleccion = sc.nextInt();
            sc.nextLine();

            switch (seleccion) {
                case 1:
                    guardarCliente();
                    break;

                case 2:
                    
                    break;
            
                case 3:
                    listaClientes();
                    break;

                default:
                    System.out.println("Opcion seleccionada no valida");
            }

        }

        sc.close();

    }

    private void guardarCliente(){

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

    private void listaClientes(){

        try {
            conexion.getCliente().get().forEach(client -> System.out.println(client));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
