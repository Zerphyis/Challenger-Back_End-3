package dev.Zerphyis.upload.controller;

import dev.Zerphyis.upload.Entity.records.DadosUsuarios;
import dev.Zerphyis.upload.Entity.usuarios.Usuarios;
import dev.Zerphyis.upload.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/criar")
    public String mostrarFormularioCriar(Model model) {
        model.addAttribute("usuario", new Usuarios()); // Passa um objeto vazio para o formulário
        return "usuario_form";
    }

    // Cria um novo usuário
    @PostMapping("/criar")
    public String criarUsuario(@ModelAttribute Usuarios usuario, Model model) {
        usuarioService.criarUsuario(usuario); // Salva o usuário no banco de dados
        model.addAttribute("mensagem", "Usuário criado com sucesso!");
        return "redirect:/usuarios/listar";
    }

    // Exibe todos os usuários cadastrados
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuarios> usuarios = usuarioService.listarUsuarios(); // Obtém a lista de usuários
        model.addAttribute("usuarios", usuarios);
        return "usuario_listar";
    }

    // Atualiza um usuário existente
    @GetMapping("/atualizar/{id}")
    public String atualizarUsuario(@PathVariable("id") Long id, @ModelAttribute Usuarios usuario, Model model) {
        usuario.setId(id);  // Garante que o ID correto será mantido
        usuarioService.criarUsuario(usuario); // Método de criação pode ser reutilizado para atualização
        model.addAttribute("mensagem", "Usuário atualizado com sucesso!");
        return "usuario_atualizar";
    }

    // Deleta um usuário
    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable("id") Long id, Model model) {
        usuarioService.deletarUsuario(id); // Deleta o usuário do banco de dados
        model.addAttribute("mensagem", "Usuário deletado com sucesso!");
        return "redirect:/usuarios/listar";
    }
}



