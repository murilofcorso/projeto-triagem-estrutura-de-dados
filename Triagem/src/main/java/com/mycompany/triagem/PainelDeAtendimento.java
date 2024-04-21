package com.mycompany.triagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PainelDeAtendimento extends javax.swing.JFrame {  
    Connection con;
    
    public PainelDeAtendimento() {
        initComponents();
        this.abrirConexao();
    }
    
    public void abrirConexao() {
        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();
    }
    
    public ArrayList<String> getFilaDePacientes() {
        ArrayList<Integer> filaDePacientes = new ArrayList<>();
        ArrayList<String> filaDeAtendimento = new ArrayList<>();
        try {          
            ResultSet rsFila = con.prepareCall("SELECT * FROM fila_de_espera ORDER BY pontuacao").executeQuery();
            while(rsFila.next()) {
                filaDePacientes.add(rsFila.getInt("id_paciente"));
            }           
            for(int idPaciente: filaDePacientes) {
                PreparedStatement stmt = con.prepareStatement("SELECT nome FROM pacientes WHERE id = ?");
                stmt.setInt(1, idPaciente);
                ResultSet rsPacientes = stmt.executeQuery();
                rsPacientes.next();
                filaDeAtendimento.add(rsPacientes.getString("nome"));
            }    
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return filaDeAtendimento;
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
