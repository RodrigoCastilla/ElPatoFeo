/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.data;

import com.teatropatito.dominio.Funcion;
import com.teatropatito.dominio.Obra;
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
public class DAOObra extends DAOGeneral<Obra>{
    
    public DAOObra(){
    }

//    agrega la los datos de la obra y agrega los horarios de la obra
    public int agregar(Obra e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();
        
        crearTablaObras();
        
        String orden ="INSERT INTO \"obras\"(nombre,  "
                + "precio_diamante, descripcion, actores, correo, telefono, telefono_alterno, estado"
                + ") VALUES (" +
                "'" + e.getNombre()      + "'," +  " '" + e.getPrecioDiamante() +"', "+
                "'" + e.getDescripcion() + "'," +  " '" + e.getActores()      + "', '" + e.getCorreo()         +"', "+
                "'" + e.getTelefono()    + "'," +  " '" + e.getTelefonoAlt()  + "',  'programada' );"
                ;
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        
       
        DAOFuncion baseDatosFunciones= new DAOFuncion();
        baseDatosFunciones.crearTablaFunciones();
        
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }
    
   
    
    public int eliminar(String condicion) throws SQLException {
         int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM \"obras\" WHERE "+condicion;
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    
    public int modificar(Obra e, String condicion) throws SQLException {
       int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE public.obras SET " +
                " nombre='"+e.getNombre()+"' "+
                " WHERE "+condicion;
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
//        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }
    

    
    public void cancelarObra(String nombre) throws SQLException{
        Connection con = getConeccion();
        char comillas= (char)34;
        String orden = "UPDATE public.obras SET " +
                " estado='cancelada' WHERE nombre= '"+nombre+"';";
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
    }
    

   // busca todas las obras existentes, ayuda para llenar la interfaz grafica
    public ArrayList<Obra> consultar() throws SQLException {
        
      ArrayList<Obra> lista = new ArrayList<Obra>();
      Connection con = getConeccion();
        String seleccion = "SELECT * FROM \"obras\"";
        PreparedStatement ps = con.prepareStatement(seleccion);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Obra obra= new Obra(rs.getString("nombre"));
            lista.add(obra);
        }
        ps.close();
        cerrarConeccion(con);
        return lista;
                
    }

    // busca todas las obras que cumplan cierta condicion
    public ArrayList<Obra> consultar(String condicion) throws SQLException{
        // si no existe ... la crea

        crearTablaObras();

        ArrayList<Obra> lista = new ArrayList<Obra>();
        Obra obra;
        Connection con = getConeccion();
        String orden = "SELECT * FROM \"obras\" WHERE " + condicion;
        Statement sentencia = con.createStatement();
        System.out.println(orden);
        ResultSet rs = sentencia.executeQuery( orden );

        while (rs.next()) {
            obra = new Obra(rs.getString("nombre"), rs.getString("precio_diamante"),
                    rs.getString("descripcion"), rs.getString("actores"), rs.getString("correo") ,
                    rs.getString("telefono"), rs.getString("telefono_alterno"), rs.getString("estado"));
            lista.add( obra );
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

        public void crearTablaObras(){
        
            try {
                crearTabla("obras");
                
                crearColumna("obras", "nombre");
                crearColumna("obras", "precio_diamante");
                crearColumna("obras", "descripcion");
                crearColumna("obras", "actores");
                crearColumna("obras", "correo");
                crearColumna("obras", "telefono");
                crearColumna("obras", "telefono_alterno");
                crearColumna("obras", "estado");

            } catch (SQLException ex) {
                
                System.out.println("la tabla Obra ya existe");
            }
             
    }
}