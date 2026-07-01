package bibili.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EditeurRestController.class)
public class EditeurRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    
}
