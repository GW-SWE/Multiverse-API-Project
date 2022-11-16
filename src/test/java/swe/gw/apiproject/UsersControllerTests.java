package swe.gw.apiproject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {
    @Autowired
    private MockMvc mvc;
    @Test
    public void shouldCreateNewUser() throws Exception{


        String user = "{  \n" +
                "    \"username\": \"TestUser314\",  \n" +
                "    \"password\":\"testpassword\"\n" +

                "}   ";
//        TODO Update Get tests with username check instead. Ideally need a way to delete the user after use
        mvc.perform(post("/users/create").contentType("application/json").content(user)).andExpect(status().isOk());
//        mvc.perform(get("/users/2")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Steve"));
//        mvc.perform(get("/users/2")).andExpect(status().isOk()).andExpect(jsonPath("$.email").value("steve@gmail.com"));


    }
//TODO: check what happens with non matching userID
}
