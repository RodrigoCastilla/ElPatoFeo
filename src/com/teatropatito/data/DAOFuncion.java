/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teatropatito.data;

import com.teatropatito.dominio.Asiento;
import com.teatropatito.dominio.Funcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author GCTec
 */
public class DAOFuncion extends DAOGeneral<Funcion> {

    public void agregar(Funcion e, int num) throws SQLException{
        Connection con = getConeccion();
        
        Statement sentencia = con.createStatement();

        
        String orden ="INSERT INTO public.\"funciones\" (num,minuto_inicio,hora_inicio, minuto_final , hora_final, dia, mes, año, estado"
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
    


    
    public ArrayList<Funcion> consultar(String nombre) throws SQLException {
        
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
    public ArrayList<Funcion> consultarFuncionesCoincidentes(String nombre) throws SQLException {
        
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


    @Override
    public int eliminar(String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int modificar(Funcion funcion, String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();
        char comillas= (char)34;
        
        String orden = "UPDATE public."+ comillas+funcion.getNombre()+"_funciones"+ comillas + " SET " +
                " minuto_inicio="+funcion.getMinutoInicio()+
                " hora_inicio="+funcion.getHoraFinal()+
                " minuto_final="+funcion.getMinutoFinal()+
                " hora_final="+funcion.getHoraFinal()+
                " dia="+funcion.getDia()+
                " mes="+funcion.getMes()+
                " año="+funcion.getAño()+
                
                
                " WHERE num='"+funcion.getNumero()+"';";
        
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }


        public void crearTablaFunciones() {
        
        try {
            crearTabla("funciones");
            crearColumna("funciones", "num");
            crearColumna("funciones", "minuto_inicio");
            crearColumna("funciones", "hora_inicio");
            crearColumna("funciones", "minuto_final");
            crearColumna("funciones", "hora_final");
            crearColumna("funciones", "dia");
            crearColumna("funciones", "mes");
            crearColumna("funciones", "año");
            crearColumna("funciones", "estado");
            
            
        } catch (SQLException ex) {
            System.out.println("la tabla de horarios para funciones ya fue creada");
        }  
    
    }

    @Override
    public int agregar(Funcion entidad) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public static void main(String[] args) {
        
        DAOFuncion dao = new DAOFuncion();
        Funcion fun= new Funcion("","","","","","","","");
        
    }
    
    
}