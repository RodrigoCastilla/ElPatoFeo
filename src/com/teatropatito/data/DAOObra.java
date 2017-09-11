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

//    public int agregar(Obra e) throws SQLException {
//        int numFilas = 0;
//        Connection con = getConeccion();
//        
//        
//        String orden ="INSERT INTO \"obras\"(nombre, hora_inicio, hora_final, fecha, estado) VALUES (" +
//             "'" + e.getNombre() + "', "  +  "'" + e.getHoraInicio() + "'" + ", " + ""
//   //         "'" + e.getHoraFinal() + "'" +  ", '" + e.getFecha() + "', 'programada' )"
//                ;
//        System.out.println(orden);
//        
//        DAOAsiento baseDatosAsientos= new DAOAsiento();
//        baseDatosAsientos.crearDatos(e.getNombre());
//        
//        crearTablaHorario(e.getNombre()+"Horarios");
//        
//        
//        Statement sentencia = con.createStatement();
//        numFilas = sentencia.executeUpdate(orden);
//        sentencia.close();
//        cerrarConeccion(con);
//        return numFilas;
//
//    }
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
        
       
        
        crearTablaFunciones(e.getNombre()+"_funciones");
        
//        orden ="INSERT INTO \""+ e.getNombre()+"_horarios" +"\" (minuto_inicio,hora_inicio, minuto_final , hora_final, dia, mes, año"
//                + ") VALUES (" +
//                "'" + e.getMinutoInicio()+ "', '" +  e.getHoraInicio()  +"',  "+
//                "'" + e.getMinutoFinal() + "', '" + e.getHoraFinal()    + "', '" + e.getDia() +"', "+
//                "'" + e.getMes()         + "', '" + e.getAño()          + "');"
//                ;
//        
//        System.out.println(orden);
        
//        sentencia = con.createStatement();
//        numFilas= sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;

    }
    
    public void insertarFuncion(Funcion e, int num) throws SQLException{
        Connection con = getConeccion();
        
        Statement sentencia = con.createStatement();

        
        String orden ="INSERT INTO public.\""+ e.getNombre()+"_funciones" +"\" (num,minuto_inicio,hora_inicio, minuto_final , hora_final, dia, mes, año, estado"
                + ") VALUES ("+"'" + num + "',"+
                "'" + e.getMinutoInicio()+ "', '" +  e.getHoraInicio()  +"',  "+
                "'" + e.getMinutoFinal() + "', '" + e.getHoraFinal()    + "', '" + e.getDia() +"', "+
                "'" + e.getMes()         + "', '" + e.getAño()          + "' , 'programada');" ;
        
        System.out.println(orden);
        
         DAOAsiento baseDatosAsientos= new DAOAsiento();
        baseDatosAsientos.crearBaseDatosAsientos(e.getNombre(), num );
        baseDatosAsientos.insertarAsientos(e.getNombre()+num+"");
        
        
        
        sentencia = con.createStatement();
        sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
  
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
                " nombre='"+e.getNombre()+"',"+
                " hora_inicio='"+e.getHoraInicio()+"', "+
                " hora_final='"+e.getHoraFinal()+"', "+
   //             " fecha= '"+ e.getFecha()+"'"+
                " WHERE "+condicion;
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }
    
        public int cancelarFuncion(String nombre, String num) throws SQLException {
       int numFilas = 0;
        Connection con = getConeccion();
        char comillas= (char)34;
        
        String orden = "UPDATE public."+ comillas+nombre+"_funciones"+ comillas + " SET " +
                " estado='cancelada' "+
                " WHERE num='"+num+"';";
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
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
            Obra obra= new Obra(rs.getString("nombre"), rs.getString("hora_inicio"), rs.getString("hora_final"), rs.getString("fecha"));
            lista.add(obra);
        }
        ps.close();
        cerrarConeccion(con);
        return lista;
                
    }
    
    public ArrayList<Funcion> consultarFunciones(String nombre) throws SQLException {
        
      ArrayList<Funcion> lista = new ArrayList<Funcion>();
      Connection con = getConeccion();
        String seleccion = "SELECT * FROM \""+ nombre+ "_funciones\" WHERE estado= 'programada'";
        PreparedStatement ps = con.prepareStatement(seleccion);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Funcion funcion= new Funcion(rs.getString("minuto_inicio"), rs.getString("hora_inicio"), rs.getString("minuto_final"), rs.getString("hora_final"),
                                rs.getString("hora_final"), rs.getString("dia"), rs.getString("mes"), rs.getString("año"), rs.getString("num"));
           
            lista.add(funcion);
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

    public String consultarHora(String condicion)throws SQLException{
        String hora ="";
        Connection con = getConeccion();
        String seleccion = "SELECT hora_inicio FROM \"Obras\" WHERE nombre= '" +condicion +"'";
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( seleccion );
        
        if(rs.next()){ 
          hora =rs.getString("hora_inicio") + " hrs. ";
        }
        sentencia.close();
        cerrarConeccion(con);
        return hora;   
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
    

    
    

    
    public void crearTablaFunciones(String nombre) {
        
        try {
            crearTabla(nombre);
            crearColumna(nombre, "num");
            crearColumna(nombre, "minuto_inicio");
            crearColumna(nombre, "hora_inicio");
            crearColumna(nombre, "minuto_final");
            crearColumna(nombre, "hora_final");
            crearColumna(nombre, "dia");
            crearColumna(nombre, "mes");
            crearColumna(nombre, "año");
            crearColumna(nombre, "estado");
            
            
        } catch (SQLException ex) {
            System.out.println("la tabla de horarios para "+nombre+ "ya fue creada");
        }
        
            
    }
    
    

}
