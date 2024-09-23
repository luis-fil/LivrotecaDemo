package com.runapp.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.runapp.negocio.basica.livros.Avaliacao;
import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.AvaliacaoNaoExisteException;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;

/**
 * @author Letícia Baracho
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    private Fachada fachada;

    @PostMapping("/admin/livro")
    public ResponseEntity<String> adicionarLivro(@RequestBody Livro livro) {
        try {
            fachada.adicionarLivro(livro.getTitulo(), livro.getAutor(), livro.getValorLivroFisico(), livro.getValorEbook(),
                    livro.getNumeroPaginas(), livro.getGenero(), livro.getSinopse(), livro.getEditora(), livro.getQuantidade());
            return ResponseEntity.ok("livro adicionado com sucesso");
        } catch (LivroJaExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}/livro")
    public ResponseEntity<?> exibirLivro(@PathVariable Long id) {
        try {
            Livro livro = fachada.procurarLivroId(id);
            return ResponseEntity.ok(livro);
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/livro")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = fachada.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @PatchMapping("/admin/{id}/livro")
    public ResponseEntity<String> atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        try {
            fachada.atualizarLivro(id, livro.getTitulo(), livro.getAutor(), livro.getValorLivroFisico(),
                    livro.getValorEbook(), livro.getNumeroPaginas(), livro.getGenero(), livro.getSinopse(),
                    livro.getEditora(), livro.getQuantidade());
            return ResponseEntity.ok("Livro atualizado com sucesso!");
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cliente/{id}/avaliaco")
    public ResponseEntity<String> avaliarLivro(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        try {
            Cliente cliente = fachada.procurarClienteId(avaliacao.getCliente().getId());
            fachada.avaliarLivro(id, cliente, avaliacao.getTitulo(), avaliacao.getCorpo(), avaliacao.getNota());
            return ResponseEntity.ok("Avaliação adicionada com sucesso!");
        } catch (LivroNaoExisteException | UsuarioNaoExisteException |ClienteNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/admin/{id}/avaliacao/{idAvaliacao}")
    public ResponseEntity<String> removerAvaliacao(@PathVariable Long id, @PathVariable Long idAvaliacao) {
        try {
            fachada.removerAvaliacao(id, idAvaliacao);
            return ResponseEntity.ok("Avaliação removida com sucesso!");
        } catch (LivroNaoExisteException | AvaliacaoNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/admin/{id}/livro")
    public ResponseEntity<?> ProcurarLivroId(@PathVariable Long id) {
        try {
            Livro livro = fachada.procurarLivroId(id);
            return ResponseEntity.ok(livro);
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/usuario/{titulo}/livro")
    public ResponseEntity<?> procurarLivroTitulo(@PathVariable String titulo) {
        try {
            Livro livro = fachada.procurarLivroTitulo(titulo);
            return ResponseEntity.ok(livro);
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/usuario/{autor}/livro")
    public ResponseEntity<?> procurarLivroAutor(@PathVariable String autor) {
        try {
            List<Livro> livros = fachada.procurarLivroAutor(autor);
            return ResponseEntity.ok(livros);
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/usuario/{genero}/livro")
    public ResponseEntity<?> procurarLivroGenero(@PathVariable String genero) {
        try {
            List<Livro> livros = fachada.procurarLivroGenero(genero);
            return ResponseEntity.ok(livros);
        } catch (LivroNaoExisteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}