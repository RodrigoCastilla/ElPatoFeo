/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.dominio;

/**
 *
 * @author melee_000
 */
public class Asiento {
    private int costo;
    private int controlAsiento;
    private String claveAsiento;
    private String nombreObra;
    private String zona;
    
    
    public Asiento(String clave, /*int color1, int color2, int color3,*/ int costo, int ctrl, String nombre, String zona){
        this.claveAsiento=clave;
        this.costo=costo;
        this.controlAsiento = ctrl;
        this.nombreObra = nombre;
        this.zona = zona;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }


    public String getClaveAsiento() {
        return claveAsiento;
    }

    public void setClaveAsiento(String claveAsiento) {
        this.claveAsiento = claveAsiento;
    }


    public int getControlAsiento() {
        return controlAsiento;
    }

    public void setControlAsiento(int controlAsiento) {
        this.controlAsiento = controlAsiento;
    }

    public int getCosto() {
        return costo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    
   
}
