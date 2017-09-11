/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.controlador;

import com.teatropatito.vista.MapaAsientosDinamico;
import com.teatropatito.vista.ReembolsoVista;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author GCTec
 */
public class ControlMapaAsientosDinamico implements ActionListener{
     private MapaAsientosDinamico mapaB;
    private String nombreObra;
    private VenderBoletoVista venderVista;
    private java.awt.Color oro = new java.awt.Color(255, 247,0);
    private java.awt.Color diamante = new java.awt.Color(0,247,255);
    private java.awt.Color plata = new java.awt.Color(60,0,255);
    private java.awt.Color cobre = new java.awt.Color(255,159,0);
    private java.awt.Color lata = new java.awt.Color(132,188,196);
    private java.awt.Color ocupado = new java.awt.Color(240,240,240);
    private java.awt.Color seleccion = new java.awt.Color(234,0,0);
    private ReembolsoVista reembolsoVista;
    private  int NBP = 0;
    private  int NBO = 0;
    private  int NBL = 0;
    private  int NBD = 0;
    private  int NBC = 0;
    private int BP = 0;
    private int BO = 0;
    private int BL = 0;
    private int BD = 0;
    private int BC = 0;
    private int costo = 0;
    private String Ruta;
    private  BufferedWriter registro; 
    private int disponibles;
    boolean reembolso;
    
    boolean[] asientos = new boolean[160];
    
    public ControlMapaAsientosDinamico() throws FileNotFoundException{
        mapaB = new MapaAsientosDinamico();
        PrepararVentana();
        CargarValores();
        
        this.Ruta = nombreObra;
        for (int i = 0; i < 160; i++) {
            mapaB.getBotones()[i].addActionListener(this);
            asientos[i]=false; // asientos como disponibles = false
        }
        mapaB.getAceptar().addActionListener(this);
        mapaB.getCancelar().addActionListener(this);
        
        mapaB.setVisible(true);
        reembolso=false;
        
        
    }
    
    public ControlMapaAsientosDinamico(VenderBoletoVista venderVista, String nombreObra) throws FileNotFoundException
    {
        this.venderVista = venderVista;
        this.nombreObra = nombreObra;
        mapaB = new MapaAsientosDinamico();
        PrepararVentana();
        this.Ruta = nombreObra;
        
        for (int i = 0; i < 160; i++) {
            mapaB.getBotones()[i].addActionListener(this);
        }
        mapaB.getAceptar().addActionListener(this);
        mapaB.getCancelar().addActionListener(this);
        
        CargarValores();
        ColocarOcupados();
        //asignarDisponibles();
        mapaB.setVisible(true);
        reembolso=false;
    }
    
