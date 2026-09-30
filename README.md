# telcelusers

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

Aplicación de consola en Java que gestiona **usuarios, direcciones y teléfonos** de una compañía telefónica ficticia usando **JDBC puro** sobre una base de datos **MySQL**. Es la versión completa del proyecto [TelcelUsuarios](https://github.com/Donaldo500/TelcelUsuarios).

## Descripción

El proyecto implementa el patrón **DTO + Model** para separar los datos de las operaciones de base de datos. Todos los modelos implementan la interfaz genérica `OperacionesCRUD<T>`, lo que garantiza que cada entidad exponga las mismas cuatro operaciones:

```java
public interface OperacionesCRUD<T> {
    T save(T t) throws SQLException;
    T updateById(T t) throws SQLException;
    int deleteById(int id) throws SQLException;
    T getById(int id) throws SQLException;
}
```

### Funcionalidades

- Conexión a MySQL mediante `DriverManager` encapsulada en la clase `MysqlConnection`.
- CRUD completo de **usuarios** (`users`), **direcciones** (`addresses`) y **teléfonos** (`phones`).
- Direcciones y teléfonos asociados a un usuario mediante `idUser`.
- Consultas parametrizadas con `PreparedStatement` para evitar inyección SQL.
- Validación del número de filas afectadas y lanzamiento de `SQLException` con mensajes descriptivos.

## Tecnologías utilizadas

| Tecnología | Uso |
| --- | --- |
| Java 21 | Lenguaje principal |
| JDBC | Acceso a datos |
| MySQL Connector/J 8.0.33 | Driver de conexión |
| MySQL 8 | Base de datos relacional |
| Maven + exec-maven-plugin | Build y ejecución |

## Estructura del proyecto

```text
src/main/java/com/ebac/modulo59/
├── Contexto.java            # Punto de entrada: ejecuta el flujo CRUD de las tres entidades
├── MysqlConnection.java     # Obtiene la conexión JDBC
├── dto/
│   ├── User.java
│   ├── Address.java
│   └── Phone.java
└── model/
    ├── OperacionesCRUD.java # Interfaz genérica
    ├── UserModel.java
    ├── AddressModel.java
    └── PhoneModel.java
```

## Instalación y uso

### 1. Levantar MySQL

Con Docker:

```bash
docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8
```

### 2. Crear la base de datos y las tablas

El esquema se deriva de las consultas que usa cada modelo:

```sql
CREATE DATABASE Telcel;
USE Telcel;

CREATE TABLE users (
    idUser   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100),
    lastName VARCHAR(100),
    age      INT
);

CREATE TABLE addresses (
    idAddress INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUser    INT NOT NULL,
    street    VARCHAR(100),
    number    INT,
    state     VARCHAR(100)
);

CREATE TABLE phones (
    idPhone INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUser  INT NOT NULL,
    phone   VARCHAR(20),
    type    VARCHAR(20)
);
```

### 3. Configurar la conexión

Las credenciales están definidas en `Contexto.java`. Ajústalas si tu servidor usa otros valores:

```java
String url = "jdbc:mysql://localhost:3306/Telcel";
String user = "root";
String password = "root";
```

### 4. Compilar y ejecutar

```bash
git clone https://github.com/Donaldo500/telcelusers.git
cd telcelusers
mvn compile exec:java -Dexec.mainClass="com.ebac.modulo59.Contexto"
```

## Ejemplos de uso

Crear y guardar un usuario:

```java
UserModel userModel = new UserModel(connection);

User maria = new User();
maria.setName("Maria");
maria.setAge(25);
userModel.save(maria);
```

Asociar un teléfono a ese usuario y actualizarlo:

```java
PhoneModel phoneModel = new PhoneModel(connection);

Phone phone = new Phone();
phone.setIdUser(1);
phone.setPhone("555-1234");
phone.setType("Celular");
phoneModel.save(phone);

phone.setPhone("555-4321");
phone.setType("Fijo");
phoneModel.updateById(phone);
```

Formato de la salida en consola (según los `toString()` de los DTO):

```text
------------------Operación con usuarios------------------
------------------SAVE------------------
Usuario{idUser=0, name=Maria, lastName=null, age=25}
Usuario{idUser=0, name=Juan, lastName=null, age=30}
------------------GET------------------
Usuario{idUser=1, name=Maria, lastName=null, age=25}
...
```

## Contribuciones

Proyecto individual con fines de aprendizaje. Si encuentras un error o tienes una mejora, abre un issue o envía un pull request.

## Autor

**Donaldo Ibarra** - [@Donaldo500](https://github.com/Donaldo500)
