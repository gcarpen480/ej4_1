package ies.castillodeluna.ad.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase que gestiona la obtención y validación de datos de entrada del usuario
 * a través de la consola
 */
public class ObtenerDatos {

    /**
     * Scanner para la entrada de datos por consola
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructor por defecto de la clase
     */
    public ObtenerDatos() {
    }

    /**
     * Metodo que solicita y valida el ID de un cliente
     * 
     * @return ID del cliente
     */
    public int guardarIDCliente() {

        int id = -1;

        do {
            System.out.println("Ingrese el ID del cliente: ");

            try {

                id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("El numero introducido tiene que ser mayor a 0");
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor introducido invalido");
            }

        } while (id <= 0);

        return id;

    }

    /**
     * Metodo que solicita y valida el nombre de un cliente
     * 
     * @return Nombre del cliente
     */
    public String guardarNombreCliente() {

        String nombre = "";

        do {
            System.out.println("Ingrese el nombre del cliente: ");
            nombre = sc.next();

            if (nombre.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío");
            }

        } while (nombre.trim().isEmpty());

        return nombre;

    }

    /**
     * Metodo que solicita y valida el correo electronico de un cliente
     * 
     * @return Correo electrónico del cliente
     */
    public String guardarCorreoCliente() {

        String correo = "";

        do {
            System.out.println("Ingrese el correo del cliente: ");
            correo = sc.next();

            if (correo.trim().isEmpty()) {
                System.out.println("El correo no puede estar vacío");
            }

        } while (correo.trim().isEmpty());

        return correo;

    }

    /**
     * Metodo que solicita y valida el telefono de un cliente
     * 
     * @return Numero de telefono del cliente
     */
    public String guardarTelefonoCliente() {

        String telefono = "";

        do {
            System.out.println("Ingrese el telefono del cliente: ");
            telefono = sc.next();

            if (telefono.trim().isEmpty()) {
                System.out.println("El telefono no puede estar vacío");
            }

        } while (telefono.trim().isEmpty());

        return telefono;
    }

    /**
     * Metodo que solicita y valida el ID de un pedido
     * 
     * @return ID del pedido
     */
    public int guardarIDPedido() {

        int id = -1;

        do {
            System.out.println("Ingrese el ID del pedido: ");

            try {
                id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("El numero introducido tiene que ser mayor a 0");
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor introducido invalido");
            }

        } while (id <= 0);

        return id;

    }

    /**
     * Metodo que solicita y valida la fecha de un pedido
     * 
     * @return Fecha del pedido
     */
    public LocalDate guardarFechaPedido() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha;

        do {

            System.out.print("Ingrese la fecha del pedido (dd/MM/yyyy): ");
            String input = sc.nextLine();

            try {

                fecha = LocalDate.parse(input, formatter);
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto");
                fecha = null;
            }

        } while (true);

        return fecha;

    }

    /**
     * Metodo que solicita y valida el importe total de un pedido
     * 
     * @return Importe total
     */
    public double guardarImporteTotal() {

        double importe = -1;

        do {
            System.out.println("Ingrese el importe total: ");

            try {
                importe = sc.nextDouble();
                if (importe <= 0) {
                    System.out.println("El importe debe ser mayor que 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor introducido invalido");
            }

        } while (importe <= 0);

        return importe;

    }

    /**
     * Metodo que solicita y valida el ID de una zona
     * 
     * @return ID de la zona
     */

    public int guardarIDZona() {

        int id = -1;

        do {
            System.out.println("Ingrese el ID de la zona: ");

            try {
                id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("El numero introducido tiene que ser mayor a 0");
                } else if (id != 1 && id != 2) {
                    System.out.println("Solo existen las zonas 1 (Norte) y 2 (Sur)");
                    id = -1;
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor introducido invalido");
            }

        } while (id <= 0);

        return id;
    }

}
