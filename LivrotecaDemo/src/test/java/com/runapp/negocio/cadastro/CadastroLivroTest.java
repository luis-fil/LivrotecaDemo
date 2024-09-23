package com.runapp.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;

@SpringBootTest
class CadastroLivroTest {

    @Autowired
    private InterfaceCadastroLivro colecaoLivro;

    @Test
    void testeAdicionarLivro() throws LivroJaExisteException, LivroNaoExisteException {
        colecaoLivro.adicionarLivro("Tres", "Valerie Perrin", 40.0, 20.0, 656, "Romance", "Os tres nao podiam ser mais diferentes. No entanto, a uniao entre eles e maior do que qualquer diferença", "Intrinseca", 500);
        Livro livroSalvo = colecaoLivro.procurarLivroTitulo("Tres");
        assertEquals("Tres", livroSalvo.getTitulo());
        assertEquals("Valerie Perrin", livroSalvo.getAutor());
        assertEquals(40.0, livroSalvo.getValorLivroFisico());
        assertEquals(20.0, livroSalvo.getValorEbook());
        assertEquals(656, livroSalvo.getNumeroPaginas());
        assertEquals("Romance", livroSalvo.getGenero());
        assertEquals("Os tres nao podiam ser mais diferentes. No entanto, a uniao entre eles e maior do que qualquer diferença", livroSalvo.getSinopse());
        assertEquals("Intrinseca", livroSalvo.getEditora());
        assertEquals(500, livroSalvo.getQuantidade());
    }
}