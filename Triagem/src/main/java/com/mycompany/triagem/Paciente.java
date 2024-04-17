/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.triagem;

/**
 *
 * @author 823129492
 */
public class Paciente {
    String nome;
    String telefone;
    String cpf;
    String sexo;
    String endereco;
    String email;
    String idade;
    int pontuacao;
    
    public Paciente(String nome, String telefone, String cpf, String sexo, 
            String endereco, String email, String idade, int pontuacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;
        this.email = email;
        this.idade = idade;
        this.pontuacao = pontuacao;
    }
    
    public void exibePaciente() {
        System.out.printf("nome: %s\ntelefone: %s\ncpf: %s\nsexo: %s\nendereço: %s\ne-mail: %s\nidade: %s\npontuação: %s", nome, telefone, cpf, sexo, endereco, email, idade, pontuacao);
    }
}
