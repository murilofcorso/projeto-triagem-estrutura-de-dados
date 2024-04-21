package com.mycompany.triagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PainelDeAtendimento extends javax.swing.JFrame {  
    Connection con;
    ArrayList<String> filaDePacientesNomes;
    String nomePacienteAtual;
    ArrayList<Integer> filaDePacientesIds;
    int idPacienteAtual;
    
    public PainelDeAtendimento() {
        initComponents();
        this.abrirConexao();
    }
    
    public void abrirConexao() {
        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();
    }
    
    public void getFilaDePacientes() {
        ArrayList<Integer> filaDePacientesIds = new ArrayList<>();
        ArrayList<String> filaDePacientesNomes = new ArrayList<>();
        try {          
            ResultSet rsFila = con.prepareCall("SELECT * FROM fila_de_espera ORDER BY pontuacao DESC").executeQuery();
            while(rsFila.next()) {
                filaDePacientesIds.add(rsFila.getInt("id_paciente"));
            }           
            for(int idPaciente: filaDePacientesIds) {
                PreparedStatement stmt = con.prepareStatement("SELECT nome FROM pacientes WHERE id = ?");
                stmt.setInt(1, idPaciente);
                ResultSet rsPacientes = stmt.executeQuery();
                rsPacientes.next();
                filaDePacientesNomes.add(rsPacientes.getString("nome"));
            }    
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        this.filaDePacientesNomes = filaDePacientesNomes;
        this.filaDePacientesIds = filaDePacientesIds;
        this.nomePacienteAtual = filaDePacientesNomes.get(0);
        this.idPacienteAtual = filaDePacientesIds.get(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pacienteAtualLabel = new javax.swing.JLabel();
        proximoPacienteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Paciente atual: ");

        pacienteAtualLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        pacienteAtualLabel.setText("0");

        proximoPacienteButton.setText("Próximo paciente");
        proximoPacienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximoPacienteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximoPacienteButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(pacienteAtualLabel)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pacienteAtualLabel))
                .addGap(50, 50, 50)
                .addComponent(proximoPacienteButton)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proximoPacienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximoPacienteButtonActionPerformed
        if(this.nomePacienteAtual != null) {
            try {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM fila_de_espera WHERE id_paciente = ?");
                stmt.setInt(1, idPacienteAtual);
                stmt.execute();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        getFilaDePacientes();
        pacienteAtualLabel.setText(nomePacienteAtual);
    }//GEN-LAST:event_proximoPacienteButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PainelDeAtendimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel pacienteAtualLabel;
    private javax.swing.JButton proximoPacienteButton;
    // End of variables declaration//GEN-END:variables
}
