/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.data;

import com.teatropatito.dominio.Asiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author melee_000
 */
public class DAOAsiento extends DAOGeneral<Asiento>{
    
    public DAOAsiento(){
    }

    public int agregar(Asiento e) throws SQLException {
    int numFilas = 0;
        Connection con = getConeccion();

        String orden ="INSERT INTO \"Asiento\"(clave_asiento, costo, control_asiento, nombre_obra, zona_asiento) VALUES (" +
            (e.getClaveAsiento() ==null? null: "'" + e.getClaveAsiento() + "' ") + ", " + e.getCosto() + ", "+
            e.getControlAsiento() + ", "+ (e.getNombreObra() ==null? null: "'" + e.getNombreObra() + "' ") +","+
                (e.getZona() ==null? null: "'" + e.getZona() + "' ") + ")";

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;   
    }

    public int eliminar(String condicion) throws SQLException {
         int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM \"Asiento\" WHERE "+ condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    
    public int modificar(Asiento e, String condicion) throws SQLException {
       int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE \"Asiento\" SET " +
                " clave_asiento='"+e.getClaveAsiento()+"' "+                
                " costo='"+e.getCosto()+"' "+
                " control_asiento='"+e.getControlAsiento()+"' "+
                " WHERE "+condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }


    
    public ArrayList consultar(String condicion) throws SQLException {
        ArrayList<Asiento> lista = new ArrayList<Asiento>();
        Asiento e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM \"Asiento\" " +
                (condicion==null || condicion.length()==0 ? "":"WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( orden );

        while (rs.next()) {
            e = new Asiento(rs.getString("clave_asiento"),
                     rs.getInt("costo"), rs.getInt("control_asiento"), rs.getString("nombre_obra"), rs.getString("zona"));
            lista.add( e );
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }
    
    public int consultarDisponibilidad(String condicion) throws SQLException {
            
      int disponibilidad =0;
      Connection con = getConeccion();
        String seleccion = "SELECT COUNT(*) AS cuenta FROM \"Asiento\" WHERE control_asiento= 1 AND nombre_obra=" +"'"+condicion+"'";
        PreparedStatement ps = con.prepareStatement(seleccion);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){ 
        disponibilidad = rs.getInt("cuenta");
        
        }
        ps.close();
        cerrarConeccion(con);
        return disponibilidad;
                
    }
    public int consultarControl(String condicion1, String condicion2) throws SQLException {
      int colorR =0;
      Connection con = getConeccion();
        String seleccion = "SELECT control_asiento FROM \"Asiento\" WHERE nombre_obra= '" +condicion1 +"' AND clave_asiento='"+ condicion2 + "'";
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( seleccion );
        
        if(rs.next()){ 
        colorR = Integer.parseInt(rs.getString("control_asiento"));  
        }
        sentencia.close();
        cerrarConeccion(con);
        return colorR;      
    }
    
    public String consultarAsientos(String cond, boolean bool)throws SQLException{
        int x=2;
        if(bool == false){
            x=3;
        }
        String asientos ="";
        Connection con = getConeccion();
        String seleccion = "SELECT clave_asiento FROM \"Asiento\" WHERE nombre_obra= '" +cond +"' AND control_asiento="+ x +"";
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( seleccion );
        
        while(rs.next()){ 
          asientos = asientos +rs.getString("clave_asiento") + ", ";
        }
        sentencia.close();
        cerrarConeccion(con);
        return asientos;   
    }
    
    public void insertarAsientos(String nombre) throws SQLException{
        Connection con = getConeccion();
        char comillas= (char)34;
        Statement sentencia = con.createStatement();
        int cont=1;
        char letra = 'A';
        for (int i = 0; i < 160; i++) {
            
            String insertar= "INSERT INTO public."+comillas+nombre+comillas+"( clave, estado)" + " VALUES ('"+ letra+ cont+"','libre');"; 
            sentencia.execute(insertar);
            cont++;
            if(cont==21){
                cont=1;
                letra= (char)(letra+1);
            }
        }
        sentencia.close();
        cerrarConeccion(con);
    }
    
    public void crearBaseDatos(String nombre) throws SQLException{
        Connection con = getConeccion();
        Statement sentencia = con.createStatement();
        char comillas= (char)34;
        String crear= "CREATE TABLE public."+comillas+nombre+comillas+"_asientos"+ " () WITH ( OIDS = FALSE); ALTER TABLE public."+comillas+nombre+comillas+" OWNER to postgres;"; 
        System.out.println(crear);
        sentencia.executeUpdate(crear);
        
        crearColumna(nombre,"clave");
        crearColumna(nombre,"estado");
        
        insertarAsientos(nombre);
        
        sentencia.close();
        cerrarConeccion(con);
    }
    // en desarrollo
    public void crearBaseDatosAsientos(String nombre, int num) throws SQLException{
        crearTabla(nombre+num+"");
        crearColumna(nombre+num+"", "clave");
        crearColumna(nombre+num+"", "estado");
        
        
    }
    
    
//    public void crearColumnas(String nombreTabla) throws SQLException{
//        Connection con = getConeccion();
//        Statement sentencia = con.createStatement();  
//        char Comillas= (char)34;
//        
//        String creaColumna = "ALTER TABLE public."+Comillas+nombreTabla+Comillas+" ADD COLUMN " +"clave" +" text;";
//        sentencia.execute(creaColumna);
//        
//        creaColumna = "ALTER TABLE public."+Comillas+nombreTabla+Comillas+" ADD COLUMN "  +"estado"+ " text;";
//        sentencia.execute(creaColumna);
//        
//        sentencia.close();
//        cerrarConeccion(con);
//    }
//    
    
   
    
}
