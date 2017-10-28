/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.dominio;

import java.util.ArrayList;

/**
 *
 * @author melee_000
 */
public class Obra {
    private String nombre;
    private String precioDiamante;
    private String descripcion;
    private String Actores;
    private String correo;
    private String telefono;
    private String telefonoAlt;
    private String estado;
    private ArrayList<Funcion> funciones;
    
    
    
    //mientras se modifica
    public Obra(String nombre){
        this.nombre = nombre;
    }

    public Obra(String nombre, String precioDiamante, String descripcion, String Actores, String correo, String telefono, String telefonoAlt, ArrayList<Funcion> funciones) {
        this.nombre = nombre;
        this.precioDiamante = precioDiamante;
        this.descripcion = descripcion;
        this.Actores = Actores;
        this.correo = correo;
        this.telefono = telefono;
        this.telefonoAlt = telefonoAlt;
        this.funciones = funciones;
    }

    public Obra(String nombre, String precioDiamante, String descripcion, String Actores, String correo, String telefono, String telefonoAlt,String est) {
        this.nombre = nombre;
        this.precioDiamante = precioDiamante;
        this.descripcion = descripcion;
        this.Actores = Actores;
        this.correo = correo;
        this.telefono = telefono;
        this.telefonoAlt = telefonoAlt;
        this.estado= est;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecioDiamante() {
        return precioDiamante;
    }

    public void setPrecioDiamante(String precioDiamante) {
        this.precioDiamante = precioDiamante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActores() {
        return Actores;
    }

    public void setActores(String Actores) {
        this.Actores = Actores;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoAlt() {
        return telefonoAlt;
    }

    public void setTelefonoAlt(String telefonoAlt) {
        this.telefonoAlt = telefonoAlt;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }
    
    
}
