/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;


import com.teatropatito.data.DAOAsiento;
import com.teatropatito.data.DAOObra;
import com.teatropatito.dominio.Funcion;
import com.teatropatito.dominio.Obra;
import com.teatropatito.vista.CrearNuevaObraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author melee_000
 */
public class ControlCrearObra implements ActionListener{
    private CrearNuevaObraVista nuevaObra;
    private Obra obra;
    

    public ControlCrearObra(){
        nuevaObra = new CrearNuevaObraVista();
        limpiarCrearNuevaObraVista();
        this.nuevaObra.getLimpiar().addActionListener(this);
        this.nuevaObra.getGuardar().addActionListener(this);
        this.nuevaObra.getRegresar().addActionListener(this); 
        this.nuevaObra.getAgregar().addActionListener(this);
        this.nuevaObra.getQuitar().addActionListener(this);
        nuevaObra.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
       if(nuevaObra.getLimpiar() == e.getSource()){
           limpiarCrearNuevaObraVista();
           
       }else if(nuevaObra.getRegresar() == e.getSource()){
           nuevaObra.dispose();       
           
        }else if(nuevaObra.getGuardar() == e.getSource()){
                
               if(nuevaObra.getTxtNombreObra().getText().compareTo("")!=0){// verificar que se introdujo un nombre
                   System.out.println(VerificarRepeticion());
                   if(VerificarRepeticion()==true){ // true significa que no se encontro repeticion
                       
                       guardarObra();
                      
                       escribirMensaje(" Obra guardada");  
                   }else{
                       escribirMensaje(" ya existe la obra");  
                   }
               }else{
                    escribirMensaje("No se registraron datos.");  
               }
               
                    
           limpiarCrearNuevaObraVista();
           
       }else if(nuevaObra.getAgregar()== e.getSource()){
           agregarElementoTabla();
           System.out.println("hey");
           
       }else if(nuevaObra.getQuitar()== e.getSource()){
           quitarElementosTabla(0);
       }
       
    }
 
    public void limpiarCrearNuevaObraVista(){
        nuevaObra.getTxtNombreObra().setText("");
        nuevaObra.getHoraInicio().resetKeyboardActions();
        nuevaObra.getHoraFin().resetKeyboardActions();
        
        for (int i = 0; i < nuevaObra.getTabla().getRowCount(); i++) {
            quitarElementosTabla(i);
        }
    }
    
   
    public void agregarElementoTabla(){
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
        JTable tabla =  new JTable(modelo); //permite modificar la tabla
        nuevaObra.setTabla(tabla);
        
        String[] datosObra= {nuevaObra.getHoraInicio().getMinutes()+"",nuevaObra.getHoraInicio().getHours()+"", 
                    nuevaObra.getHoraFin().getMinutes()+"",nuevaObra.getHoraFin().getHours()+"",
                    nuevaObra.getFecha().getCalendar().get(Calendar.DAY_OF_MONTH)+"" , (nuevaObra.getFecha().getCalendar().get(Calendar.MONTH)+1) +"",
                            nuevaObra.getFecha().getCalendar().get(Calendar.YEAR)+"" };
        
        modelo.addRow(datosObra);
        
        System.out.println("se hace");
        nuevaObra.getjScrollPane1().updateUI();
        
        
    }
    
    
    public void quitarElementosTabla(int num){
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
        JTable tabla =  new JTable(modelo);
        
        tabla.getRowCount();
        modelo.removeRow(num);
        
        
    }
    
   
    public void guardarObra(){
        
        try {
            DAOObra baseDatos = new DAOObra();
            String[] tempFechaObra = nuevaObra.getFecha().getDateFormatString().split("7");
            for(int k = 0; k<tempFechaObra.length; k++)
                System.out.println(tempFechaObra[k]);
            Obra obra= new Obra(
                    nuevaObra.getTxtNombreObra().getText(),
                    nuevaObra.getHoraInicio().getMinutes()+"",
                    nuevaObra.getHoraInicio().getHours()  +"",
                    nuevaObra.getHoraFin().getMinutes()   +"",
                    nuevaObra.getHoraFin().getHours()     +"",
                    nuevaObra.getFecha().getCalendar().get(Calendar.DAY_OF_MONTH) +"",
                    (nuevaObra.getFecha().getCalendar().get(Calendar.MONTH)+1)    +"",
                    nuevaObra.getFecha().getCalendar().get(Calendar.YEAR)         +"",
                    nuevaObra.getPrecioDiamante().getText(),
                    nuevaObra.getDescripcion().getText(),
                    nuevaObra.getActores().getText(),
                    nuevaObra.getCorreo().getText(),
                    nuevaObra.getTelefono().getText(),
                    nuevaObra.getTelefonoAlt().getText()
            
            
            );
            
                      
            int num= baseDatos.agregar(obra);
            guardarHorarios();
//            por el momento no es necesario
//            DAOAsiento baseDatosAsientos = new DAOAsiento();
//            baseDatosAsientos.crearDatos(nuevaObra.getTxtNombreObra().getText());


// importante************************************** los horarios son un objeto ... por mientras, los horarios se guardaran aqui

////            
        } catch (SQLException ex) {
            Logger.getLogger(ControlCrearObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void guardarHorarios() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
            JTable tabla =  new JTable(modelo);
            DAOObra baseDatos = new DAOObra();
            
            Funcion funcion;
            
            
            for (int i = 0; i < nuevaObra.getTabla().getModel().getRowCount(); i++) {
                
                    funcion= new Funcion(
                            nuevaObra.getTabla().getValueAt(i, 0)+"",
                            nuevaObra.getTabla().getValueAt(i, 1)+"",
                            nuevaObra.getTabla().getValueAt(i, 2)+"",
                            nuevaObra.getTabla().getValueAt(i, 3)+"",
                            nuevaObra.getTabla().getValueAt(i, 4)+"",
                            nuevaObra.getTabla().getValueAt(i, 5)+"",
                            nuevaObra.getTabla().getValueAt(i, 6)+"",
                            nuevaObra.getTxtNombreObra().getText(),
                            (i+1)+"");
                    
                    baseDatos.insertarFuncion(funcion, i+1);
                    modelo.removeRow(i);
            }
    }
    
    public void escribirMensaje(String mensaje){
        ControlNotificacion notificacionVista = new ControlNotificacion();
        notificacionVista.setMensajeNotificacion(mensaje);
        
    }
    
    
    
    public Boolean VerificarRepeticion() {
        Boolean esNueva=false;
        try {
            ArrayList<Obra> listaObras = new ArrayList<Obra>();
            DAOObra datosObras = new DAOObra();
            
            listaObras =datosObras.consultar(" nombre= '"+nuevaObra.getTxtNombreObra().getText()+"'" );// regresa un array 
            //con las obras que tienen nombres iguales
            if(listaObras.isEmpty()){
                esNueva=true; 
            }else{
                escribirMensaje(" ya existe una obra con ese nombre");
                esNueva = false;
            }
            
            
        } catch (SQLException ex) {
            escribirMensaje(" error en los datos");
            esNueva=false;
            Logger.getLogger(ControlCrearObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esNueva;
    }
    
    
    
    
    
    
}