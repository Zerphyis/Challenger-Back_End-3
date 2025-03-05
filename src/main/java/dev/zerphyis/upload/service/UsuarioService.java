package dev.zerphyis.upload.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.zerphyis.upload.model.usuario.Usuario;
import dev.zerphyis.upload.repository.UsuarioRepository;

@Service
@Transactional(readOnly = false)
public class UsuarioService {

    private static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado.";
    private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Random random = new Random();

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para criar um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new RuntimeException(E_MAIL_JA_CADASTRADO);
            }

            final String senhaGerada = gerarSenhaAleatoria();
            final String senhaCriptografada = passwordEncoder.encode(senhaGerada);

            usuario.setSenha(senhaCriptografada);
        } else {
            final Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException(USUARIO_NAO_ENCONTRADO));

            if (usuarioRepository.existsByEmailAndIdNot(usuario.getEmail(), usuario.getId())) {
                throw new RuntimeException(E_MAIL_JA_CADASTRADO);
            }

            usuario.setSenha(usuarioExistente.getSenha());
        }

        return usuarioRepository.save(usuario);
    }

    // Método para gerar uma senha aleatória
    private String gerarSenhaAleatoria() {
        int senha = 100000 + random.nextInt(900000); // Garante 6 dígitos
        return String.valueOf(senha);
    }

    // Método para listar todos os usuários
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar usuário por ID
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para atualizar um usuário
    public Usuario atualizarUsuario(Long id, String nome, String email) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(nome);
            usuario.setEmail(email);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException(USUARIO_NAO_ENCONTRADO);
    }

    // Método para atualizar a senha de um usuário
    public Usuario atualizarSenha(Long id, String novaSenha) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioExistente = usuario.get();
            usuarioExistente.setSenha(passwordEncoder.encode(novaSenha));
            return usuarioRepository.save(usuarioExistente);
        }
        throw new RuntimeException(USUARIO_NAO_ENCONTRADO);
    }

    // Método para deletar um usuário
    public void excluirUsuario(Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException(USUARIO_NAO_ENCONTRADO);
        }
    }
}
