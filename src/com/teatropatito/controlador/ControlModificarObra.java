/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.data.DAOObra;
import com.teatropatito.dominio.Obra;
import com.teatropatito.vista.CrearNuevaObraVista;
import com.teatropatito.vista.ModificacionVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GCTec
 */
public class ControlModificarObra implements ActionListener{
    private ModificacionVista modificarObra;
    private Obra obra;
    

    public ControlModificarObra(){
        modificarObra = new ModificacionVista();
        asignarListaObras();
        
        this.modificarObra.getGuardarVista().addActionListener(this);
        this.modificarObra.getCancelarVista().addActionListener(this);
        this.modificarObra.getCargarVista().addActionListener(this);
        this.modificarObra.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(modificarObra.getGuardarVista() == e.getSource()){
             guardarCambios();
         }else if(modificarObra.getCancelarVista() == e.getSource()){
             modificarObra.dispose();
         }else if(modificarObra.getCargarVista() == e.getSource()){
                cargarElemento();
         }
        
        
    }
    
    public void cargarElemento() {
      
        try {
            DAOObra baseDatosObra = new DAOObra();
            ArrayList<Obra> obras= new ArrayList<Obra>();
            obras=baseDatosObra.consultar("nombre= '"+modificarObra.getListaObrasVista().getSelectedItem().toString()+ "'");
            
            
            //carga los elementos en la interfaz grafica
            modificarObra.getNombreVista().setText(obras.get(0).getNombre());
            modificarObra.getHoraInicioVista().setText(obras.get(0).getHoraInicio());
            modificarObra.getHoraFinVista().setText(obras.get(0).getHoraFinal());
//            modificarObra.getFechaVista().setText(obras.get(0).getFecha());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarCambios(){
        
        try {
            DAOObra baseDatosObras= new DAOObra();
           
            Obra nuevaObra= new Obra(
                    modificarObra.getNombreVista().getText(),
                    modificarObra.getHoraInicioVista().getText(),
                    modificarObra.getHoraFinVista().getText() /* ,*/
                  //  modificarObra.getFechaVista().getText()
            );
            
            String nombreAntiguo = modificarObra.getListaObrasVista().getSelectedItem().toString();
            String nuevoNombre= modificarObra.getNombreVista().getText();
            
            

            baseDatosObras.renombrarTabla(nombreAntiguo, nuevoNombre);
            baseDatosObras.modificar(nuevaObra, " nombre= '"+nombreAntiguo+"'");
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    
    public void asignarListaObras(){          
        try {
            modificarObra.getListaObrasVista().removeAllItems();
            DAOObra baseDatosObras= new DAOObra();
            ArrayList<Obra> obras = new ArrayList<Obra>();
            obras=baseDatosObras.consultar();
            
            for (int i = 0; i < obras.size(); i++) {
                modificarObra.getListaObrasVista().addItem(obras.get(i).getNombre());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
        
    }
    
    
    
    
}
