package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.dto.EnderecoDTO;
import com.javanauta.usuario.business.dto.TelefoneDTO;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.entity.Endereco;
import com.javanauta.usuario.entity.Telefone;
import com.javanauta.usuario.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(usuarioDTO.getEnderecos() != null?
                        paraListaEndereco(usuarioDTO.getEnderecos()) : null)
                .telefones(usuarioDTO.getTelefones() != null ?
                        paraListaTelefones(usuarioDTO.getTelefones()) : null)

                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
           return enderecoDTOS.stream().map(this::paraEndereco).toList();

    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();


    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


    //CONVERSAO DE DTO PARA ENTITY

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(usuario.getEnderecos() != null ?
                           paraListaEnderecoDTO(usuario.getEnderecos()) : null)
                .telefones(usuario.getTelefones() != null?
                        paraListaTelefonesDTO(usuario.getTelefones()) : null)

                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> endereco) {
        return endereco.stream().map(this::paraEnderecoDTO).toList();

    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefone){
        return telefone.stream().map(this::paraTelefoneDTO).toList();


    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    //metodo de update somente do usuario
    public Usuario updateUsuario(UsuarioDTO usuarioDto, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDto.getNome() != null ? usuarioDto.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDto.getSenha() !=null ? usuarioDto.getSenha() :entity.getSenha())
                .email(usuarioDto.getEmail() !=null ? usuarioDto.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }
    public Endereco updateEndereco(EnderecoDTO dto, Endereco entity) {
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .complemento(dto.getComplemento() != null ? dto.getComplemento(): entity.getComplemento())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())

                .build();
    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity) {
        return Telefone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())

                .build();
    }

}

