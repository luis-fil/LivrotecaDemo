package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.basica.livros.Avaliacao;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FachadaLivroTest {

    @Autowired
    private Fachada fachada;

    @BeforeEach
    public void init() throws UsuarioDuplicadoException, LivroJaExisteException {
        Cliente cliente = new Cliente("leticia", "leticia@email.com", "12345", new Endereco("Rua Leticia", "24", "Bairro Leticia", "00000-00", "PE"));
        fachada.cadastrarUsuario(cliente);
        fachada.adicionarLivro("Boa Sorte", "Helena Cunha", 50.0, 30.0, 250, "Ficcao", "Boa sorte retrata um ano –o pior de todos– na vida de Julieta.", "nVersos", 264);
    }

    @Test
    void testeAvaliarLivro() throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException {
    	Cliente cliente = (Cliente) fachada.procurarUsuarioEmail("leticia@email.com");
        Livro livro = fachada.procurarLivroTitulo("Boa Sorte");
        assertEquals(0, livro.getAvaliacoes().size());
        fachada.avaliarLivro(livro.getId(), cliente, "Livro muito bom", "O livro e otimo, palavras bem escolhidas", 10.0);
        Livro livroAvaliado = fachada.procurarLivroId(livro.getId());
        assertNotNull(livroAvaliado.getAvaliacoes());
        assertEquals(1, livroAvaliado.getAvaliacoes().size());
        Avaliacao avaliacaoAdicionada = livroAvaliado.getAvaliacoes().get(0);
        assertNotNull(avaliacaoAdicionada);
        assertEquals(10.0, avaliacaoAdicionada.getNota());
        assertEquals("O livro e otimo, palavras bem escolhidas", avaliacaoAdicionada.getCorpo());
    }
}
    
