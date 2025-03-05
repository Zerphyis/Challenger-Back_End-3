package dev.zerphyis.upload.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.zerphyis.upload.model.usuario.Usuario;
import dev.zerphyis.upload.service.EmailService;
import dev.zerphyis.upload.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final String MENSAGEM = "mensagem";

    private final UsuarioService usuarioService;

    private final EmailService emailService;

    public UsuarioController(final UsuarioService usuarioService, final EmailService emailService) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    // Mostra o formulário de cadastro de usuário
    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo usuário ao modelo
        return "usuario/form"; // Retorna a página de formulário
    }

    // Mostra o formulário de edição de usuário
    @PutMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id); // Busca o usuário por ID
        model.addAttribute("usuario", usuario.orElseThrow()); // Adiciona o usuário ao modelo
        return "usuario/form"; // Retorna a página de formulário
    }

    // Salva um usuário
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario, Model model) {
        final Usuario usuarioSalvo = usuarioService.criarUsuario(usuario); // Cria o usuário
        model.addAttribute(MENSAGEM, "Usuário salvo com sucesso!");

        final String assunto = "Bem-vindo à nossa aplicação!";
        final String mensagem = new StringBuilder().append("Olá, ").append(usuarioSalvo.getNome())
                .append("!\n\nSua conta foi criada com sucesso.\nSua senha de acesso é: ")
                .append(usuarioSalvo.getSenha()).toString();

        emailService.enviarEmail(usuario.getEmail(), assunto, mensagem);

        return "redirect:/usuarios/listar";
    }

    // Lista todos os usuários
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios(); // Busca todos os usuários
        model.addAttribute("usuarios", usuarios); // Adiciona os usuários ao modelo
        return "usuario/listar";
    }

    // Exclui um usuário
    @DeleteMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable("id") Long id, Model model) {
        usuarioService.excluirUsuario(id); // Exclui o usuário por ID
        model.addAttribute(MENSAGEM, "Usuário excluído com sucesso!");
        return "redirect:/usuarios/listar";
    }
}


