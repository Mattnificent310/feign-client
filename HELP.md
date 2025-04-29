# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.5/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.5/maven-plugin/build-image.html)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.5/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.5/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Running the Application

1. **Start the Python Notification Application**:
   - Navigate to the directory containing `notification-app.py`.
   - Run the script using the following command:
     ```bash
     python3 notification-app.py
     ```
   - This will start a Flask application on port `4000` with a POST endpoint at `/orders/order/<id>`.

2. **Run the Spring Boot Application**:
   - Start the Spring Boot application using your preferred method (e.g., `mvn spring-boot:run` or running the `DemoApplication` class).

3. **How the Feign Client Works**:
   - The Feign client in this application forwards API calls to the Python application based on the `recipientId` defined in `application.yml`.
   - When a POST request is made to `/api/order` on the Spring Boot application, the `recipientId` in the request body determines the forwarding URL.

4. **Testing the Integration**:
   - Use the following `curl` command to test the integration:
     ```bash
     curl -X 'POST' 'http://localhost:8080/api/order' \
       -H 'Content-Type: application/json' \
       -d '{"orderId": "1", "recipientId": "Customer1", "item": "Item1", "quantity": 1}'
     ```
   - Expected response:
     ```json
     {
       "orderId": "Customer1",
       "status": "PROCESSED",
       "customer": "Customer-Customer1",
       "items": [
         {"productId": "item1", "quantity": 2},
         {"productId": "item2", "quantity": 1}
       ],
       "totalAmount": 150.0
     }
     ```

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
