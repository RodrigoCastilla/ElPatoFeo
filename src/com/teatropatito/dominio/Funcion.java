/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.dominio;

/**
 *
 * @author GCTec
 */
public class Funcion {
    private String nombre;
    private String horaInicio;
    private String numero;
    private String horaFinal;
    private String minutoInicio;
    private String minutoFinal;
    private String dia;
    private String mes;
    private String año;
    private String estado;

    public Funcion(String horaInicio, String horaFinal, String minutoInicio, String minutoFinal, String dia, String mes, String año,String nombre, String num, String estado) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.minutoInicio = minutoInicio;
        this.minutoFinal = minutoFinal;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.numero= num;
        this.estado = estado;
    }
    public Funcion(String horaInicio, String horaFinal, String minutoInicio, String minutoFinal, String dia, String mes, String año,String nombre, String num) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.minutoInicio = minutoInicio;
        this.minutoFinal = minutoFinal;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.numero= num;
        this.estado = "programada";
    }
    

    public Funcion(String horaInicio, String horaFinal, String minutoInicio, String minutoFinal, String dia, String mes, String año, String num) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.minutoInicio = minutoInicio;
        this.minutoFinal = minutoFinal;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.numero = num;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
}
