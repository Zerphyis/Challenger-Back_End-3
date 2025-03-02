package dev.Zerphyis.upload.service;

import dev.Zerphyis.upload.Entity.usuarios.Usuarios;
import dev.Zerphyis.upload.repository.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class UsuarioService {
    @Autowired
    private RepositoryUsuarios usuarioRepository;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para criar um novo usuário
    public Usuarios criarUsuario(Usuarios usuario) {
        Optional<Usuarios> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        String senhaGerada = gerarSenhaAleatoria();
        String senhaCriptografada = passwordEncoder.encode(senhaGerada);

        usuario.setSenha(senhaCriptografada);
        Usuarios usuarioSalvo = usuarioRepository.save(usuario);

        String assunto = "Bem-vindo à nossa aplicação!";
        String mensagem = "Olá, " + usuario.getNome() + "!\n\nSua conta foi criada com sucesso.\nSua senha de acesso é: " + senhaGerada;

        emailService.enviarEmail(usuario.getEmail(), assunto, mensagem);

        return usuarioSalvo;
    }

    // Método para gerar uma senha aleatória
    private String gerarSenhaAleatoria() {
        Random random = new Random();
        int senha = 100000 + random.nextInt(900000); // Garante 6 dígitos
        return String.valueOf(senha);
    }

    // Método para listar todos os usuários
    public List<Usuarios> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar usuário por ID
    public Optional<Usuarios> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para atualizar um usuário
    public Usuarios atualizarUsuario(Long id, String nome, String email) {
        Optional<Usuarios> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();
            usuario.setNome(nome);
            usuario.setEmail(email);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuário não encontrado.");
    }

    // Método para atualizar a senha de um usuário
    public Usuarios atualizarSenha(Long id, String novaSenha) {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuarios usuarioExistente = usuario.get();
            usuarioExistente.setSenha(passwordEncoder.encode(novaSenha));
            return usuarioRepository.save(usuarioExistente);
        }
        throw new RuntimeException("Usuário não encontrado.");
    }

    // Método para deletar um usuário
    public void deletarUsuario(Long id) {
        Optional<Usuarios> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
