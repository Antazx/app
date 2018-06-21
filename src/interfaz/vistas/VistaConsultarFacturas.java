/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.vistas;

import interfaz.controladores.CtrVistaConsultarFacturas;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author GUillermo
 */
public class VistaConsultarFacturas extends javax.swing.JFrame {
    
    
    private CtrVistaConsultarFacturas controlador;
    /**
     * Creates new form VistaConsultarFacturas
     */
    public VistaConsultarFacturas() {
        initComponents();
        controlador = new CtrVistaConsultarFacturas(this);
        error.setVisible(false);
        proveedorText.setVisible(false);
        proveedorCombo.setVisible(false);
        consultarFacturas.setVisible(false);
        todosLosProveedores.setVisible(false);
    }
    
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fechaInicio = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        fechaFin = new datechooser.beans.DateChooserCombo();
        proveedorText = new javax.swing.JLabel();
        proveedorCombo = new javax.swing.JComboBox<>();
        scrollPanel = new javax.swing.JScrollPane();
        textFacturas = new javax.swing.JTextArea();
        cancelar = new javax.swing.JButton();
        consultar = new javax.swing.JButton();
        consultarFacturas = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        todasLasFacturas = new javax.swing.JButton();
        todosLosProveedores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fecha Inicio:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Consultar  facturas  pendientes  de  pago:");

        jLabel3.setText("Fecha Fin:");

        proveedorText.setText("Proveedor:");

        proveedorCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                proveedorComboItemStateChanged(evt);
            }
        });
        proveedorCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                proveedorComboMousePressed(evt);
            }
        });
        proveedorCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorComboActionPerformed(evt);
            }
        });

        textFacturas.setColumns(20);
        textFacturas.setRows(5);
        textFacturas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textFacturas.setEnabled(false);
        scrollPanel.setViewportView(textFacturas);

        cancelar.setText("Cancelar");
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelarMousePressed(evt);
            }
        });

        consultar.setText("Consultar");
        consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                consultarMousePressed(evt);
            }
        });

        consultarFacturas.setText("Consultar facturas");
        consultarFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                consultarFacturasMousePressed(evt);
            }
        });

        error.setForeground(new java.awt.Color(255, 0, 0));
        error.setText("ERROR");

        todasLasFacturas.setText("Consultar todas las facturas del año");
        todasLasFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                todasLasFacturasMousePressed(evt);
            }
        });

        todosLosProveedores.setText("Consultar facturas de todos los proveedores");
        todosLosProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                todosLosProveedoresMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(proveedorText)
                        .addGap(18, 18, 18)
                        .addComponent(proveedorCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consultarFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todasLasFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todosLosProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPanel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(error)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(proveedorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(proveedorText))
                        .addGap(18, 18, 18)
                        .addComponent(consultarFacturas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(todosLosProveedores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(todasLasFacturas)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelar)
                            .addComponent(consultar))))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMousePressed
        error.setVisible(false);
        consultar.setVisible(false);
        controlador.procesaEventoIntroduceDatos();  
    }//GEN-LAST:event_consultarMousePressed

    private void cancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMousePressed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelarMousePressed

    private void proveedorComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_proveedorComboItemStateChanged
        // TODO add your handling code here:
        
        consultarFacturas.setVisible(true);
    }//GEN-LAST:event_proveedorComboItemStateChanged

    private void proveedorComboMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedorComboMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorComboMousePressed

    private void consultarFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarFacturasMousePressed
        // TODO add your handling code here:
        
        controlador.procesaEventoProveedorSel();
    }//GEN-LAST:event_consultarFacturasMousePressed

    private void todasLasFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todasLasFacturasMousePressed
        // TODO add your handling code here:
        controlador.todasLasFacturas();
    }//GEN-LAST:event_todasLasFacturasMousePressed

    private void todosLosProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_todosLosProveedoresMousePressed
        // TODO add your handling code here:
        controlador.todosLosProveedores();
    }//GEN-LAST:event_todosLosProveedoresMousePressed

    private void proveedorComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorComboActionPerformed
     public LocalDate getFechaInicio(){
        
        int dia = fechaInicio.getCurrent().get(Calendar.DAY_OF_MONTH);
        int mes = fechaInicio.getCurrent().get(Calendar.MONTH);
        int año = fechaInicio.getCurrent().get(Calendar.YEAR);
        
        LocalDate fi = LocalDate.of(año, mes+1, dia);
        return fi;
    }
     
     public LocalDate getFechaFin(){
         
        int dia = fechaFin.getCurrent().get(Calendar.DAY_OF_MONTH);
        int mes = fechaFin.getCurrent().get(Calendar.MONTH);
        int año = fechaFin.getCurrent().get(Calendar.YEAR);
        
        LocalDate ff = LocalDate.of(año, mes+1, dia);
        return ff;
    }
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
            java.util.logging.Logger.getLogger(VistaConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaConsultarFacturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton consultar;
    private javax.swing.JButton consultarFacturas;
    private javax.swing.JLabel error;
    private datechooser.beans.DateChooserCombo fechaFin;
    private datechooser.beans.DateChooserCombo fechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> proveedorCombo;
    private javax.swing.JLabel proveedorText;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JTextArea textFacturas;
    private javax.swing.JButton todasLasFacturas;
    private javax.swing.JButton todosLosProveedores;
    // End of variables declaration//GEN-END:variables

    public void mostrarError(String msg) {
        
        
        error.setText(msg);
        error.setVisible(true);
        consultar.setVisible(true);
    }

    public void mostrarProveedor(String string) {
        todosLosProveedores.setVisible(true);
        proveedorText.setVisible(true);
        proveedorCombo.setVisible(true);
        proveedorCombo.addItem(string);
    }

    public int getNombreProveedor() {
        return proveedorCombo.getSelectedIndex();
    }

    public void mostraFacturas(String datosFactura) {
       textFacturas.setText("");
       textFacturas.setText(datosFactura);
    }
}
