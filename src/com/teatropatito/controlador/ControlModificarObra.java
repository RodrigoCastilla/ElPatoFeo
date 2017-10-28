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
import com.teatropatito.vista.ModificarObraVista;
import com.teatropatito.vista.Notificacion;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GCTec
 */
public class ControlModificarObra implements ActionListener{
    private ModificarObraVista modificarObra;
    private ArrayList<Obra> listaObras;
    private Obra obra;
    private Funcion funcion;
    private int ultimaObraSeleccionada, ultimaFuncionSeleccionada;
    

    public ControlModificarObra() throws SQLException{
        modificarObra = new ModificarObraVista();
        asignarListaObras();
        inicializarElementos();
        
        this.modificarObra.getGuardarBtn().addActionListener(this);
        this.modificarObra.getVolverBTN().addActionListener(this);
        this.modificarObra.getRefreshBtn().addActionListener(this);
        this.modificarObra.getAgregarFucionbtn().addActionListener(this);
        this.modificarObra.getEliminarFuncionBtn().addActionListener(this);
        this.modificarObra.setVisible(true);
        this.modificarObra.getPanelDatoaObra().setVisible(false);
        this.modificarObra.getPanelDatosFunciones().setVisible(false);
        ultimaObraSeleccionada = ultimaFuncionSeleccionada =0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(modificarObra.getGuardarBtn()== e.getSource()){
             guardarCambios();
         }else if(modificarObra.getVolverBTN()== e.getSource()){
             modificarObra.dispose();
         }else if(modificarObra.getRefreshBtn()== e.getSource()){
             cargarElemento();
         } else if(modificarObra.getAgregarFucionbtn() == e.getSource()){
             añadirFuncion();
         } else if(modificarObra.getEliminarFuncionBtn()== e.getSource()){
             listaObras.get(ultimaObraSeleccionada).getFunciones().remove(modificarObra.getFuncionesCBX().getSelectedIndex());
             modificarObra.inicializarComponentes();
         }
        
        
    }
    public void inicializarElementos() throws SQLException {
        try {
            modificarObra.getObrasCBX().removeAllItems();
            DAOObra baseDatosObra = new DAOObra();
            DAOFuncion baseDatosFunciones = new DAOFuncion();
            listaObras= baseDatosObra.consultar(" estado= 'programada'");
      
            for(int i=0; i<listaObras.size();i++){
                modificarObra.getObrasCBX().addItem(listaObras.get(i).getNombre());
            
            }
            cargarElementosFunciones();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarElemento() {
        
        ultimaObraSeleccionada = modificarObra.getObrasCBX().getSelectedIndex(); //modificarObra.inicializarComponentes();
        
        cargarElementosFunciones();
        
        modificarObra.getPanelDatoaObra().setVisible(true);
        modificarObra.getPanelDatosFunciones().setVisible(true);
        modificarObra.getNombreObraTxt().setText(listaObras.get(ultimaObraSeleccionada).getNombre());
    }
    
    private void cargarElementosFunciones(){
        modificarObra.getFuncionesCBX().removeAllItems();
        DAOFuncion baseDatosFuncion = new DAOFuncion();
        ArrayList<Funcion> funciones= new ArrayList<Funcion>();
        
        try {
            funciones= baseDatosFuncion.consultarProgramadas(
                                                        modificarObra.getObrasCBX().getSelectedItem()+""
                                                            );
            
            for (int i = 0; i < funciones.size(); i++) {
                modificarObra.getFuncionesCBX().addItem(
                                                    funciones.get(i).getNumero()      +" "+
                                                    funciones.get(i).getHoraInicio()  +":"+
                                                    funciones.get(i).getMinutoInicio()+" "+
                                                    funciones.get(i).getHoraFinal()   +":"+
                                                    funciones.get(i).getMinutoFinal() +" "+
                                                    funciones.get(i).getDia()         +"/"+
                                                    funciones.get(i).getMes()         +"/"+   
                                                    funciones.get(i).getAño()       
                                                        );
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlModificarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private void añadirFuncion(){
            Funcion nuevaFuncion= new Funcion(modificarObra.getMinInicio().getText(), modificarObra.getHoraFin().getText(),(modificarObra.getMinInicio().getText()), modificarObra.getMinFin().getText(),modificarObra.getDiaTxT().getText(), modificarObra.getMesTxT().getText(),modificarObra.getAñoTxT().getText(), (listaObras.get(ultimaObraSeleccionada).getFunciones().size()-1)+ "");
            
    
    }
    
    
    public void guardarCambios(){
        
        try {
            DAOObra baseDatosObras= new DAOObra();
            DAOFuncion baseDatosFunciones = new DAOFuncion();
            
            listaObras.get(ultimaObraSeleccionada).setNombre(modificarObra.getNombreObraTxt().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setDia(modificarObra.getDiaTxT().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setMes(modificarObra.getMesTxT().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setAño(modificarObra.getAñoTxT().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setHoraFinal(modificarObra.getHoraFin().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setMinutoFinal(modificarObra.getMinFin().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setHoraInicio(modificarObra.getHoraInicio().getText());
            listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()).setMinutoFinal(modificarObra.getMinInicio().getText());
            baseDatosFunciones.modificar(listaObras.get(ultimaObraSeleccionada).getFunciones().get(modificarObra.getFuncionesCBX().getSelectedIndex()), "");
            
            Obra nuevaObra= new Obra("a");//modificarObra.getNombreObraTxt().getText());
            String nombreAntiguo = modificarObra.getObrasCBX().getSelectedItem().toString();
            String nuevoNombre= modificarObra.getNombreObraTxt().getText();
            
            baseDatosObras.modificar(listaObras.get(ultimaObraSeleccionada), " nombre= '"+nombreAntiguo+"'");
            
            ControlNotificacion notificacion = new ControlNotificacion();
            notificacion.setMensajeNotificacion("se han guardado los cambios");
            inicializarElementos();
            
            
            
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
