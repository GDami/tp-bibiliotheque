package bibili.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import bibili.dao.IDAOCollection;
import bibili.model.Collection;

@DataJpaTest
public class CollectionRepositoryTest {

    @Autowired
    private IDAOCollection repository;

    @Test
    public void testInsertCollection() {
        Collection Collection = new Collection(null, "Ninjas de Konoha");
        Collection savedCollection = repository.save(Collection);

        assertNotNull(savedCollection.getId());
        assertEquals("Ninjas de Konoha", savedCollection.getNom());
    }

    @Test
    public void testFindCollectionById() {
        Collection Collection = new Collection(null, "Ninjas de Konoha");
        Collection savedCollection = repository.save(Collection);

        Collection foundCollection = repository.findById(savedCollection.getId()).orElse(null);

        assertNotNull(foundCollection);
        assertEquals(savedCollection.getId(), foundCollection.getId());
        assertEquals(savedCollection.getNom(), foundCollection.getNom());
    }

    @Test
    public void testUpdateCollection() {
        Collection Collection = new Collection(null, "Ninjas de Konoha");
        Collection savedCollection = repository.save(Collection);

        savedCollection.setNom("Bibliothèque de Konoha");
        Collection updatedCollection = repository.save(savedCollection);

        assertEquals("Bibliothèque de Konoha", updatedCollection.getNom());
    }

    @Test
    public void testDeleteCollection() {
        Collection Collection = new Collection(null, "Ninjas de Konoha");
        Collection savedCollection = repository.save(Collection);

        repository.delete(savedCollection);
        Collection deletedCollection = repository.findById(savedCollection.getId()).orElse(null);

        assertEquals(null, deletedCollection);
    }

    @Test
    public void testFindAllCollections() {
    Collection Collection1 = new Collection(null, "Ninjas de Konoha 1");
    Collection Collection2 = new Collection(null, "Ninjas de Konoha 2");

    repository.save(Collection1);
    repository.save(Collection2);

    List<Collection> Collections = repository.findAll();

    assertNotNull(Collections);
    assertEquals(2, Collections.size());
    }

    @Test
    public void testFindByIdWithLivres() {
        Collection Collection = new Collection(null, "Ninjas de Konoha");
        Collection savedCollection = repository.save(Collection);

        Collection foundCollection = repository.findByIdWithLivres(savedCollection.getId());

        assertNotNull(foundCollection);
        assertEquals(savedCollection.getId(), foundCollection.getId());
        assertEquals(savedCollection.getNom(), foundCollection.getNom());
    }
    
}
