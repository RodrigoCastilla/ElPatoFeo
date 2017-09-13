/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.data.DAOObra;
import com.teatropatito.dominio.Obra;
import com.teatropatito.vista.ModificarObraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GCTec
 */
public class ControlModificarObra implements ActionListener{
    private ModificarObraVista modificarObra;
    private Obra obra;
    

    public ControlModificarObra(){
        modificarObra = new ModificarObraVista();
        asignarListaObras();
        
        this.modificarObra.getGuardarBtn().addActionListener(this);
        this.modificarObra.getVolverBTN().addActionListener(this);
        this.modificarObra.getRefreshBtn().addActionListener(this);
        this.modificarObra.setVisible(true);
        this.modificarObra.getPanelDatoaObra().setVisible(false);
        this.modificarObra.getPanelDatosFunciones().setVisible(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(modificarObra.getGuardarBtn()== e.getSource()){
             guardarCambios();
         }else if(modificarObra.getVolverBTN()== e.getSource()){
             modificarObra.dispose();
         }else if(modificarObra.getRefreshBtn()== e.getSource()){
                cargarElemento();
         }
        
        
    }
    
    public void cargarElemento() {
      
        try {
            DAOObra baseDatosObra = new DAOObra();
            ArrayList<Obra> obras= new ArrayList<Obra>();
            obras=baseDatosObra.consultar("nombre= '"+modificarObra.getObrasCBX().getSelectedItem().toString()+ "'");

            //carga los elementos en la interfaz grafica
            modificarObra.getNombreObraTxt().setText(obras.get(0).getNombre());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarCambios(){
        
        try {
            DAOObra baseDatosObras= new DAOObra();
           
            Obra nuevaObra= new Obra(modificarObra.getNombreObraTxt().getText());
/*            nuevaObra.setHoraInicio(modificarObra.getHoraInicio().getText());
            nuevaObra.setMinutoInicio(modificarObra.getMinInicio().getText());
            nuevaObra.setHoraFinal(modificarObra.getHoraFin().getText());
            nuevaObra.setMinutoFinal(modificarObra.getMinFin().getText()); 
            nuevaObra.setDia((modificarObra.getFecha().getCalendar().get(Calendar.DAY_OF_MONTH)) + "");
            nuevaObra.setMes((modificarObra.getFecha().getCalendar().get(Calendar.MONTH))+ "");
            nuevaObra.setAño((modificarObra.getFecha().getCalendar().get(Calendar.YEAR))+""); */
            String nombreAntiguo = modificarObra.getObrasCBX().getSelectedItem().toString();
            String nuevoNombre= modificarObra.getNombreObraTxt().getText();
            
            

            baseDatosObras.renombrarTabla(nombreAntiguo, nuevoNombre);
            baseDatosObras.modificar(nuevaObra, " nombre= '"+nombreAntiguo+"'");
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarListaObras(){          
        try {
            modificarObra.getObrasCBX().removeAllItems();
            DAOObra baseDatosObras= new DAOObra();
            ArrayList<Obra> obras = new ArrayList<Obra>();
            obras=baseDatosObras.consultar();
            
            for (int i = 0; i < obras.size(); i++) {
                modificarObra.getObrasCBX().addItem(obras.get(i).getNombre());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
