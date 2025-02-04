package ies.castillodeluna.ad.ui;

import java.util.Map;
import java.util.Scanner;

import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.backend.Factory;

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
     * 
     */
    private MetodosUI ui = new MetodosUI();

    /**
     * Constructor que inicializa la UI con las opciones de conexión
     * @param opciones Mapa con las opciones de configuración para la conexión
     */
    public UI(Map<String,Object> opciones) {
        this.conexion = Factory.crearConexion(opciones);
        this.datos = new ObtenerDatos();
    }

    /**
     * 
     */
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
                    ui.guardarCliente(conexion, datos);
                    break;

                case 2:
                    
                    break;
            
                case 3:
                    ui.listaClientes(conexion);
                    break;

                default:
                    System.out.println("Opcion seleccionada no valida");
            }

        }

        sc.close();

    }
}
