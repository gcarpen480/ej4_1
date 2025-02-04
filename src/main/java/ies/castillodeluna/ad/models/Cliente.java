package ies.castillodeluna.ad.models;

import edu.acceso.sqlutils.Entity;
import edu.acceso.sqlutils.annotations.Fk;

/**
 *  Modela un cliente
 */
public class Cliente implements Entity{
   
    /**
     * Identificador del cliente
     */
    private int id;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Email del cliente
     */
    private String email;

    /**
     * Teléfono del cliente
     */
    private String telefono;

    /**
     * Zona de envío del cliente
     */
    @Fk private ZonaEnvio id_zona;

    /**
     * Constructor por defecto
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los atributos
     * @param id Identificador del cliente
     * @param nombre Nombre del cliente
     * @param email Email del cliente
     * @param telefono Teléfono del cliente
     * @param id_zona Zona de envío del cliente
     */
    
    public Cliente(int id, String nombre, String email, String telefono, ZonaEnvio id_zona) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.id_zona = id_zona;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ZonaEnvio getId_zona() {
        return id_zona;
    }

    public void setId_zona(ZonaEnvio id_zona) {
        this.id_zona = id_zona;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", id_zona="
                + id_zona + "]";
    }

    

}
