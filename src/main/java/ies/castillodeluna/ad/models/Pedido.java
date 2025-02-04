package ies.castillodeluna.ad.models;

import java.time.LocalDate;

import edu.acceso.sqlutils.Entity;
import edu.acceso.sqlutils.annotations.Fk;



/**
 *  Modela un pedido
 */
public class Pedido implements Entity{
    
    /**
     * Identificador del pedido
     */
    private int id;

    /**
     * Fecha del pedido
     */
    private LocalDate fecha;

    /**
     * Importe total del pedido
     */
    private double importe;

    /**
     * Cliente que realiza el pedido
     */
    @Fk private Cliente id_cliente;

    /**
     * Constructor por defecto
     */
    public Pedido() {
    }

    /**
     * Constructor con todos los atributos
     * @param id Identificador del pedido
     * @param fecha Fecha del pedido
     * @param importe Importe total del pedido
     * @param id_cliente Cliente que realiza el pedido
     */    
    public Pedido(int id, LocalDate fecha, double importe, Cliente id_cliente) {
        this.id = id;
        this.fecha = fecha;
        this.importe = importe;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "Pedidos [id=" + id + ", fecha=" + fecha + ", importe=" + importe + ", id_cliente=" + id_cliente + "]";
    }


}
