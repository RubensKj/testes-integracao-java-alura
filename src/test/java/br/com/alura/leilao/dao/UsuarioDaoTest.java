package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario usuarioEncontrado = this.dao.buscarPorUsername(usuario.getNome());

        assertNotNull(usuarioEncontrado);
        assertEquals(usuario.getNome(), usuarioEncontrado.getNome());
        assertEquals(usuario.getEmail(), usuarioEncontrado.getEmail());
        assertEquals(usuario.getSenha(), usuarioEncontrado.getSenha());
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        assertThrows(
                NoResultException.class,
                () -> this.dao.buscarPorUsername("outro_usuario_nome")
        );
    }
}