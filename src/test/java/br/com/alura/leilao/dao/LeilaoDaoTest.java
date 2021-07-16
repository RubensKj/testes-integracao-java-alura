package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.LeilaoBuilder;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao() {
        Leilao mochila = createLeilao();

        mochila = this.dao.salvar(mochila);

        Leilao leilao = this.dao.buscarPorId(mochila.getId());

        assertNotNull(leilao);
    }

    @Test
    void deveriaAtualizarUmLeilao() {
        Leilao mochila = createLeilao();
        mochila = this.dao.salvar(mochila);

        mochila.setNome("Celular");
        mochila.setValorInicial(new BigDecimal("200"));

        this.dao.salvar(mochila);

        Leilao leilao = this.dao.buscarPorId(mochila.getId());

        assertNotNull(leilao);
        assertEquals("Celular", leilao.getNome());
        assertEquals(new BigDecimal("200"), leilao.getValorInicial());
    }

    private Leilao createLeilao() {
        Usuario usuario = createUsuario();

        return new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial(new BigDecimal("70"))
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .build();
    }

    private Usuario createUsuario() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@gmail.com")
                .comSenha("1232154")
                .build();
        em.persist(usuario);
        return usuario;
    }
}