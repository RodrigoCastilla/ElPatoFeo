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

        
        String orden ="INSERT INTO public.\"funciones\" (num,obra, minuto_inicio,hora_inicio,"
                                                                            + " minuto_final , hora_final, dia, mes, año, estado"
                + ") VALUES ("+"'" + num + "', '" + e.getNombre()       +"' ,"+
                "'" + e.getMinutoInicio()+ "', '" + e.getHoraInicio()   +"', "+
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
    
    public int cancelarFuncion(String nombreObra, String num) throws SQLException {
    
        int numFilas = 0;
        Connection con = getConeccion();
        char comillas= (char)34;
        
        String orden = "UPDATE public."+ comillas+"funciones"+ comillas + " SET " +
                " estado='cancelada' "+
                " WHERE (num='"+num+"') and (obra='"+ nombreObra + "');";
        
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
        String seleccion = "SELECT * FROM \"funciones\" WHERE estado= 'programada' and "+ nombre;
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
    
    public ArrayList<Funcion> consultarTodas() throws SQLException {
        
        ArrayList<Funcion> lista = new ArrayList<Funcion>();
        Connection con = getConeccion();
        String seleccion = "SELECT * FROM \"funciones\" ";
        PreparedStatement ps = con.prepareStatement(seleccion);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Funcion funcion= new Funcion(rs.getString("hora_inicio"), rs.getString("hora_final"), rs.getString("minuto_inicio"), rs.getString("minuto_final"),
                                            rs.getString("dia"), rs.getString("mes"), 
                                                rs.getString("año"), rs.getString("num"), rs.getString("obra"));
           
            lista.add(funcion);
        }
        ps.close();
        cerrarConeccion(con);
        return lista;
                
    }
    public ArrayList<Funcion> consultarProgramadas(String nombre) throws SQLException {
        
        ArrayList<Funcion> lista = new ArrayList<Funcion>();
        Connection con = getConeccion();
        String seleccion = "SELECT * FROM \"funciones\" WHERE obra= '"+nombre+"'";
        PreparedStatement ps = con.prepareStatement(seleccion);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Funcion funcion= new Funcion(rs.getString("minuto_inicio"), rs.getString("hora_inicio"), rs.getString("minuto_final"), rs.getString("hora_final"),
                                rs.getString("hora_final"), rs.getString("dia"), rs.getString("mes"), rs.getString("año"), rs.getString("num"), rs.getString("estado"));
           
            lista.add(funcion);
        }
        ps.close();
        cerrarConeccion(con);
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getEstado().compareTo("cancelada") == 0)
                lista.remove(i);
        }
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
        
        String orden = "UPDATE public."+ comillas+"funciones"+ comillas + " SET " +
                " minuto_inicio='"+funcion.getMinutoInicio()+ "'," +
                " hora_inicio='"+funcion.getHoraFinal()+ "'," +
                " minuto_final'="+funcion.getMinutoFinal()+  "'," +
                " hora_final='"+funcion.getHoraFinal()+  "'," +
                " dia='"+funcion.getDia()+  "'," +
                " mes='"+funcion.getMes()+  "'," +
                " año='"+funcion.getAño()+  "'," +
                " estado_modificacion= 'modificada' " +
                
                " WHERE num='"+funcion.getNumero()+"' and"+ condicion+ ";";
        
        
        System.out.println(orden);
        Statement sentencia = con.createStatement();
//        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    
    public void crearTablaFunciones() {

        try {
            crearTabla("funciones");

            crearColumna("funciones", "num"          );
            crearColumna("funciones", "obra"         );
            crearColumna("funciones", "minuto_inicio");
            crearColumna("funciones", "hora_inicio"  );
            crearColumna("funciones", "minuto_final" );
            crearColumna("funciones", "hora_final"   );
            crearColumna("funciones", "dia"          );
            crearColumna("funciones", "mes"          );
            crearColumna("funciones", "año"          );
            crearColumna("funciones", "estado"       );
            crearColumna("funciones", "estado_modificacion");


        } catch (SQLException ex) {
            System.out.println("la tabla de funcion ya fue creada");
        }  

    }

    public boolean verificarExistenciaFuncion(Funcion funcion) throws SQLException{
        boolean repetido = false;
        ArrayList<Funcion> funciones = consultarTodas();
        for(int i=0; i<funciones.size(); i++){
            if(funciones.get(i).getDia().compareTo(funcion.getDia())==0 && funciones.get(i).getMes().compareTo(funcion.getMes())==0 && funciones.get(i).getAño().compareTo(funcion.getAño())==0){
                if(((Integer.parseInt(funciones.get(i).getHoraInicio()) >= Integer.parseInt(funcion.getHoraInicio())) && (Integer.parseInt(funciones.get(i).getHoraFinal()) <= Integer.parseInt(funcion.getHoraFinal()))) ||
                    ((Integer.parseInt(funciones.get(i).getHoraInicio()) >= Integer.parseInt(funcion.getHoraInicio())) && (Integer.parseInt(funciones.get(i).getHoraFinal()) <= Integer.parseInt(funcion.getHoraFinal()))) /*||
                    ((Integer.parseInt(funciones.get(i).getHoraInicio()) >= Integer.parseInt(funcion.getHoraInicio())) && (Integer.parseInt(funciones.get(i).getHoraFinal()) <= Integer.parseInt(funcion.getHoraFinal()))) ||
                    ((Integer.parseInt(funciones.get(i).getHoraInicio()) >= Integer.parseInt(funcion.getHoraInicio())) && (Integer.parseInt(funciones.get(i).getHoraFinal()) <= Integer.parseInt(funcion.getHoraFinal())))*/){
                    repetido = true;
                    
                }
            }
        }

        return repetido;
    }
    
//        public void crearTablaFunciones(String nombre) {
//        
//        try {
//            crearTabla(nombre);
//            
//            crearColumna(nombre, "num");
//            crearColumna(nombre, "minuto_inicio");
//            crearColumna(nombre, "hora_inicio");
//            crearColumna(nombre, "minuto_final");
//            crearColumna(nombre, "hora_final");
//            crearColumna(nombre, "dia");
//            crearColumna(nombre, "mes");
//            crearColumna(nombre, "año");
//            crearColumna(nombre, "estado");
//            
//            
//        } catch (SQLException ex) {
//            System.out.println("la tabla de horarios para "+nombre+ "ya fue creada");
//        }  
//    
//    }

    @Override
    public int agregar(Funcion entidad) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public static void main(String[] args) throws SQLException {
        
        DAOFuncion dao = new DAOFuncion();
        Funcion fun= new Funcion("","","","","","","","");
        if(dao.verificarExistenciaFuncion(fun))
            System.out.println("Ups, Existe");
        else
            System.out.println("Parece que es nueva");
        
    }
    
    
    
}