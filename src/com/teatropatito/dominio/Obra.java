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
public class Obra {
    private String nombre;
    private String horaInicio;
    private String horaFinal;
    private String minutoInicio;
    private String minutoFinal;
    private String dia;
    private String mes;
    private String año;
    private String precioDiamante;
    private String descripcion;
    private String Actores;
    private String correo;
    private String telefono;
    private String telefonoAlt;
    private String estado;
    
    
    
    //mientras se modifica
    

    public Obra(String nombre, String minuInicio, String horaInicio, String minutoFinal, String horaFinal, String dia, String mes, String año, String precioDiamante, String descripcion, String Actores, String correo, String telefono, String telefonoAlt) {
        this.nombre = nombre;
        this.minutoInicio = minuInicio;
        this.horaInicio = horaInicio;
        this.minutoFinal = minutoFinal;
        this.horaFinal = horaFinal;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.precioDiamante = precioDiamante;
        this.descripcion = descripcion;
        this.Actores = Actores;
        this.correo = correo;
        this.telefono = telefono;
        this.telefonoAlt = telefonoAlt;
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
    
    
    
    
    public Obra(String nombre, String horaInicio, String horaFinal) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    public Obra(String nombre, String horaInicio, String horaFinal, String fecha){
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }



    public String getNombre() {
        return nombre;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
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

    public String getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(String minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public String getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(String minutoFinal) {
        this.minutoFinal = minutoFinal;
    }
    
    
    
    
}
