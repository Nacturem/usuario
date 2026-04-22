package com.javanauta.usuario.infrastructure.clients;


import com.javanauta.usuario.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="via-cep", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/ws{cep}/json/") // get nao precisa de uri
    ViaCepDTO buscaDadosEndereco(@PathVariable("cep") String cep);




}
