/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;


import com.teatropatito.vista.Confirmacion;
import com.teatropatito.vista.Notificacion;
import com.teatropatito.vista.ReembolsoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author melee_000
 */
public class ControlReembolso implements ActionListener{
    ReembolsoVista reembolsoVista;
    ControlMapaAsientosDinamico controlMapa;
    private Confirmacion confirmacion;
    private Notificacion notificacion;
    int validar;
    
    public ControlReembolso(){
        reembolsoVista =  new ReembolsoVista();
        asignarListaObras();
        reembolsoVista.consultar.addActionListener(this);
        reembolsoVista.cancelarCompra.addActionListener(this);
        reembolsoVista.regresar.addActionListener(this);
        reembolsoVista.setVisible(true);
        notificacion = new Notificacion();
        notificacion.aceptar.addActionListener(this);
        confirmacion = new Confirmacion();
        confirmacion.aceptar.addActionListener(this);
        confirmacion.cancelar.addActionListener(this);
        
    }
    
    
    
    
    public void actionPerformed(ActionEvent e) {
        if(reembolsoVista.consultar == e.getSource()){
            
           
                
                try {
                    controlMapa = new ControlMapaAsientosDinamico(reembolsoVista, reembolsoVista.listaObras.getSelectedItem().toString());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ControlReembolso.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        else if(reembolsoVista.cancelarCompra == e.getSource()){
           confirmacion.mesajeConfirmar.setText("¿Esta seguro que desea cancelar los boletos?");
           confirmacion.setVisible(true);
           
           
       }else if(reembolsoVista.regresar == e. getSource()){
           
           reembolsoVista.dispose();
           
       }else if(notificacion.aceptar == e.getSource()){
           notificacion.dispose();
           
       }else if(confirmacion.cancelar == e.getSource()){
           confirmacion.dispose();
           
       }else if(confirmacion.aceptar == e.getSource()){
           
           confirmacion.dispose();
           notificacion.mensajeNotificacion.setText("La cancelacion se ha realizado con éxito.");
           notificacion.setVisible(true);
       }
    }
    
        public void asignarListaObras(){          
        reembolsoVista.listaObras.removeAllItems();
        try {
            Scanner ArchivoF = new Scanner(new FileReader("Resource/CatalogoObras.txt"));
            while(ArchivoF.hasNextLine()){
                reembolsoVista.listaObras.addItem(ArchivoF.nextLine());
            }
            ArchivoF.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
