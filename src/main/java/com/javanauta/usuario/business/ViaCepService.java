package com.javanauta.usuario.business;

import com.javanauta.usuario.infrastructure.clients.ViaCepClient;
import com.javanauta.usuario.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {
//try/catch opcional, questao de estilo/necessidade
    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
        return client.buscaDadosEndereco(processarCep(cep));
    }

    //formatacao do cep para deixar apenas numeros
    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");

        //se o length do cep for diferente de 8 (true) ele joga a exception.
        if(!cepFormatado.matches("\\d+") || Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentException("O CEP contém caracteres inválidos, verifique!");
        }

        return cepFormatado;
    }
}
