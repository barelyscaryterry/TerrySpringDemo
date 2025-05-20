# TerrySpringDemo

A Spring Boot web application with Thymeleaf templates, order management functionality, and comprehensive test suite. Features MVC architecture with controllers for home, hello, and order pages, static resources for styling, and Gradle build configuration using Java 17.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [Developing New Features](#developing-new-features)
- [Project Structure](#project-structure)
- [Testing](#testing)

## Prerequisites

- Java 17 or higher
- Gradle (the project includes Gradle wrapper)
- Git

## Running the Application

### Using Gradle Wrapper

1. Clone the repository:
   ```bash
   git clone https://github.com/barelyscaryterry/TerrySpringDemo.git
   cd TerrySpringDemo
   ```

2. Run the application:
   ```bash
   ./gradlew bootRun
   ```

3. Access the application in your web browser:
   ```
   http://localhost:8080
   ```

### Using an IDE

1. Import the project into your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Run the `DemoApplication.java` class as a Java application

## Developing New Features

### 1. Understanding the Project Structure

The project follows the standard Spring Boot MVC architecture:
- Controllers handle HTTP requests
- Services contain business logic
- Models represent data entities
- Templates (Thymeleaf) render the views

### 2. Adding a New Feature

#### Step 1: Create or Modify Model Classes

1. Navigate to `src/main/java/com/example/demo/model/`
2. Create a new model class or modify existing ones

Example:
```java
package com.example.demo.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
}
```

#### Step 2: Create or Modify Service Classes

1. Navigate to `src/main/java/com/example/demo/service/`
2. Create a new service class or modify existing ones

Example:
```java
package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    
    // Service methods
    public List<Product> getAllProducts() {
        return products;
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }
}
```

#### Step 3: Create or Modify Controllers

1. Navigate to `src/main/java/com/example/demo/controller/`
2. Create a new controller or modify existing ones

Example:
```java
package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }
}
```

#### Step 4: Create or Modify Templates

1. Navigate to `src/main/resources/templates/`
2. Create new Thymeleaf templates or modify existing ones

Example (`products.html`):
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" 
      th:replace="~{layouts/default :: layout(~{::title}, ~{::section})}">
<head>
    <title>Products</title>
</head>
<body>
    <section>
        <h1>Products</h1>
        <a href="/products/add" class="btn">Add New Product</a>
        
        <div th:if="${products.empty}">
            <p>No products available</p>
        </div>
        
        <div th:unless="${products.empty}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="product : ${products}">
                        <td th:text="${product.id}"></td>
                        <td th:text="${product.name}"></td>
                        <td th:text="${product.description}"></td>
                        <td th:text="${product.price}"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
</body>
</html>
```

#### Step 5: Add CSS Styling

1. Navigate to `src/main/resources/static/css/`
2. Create a new CSS file or modify existing ones

Example (`products.css`):
```css
table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

.btn {
    display: inline-block;
    padding: 10px 15px;
    background-color: #4CAF50;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    margin: 10px 0;
}
```

#### Step 6: Write Tests

1. Navigate to `src/test/java/com/example/demo/`
2. Create test classes for your new components

Example:
```java
package com.example.demo.service;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    
    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        
        productService.addProduct(product);
        
        assertTrue(productService.getAllProducts().contains(product));
    }
}
```

### 3. Best Practices

- Follow the existing project structure and naming conventions
- Write unit tests for all new functionality
- Use Lombok annotations to reduce boilerplate code
- Keep controllers thin and put business logic in services
- Use meaningful commit messages when pushing changes

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── demo
│   │               ├── config
│   │               ├── controller
│   │               ├── model
│   │               ├── service
│   │               └── DemoApplication.java
│   └── resources
│       ├── static
│       │   ├── css
│       │   └── js
│       ├── templates
│       │   ├── fragments
│       │   ├── layouts
│       │   └── *.html
│       └── application.properties
└── test
    └── java
        └── com
            └── example
                └── demo
                    ├── controller
                    ├── model
                    ├── service
                    └── DemoApplicationTests.java
```

## Testing

Run tests using Gradle:

```bash
./gradlew test
```

Or run specific tests:

```bash
./gradlew test --tests "com.example.demo.controller.HomeControllerTest"
```

View test reports in `build/reports/tests/test/index.html`
