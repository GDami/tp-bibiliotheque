package bibili.idao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import bibili.dao.IDAOAuteur;
import bibili.model.Auteur;

@DataJpaTest
public class AuteurIdaoTest {
    @Autowired
    private IDAOAuteur idaoAuteur;

    @Test
    @Sql(scripts = "classpath:/create-auteur.sql")
    void shouldGetAll() {
        // given
        int length = 4;
        // when
        List<Auteur> result = this.idaoAuteur.findAll();

        // then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(length, result.size());
    }

    @Test
    @Sql(scripts = "classpath:/reset-id-auteur.sql")
    @Sql(scripts = "classpath:/create-auteur.sql")
    void shouldGetById() {
        // given
        int id = 1;
        // when
        Auteur result = this.idaoAuteur.findById(id).orElse(null);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("prenom1", result.getPrenom());
        Assertions.assertEquals("nom1", result.getNom());
        Assertions.assertEquals("nat1", result.getNationalite());
    }

    @Test
    @Sql(scripts = "classpath:/create-auteur.sql")
    void shouldAddItem() {
        // given
        int length = 4;
        Auteur auteur = new Auteur(null, "nom5", "prenom5", "nat5");
        // when
        List<Auteur> result = this.idaoAuteur.findAll();

        // then
        Assertions.assertEquals(length, result.size());

        this.idaoAuteur.save(auteur);
        result = this.idaoAuteur.findAll();
        Assertions.assertEquals(length + 1, result.size());
    }

    @Test
    @Sql(scripts = "classpath:/reset-id-auteur.sql")
    @Sql(scripts = "classpath:/create-auteur.sql")
    void shoulUpdateItem() {
        // given
        int length = 4;
        String basicNat = "nat1";
        String natUpdate = "natUpdate";
        int index = 0;
        Auteur auteur = new Auteur(1, "nom1", "prenom1", natUpdate);
        // when
        List<Auteur> result = this.idaoAuteur.findAll();

        // then
        Assertions.assertEquals(length, result.size());
        Assertions.assertEquals(result.get(index).getNationalite(), basicNat);

        this.idaoAuteur.save(auteur);
        result = this.idaoAuteur.findAll();
        Assertions.assertEquals(length, result.size());
        Assertions.assertEquals(result.get(index).getNationalite(), natUpdate);
    }

    @Test
    @Sql(scripts = "classpath:/reset-id-auteur.sql")
    @Sql(scripts = "classpath:/create-auteur.sql")
    void shouldDeleteItem() {
        // given
        int length = 4;
        int id = 1;
        // when
        List<Auteur> result = this.idaoAuteur.findAll();

        // then
        Assertions.assertEquals(length, result.size());

        this.idaoAuteur.delete(result.get(id));
        result = this.idaoAuteur.findAll();
        Assertions.assertEquals(length - 1, result.size());
    }

    @Test
    @Sql(scripts = "classpath:/reset-id-auteur.sql")
    @Sql(scripts = "classpath:/create-auteur.sql")
    @Sql(scripts = "classpath:/create-editeur.sql")
    @Sql(scripts = "classpath:/create-collection.sql")
    @Sql(scripts = "classpath:/create-livre.sql")
    void shouldfindByIdWithLivres() {
        // given
    	int length = 2;
    	int id = 1;
        // when
      
        // then
        Auteur result = this.idaoAuteur.findByIdWithLivres(id);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.getLivres().isEmpty());
        Assertions.assertEquals(length, result.getLivres().size());
    }
}
