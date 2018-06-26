package com.rajendarreddyj.reactivewebexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ReactiveWebExampleApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testExampleHello() {
        webTestClient
                .get().uri("/example")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring Webflux Example!");
    }

    @Test
    public void testExampleHelloFurther1() {
        webTestClient
                .get().uri("/exampleFurther1")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring Webflux Example 1!");
    }

    @Test
    public void testExampleHelloFurther2() {
        webTestClient
                .get().uri("/exampleFurther2")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring Webflux Example 2!");
    }

    @Test
    public void testExampleHelloFurther3WithParameter() {
        webTestClient
                .get().uri("/exampleFurther3?name=Spring Webflux Example")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring Webflux Example!");
    }

    @Test
    public void testExampleWithError() {
        webTestClient
                .get().uri("/example/something")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isNotFound();
    }

}
