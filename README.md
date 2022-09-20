# emploments-java-srpint-boot
App de vacantes hecha en Spring Boot - Java 

Crear Controladores - Servicios - formularios (thymeleaf) para consultar, agregar, modificar y borrar empleos.

### Consultar vacantes
<img src="https://user-images.githubusercontent.com/7141537/190707978-4c083855-777b-4ec7-9666-5fe1b286c0f5.PNG" width="600" />

### Agregar vacante
<img src="https://user-images.githubusercontent.com/7141537/190707981-9c8b0297-75d5-4b64-b647-90ffaefcfe7d.PNG" width="600" />

### Subir (download) imagen
<img src="https://user-images.githubusercontent.com/7141537/190707971-c2f417e1-5f73-40e3-bd9b-cd317b66406a.PNG" width="600" />

### Vista llena con datos
<img src="https://user-images.githubusercontent.com/7141537/190707975-4c1b226e-087e-4a98-a99b-23a1363891ee.PNG" width="600" />

### Resultado luego de agregar vacante
<img src="https://user-images.githubusercontent.com/7141537/190707977-bcc83c87-31bf-444c-bb3e-3b0d872f386d.PNG" width="600" />

### Detalles del empleo
<img src="https://user-images.githubusercontent.com/7141537/190728971-7e1cba5d-b552-40ac-ba85-a2f7f3b9d019.PNG" width="600" />

## pom.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.wlopera</groupId>
	<artifactId>employments</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>employments</name>
	<description>App para Ofertas de empleo</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.5.0</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

## Agregar application.properties
```
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5

## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=employment
spring.datasource.username=spring
spring.datasource.password=admin

#drop n create table again, good for testing, comment this in production
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```
Nota: spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl 
==> Debido a la mayúscula en el nombre de la tabla

## Agregar .../Category.java (Modelo)
```
package com.wlopera.employments.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employment.\"Categorias\"")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}

```
nota: @Table(name = "employment.\"Categorias\"") ==> Debido a la mayúscula en el nombre de la tabla

## .../repository/CategoryRepository.java (Repositorio)
```
package com.wlopera.employments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlopera.employments.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
```

## .../CategoryServiceImp.java (Servicio)
```
package com.wlopera.employments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlopera.employments.model.Category;
import com.wlopera.employments.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Override
	public List<Category> getAll() {
		List<Category> categories = categoryRepository.findAll();		
		return categories;
	}

}
```

## .../VacantController.java (Controller)
```
package com.wlopera.employments.controller;

...

@Controller
@RequestMapping("/vacant")
public class VacantController {

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IVacantService vacantService;
	
...
	@GetMapping("/index")
	public String listVacants(Model model) {
		...
		
		List<Category> categories = categoryService.getAll();
		System.out.println("Listado de categorias: "+ categories);
		
		return "vacant/index";
	}

 ...
}

```

### Base de datos (Postgres)
<img src="https://user-images.githubusercontent.com/7141537/191326013-f72dab6c-1a96-4cb0-a40a-250f524b710d.PNG" width="600" />

### Registros de datos en Base de datos (Categorias)
<img src="https://user-images.githubusercontent.com/7141537/191326009-126abebe-154c-4dd8-a944-a0f44634401b.PNG" width="600" />

### Salida del controlador
<img src="https://user-images.githubusercontent.com/7141537/191326011-78223f85-b11f-4f17-a87b-8f38c2c59899.PNG" width="600" />




