package ies.castillodeluna.ad.models;

import edu.acceso.sqlutils.Entity;

/**
 *  Modela una zona de envío
 */
public class ZonaEnvio implements Entity{
    
    /**
     * Identificador de la zona de envío
     */
    private int id;

    /**
     * Nombre de la zona de envío
     */
    private String nombre;

    /**
     * Coste de envío de la zona
     */
    private double coste;

    /**
     * Constructor por defecto
     */
    public ZonaEnvio() {
    }

    /**
     * Constructor con todos los atributos
     * @param id Identificador de la zona de envío
     * @param nombre Nombre de la zona de envío
     * @param coste Coste de envío de la zona
     */
    public ZonaEnvio(int id, String nombre, double coste) {
        this.id = id;
        this.nombre = nombre;
        this.coste = coste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "ZonaEnvio{" + "id=" + id + ", nombre=" + nombre + ", coste=" + coste + '}';
    }
    
}
