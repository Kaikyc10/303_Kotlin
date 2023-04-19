package com.example.lista1item

public class Cores(nome: String, valor: String) {
    var nome: String = ""
        private set
    var valor: String = " "
        private set

    init{
        require(nome.trim().length > 0){

        }
        require(valor.trim().length > 0){
        }
        this.nome = nome
        this.valor = valor
    }
}