package bibili.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import bibili.dao.IDAOEditeur;
import bibili.model.Editeur;
//Import nécessaire à cause de JwtHeadFilter
import bibili.service.UtilisateurService;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@WebMvcTest(controllers = EditeurRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EditeurRestControllerTest {

    /* Pas sûre d'avoir tout compris mais d'après GPT, Spring essaie aussi d'instancier JwtHeadFilter qui dépend
    de UtilisateurService service; donc je dois l'ajouter icic pour qu'il soit content */
    @MockitoBean
    private UtilisateurService utilisateurService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOEditeur repository;

    @Test
    @WithMockUser
    void testFindAllEditeurs() throws Exception {

        // given
        Editeur editeur1 = new Editeur();
        editeur1.setId(1);
        editeur1.setNom("Editeur 1");
        
        Mockito.when(this.repository.findAll()).thenReturn(List.of(editeur1));

        String url = "/api/editeur";

        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(url));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Editeur 1"));
        Mockito.verify(this.repository).findAll();

    }

    @Test
    @WithMockUser
    void testFindEditeurById() throws Exception {

        // given
        Editeur editeur1 = new Editeur();
        editeur1.setId(1);
        editeur1.setNom("Editeur 1");

        Mockito.when(this.repository.findById(1)).thenReturn(Optional.of(editeur1));

        String url = "/api/editeur/1";

        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(url));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Editeur 1"));
        Mockito.verify(this.repository).findById(1);

    }

    @Test
    @WithMockUser
    void testCreateEditeur() throws Exception {

        // given
        Editeur editeur1 = new Editeur();
        editeur1.setId(1);
        editeur1.setNom("Editeur 1");

        Mockito.when(this.repository.save(editeur1)).thenReturn(editeur1);

        String url = "/api/editeur";

        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post(url));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Editeur 1"));
        Mockito.verify(this.repository).save(editeur1);

    }

    @Test
    @WithMockUser
    void testUpdateEditeur() throws Exception {

        // given
        Editeur editeur1 = new Editeur();
        editeur1.setId(1);
        editeur1.setNom("Ancien nom");

        Editeur editeur1modifie = new Editeur();
        editeur1modifie.setId(1);
        editeur1modifie.setNom("Nouveau nom");

        Mockito.when(this.repository.save(Mockito.any(Editeur.class))).thenReturn(editeur1modifie);

        String url = "/api/editeur/1";

        String json = """
        {
            "id": 1,
            "nom": "Nouveau nom"
        }
        """;

        //when
       ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.put(url).contentType("application/json").content(json));
       
        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Nouveau nom"));
        Mockito.verify(this.repository).save(Mockito.any(Editeur.class));

    }

    @Test
    @WithMockUser
    void testDeleteEditeur() throws Exception {

        // given
        Editeur editeur1 = new Editeur();
        editeur1.setId(1);
        editeur1.setNom("Editeur 1");

        Mockito.doNothing().when(this.repository).deleteById(1);

        String url = "/api/editeur/1";

        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.delete(url));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(this.repository).deleteById(1);

    }

}
