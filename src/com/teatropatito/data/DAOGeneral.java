/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.data;

/**
 *
 * @author melee_000
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class DAOGeneral<T> {

    private String host="localhost";
    private String bd="postgres";
    private String login="postgres";
    private String password="chino1212";
    private boolean cargadoDriver;

    public DAOGeneral() {
        cargarDriver();
   }

    public void cargarDriver(){
        try {
            if ( !cargadoDriver ) {
                Class.forName("org.postgresql.Driver");
                cargadoDriver = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


     public Connection getConeccion(String host, String bd, String login, String password){
        Connection conexion=null;
        String urlConexion = "jdbc:postgresql://"+ host +"/" + bd;
        try {
            conexion=DriverManager.getConnection(urlConexion, login, password);
        }catch (SQLException e) { e.printStackTrace(); }
        return conexion;
    }

     public Connection getConeccion(){
        return getConeccion(host, bd, login, password );
    }

    public void cerrarConeccion(Connection con){
        try {
            if ( con != null )
                if ( !con.isClosed() )    // Si no esta cerrada, se cierra
                    con.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public void renombrarTabla(String nombreAntiguo, String nuevoNombre){
        try {
            Connection con = getConeccion();
            Statement sentencia = con.createStatement();
            
            String renombrar = "ALTER TABLE public."+ nombreAntiguo +" RENAME TO "+ nuevoNombre+";" ;
            sentencia.execute(renombrar);
            
            
            sentencia.close();   
            cerrarConeccion(con);
        } catch (SQLException ex) {
            Logger.getLogger(DAOGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    
        public void crearTabla(String nombre) 
        {
        try {
            char comillas= (char)34;
            Connection con = getConeccion();
            String crear= "CREATE TABLE public."+comillas+nombre+comillas+"() WITH ( OIDS = FALSE); ALTER TABLE public."+comillas +nombre+comillas+" OWNER to postgres;";
            System.out.println(crear);
            Statement sentencia = con.createStatement();
            sentencia.execute(crear);
            
            
            sentencia.close();
            cerrarConeccion(con);
        } catch (SQLException ex) {
            System.out.println("ya existe");
        }
        
    }
        
       public void crearColumna(String nombreTabla, String nomColumna) throws SQLException{
        Connection con = getConeccion();
        Statement sentencia = con.createStatement();  
        char comillas= (char)34;
        
        String creaColumna = "ALTER TABLE public."+comillas+nombreTabla+comillas+" ADD COLUMN " + comillas + nomColumna + comillas +" text;";
        System.out.println(creaColumna);
        sentencia.execute(creaColumna);
        
        sentencia.close();
        cerrarConeccion(con);
    }

    public abstract int agregar(T entidad)throws SQLException;

    public abstract int eliminar(String condicion)throws SQLException;

    public abstract int modificar(T entidad, String condicion)throws SQLException;

    public abstract ArrayList<T> consultar(String condicion)throws SQLException;

      public String getHost() {
        return host;
    }

    public String getBd() {
        return bd;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCargadoDriver() {
        return cargadoDriver;
    }
}