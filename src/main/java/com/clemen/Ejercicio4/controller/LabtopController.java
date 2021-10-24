package com.clemen.Ejercicio4.controller;


import com.clemen.Ejercicio4.entities.Labtop;
import com.clemen.Ejercicio4.repository.LabtopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;



import java.util.List;
import java.util.Optional;

/**
 * @author Clemen
 */

@RestController
public class LabtopController {

    // para Creamos un Logger para errores personalizados
    private final Logger log = LoggerFactory.getLogger(LabtopController.class);

    // atributos
    private LabtopRepository labtopRepository;

    //constructor
    public LabtopController(LabtopRepository labtopRepository) {
        this.labtopRepository = labtopRepository;
    }


    /**
     * Devuelve una lista con todos los objetos en la BD
     * findAll()
     *
     * @return Lista de Labtop
     */
    @GetMapping("/api/labtops")
    public List<Labtop> findAll() {
        return labtopRepository.findAll();
    }

    /**
     * finOneById()
     *
     * @param id
     * @return
     */
    // @ApiParam, ApiOperation -->Swagger
    @GetMapping("/api/labtops/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Labtop> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id) {
        Optional<Labtop> labtopOpt = labtopRepository.findById(id);

        if (labtopOpt.isPresent()) {
            return ResponseEntity.ok(labtopOpt.get());
        } else
            return ResponseEntity.notFound().build();

        // Equivalente funcional
        // return labtopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //

    /**
     * Insertar un objeto en la DB
     *Basico
     */
    /*@PostMapping("/api/labtops")
    public Labtop create(@RequestBody Labtop labtop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        return labtopRepository.save(labtop);
    }*/


    /**
     * Insertar un objeto en la BD
     *
     * @param labtop
     * @param headers
     * @return
     */
    @PostMapping("/api/labtops")
    public ResponseEntity<Labtop> create(@RequestBody Labtop labtop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));

        if (labtop.getId() != null) { // quiere decir que existe el id y por tanto no es una creación
            log.warn("trying to create a labtop with id");
            System.out.println("trying to create a labtop with id");
            return ResponseEntity.badRequest().build();
        }
        Labtop result = labtopRepository.save(labtop);
        return ResponseEntity.ok(result); // el labtop devuelto tiene una clave primaria
    }

    /**
     * Metodo para actualizar un labtop verificando su existencia con Id
     *
     * @param labtop
     * @return
     */
    @PutMapping("/api/labtops")
    public ResponseEntity<Labtop> update(@RequestBody Labtop labtop) {
        if (labtop.getId() == null) { // si no tiene id quiere decir que  es una creación
            log.warn("Trying to update a non existent labtop");
            return ResponseEntity.badRequest().build();
        }
        if (!labtopRepository.existsById(labtop.getId())) {
            log.warn("Trying to update a non existent labtop");
            return ResponseEntity.notFound().build();
        }
        Labtop result = labtopRepository.save(labtop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/labtops/{id}")
    public ResponseEntity<Labtop> delete(@PathVariable Long id) {

        if (!labtopRepository.existsById(id)) {
            log.warn("Trying to delete a non existent labtop");
            return ResponseEntity.notFound().build();
        }

        labtopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiIgnore  // ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/api/labtops")
    public ResponseEntity<Labtop> deleteAll() {
        log.info("REST Request for delete all labtops");
        labtopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
