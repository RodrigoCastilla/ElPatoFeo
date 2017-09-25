/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;


import com.teatropatito.data.DAOAsiento;
import com.teatropatito.data.DAOFuncion;
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
    ArrayList<Funcion> funciones = new ArrayList<Funcion>();
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
                   if(VerificarRepeticion()){ // true significa que no se encontro repeticion
                       
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
           try {
               agregarElementoTabla();
           } catch (SQLException ex) {
               Logger.getLogger(ControlCrearObra.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }else if(nuevaObra.getQuitar()== e.getSource()){
           quitarElementosTabla();
       }
       
    }
 
    public void limpiarCrearNuevaObraVista(){
        nuevaObra.getTxtNombreObra().setText("");
        nuevaObra.getHoraInicio().resetKeyboardActions();
        nuevaObra.getHoraFin().resetKeyboardActions();
        
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
        System.out.println(nuevaObra.getTabla().getRowCount());
//        for (int i = nuevaObra.getTabla().getRowCount(); nuevaObra.getTabla().getRowCount() >0 ; i--) {
//            modelo.removeRow(i);
//        }
//        
        
        while(nuevaObra.getTabla().getRowCount()>0 ){
            modelo.removeRow(nuevaObra.getTabla().getRowCount()-1);
        }
        
    }
    
   
    public void agregarElementoTabla() throws SQLException{
        try{
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
        JTable tabla =  new JTable(modelo); //permite modificar la tabla
        nuevaObra.setTabla(tabla);
        DAOFuncion baseDatosFunciones = new DAOFuncion();
        if(baseDatosFunciones.verificarExistenciaFuncion(new Funcion(nuevaObra.getHoraInicio().getHours()+"",nuevaObra.getHoraFin()+"",nuevaObra.getHoraInicio().getMinutes()+"",nuevaObra.getHoraFin().getMinutes()+"",
        nuevaObra.getFecha().getCalendar().get(Calendar.DAY_OF_MONTH)+"", (nuevaObra.getFecha().getCalendar().get(Calendar.MONTH)+1) +"", nuevaObra.getFecha().getCalendar().get(Calendar.YEAR)+"", "")) ){
            
            escribirMensaje("Ya existe una función en ese horario.");
        }else{
            String[] datosObra= {nuevaObra.getHoraInicio().getMinutes()+"",nuevaObra.getHoraInicio().getHours()+"", 
                                 nuevaObra.getHoraFin().getMinutes()+"",nuevaObra.getHoraFin().getHours()+"",
                                 nuevaObra.getFecha().getCalendar().get(Calendar.DAY_OF_MONTH)+"" , (nuevaObra.getFecha().getCalendar().get(Calendar.MONTH)+1) +"",
                                 nuevaObra.getFecha().getCalendar().get(Calendar.YEAR)+"" };
        

            modelo.addRow(datosObra);
        }
        
        
        
        nuevaObra.getjScrollPane1().updateUI();
        }catch(NullPointerException e){
            escribirMensaje("no se han introducido datos");
        }
    }
    
    
    public void quitarElementosTabla(){ //Cambiar tu parametro de entrada
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
        try{
            
            int itemSeleccionado= nuevaObra.getTabla().getSelectedRowCount();
            modelo.removeRow(itemSeleccionado);
        }catch(Exception e){
            escribirMensaje("elige algo >:v");
        }
  
    }
    
    public void guardarObra(){
        
        try {
            DAOObra tablaObra = new DAOObra();
            DAOFuncion tablaFunciones = new DAOFuncion();
            funciones.add(new Funcion("", "", "" ,"", "","", "", "" ,""));
            Obra obra= new Obra(
                    nuevaObra.getTxtNombreObra().getText(),
                    nuevaObra.getPrecioDiamante().getText(),
                    nuevaObra.getDescripcion().getText(),
                    nuevaObra.getActores().getText(),
                    nuevaObra.getCorreo().getText(),
                    nuevaObra.getTelefono().getText(),
                    nuevaObra.getTelefonoAlt().getText(),
                    funciones
            );
            
            //funciones.add(new Funcion("a"," ","","","", "","", "", "");
            
            int num= tablaObra.agregar(obra);
            guardarHorarios();

// importante************************************** los horarios son un objeto ... por mientras, los horarios se guardaran aqui

////            
        } catch (SQLException ex) {
            Logger.getLogger(ControlCrearObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void guardarHorarios() throws SQLException{
        DefaultTableModel modelo = (DefaultTableModel) nuevaObra.getTabla().getModel();
            JTable tabla =  new JTable(modelo);
            DAOFuncion baseDatosFunciones = new DAOFuncion();
            
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
                    
                    baseDatosFunciones.agregar(funcion, i+1);
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
            DAOFuncion datosFunciones = new DAOFuncion();
            listaObras =datosObras.consultar(" nombre= '"+nuevaObra.getTxtNombreObra().getText()+"'" );// regresa un array 
            
            ArrayList<Obra> listaFunciones;
            /*for(int i =0; i< listaObras.size(); i++){
                listaFunciones = datosFunciones.consultarProgramadas(listaObras.get(i).getNombre());
                listaObras.get(i).setFunciones(funciones);
            }*/
            
            
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