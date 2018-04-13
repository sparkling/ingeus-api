package com.ingeus.demo.api.controller;

import com.ingeus.demo.api.model.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.nio.charset.Charset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.    WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerIntegrationTests {

    @Autowired
    private WebTestClient webClient;

    private JacksonTester<List<UserDto>> json;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void getUsersTest() {
        this.webClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(contentType)
                .expectBody().json("[{\"id\":1,\"firstName\":\"Jim\",\"lastName\":\"Henson\"},{\"id\":2,\"firstName\":\"Elon\",\"lastName\":\"Musk\"}]");

    }

    @Test
    public void updateUserTest() {
        UserDto updated = new UserDto(1, "Willie", "Nelson");

        //FIXME: Get this from Jackson instead
        String updatedJson = "{\"id\":1,\"firstName\":\"Willie\",\"lastName\":\"Nelson\"}";

        this.webClient.post()
                .uri("/user/1")
                .contentType(contentType)
                .body(BodyInserters.fromObject(updated))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(contentType)
                .expectBody().json(updatedJson);

        this.webClient.get().uri("/user/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(contentType)
                .expectBody().json(updatedJson);


    }



}