    public ControlMapaAsientosDinamico(ReembolsoVista reembolsoVista, String nombreObra) throws FileNotFoundException{
        this.reembolsoVista = reembolsoVista;
        this.nombreObra = nombreObra;
        mapaB = new MapaAsientosDinamico();
        mapaB.getAceptar().addActionListener(this);
        mapaB.getCancelar().addActionListener(this);
        PrepararVentana();
        CargarValores();
        this.Ruta = nombreObra;
        for (int i = 0; i < 160; i++) {
            mapaB.getBotones()[i].addActionListener(this);
        }
        ColocarOcupados();
        mapaB.setVisible(true);
        reembolso=true;
    }
     
     
     public void actionPerformed(ActionEvent e){
        for (int i = 0; i < mapaB.getBotones().length; i++) {
            
            
            if(mapaB.getBotones()[i]==e.getSource()){
                try {
                    AlternaAsiento(i);
                    if(DeterminarDispo(i)){
                        mapaB.getBotones()[i].setBackground(seleccion);
                        CancelacionDeLugar(i);
                        mapaB.getjPanel1().updateUI();
                        
                    }   else    {
                        ColorearBoton(i);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ControlMapaAsientosDinamico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (mapaB.getAceptar() == e.getSource() && reembolso== false){
            try {
                CrearInventario(Ruta, "D", getNBD());
                CrearInventario(Ruta, "P", getNBP());
                CrearInventario(Ruta, "O", getNBO());
                CrearInventario(Ruta, "L", getNBL());
                CrearInventario(Ruta, "C", getNBC());
                
                venderVista.TablaTexto.setText("Bienvenidos al sistema de ventas \n Patito Feo \n Numero de Boletos comprados. \n Clase Lata: " +
                    BL + "\n Clase Cobre: " + BC + "\n Clase Diamante: " + BD + "Clase Plata: " + BP +
                    "\n Clase Oro: " + BO + "| Y el total costo es de: " + costo);
                mapaB.dispose();
            } catch (IOException ex) {
                Logger.getLogger(ControlMapaAsientosDinamico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(mapaB.getAceptar()== e.getSource() && reembolso){
            mapaB.dispose();
        }
     }

     //Colorea el boton segun la clase
     public void ColorearBoton(int numB){
         if(numB<6 || (numB>13 && numB<26) || (numB>33 &&  numB<40) ){
             mapaB.getBotones()[numB].setBackground(plata);
             NBP--;
             BP--;
             costo = costo - 250;
         }else if((numB>45 && numB<54) || (numB>65 && numB<74) || (numB>85 &&  numB<94)){
             NBD--;
             BD--;
             costo = costo - 300;
             mapaB.getBotones()[numB].setBackground(diamante);
         }else if ((numB>105 && numB<114) || (numB>125 && numB<134) || (numB>145 &&  numB<154)){
             mapaB.getBotones()[numB].setBackground(cobre);
             NBC--;
             BC--;
             costo = costo - 200;
         }else if ((numB>5 && numB<14) || (numB>25 &&  numB<34)){
             mapaB.getBotones()[numB].setBackground(oro);
             NBO--;
             BO--;
             costo = costo - 350;
         }else{
             mapaB.getBotones()[numB].setBackground(lata);
             NBL--;
             BL--;
             costo = costo - 150;
         }
     }
     
     //Si el lugar vuelve a desocuparse, elimina el contador registrado
     public void CancelacionDeLugar(int numB){
         if(numB<6 || (numB>13 && numB<26) || (numB>33 &&  numB<40) ){
             NBP++;
             BP++;
             costo = costo + 250;
         }else if((numB>45 && numB<54) || (numB>65 && numB<74) || (numB>85 &&  numB<94)){
             NBD++;
             BD++;
             costo = costo + 300;
         }else if ((numB>105 && numB<114) || (numB>125 && numB<134) || (numB>145 &&  numB<154)){
             NBC++;
             BC++;
             costo = costo + 200;
         }else if ((numB>5 && numB<14) || (numB>25 &&  numB<34)){
             NBO++;
             BO++;
             costo = costo + 350;
         }else{
             NBL++;
             BL++;
             costo = costo + 150;
         }
     }

    //Carga los valores de la obra. El numero de asientos disponibles segun la clase.
    public void CargarValores() throws FileNotFoundException{
        String[] tipos = {"C","D","L","O","P"};
        int[] CantTipos = new int[5];
        for (int i = 0; i < 5; i++) {
            BufferedWriter Boleto;
            String rutaBoleto = "Resource/"+ Ruta + "/" + tipos[i]  +".txt";
            File boleto1 = new File(rutaBoleto);
            if (boleto1.exists()){
                try {
                    FileReader f = new FileReader(rutaBoleto);
                    BufferedReader b = new BufferedReader(f);
                    CantTipos[i] = (int) Double.parseDouble(b.readLine());
                    b.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControlMapaAsientosDinamico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }
        
        setNBC(CantTipos[0]);
        setNBD(CantTipos[0]);
        setNBL(CantTipos[0]);
        setNBO(CantTipos[0]);
        setNBP(CantTipos[0]);
         
    }
    
    public void ColocarOcupados(){
        int valor=0;
        for (int i = 0; i < 160 ; i++) {
            BufferedWriter Boleto;
            String rutaBoleto = "Resource/"+ Ruta + "/boletos/a" + i  +".txt";
            File boleto1 = new File(rutaBoleto);
            if (boleto1.exists()){
                try {
                    FileReader f = new FileReader(rutaBoleto);
                    BufferedReader b = new BufferedReader(f);
                    valor = (int) Double.parseDouble(b.readLine());
                    if(valor==1){
                        mapaB.getBotones()[i].setBackground(seleccion);
                        setDisponibles(getDisponibles()+1);
                    }
                    
                    b.close();
                } catch (IOException ex) {
                    Logger.getLogger(ControlMapaAsientosDinamico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }
    }
    
    
     
     
     //Se Alterna la disponibilidad del Asiento ---- 0 Disponible --- 1 Ocupado
public void AlternaAsiento(int valor) throws IOException{
    String ruta = "Resource/" + Ruta +"/boletos/a"+ valor + ".txt";
        File archivo = new File(ruta);
        BufferedWriter Boleto;
        BufferedWriter bw;
        if(archivo.exists()) {
            
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            double cadena = Double.parseDouble(b.readLine());
            
            if (cadena == 0){
                File boleto = new File(ruta);
                Boleto = new BufferedWriter(new FileWriter(boleto));
                Boleto.write("1");
                Boleto.close();
            }
            if(cadena == 1){
                File boleto = new File(ruta);
                Boleto = new BufferedWriter(new FileWriter(boleto));
                Boleto.write("0");
                Boleto.close();
            }
            b.close();
        }
}
     
    //Asigna la disponibilidad de cada asiento --- 0 Disponible --- 1 Ocupado
public boolean DeterminarDispo(int valor) throws FileNotFoundException, IOException{
        String ruta = "Resource/" + Ruta +"/boletos/a"+ valor + ".txt";
        File archivo = new File(ruta);
        BufferedWriter Boleto;
        BufferedWriter bw;
        boolean band = false;
        if(archivo.exists()) {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            double cadena = Double.parseDouble(b.readLine());
            
            if (cadena == 0){
                band = false;
            }
            if(cadena == 1){
                band = true;
                
            }
            b.close();
        }
     return band;
}
     
    //Establece cuantos asientos de cada tipo hay y los asigna a los archivos correspondientes.
    public void CrearInventario(String Ruta, String Tipo, double Numboletos) throws IOException{ 
        BufferedWriter Boleto;
        String rutaBoleto = "Resource/"+ Ruta + "/" + Tipo  +".txt";
        
        File boleto1 = new File(rutaBoleto);
        
        if (boleto1.exists()){
            FileReader f = new FileReader(rutaBoleto);
            BufferedReader b = new BufferedReader(f);
            double Total =Numboletos;
            
            Boleto = new BufferedWriter(new FileWriter(boleto1));
            Boleto.write(Total + "");
            Boleto.close();
        }   
    }
   
    public void PrepararVentana(){// darle nombre a todos los botones
        char fila = 'A';
        int cont=0;
        for (int i = 0; i < 160; i++) {
            
            JButton boton= new JButton(""+fila+(i+1)+"");
            mapaB.getjPanel1().add(boton);
            mapaB.getjPanel1().updateUI();
            mapaB.getBotones()[i]=boton;
            if(cont==20){
                fila= (char)(fila+1); // si ya se coloco toda la fila.. entonces se cambia la letra
                cont=0;
            }
           ColorearBoton(i); 
             
            cont++; 
        }
        
       
        System.out.println("sjhkjasdh");
    }
    
   
    //Metodos Gets
    public int getNBP() {
        return NBP;
    }

    public int getNBO() {
        return NBO;
    }

    public int getNBL() {
        return NBL;
    }

    public int getNBD() {
        return NBD;
    }

    public int getNBC() {
        return NBC;
    }

    //Metodos setters
    public void setNBP(int NBP) {
        this.NBP = NBP;
    }

    public void setNBO(int NBO) {
        this.NBO = NBO;
    }

    public void setNBL(int NBL) {
        this.NBL = NBL;
    }

    public void setNBD(int NBD) {
        this.NBD = NBD;
    }

    public void setNBC(int NBC) {
        this.NBC = NBC;
    }

    public void setCosto(int Costo) {
        this.costo = Costo;
    } 

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        ControlMapaAsientosDinamico control = new ControlMapaAsientosDinamico();
        
    }
    
}
