## Ejercicio 4

Crear un proyecto Spring Boot con las dependencias:

- H2
- Spring Data JPA
- Spring Web
- Spring Boot dev tools

Crear una clase HelloController que sea un controlador REST.
Dentro de la clase crear un método que retorne un saludo.
Probar que retorna el saludo desde el navegador y desde Postman.


## Ejercicio 5
Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores":

- Laptop (entidad)
- LaptopRepository (repositorio)
- LaptopController (controlador)

Desde LaptopController crear un método que devuelva una lista de objetos Laptop.
Probar que funciona desde Postman.
Los objetos Laptop se pueden insertar desde el método main de la clase principal.


## Ejercicio 6
Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y persistirlo en la base de datos.
Comprobar que al obtener de nuevo los laptops aparece el nuevo ordenador creado.

## Ejercicio 7

Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores.

Los métodos CRUD:

- findAll()
- findOneById()
- create()
- update()
- delete()
- deleteAll()

## Ejercicio 8

Implementar swagger sobre el API REST de Laptop y verificar que funciona en la URL:

http://localhost:8081/swagger-ui/

## Ejercicio 9

Crear casos de test para el controlador de Laptop desde Spring Boot.

Con click derecho dentro del código de la clase LaptopController utilizar la opción Generate > Test
para crear la clase con todos los tests unitarios e implementarlos siguiendo el proceso visto en clase. 

## Ejercicio 10
- Probar a empaquetar la aplicación con maven package desde Intellij IDEA
- Desde terminal en Intellij IDEA verificar que se se ejecuta correctamente con el comando:

java -jar target/spring-deploy-1.0.jar
- Crear un perfil para dev y otro para test con una propiedad nueva que carguemos en el controlador.

## Ejercicio 11
Desplegar el API REST de Laptops en Heroku y verificar funcionamiento desde POSTMAN.

## Ejercicio 12
Añadir Spring Security sobre el proyecto API REST de Laptops y configurar 2 usuarios en memoria utilizando una clase WebSecurityConfig.