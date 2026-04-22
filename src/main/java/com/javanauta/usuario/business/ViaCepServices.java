package com.javanauta.usuario.business;


import com.javanauta.usuario.infrastructure.clients.ViaCepClient;
import com.javanauta.usuario.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepServices {


    private final ViaCepClient client;

    public ViaCepDTO buscaDadosEndereco(String cep){
        return client.buscaDadosEndereco(processarCep(cep));

    }

    private String processarCep(String cep) {
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");

        //regex para verificar só tem tem numeros no cep
        if(!cepFormatado.matches("\\d+") || Objects.equals(cepFormatado.length(),8)){
            throw new IllegalArgumentException("O cep contém caracteres inválidos, favor verificar");

        }


        return cepFormatado;
    }


}
