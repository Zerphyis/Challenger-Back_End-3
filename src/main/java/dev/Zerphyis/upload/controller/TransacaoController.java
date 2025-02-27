package dev.Zerphyis.upload.controller;

import dev.Zerphyis.upload.Entity.importacao.Importacao;
import dev.Zerphyis.upload.Entity.transacao.Transacao;
import dev.Zerphyis.upload.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/mostrar")
    public String mostrarPaginaImportacao(Model model) {
        List<Importacao> importacoes = transacaoService.listarTodasImportacoes();
        model.addAttribute("importacoes", importacoes);
        return "index";
    }

    @PostMapping("/importar")
    public String importarTransacoes(@RequestParam("file") MultipartFile file, Model model) {
        try {
            transacaoService.processarEImportarTransacoes(file);
            model.addAttribute("message", "Transações importadas com sucesso.");
            List<Importacao> importacoes = transacaoService.listarTodasImportacoes();
            model.addAttribute("importacoes", importacoes);
            return "index";
        } catch (Exception e) {
            model.addAttribute("message", "Erro ao importar transações: " + e.getMessage());
            return "respota";
        }
    }

    @GetMapping("/listar")
    public String listarTransacoes(Model model) {
        List<Transacao> transacoes = transacaoService.listarTodasTransacoes();
        model.addAttribute("transacoes", transacoes);
        return "listar_transacao";
    }
    }

