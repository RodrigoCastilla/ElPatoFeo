/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.data.DAOFuncion;
import com.teatropatito.data.DAOObra;
import com.teatropatito.dominio.Funcion;
import com.teatropatito.dominio.Obra;
import com.teatropatito.vista.Confirmacion;
import com.teatropatito.vista.EliminarObraVista;
import com.teatropatito.vista.Notificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author melee_000
 */
public class ControlEliminarObra implements ActionListener{
    private EliminarObraVista eliminarObra;  
    private Confirmacion confirmacion;
    private Notificacion notificacion;
    
    public ControlEliminarObra(){
        eliminarObra = new EliminarObraVista();
        
        this.eliminarObra.getEliminar().addActionListener(this);
        this.eliminarObra.getRegresar().addActionListener(this);
        this.eliminarObra.getBotonDatosTabla().addActionListener(this);
        this.eliminarObra.getCancelarFuncion().addActionListener(this);
        asignarListaObrasComboBox();
        
        eliminarObra.getNumeroObra().removeAllItems();
        eliminarObra.getNumeroObra().setBackground(new java.awt.Color(248,225,189));
        eliminarObra.setVisible(true);
        
        
        confirmacion = new Confirmacion();    
        this.confirmacion.aceptar.addActionListener(this);
        this.confirmacion.cancelar.addActionListener(this);
        
        notificacion = new Notificacion();
        this.notificacion.aceptar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(eliminarObra.getEliminar() == e.getSource()){  
            
           confirmacion.mesajeConfirmar.setText("¿Esta seguro que desea eliminar obra?");
           confirmacion.setVisible(true);           
           
       }else if(eliminarObra.getRegresar() == e.getSource()){
           eliminarObra.dispose();
           
       }else if(eliminarObra.getBotonDatosTabla()==e.getSource()){
           cargarHorarios();
           
       }else if(eliminarObra.getCancelarFuncion()== e.getSource()){
           cancelarObraSeleccionada();
           
       }else if(confirmacion.aceptar == e.getSource()){
            eliminarObra();  
            ControlNotificacion notificacion = new ControlNotificacion();
            notificacion.setMensajeNotificacion("Se ha eliminado la obra de manera exitosa.");
            asignarListaObrasComboBox();
            confirmacion.dispose();
            
       }else if(confirmacion.cancelar == e.getSource()){
           confirmacion.dispose();
           
       }else if(notificacion.aceptar == e.getSource()){
           notificacion.dispose();
           
       }
    }
    
    public void limpiarEliminarObraVista(){       
        eliminarObra.getListaObras().setSelectedIndex(0);
    }
    
    public void eliminarObra(){
        try {
            DAOObra baseDatosObras = new DAOObra();
            baseDatosObras.cancelarObra(eliminarObra.getListaObras().getSelectedItem().toString());
            
            
            
            asignarListaObrasComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Actualiza la lista de obras, eliminando la obra que se desea quitar.
   
     
    public void asignarListaObrasComboBox(){          
        try {
            eliminarObra.getListaObras().removeAllItems();
            DAOObra baseDatosObras= new DAOObra();
            ArrayList<Obra> obras = new ArrayList<Obra>();
            obras=baseDatosObras.consultar("estado= 'programada'");
            
            for (int i = 0; i < obras.size(); i++) {
                eliminarObra.getListaObras().addItem(obras.get(i).getNombre());
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    public void cargarHorarios(){
        try {
            DAOFuncion baseDatosFunciones = new DAOFuncion();
            eliminarObra.getNumeroObra().removeAllItems();
            
            ArrayList<Funcion> listaHorarios = new ArrayList<Funcion>();
            listaHorarios= baseDatosFunciones.consultar(eliminarObra.getListaObras().getSelectedItem()+"");
            
            DefaultTableModel modelo = (DefaultTableModel) eliminarObra.getTabla().getModel();
            JTable tabla =  new JTable(modelo); //permite modificar la tabla
            eliminarObra.setTabla(tabla);
            
            for (int i = 0; i <listaHorarios.size(); i++) {
                String[] datosObra= { listaHorarios.get(i).getNumero(),
                                    listaHorarios.get(i).getMinutoInicio()+":"+listaHorarios.get(i).getHoraInicio(),
                                    listaHorarios.get(i).getMinutoFinal()+":"+listaHorarios.get(i).getHoraFinal(),
                                    listaHorarios.get(i).getDia()+"/"+listaHorarios.get(i).getMes()+"/"+listaHorarios.get(i).getAño()
                    
                    
                };
                eliminarObra.getNumeroObra().addItem(listaHorarios.get(i).getNumero());
                
                 modelo.addRow(datosObra);
            }

        eliminarObra.getjScrollPane1().updateUI();
  
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    

public void cancelarObraSeleccionada(){
        try {
            eliminarObra.getListaObras().getSelectedItem().toString();
            DAOFuncion baseDatosFunciones = new DAOFuncion();
            
            baseDatosFunciones.cancelarFuncion(
                                        eliminarObra.getListaObras().getSelectedItem().toString(),
                                        eliminarObra.getNumeroObra().getSelectedItem().toString()
                                        );
            
            
            asignarListaObrasComboBox();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}   
    
    
    
    
}
