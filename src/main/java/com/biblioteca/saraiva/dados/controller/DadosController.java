package com.biblioteca.saraiva.dados.controller;

import com.biblioteca.saraiva.dados.dto.DadosResponse;
import com.biblioteca.saraiva.dados.service.DadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Dados", description = "Métricas e estatísticas do sistema de vendas")
@RestController
@RequestMapping("/dados")
public class DadosController {

    private final DadosService dadosService;

    public DadosController(DadosService dadosService){
        this.dadosService = dadosService;
    }


    @Operation(summary = "Retorna um resumo de dados sobre lucro e etc...")
    @GetMapping
    public DadosResponse getDados(){
        return dadosService.getDadosGerais();
    }

    @Operation(summary = "Exportar dados em PDF ou XML")
    @GetMapping ("/exportar")
    public void exportar(@RequestParam String formato, HttpServletResponse response) throws Exception {
        DadosResponse dados = dadosService.getDadosGerais();

        if ("xml".equalsIgnoreCase(formato)) {
            response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "attachment; filename=dados.xml");

            com.fasterxml.jackson.dataformat.xml.XmlMapper xmlMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper();

            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), dados);
        }
        else {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=dados.pdf");

            // Transforma o texto em PDF
            String html = dadosService.gerarHtml(dados);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(response.getOutputStream());
        }
    }

}