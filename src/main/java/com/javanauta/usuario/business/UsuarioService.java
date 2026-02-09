package com.javanauta.usuario.business;


import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.entity.Usuario;
import com.javanauta.usuario.exceptions.ConflictException;
import com.javanauta.usuario.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.infrastructure.security.JwtUtil;
import com.javanauta.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtutil;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario =
                usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));

    }



    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);

            }

        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado "+ e.getCause());
        }

    }
    // * esse método fora do trycath é para caso precise verificar se o email existe em algum outro momento */
    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);

    }
    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado" + email));
        //.orElseThrow(); caso usuario nao existe, o optiona vai enviar uma mensagem de erro
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);

    }

    //tira email do token para nao obrigar usuario passar email
    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtutil.extrairEmailToken(token.substring(7));

        //criptografia de senha
        dto.setSenha(dto.getSenha() != null? passwordEncoder.encode(dto.getSenha()) : null);

        //busca os dados do usuario no db
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
         new ResourceNotFoundException("Email nao localizado"));
        //mescla os dados que recebemos da requisicao dto com db
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        //coloca criptografia na senha novamente

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }

}
