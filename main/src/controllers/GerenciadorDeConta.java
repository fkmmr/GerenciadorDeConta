/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;

/**
 *
 * @author kamimuraf
 */
public class GerenciadorDeConta {
    
    
    public boolean criarConta(String titular, String agencia, int numero, double saldo) {
       Conta c = new Conta(titular, agencia, numero, saldo);
       
       if(consultaExistencia(c) == true){
           return false;
       }else{
           BdContas.getInstance().getContas().add(c);
           return true;
       } 
       
    }
    
    
    public String listarContas(){
        ArrayList<Conta> contas = BdContas.getInstance().getContas();
        String todasContas = "" ;
        if(contas.isEmpty())
           todasContas = "Não há contas cadastradas!";
        else{
            for(Conta conta: contas){
            todasContas += (" Numero: " + conta.getNumero() +
                            " Agencia: " + conta.getAgencia() +
                            " Titular: " + conta.getTitular() +
                            " Saldo: " + conta.getSaldo() + "\n" );
            }
        }
        return todasContas;
    }
    
   
public boolean removerConta(int numeroDaConta){

        ArrayList<Conta> contas = BdContas.getInstance().getContas();
        for(Conta conta: contas){
            if(conta.getNumero() == numeroDaConta){
                BdContas.getInstance().getContas().remove(conta);
                return true;
            }
        }
        return false;
}
    
public boolean atualizarNomeDoTitular(int numeroDaConta,String novoNomeDoTitular){
    ArrayList<Conta> contas = BdContas.getInstance().getContas();
    for(Conta conta: contas){
        if(conta.getNumero() == numeroDaConta){
            conta.setTitular(novoNomeDoTitular);
            return true;
        }
    }
    return false;
}
    
public String pesquisarAgencia(int numeroDaConta){
    ArrayList<Conta> contas = BdContas.getInstance().getContas();
    String agencia = "Conta não cadastrada";
    for(Conta conta: contas){
        if(conta.getNumero() == numeroDaConta){
            agencia = conta.getAgencia();
        }
    }
    return agencia;
}

public double pesquisarSaldo(int numeroDaConta){
    double saldo = 0;
    ArrayList<Conta> contas = BdContas.getInstance().getContas();
    for (Conta conta: contas){
        if(conta.getNumero() == numeroDaConta){
            saldo = conta.getSaldo();
        }
    }
    return saldo;
}
    
    //aux
    public Boolean consultaExistencia(Conta c){
        for (Conta conta: BdContas.getInstance().getContas()){
            if (c.getNumero() == conta.getNumero())
                   return true;
        }
        return false;
    }
    
}
