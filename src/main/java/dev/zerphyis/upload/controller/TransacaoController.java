package dev.zerphyis.upload.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import dev.zerphyis.upload.model.importacao.Importacao;
import dev.zerphyis.upload.model.transacao.Transacao;
import dev.zerphyis.upload.service.TransacaoService;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(final TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

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
            return "transacao/resposta";
        }
    }

    @GetMapping("/listar")
    public String listarTransacoes(Model model) {
        List<Transacao> transacoes = transacaoService.listarTodasTransacoes();
        model.addAttribute("transacoes", transacoes);
        return "transacao/listar";
    }
}

