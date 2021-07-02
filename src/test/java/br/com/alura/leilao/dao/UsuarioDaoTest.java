package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        em.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = createUsuario();

        Usuario usuarioEncontrado = this.dao.buscarPorUsername(usuario.getNome());

        assertNotNull(usuarioEncontrado);
        assertEquals(usuario.getNome(), usuarioEncontrado.getNome());
        assertEquals(usuario.getEmail(), usuarioEncontrado.getEmail());
        assertEquals(usuario.getSenha(), usuarioEncontrado.getSenha());
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        createUsuario();

        assertThrows(
                NoResultException.class,
                () -> this.dao.buscarPorUsername("outro_usuario_nome")
        );
    }

    private Usuario createUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345");
        em.persist(usuario);
        return usuario;
    }
}