# FORO-HUD
Es un challenge de Oracle-Next-Generation Alura-Latam. Se trata de un foro (en este caso busque uno relacionado con StackOverFlow) implementado una ApiRest, permitiendo a los usuarios crear tópicos, responder a los mismos y participar en discusiones.

---

## Caracteristicas.
- Registro y autenticacion de usuarios, con un nombre de usuario y contraseña.
- CRUD de topicos.
- Autenticacion por JWT.

---

## Tecnologias.
- Java
- Sprint Boot
- Depencias con Spring Initializr (https://start.spring.io/):
  - Lombok
  - Spring Web
  - Spring Boot DevTools
  - Spring Data JPA
  - Flyway Migration
  - MySQL Driver
  - Validation
  - Spring Security
  - Auth (https://github.com/auth0/java-jwt).
- MySQL
- Insomnia
- IDE usado -> Intellij

---

## Instalación

Clona este repositorio:

```bash
git clone master https://github.com/Pachanico99/Foro-Hud.git
```

Abre el proyecto en tu de preferencia IDE.

Abre el archivo application.yml y configuralo con tus credencias de mysql o la base de datos que uses.

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/{INGRESA EL NOMBRE DE TU BASE DE DATOS}
    username: {INGRESA EL NOMBRE USUARIO}
    password: {INGRESA LA CONTRASEÑA}
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
server:
  error:
    include-stacktrace: never
  port: {Ingresa el puerto que desees usar}

api:
  security:
    secret: ${JWT_SECRET:INGRESA UNA KEY}yml
```

---

## Pruebas:
Registrarse:
Caso OK
![image](https://github.com/user-attachments/assets/2e223748-4437-4755-aad5-6f2f9f055d2e)

Caso ERROR( se ingresa un usuario ya registrado):
![image](https://github.com/user-attachments/assets/23f4ee12-1b4b-4a67-8aa1-85828ad8ccc0)

Login:
Caso OK
![image](https://github.com/user-attachments/assets/050c2d6d-2002-4356-9e60-995a874073b9)

Caso ERROR( se ingresa un usuario y contraseña que no existen en db):
MAL NOMBRE
![image](https://github.com/user-attachments/assets/e0cd34e0-06c9-4095-8e72-7c7baf7757cb)

MAL CONTRASEÑA
![image](https://github.com/user-attachments/assets/d13eb9ac-43e0-43d5-87da-df923bedd1fe)

Registrar TOPICO:
![image](https://github.com/user-attachments/assets/9f9c1cfd-c4f8-4d69-9735-f17bb203c446)

Listar TOPICOS:
![image](https://github.com/user-attachments/assets/77dce0bb-c55d-403d-bafb-13c982224461)

LISTA un TOPICO por id:
![image](https://github.com/user-attachments/assets/4a70f073-7557-43bc-9e37-19e09c8bc1ac)

Modificar TOPICO:
![image](https://github.com/user-attachments/assets/fd4917a3-5851-4136-9b70-04556a98591c)

Eliminar TOPICO:
![image](https://github.com/user-attachments/assets/c63ae35d-0c24-40fd-a498-42a4a14993b2)

---

## FUTURAS IMPLEMENTACIONES:
 - Implementar el crud para usuario, respuestas.
 - Implementar test unitarios.
 - Implementar swangger para documentar.










