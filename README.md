# Estructura-de-datos

**branch** alrdiaz-BACKEND

**branch** alrdiaz-FRONTEND

**Desarrollado por:** Alejandro Romero 

**para:** SOFTCARIBBEAN


**Usando un árbol binario y una tabla de base de datos en MySQL, CRUD con backend JAVA SPRING (Test) y frontend en ANGULAR (Test)**


## BackEnd. JAVA 11 SPRING 2.6.2 [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface)

### ÁRBOL BINARIO

implementado con la case TreeMap de Java [clase TreeMap de Java](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html), adicionalmente se incluye en src/main/java/com/commons.
clases desarroladas como research al ejercicio y de manera academica. pero no se usaron para el funcionamiento 
del api rest, en su lugar se utilizo la calse de java treeMap que es en si una estructura de arbol binario 
balanceado red-black.


### DATABASE

*application.properties*

- crear base datos segùn archivo src/main/resorces/application.properties o modificar el mismo para la creación de la base de datos.

- Las tablas se crearan automaticamente al inicio del servidor y se eliminaran al terminar para efectos de desarrollo, modificar en la linea 5 del application.properties:
spring.jpa.hibernate.ddl-auto=create-drop

*import.sql*
- carga de datos iniciales en la base de datos para pruebas y visualización al iniciar spring boot app

#### MODEL

*Entity class:*

- libreria de ide y dependencia de proyecto [Lombok](https://projectlombok.org/) para la creación automatica de getters, setters y constructores.

- JPA como framework ORM para la interacción con base de datos en MySQL.

- Validaciones por medio de anotaciones de validación de atributos.

- se crea Metodo isValidate(), para la validación de datos con expresiones regulares desde el backend. el cual returna mensajes de error.  se puede usar estas [alternativas de validación](https://reflectoring.io/bean-validation-with-spring-boot/)

*DAO o Repository class:*

- Interface que extiende de la clase interface generica [CrudRepository](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html) con todos los metodos CRUD.

*Servicios:*

- Interface que extiende de la clase interface generica GenericService (puede ser usada para nuevas entidades) en com.commons con los metodos abstractos CRUD y extiende Serializable " que sirve solamente para especificar que todo el estado de un objeto instanciado podrá ser escrito o enviado en la red como una trama de bytes. "para la transferencia de datos.

- Implementación inyecta la dependecia del repositorio y genera el metodo getDao para retornar el repositorio al que se le generan los metodos CRUD del GenericImpl. la implementación hereda los metodos de la clase generica GenericImpl que implementa la interface generica GenericService mencionada anteriormente sobreescribiendo los metodos abstractos.


#### CONTROLLER

*Api rest => @RestController*

*Mapeo del endpoint =>@RequestMapping("/clientes")*

*CrossOrigin habilitado solo para Angular localhost:4200 y por defecto todos los metodos=>@CrossOrigin(origins= {"http://localhost:4200"})*

*Implementa => con el uso de la anotación @Component para =>  [ApplicationRunner](https://programmerclick.com/article/864032113/) para metodo run de instancia y carga del árbol binario con datos de la base de datos.*


*Se inyecta el servicio la interface IClienteService y spring busca el primer candidato que implemente esta interface, es decir ClienteServiceImpl con todos los metodos CRUD*

*Manejo de errores incluidos en todos los metodos.*



>El back End debe realizar las siguientes operaciones.
>1. Inserción y actualización Al insertar o actualizar el objeto, debe primero agregarlo al árbol binario y luego almacenarlo a la base de datos. De esta forma >los datos quedan en memoria almacenados en el árbol y en la base de datos de forma permanente.
>2.  Carga (Inicialización) Cuando el servicio suba debe tomar todos los datos de la tabla en la base de datos y cargarlos al árbol. 
>3.  Consulta La consulta de un objeto se debe hacer contra el árbol binario y no directamente contra la base de datos. 
>4.  Manejo de excepciones Debe tener un manejo personalizado de excepciones

#### TEST

*Datos*

- Clase con datos para pruebas unitarias de los servicios.

*Prueba unitaria para metodo isValidate()*

*Pruebas unitarias para los Servicios al 100% de coverage.*

## FrontEnd [ANGULAR v13.1.0](https://angular.io/start)  - [Boostrap v5.1.3](https://getbootstrap.com/) - [sweetalert2 v11.3.3](https://sweetalert2.github.io/)

### Responsive

- se utilizo diseño sencillo con grid y clases de boostrap para el despliege de la aplicación.

### Diseño

- se utilizo sweetalert2 para el despliegue de mensajes de confirmación, alerta y error tipo modal ante los eventos en la interación copn el usuario.

### SPA

- se utilizo patron de single page application con la implementación de rutas.

### TEST

- Se   realizo pruebas unitarias a los servicios del cliente y para la creación de los componentes.










