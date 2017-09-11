/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;


import com.teatropatito.vista.Confirmacion;
import com.teatropatito.vista.ImprimirReporteVista;
import com.teatropatito.vista.Notificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author melee_000
 */
public class ControlReporte implements ActionListener{
    ImprimirReporteVista imprimirReporte;
    Notificacion notificacion;
    Confirmacion confirmacion;
    private double CostoP;
    private int numLug;
    
    public ControlReporte(){
        imprimirReporte = new ImprimirReporteVista();
        imprimirReporte.ver.addActionListener(this);
        imprimirReporte.imprimir.addActionListener(this);
        imprimirReporte.regresar.addActionListener(this);
        asignarListaObras();
        imprimirReporte.setVisible(true);
        
        notificacion = new Notificacion();
        notificacion.aceptar.addActionListener(this);
        
        confirmacion = new Confirmacion();
        confirmacion.aceptar.addActionListener(this);
        confirmacion.cancelar.addActionListener(this);
        setCostoP(0);
    }

    
    public void actionPerformed(ActionEvent e) {
        if(imprimirReporte.ver == e.getSource()){
            limpiarTabla1();
            llenarTabla();
        }else if(imprimirReporte.imprimir == e.getSource()){
            //Mensaje de encabezado
            MessageFormat headerFormat = new MessageFormat("Tutorial Imprimir JTables");
            //Mensaje en el pie de pagina
            MessageFormat footerFormat = new MessageFormat("ContreSpace");
            try {
                //Imprimir JTable
                imprimirReporte.resumenReporte.print(JTable.PrintMode.NORMAL, headerFormat, footerFormat);
            } catch (PrinterException ex) {
                Logger.getLogger(ControlReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(imprimirReporte.regresar == e.getSource()){
            imprimirReporte.dispose();
        }          

        
    }
    public void asignarListaObras(){          
        imprimirReporte.listaObras.removeAllItems();
        try {
            Scanner ArchivoF = new Scanner(new FileReader("Resource/CatalogoObras.txt"));
            while(ArchivoF.hasNextLine()){
                imprimirReporte.listaObras.addItem(ArchivoF.nextLine());
            }
            ArchivoF.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarTabla(){
      
       DefaultTableModel modelo =(DefaultTableModel) imprimirReporte.resumenReporte.getModel();
                 String[] fila1 = new String[4];
                 fila1[0]= "Area Lata:";
                 fila1[1]=contarAsientos("L");
                 fila1[2]=consultarCosto("L");
                 fila1[3]=costoTotalArea("L");
                 modelo.addRow(fila1);                 
                 
                 String[] fila2 = new String[4];
                 fila2[0]= "Area Cobre:";
                 fila2[1]=contarAsientos("C");
                 fila2[2]=consultarCosto("C");
                 fila2[3]=costoTotalArea("C");
                 modelo.addRow(fila2);                 
                 
                 String[] fila3 = new String[4];
                 fila3[0]= "Area Plata:";
                 fila3[1]=contarAsientos("P");
                 fila3[2]=consultarCosto("P");
                 fila3[3]=costoTotalArea("P");
                 modelo.addRow(fila3);                 
                 
                 String[] fila4 = new String[4];
                 fila4[0]= "Area Diamante:";
                 fila4[1]=contarAsientos("D");
                 fila4[2]=consultarCosto("D");
                 fila4[3]=costoTotalArea("D");
                 modelo.addRow(fila4);
                 
                 String[] fila5 = new String[4];
                 fila5[0]= "Area Oro:";
                 fila5[1]=contarAsientos("O");
                 fila5[2]=consultarCosto("O");
                 fila5[3]=costoTotalArea("O");
                 modelo.addRow(fila5);
                 
                 String[] fila6 = new String[4];
                 fila6[0]= "Nombre Obra:";
                 fila6[1]= imprimirReporte.listaObras.getSelectedItem() + "";
                 fila6[2]="Total Ventas:";
                 fila6[3]=totalVentasObra();
                 modelo.addRow(fila6);
                 imprimirReporte.resumenReporte.setModel(modelo);
    }
    
    public void limpiarTabla1(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) imprimirReporte.resumenReporte.getModel();
            int filas=imprimirReporte.resumenReporte.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public void limpiarTabla2(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) imprimirReporte.resumenReporte.getModel();
            int filas=imprimirReporte.resumenReporte.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
               DefaultTableModel modelo =(DefaultTableModel) imprimirReporte.resumenReporte.getModel();

        String[] fila1 = new String[4];
                 fila1[0]= "Area Lata:";
                 fila1[1]="";
                 fila1[2]="";
                 fila1[3]="";
                 modelo.addRow(fila1);                 
                 
                 String[] fila2 = new String[4];
                 fila2[0]= "Area Cobre:";
                 fila2[1]="";
                 fila2[2]="";
                 fila2[3]="";
                 modelo.addRow(fila2);                 
                 
                 String[] fila3 = new String[4];
                 fila3[0]= "Area Plata:";
                 fila3[1]="";
                 fila3[2]="";
                 fila3[3]="";
                 modelo.addRow(fila3);                 
                 
                 String[] fila4 = new String[4];
                 fila4[0]= "Area Diamante:";
                 fila4[1]="";
                 fila4[2]="";
                 fila4[3]="";
                 modelo.addRow(fila4);
                 
                 String[] fila5 = new String[4];
                 fila5[0]= "Area Oro:";
                 fila5[1]="";
                 fila5[2]="";
                 fila5[3]="";
                 modelo.addRow(fila1);
                 
                 String[] fila6 = new String[4];
                 fila6[0]= "Nombre Obra:";
                 fila6[1]="";
                 fila6[2]="Total Ventas:";
                 fila6[3]="";
                 modelo.addRow(fila6);
                 imprimirReporte.resumenReporte.setModel(modelo);
    }
    
    //Lee los archivos de cada tipo de asiento y regresa el valor almacenado en ellos.
    public String contarAsientos(String cond){
        String cant="";
        try {
            Scanner ArchivoF = new Scanner(new FileReader("Resource/" + imprimirReporte.listaObras.getSelectedItem() + "/" + cond + ".txt"));
            cant = ArchivoF.nextLine();
            ArchivoF.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }
    
    //Devuelve el costo del asiento segun el tipo o clase
    public String consultarCosto(String cond){
        String cant="";
        if (cond.compareTo("L")==0)      cant = "150";
        else if (cond.compareTo("C")==0) cant = "200";
        else if (cond.compareTo("P")==0) cant = "250";
        else if (cond.compareTo("D")==0) cant = "300";
        else if (cond.compareTo("O")==0) cant = "350";
        
        return cant;
    }
    
    
    public String costoTotalArea(String cond){
        double cant = 0;
        double lugaresO =0;
        try {
            Scanner ArchivoF = new Scanner(new FileReader("Resource/" + imprimirReporte.listaObras.getSelectedItem() + "/" + cond + ".txt"));
            lugaresO = Double.parseDouble(ArchivoF.nextLine());
            ArchivoF.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlEliminarObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (cond.compareTo("L")==0)      cant = 150 * lugaresO;
        else if (cond.compareTo("C")==0) cant = 200 * lugaresO;
        else if (cond.compareTo("P")==0) cant = 250 * lugaresO;
        else if (cond.compareTo("D")==0) cant = 300 * lugaresO;
        else if (cond.compareTo("O")==0) cant = 350 * lugaresO;
        
        setCostoP(cant + getCostoP());
        return cant + "";
    }
    
    public String totalVentasObra(){
        String cant="";
        cant = getCostoP() + "";
        return cant;
    }

    public double getCostoP() {
        return CostoP;
    }

    public void setCostoP(double CostoP) {
        this.CostoP = CostoP;
    }

    public int getNumLug() {
        return numLug;
    }

    public void setNumLug(int numLug) {
        this.numLug = numLug;
    }
}
