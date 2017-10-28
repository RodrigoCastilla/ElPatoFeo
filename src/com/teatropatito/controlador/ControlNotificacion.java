/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.vista.Notificacion;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author melee_000
 */
public class ControlNotificacion implements ActionListener{
    private Notificacion notificacion;
    
    public ControlNotificacion(){
        Toolkit.getDefaultToolkit().beep();
        notificacion = new Notificacion();
        this.notificacion.aceptar.addActionListener(this);
        notificacion.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(notificacion.aceptar == e.getSource()){
            notificacion.dispose();
        }
    }
    
    public void setMensajeNotificacion(String msg){
        this.notificacion.mensajeNotificacion.setText(msg);
    }
    
    
}
