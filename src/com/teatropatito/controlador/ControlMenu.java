/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author melee_000
 */
public class ControlMenu implements ActionListener {
    private MenuPrincipalVista menu;

    public ControlMenu(){
        this.menu = new MenuPrincipalVista();
        
        this.menu.getCrearObra().addActionListener(this);
        this.menu.getEliminarObra().addActionListener(this);
        this.menu.getVender().addActionListener(this);
        this.menu.getReembolso().addActionListener(this);
        this.menu.getImprimirReporte().addActionListener(this);
        this.menu.getModificarObra().addActionListener(this);
        
        this.menu.setVisible(true);
    }
   
    public void actionPerformed(ActionEvent e){
        if(menu.getCrearObra() == e.getSource()){
            ControlCrearObra nuevaObra = new ControlCrearObra();
            
        }else if(menu.getEliminarObra() == e.getSource()){
            ControlEliminarObra eliminarObra = new ControlEliminarObra();
            
        }else if(menu.getVender() == e.getSource()){
            ControlVenta venta = new ControlVenta();
            
        }else if(menu.getReembolso() == e.getSource()){
            ControlReembolso reembolso = new ControlReembolso();
            
        }else if(menu.getImprimirReporte() == e.getSource()){
            ControlReporte reporte = new ControlReporte();
        }else if(menu.getModificarObra() == e.getSource()){
            try {
                ControlModificarObra modificarObra = new ControlModificarObra();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
