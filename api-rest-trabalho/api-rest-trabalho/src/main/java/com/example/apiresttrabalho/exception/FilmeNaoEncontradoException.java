package com.example.apiresttrabalho.exception;

public class FilmeNaoEncontradoException
        extends RuntimeException {

    public FilmeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}