
package com.teatropatito.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class EliminarObraVista extends javax.swing.JFrame {


    public EliminarObraVista() {
        initComponents();
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public JLabel getEtiquetaObra() {
        return etiquetaObra;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getTabla() {
        return Tabla;
    }

    public void setTabla(JTable Tabla) {
        this.Tabla = Tabla;
    }

    public JButton getCancelarFuncion() {
        return cancelarFuncion;
    }

    public void setCancelarFuncion(JButton cancelarFuncion) {
        this.cancelarFuncion = cancelarFuncion;
    }

    
    public JButton getBotonDatosTabla() {
        return botonDatosTabla;
    }

    public void setBotonDatosTabla(JButton botonDatosTabla) {
        this.botonDatosTabla = botonDatosTabla;
    }
    
    

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JComboBox<String> getListaObras() {
        return listaObras;
    }

    public JButton getRegresar() {
        return regresar;
    }

    public JComboBox<String> getNumeroObra() {
        return numeroObra;
    }

    public void setNumeroObra(JComboBox<String> numeroObra) {
        this.numeroObra = numeroObra;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listaObras = new javax.swing.JComboBox<>();
        etiquetaObra = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        botonDatosTabla = new javax.swing.JButton();
        cancelarFuncion = new javax.swing.JButton();
        numeroObra = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(420, 220));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(listaObras);
        listaObras.setBounds(160, 50, 231, 20);

        etiquetaObra.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        etiquetaObra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teatropatito/controlador/fotos/obras.jpg"))); // NOI18N
        getContentPane().add(etiquetaObra);
        etiquetaObra.setBounds(30, 30, 110, 50);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teatropatito/controlador/fotos/eliminar obra.jpg"))); // NOI18N
        getContentPane().add(eliminar);
        eliminar.setBounds(60, 330, 110, 50);

        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teatropatito/controlador/fotos/Cancelar.jpg"))); // NOI18N
        getContentPane().add(regresar);
        regresar.setBounds(270, 340, 110, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teatropatito/controlador/fotos/PatoteInicial.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 430, 180);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "num", "hora inicio", "hora final", "fecha"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(500, 30, 490, 300);

        botonDatosTabla.setText("ver Funciones");
        getContentPane().add(botonDatosTabla);
        botonDatosTabla.setBounds(200, 230, 130, 23);

        cancelarFuncion.setText("cancelar Funcion");
        getContentPane().add(cancelarFuncion);
        cancelarFuncion.setBounds(770, 370, 130, 23);

        numeroObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(numeroObra);
        numeroObra.setBounds(540, 360, 180, 40);

        setSize(new java.awt.Dimension(1041, 488));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EliminarObraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarObraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarObraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarObraVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarObraVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton botonDatosTabla;
    private javax.swing.JButton cancelarFuncion;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel etiquetaObra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listaObras;
    private javax.swing.JComboBox<String> numeroObra;
    private javax.swing.JButton regresar;
    // End of variables declaration//GEN-END:variables
}
