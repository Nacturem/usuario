package com.javanauta.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor //construtor com todos argumentos
@NoArgsConstructor //construtor sem argumentos
@Entity
@Table(name = "usuario")//definindo o nome da tablea

public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar id automaticamente
    private Long Id;
    @Column(name = "nome", length = 100) //definir o nome da coluna
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL)// ao excluir o usuário, o endereço será excluido automaticamente.
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") //para fazer junção com a tabela endereço
    private List<Endereco> enderecos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}



