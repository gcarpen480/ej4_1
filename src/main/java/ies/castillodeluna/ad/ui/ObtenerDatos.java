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

    public double guardarImporteTotal(){

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

}
