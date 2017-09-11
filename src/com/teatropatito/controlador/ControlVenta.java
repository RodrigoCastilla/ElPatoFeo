/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.data.DAOObra;
import com.teatropatito.dominio.Obra;
import com.teatropatito.vista.Confirmacion;
import com.teatropatito.vista.Notificacion;
import com.teatropatito.vista.VenderBoletoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
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
public class ControlVenta implements ActionListener{
    private VenderBoletoVista venderBoleto;
    private ControlMapaAsientosDinamico controlMapa;
    private Confirmacion confirmacion;
    private Notificacion notificacion;
    private int validar;
    
    
    public ControlVenta(){
        venderBoleto = new VenderBoletoVista();
        
        venderBoleto.consultar.addActionListener(this);
        venderBoleto.comprar.addActionListener(this);
        venderBoleto.regresar.addActionListener(this);
        asignarListaObras();
        venderBoleto.setVisible(true);
        
        notificacion = new Notificacion();
        notificacion.aceptar.addActionListener(this);
        
        confirmacion = new Confirmacion();
        confirmacion.aceptar.addActionListener(this);
        confirmacion.cancelar.addActionListener(this);
        
        
    }
    
    public void actionPerformed(ActionEvent e) {
       if(venderBoleto.consultar == e.getSource()){
           try {
               ConsultarDisponibilidad((String)venderBoleto.listaObras.getSelectedItem());
           } catch (IOException ex) {
               Logger.getLogger(ControlVenta.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }else if(venderBoleto.comprar == e.getSource()){
           confirmacion.mesajeConfirmar.setText("¿Esta seguro que desea realizar la venta?");
           confirmacion.setVisible(true);
           
           
       }else if(venderBoleto.regresar == e. getSource()){
//           actualizar0(2);
           venderBoleto.dispose();
           
       }else if(notificacion.aceptar == e.getSource()){
           notificacion.dispose();
           
       }else if(confirmacion.cancelar == e.getSource()){
           confirmacion.dispose();
           
       }else if(confirmacion.aceptar == e.getSource()){
//           limpiarTabla();
//           actualizar1(2);
           confirmacion.dispose();
           notificacion.mensajeNotificacion.setText("La venta se ha realizado con éxito.");
           notificacion.setVisible(true);
           
           try {
               crearRecibo((String)venderBoleto.listaObras.getSelectedItem());
           } catch (IOException ex) {
               Logger.getLogger(ControlVenta.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
    }
    
    public void asignarListaObras(){          
        try {          
            venderBoleto.listaObras.removeAllItems();

            ArrayList<Obra> listaObras = new ArrayList<Obra>();
            DAOObra dao = new DAOObra();
            listaObras =dao.consultar();
            
            for (int i = 0; i < listaObras.size(); i++) {
                venderBoleto.listaObras.addItem(listaObras.get(i).getNombre());
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControlVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void crearRecibo(String Ruta) throws FileNotFoundException, IOException {
        double cadena;
        int contador= 0;
        

            String ruta = "Resource/" + Ruta +"/Numero de ventas"+ ".txt";
            File archivo = new File(ruta);
            if(archivo.exists()) {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            cadena = Double.parseDouble(b.readLine());
            b.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write((cadena+1)+"");
            bw.close();
            
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("Resource/"+ Ruta +"/Recibo"+(cadena+1)+".txt"));
            String datosCompra=venderBoleto.getTablaTexto().getText();
            bw2.write(datosCompra);
            bw2.close();
            
            
            
            } else {
                
                
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                String datosCompra=venderBoleto.getTablaTexto().getText();
                bw.write("1");
                bw.close();
                bw = new BufferedWriter(new FileWriter("Resource/"+ Ruta +"/Recibo"+"1"+".txt"));
                bw.write(venderBoleto.getTablaTexto().getText());
                bw.close();
            }  

        BufferedWriter bwVenta = new BufferedWriter(new FileWriter(archivo));
        bwVenta.write(1+"");
        bwVenta.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Resource/"+ Ruta +"/Recibo"+"1"+".txt"));
        String datosCompra=venderBoleto.getTablaTexto().getText();
        bw.write(datosCompra);
        bw.close();
        
    }
    
    
    
    public void ConsultarDisponibilidad(String Ruta) throws IOException{
        double cadena;
        int contador= 0;
        
        for (int i = 1; i <= 160; i++) {
            String ruta = "Resource/" + Ruta +"/boletos/a"+ i + ".txt";
            File archivo = new File(ruta);
            //BufferedWriter bw;
            if(archivo.exists()) {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            cadena = Double.parseDouble(b.readLine());
                if (cadena == 0) {
                    contador++;    
                }
            b.close();
            } else {
                System.out.println("No existe la obra");
            }  
        }
        if (contador > 0){
            controlMapa = new ControlMapaAsientosDinamico(venderBoleto, venderBoleto.listaObras.getSelectedItem().toString());
        }   else    {
            ControlNotificacion Noti = new ControlNotificacion();
            Noti.setMensajeNotificacion("No hay mas Lugares disponibles para esta Obra");
        }
    }
}
