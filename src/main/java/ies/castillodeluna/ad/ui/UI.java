package ies.castillodeluna.ad.ui;

import java.util.Map;
import java.util.Scanner;

import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.backend.Factory;
import ies.castillodeluna.ad.backend.hibernate.HibernateUtil;

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
     * Metodo que muestra y gestiona el menú principal
     */
    public void menu(){

        Scanner sc = new Scanner(System.in);
        boolean salir = true;

        while (salir) {
            
            System.out.println("········· MENU ········· ");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar pedido");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Borrar cliente");
            System.out.println("5. Borrar pedido");
            System.out.println("6. Listar clientes");
            System.out.println("7. Listar zonas de envío");
            System.out.println("8. Consultar pedidos de cliente");
            System.out.println("0. Salir");

            int seleccion = sc.nextInt();
            sc.nextLine();

            switch (seleccion) {
                case 1:
                    ui.guardarCliente(conexion, datos);
                    break;

                case 2:
                    ui.guardarPedido(conexion, datos);
                    break;
            
                case 3:
                    ui.actualizarCliente(conexion, datos);
                    break;

                case 4:
                    ui.borrarCliente(conexion);
                    break;

                case 5:
                    ui.borrarPedido(conexion);
                    break;

                case 6:
                    ui.listaClientes(conexion);
                    break;

                case 7:
                    ui.listaZonasEnvio(conexion);
                    break;

                case 8:
                    ui.consultarPedidosCliente(conexion);
                    break;

                case 0:

                    salir = false;
                    cerrarConexiones();
                    System.out.println("Cerrando conexiones...");
                    System.out.println("Hasta luegooooo!!!!!");

                    break;
                default:
                    System.out.println("Opcion seleccionada no valida");
            }

        }

        sc.close();

    }

    private void cerrarConexiones() {
    try {
        HibernateUtil.shutdown();
        System.out.println("Conexiones cerradas correctamente");
    } catch (Exception e) {
        System.err.println("Error al cerrar conexiones: " + e.getMessage());
    }
}

}
