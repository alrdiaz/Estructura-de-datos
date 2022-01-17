# Estructura de datos

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

1. metodo **run**  
* utiliza el servicio y realiza consulta de todos los registros en base de datos
* mapea y carga en memoria árbol binario. <KEY, VALUE> KEY=id del cliente, VALUE=Instancia de clase cliente.
* en caso de error retorna por consola mensaje de error.
2. metodo **getTree**
* retorna [ResponseEntity ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) con body y httpstatus:
* en caso de tener valores el árbol binario el body sera listado de clase cliente y HttpStatus.OK
* si esta vacio el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.NOT_FOUND
3. metodo **getAll**
* ejecuta llamado al servicio getAll para obtener todos los registros de la base de datos  y retorna [ResponseEntity ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) con body y httpstatus:
* en caso de tener valores el body sera listado de clase cliente y HttpStatus.OK
* si esta vacio el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.NOT_FOUND
* en caso de obetener error en la consulta a la base de datos el body sera el arreglo de errores en la consulta y HttpStatus.INTERNAL_SERVER_ERROR
4. metodo **find**
* ejecuta llamado al servicio get(id) con argumento el id del cliente para obtener la base de datos su instancia de clase y retorna [ResponseEntity ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) con body y httpstatus:
* en caso de tener valores el body sera su instancia de clase cliente y HttpStatus.OK
* si es null,  el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.NOT_FOUND
5. metodo **save**
*  ejecuta llamado al servicio save(cliente) con argumento una instacia de clase cliente para guardar en  la base de datos y retorna [ResponseEntity ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html) con body y httpstatus:
* en caso de de que el argumento de clase isvalidate sea diferente de null, el body sera sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.BAD_REQUEST
* si la nueva instacia de cliente no tiene numero de cliente o no esta en el arbol binario => se definira el nuemero de cliente como el consecutivo al ultimo registro del árbol y se guardara posteriormente en este mismo, luego se procede a ejecutar el servicio save. con respuesta postiva en body  la nueva instacia de clase cliente guardada en la base de datos y  HttpStatus.CREATED
* si ocurre algun error al ejecutar el servicio save, el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.INTERNAL_SERVER_ERROR
6. metodo **update**
* realiza la consulta del árbol en memoria del key:id del cliente a consultar, si es null el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.NOT_FOUND
* en caso de de que el argumento de clase isvalidate sea diferente de null, el body sera sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.BAD_REQUEST
* si es valido y la instacia ya existe en el árbol los atributos del RequestBody se asignan a la variable temporal con los datos encontrados en el árbol.
luego se guardan los cambios en el árbol en memoria y luego se ejecuta el servicio save con los cambios. sin errores el body es  la nueva instacia de clase cliente guardada en la base de datos y  HttpStatus.CREATED
* si ocurre algun error al ejecutar el servicio save, el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.INTERNAL_SERVER_ERROR
7. metodo **delete**
* realiza la consulta del árbol en memoria del key:id del cliente a consultar, si es null el body sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.NOT_FOUND
* si se encuentra dentro del árbol primero se borra del mismo y luego se ejecuta el servicio delete. si existe algún error al ejecutar el servicio y posterior con la base de datos se agrega nuevamente al arbol y el body sera sera arreglo tipo [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) con los errores almacenados y HttpStatus.INTERNAL_SERVER_ERROR
* si se ejecuta el servicio delete correctamenmte el body sera mensaje de exito y HttpStatus.OK.

#### TEST  [Junit](https://junit.org/junit5/docs/current/user-guide/) [Mockito]()

*Datos*

- Clase con datos para pruebas unitarias de los servicios.

*Prueba unitaria para metodo isValidate()*
- con la clase [Assert](https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html) se verifica el valor esperado y el valor al ejecutar el metodo isValidate de la clase cliente de prueba.

*Pruebas unitarias para los Servicios al 100% de coverage.*
![image](https://user-images.githubusercontent.com/71915394/149702137-c45e4b3b-77ab-4789-affc-ab42731eec0a.png)


- @SpringBootTest para integrar todo el contexto de pruebas con junit y mockito
- @BeforeEach para instanciar el ClienteDao (mock) que simulara el repositorio y el servicio a partir de este, antes de cada prueba.
- para cada metodo de prueba, se declara el resultado para la ejecución del servicio con [when()y thenReturn()](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#when-T-)
- se verifica el valor esperado y el valor retornado despues de la ejecuciòn del servicio **assertEquals**
- se verifica que se ejecute el servicio [verify()](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#1)

## FrontEnd [ANGULAR v13.1.0](https://angular.io/start)  - [Boostrap v5.1.3](https://getbootstrap.com/) - [sweetalert2 v11.3.3](https://sweetalert2.github.io/)

### SPA

- se utilizo patron de single page application con la implementación de rutas dentro del archivo => *app.module.ts 
**maqueta** => *app.component.html
- header tipo fijo
- Rutas
- Footer

### Responsive 
- se utilizo diseño sencillo con grid y clases de [Boostrap v5.1.3](https://getbootstrap.com/)  instalado directamente en el proyecto para el despliege de la aplicación.

### Diseño
- se instalo en el proyecto sweetalert2  para el despliegue de mensajes de confirmación, alerta y error tipo modal ante los eventos en la interación con el usuario.

### Componente Clientes 
#### Modelo
- clase cliente con los atributos a definir desde el lado del cliente.
#### Servicios
- getClientes()

### TEST

- Se   realizo pruebas unitarias a los servicios del cliente y para la creación de los componentes.










