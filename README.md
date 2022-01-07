# Estructura-de-datos

branch alrdiaz-BACKEND

branch alrdiaz-FRONTEND

Desarrollado por: Alejandro Romero 

para: SOFTCARIBBEAN


Usando un árbol binario y una tabla de base de datos en MySQL, CRUD con backend JAVA SPRING (Test) y frontend en ANGULAR (Test)


**BackEnd. JAVA 11 SPRING 2.6.2 JPA**

**ÁRBOL BINARIO**

implementado con la case TreeMap de Java, adicionalmente se incluye en src/main/java/com/commons.
clases desarroladas como research al ejercicio y de manera academica. pero no se usaron para el funcionamiento 
del api rest, en su lugar se utilizo la calse de java treeMap que es en si una estructura de arbol binario 
balanceado red-black.


**DATABASE**

*application.properties

-crear base datos segùn archivo src/main/resorces/application.properties o modificar el mismo para la creación de la base de datos.

-Las tablas se crearan automaticamente al inicio del servidor y se eliminaran al terminar, modificar en la linea 5 del application.properties:
spring.jpa.hibernate.ddl-auto=create-drop

*import.sql
-carga de datos iniciales en la base de datos para pruebas y visualización al iniciar spring boot app

**MODEL**

*Entity class:

-loombok para la creación automatica de getters, setters y constructores.

-JPA como framework ORM para la interacción con base de datos en MySQL.

-Metodo isValidate(), para la validación de datos con expresiones regulares desde el backend.

*DAO o Repository class:

-Interface que extiende de la clase CrudRepository con todos los metodos CRUD.

*Servicios:

-Interface que extiende de la clase interface generica GenericService (puede ser usada para nuevas entidades) en com.commons con los metodos CRUD y extiende Serializable para la transferencia de datos.

-Implementación que extiende de la clase generica GenericImpl que implementa la interface generica mencionada anteriormente.

**CONTROLLER**

*Implementa  ApplicationRunner para metodo run de instancia y carga del árbol binario con datos de la base de datos.

*Manejo de errores incluidos en todos los metodos.

*CrossOrigin habilitado solo para Angular localhost:4200 y por defecto todos los metodos.

El back End debe realizar las siguientes operaciones.
1. Inserción y actualización Al insertar o actualizar el objeto, debe primero agregarlo al árbol binario y luego almacenarlo a la base de datos. De esta forma los datos quedan en memoria almacenados en el árbol y en la base de datos de forma permanente.
2.  Carga (Inicialización) Cuando el servicio suba debe tomar todos los datos de la tabla en la base de datos y cargarlos al árbol. 
3.  Consulta La consulta de un objeto se debe hacer contra el árbol binario y no directamente contra la base de datos. 
4.  Manejo de excepciones Debe tener un manejo personalizado de excepciones

**TEST**

*Datos

-Clase con datos para pruebas unitarias de los servicios.

*Prueba unitaria para metodo isValidate()

*Pruebas unitarias para los Servicios al 100% de coverage.

**FrontEnd ANGULAR v13.1.0 - Boostrap v5.1.3 - sweetalert2 v11.3.3**

*Responsive

-se utilizo diseño sencillo con grid y clases de boostrap para el despliege de la aplicación.

*Diseño

-se utilizo sweetalert2 para el despliegue de mensajes de confirmación, alerta y error tipo modal ante los eventos en la interación copn el usuario.

*SPA 

- se utilizo patron de single page application con la implementación de rutas.

*TEST

-Se   realizo pruebas unitarias a los servicios del cliente y para la creación de los componentes.










