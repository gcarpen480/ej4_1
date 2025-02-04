package ies.castillodeluna.ad.ui;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * 
 */
public class MetodosUI {

    /**
     * 
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * 
     * @param conexion
     * @param datos
     */
    public void guardarCliente(Conexion conexion, ObtenerDatos datos) {

        int id = datos.guardarIDCliente();
        String nombre = datos.guardarNombreCliente();
        String correo = datos.guardarCorreoCliente();
        String telefono = datos.guardarTelefonoCliente();
        int id_zona = datos.guardarIDZona();

        try {

            ZonaEnvio zona = conexion.getZonaEnvio().get(id_zona).orElse(null);

            Cliente cliente = new Cliente(id, nombre, correo, telefono, zona);
            conexion.getCliente().insert(cliente);

            System.out.println("Cliente guardado correctamente");

        } catch (DataAccessException e) {
            System.err.println("Error al guardar el cliente: " + e.getMessage());
        }

    }

    /**
     * 
     * @param conexion
     * @param datos
     */
    public void guardarPedido(Conexion conexion, ObtenerDatos datos) {

        try {

            int id = datos.guardarIDPedido();
            LocalDate fecha = datos.guardarFechaPedido();
            double importe = datos.guardarImporteTotal();
            int id_cliente = datos.guardarIDCliente();

            Cliente cliente = conexion.getCliente().get(id_cliente).orElse(null);

            if (cliente == null) {
                System.out.println("Error: Cliente no encontrado");
                return;
            }

            Pedido pedido = new Pedido(id, fecha, importe, cliente);
            conexion.getPedido().insert(pedido);
            System.out.println("Pedido guardado correctamente");

        } catch (DataAccessException e) {
            System.err.println("Error al guardar el pedido: " + e.getMessage());
        }
    }

    /**
     * 
     * @param conexion
     */
    public void borrarCliente(Conexion conexion) {

        System.out.println("Ingrese el ID del cliente a borrar: ");
        int id = sc.nextInt();

        try {

            if (conexion.getCliente().delete(id)) {

                System.out.println("Cliente eliminado correctamente");

            } else {

                System.out.println("No se encontró el cliente con ID: " + id);

            }
        } catch (DataAccessException e) {
            System.err.println("Error al borrar el cliente: " + e.getMessage());
        }

    }

    /**
     * 
     * @param conexion
     */
    public void borrarPedido(Conexion conexion) {

        System.out.println("Ingrese el ID del pedido a borrar: ");
        int id = sc.nextInt();

        try {

            if (conexion.getPedido().delete(id)) {

                System.out.println("Pedido eliminado correctamente");

            } else {

                System.out.println("No se encontró el pedido con ID: " + id);

            }
        } catch (DataAccessException e) {
            System.err.println("Error al borrar el pedido: " + e.getMessage());
        }
    }

    /**
     * 
     * @param conexion
     * @param datos
     */
    public void actualizarCliente(Conexion conexion, ObtenerDatos datos) {

        try {

            System.out.println("Ingrese el ID del cliente a actualizar: ");
            int id = sc.nextInt();

            Cliente clienteExistente = conexion.getCliente().get(id).orElse(null);

            if (clienteExistente == null) {
                System.out.println("Cliente no encontrado");
                return;
            }

            String nombre = datos.guardarNombreCliente();
            String correo = datos.guardarCorreoCliente();
            String telefono = datos.guardarTelefonoCliente();

            clienteExistente.setNombre(nombre);
            clienteExistente.setEmail(correo);
            clienteExistente.setTelefono(telefono);

            if (conexion.getCliente().update(clienteExistente)) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("No se pudo actualizar el cliente");
            }

        } catch (DataAccessException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    /**
     * 
     * @param conexion
     */
    public void consultarPedidosCliente(Conexion conexion) {

        System.out.println("Introduce el ID del cliente: ");
        int id_cliente = sc.nextInt();

        try {

            if (conexion.getCliente().get(id_cliente).isEmpty()) {
                System.out.println("Cliente no encontrado");
                return;
            }

            System.out.println("\nPedidos del cliente: ");
            double total = 0;

            Stream<Pedido> pedidos = conexion.getPedido().get();

            for (Pedido pedido : pedidos.toList()) {

                if (pedido.getId_cliente() != null && pedido.getId_cliente().getId() == id_cliente) {
                    System.out.println("ID: " + pedido.getId() +
                            " | Fecha: " + pedido.getFecha() +
                            " | Importe: " + pedido.getImporte() + "€");
                    total += pedido.getImporte();

                }
            }

            System.out.println("\nTotal gastado: " + total + "€");

        } catch (DataAccessException e) {
            System.out.println("Error al consultar los pedidos: " + e.getMessage());
        }
    }

    /**
     * 
     * @param conexion
     */
    public void listaClientes(Conexion conexion) {

        try {

            System.out.println("\n=== Lista de Clientes ===");

            for (Cliente cliente : conexion.getCliente().get().toList()) {
                System.out.println(cliente);
                
            }

        } catch (DataAccessException e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
        }
    }

    /**
     * 
     * @param conexion
     */
    public void listaZonasEnvio(Conexion conexion) {

        try {

            System.out.println("\n=== Lista de Zonas de Envío ===");

            for (ZonaEnvio zona : conexion.getZonaEnvio().get().toList()) {
                System.out.println(zona);
            }

        } catch (DataAccessException e) {
            System.err.println("Error al listar las zonas de envío: " + e.getMessage());
        }
    }

}
