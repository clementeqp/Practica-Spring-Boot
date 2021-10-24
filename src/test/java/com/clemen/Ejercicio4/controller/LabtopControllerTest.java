package com.clemen.Ejercicio4.controller;


import com.clemen.Ejercicio4.entities.Labtop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabtopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @LocalServerPort
    private int port;

    public void cargarDatos(){
        // Creamos Labtop, definimos formato
        HttpHeaders headers = new HttpHeaders();
        //Formato JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        //Labtops Prueba
        String labtop1 = """
                {
                 "marca": "Lenovo",
                 "modelo": "Thinkpad",
                 "ram": 16,
                 "price": 599.99,
                 "fechaLanzamiento": "2017-12-15",
                 "tieneSo": true
                }
                """;
        String labtop2 = """
                {
                  "marca": "HP",
                  "modelo": "XPro2",
                  "ram": 16,
                  "price": 699.99,
                  "fechaLanzamiento": "2018-01-15",
                  "tieneSo": true
                }
                """;


        //Definimos los datos a pasar
        HttpEntity<String> request1 = new HttpEntity<String>(labtop1, headers);
        HttpEntity<String> request2 = new HttpEntity<String>(labtop2, headers);

        //Pasamos los datos
        ResponseEntity<Labtop> response1 = testRestTemplate.exchange("/api/labtops", HttpMethod.POST, request1, Labtop.class);
        ResponseEntity<Labtop> response2 = testRestTemplate.exchange("/api/labtops", HttpMethod.POST, request2, Labtop.class);
    }

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }


    @Test
    void findAllFull() {
        cargarDatos();

        ResponseEntity<Labtop[]> response = testRestTemplate.getForEntity("/api/labtops", Labtop[].class);
        List<Labtop> labtops = Arrays.asList(response.getBody());

        if (response.getBody() != null) {
            // Estado ok?
            assertEquals(HttpStatus.OK, response.getStatusCode());
            // Devuelve un codigo de estado 200 (ok)?
            assertEquals(200, response.getStatusCodeValue());
            // Hay 2 labtops en la lista ???
            assertEquals(2, labtops.size());
            // Hay elementos en el Array??
            assertTrue(labtops.size() > 0);
        } else {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }


    }


    @Test
    void findAll() {
        cargarDatos();
        ResponseEntity<Labtop[]> response =
                testRestTemplate.getForEntity("/api/labtops", Labtop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Labtop> labtops = Arrays.asList(response.getBody());
        System.out.println(labtops.size());

    }


    @Test
    void findOneById() {
        cargarDatos();
        ResponseEntity<Labtop> response = testRestTemplate.getForEntity("/api/labtops/1", Labtop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void findOneByIdEmpty() {
        ResponseEntity<Labtop> response = testRestTemplate.getForEntity("/api/labtops/1", Labtop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String labtop = """
                {
                   "marca": "SlimBook",
                   "modelo": "ZZBest",
                   "ram": 32,
                   "price": 1699.99,
                   "fechaLanzamiento": "2011-01-15",
                   "tieneSo": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(labtop, headers);

        ResponseEntity<Labtop> response = testRestTemplate.exchange("/api/labtops", HttpMethod.POST, request, Labtop.class);

        Labtop result = response.getBody();

        if(200==response.getStatusCodeValue()){
            assertEquals("ZZBest", result.getModelo());
        }



    }

    @Test
    void update() {
        cargarDatos();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String labtop = """
                {
                   "id": 1,
                   "marca": "SlimBook",
                   "modelo": "ZZBest",
                   "ram": 32,
                   "price": 1699.99,
                   "fechaLanzamiento": "2011-01-15",
                   "tieneSo": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(labtop, headers);

        ResponseEntity<Labtop> response = testRestTemplate.exchange("/api/labtops", HttpMethod.PUT, request, Labtop.class);
        Labtop result = response.getBody();
        if(200==response.getStatusCodeValue()){
            assertEquals("ZZBest", result.getModelo());
        }
    }

    @Test
    void delete() {
        cargarDatos();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>( headers);
        ResponseEntity<Labtop> response = testRestTemplate.exchange("/api/labtops/1", HttpMethod.DELETE, request, Labtop.class);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());


    }

    @Test
    void deleteAll() {
        cargarDatos();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>( headers);
        ResponseEntity<Labtop> response = testRestTemplate.exchange("/api/labtops/", HttpMethod.DELETE, request, Labtop.class);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

        ResponseEntity<Labtop[]> response2 =
                testRestTemplate.getForEntity("/api/labtops", Labtop[].class);

        List<Labtop> labtops = Arrays.asList(response2.getBody());
        assertFalse(labtops.size() != 0);


    }
}