package com.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.models.entity.Cliente;
import com.models.services.IClienteService;


@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins= {"http://localhost:4200"})
@Component
public class ClienteController implements ApplicationRunner{

	@Autowired
	private IClienteService clienteService;
//---------------------------------------------------------------------------------------------------------------
/*  2. Carga (Inicialización) Cuando el servicio suba debe tomar todos
 *  los datos de la tabla en la base de datos y cargarlos al árbol.	
*/
//---------------------------------------------------------------------------------------------------------------	
	TreeMap<Long, Cliente> clienteTree = new TreeMap<Long, Cliente>();
	List<Cliente> dbClientes = null;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			dbClientes = clienteService.getAll();
			for (Cliente element : dbClientes) {
				clienteTree.put(element.getNmid(), element);
			}						
		} catch (DataAccessException e) {
			System.out.println("Error al realizar la consulta en la base de datos!");
			System.out.println(e);
		}		
	}

	//---------------------------------------------------------------------------------------------------------------
	//La consulta de un objeto se debe hacer contra el árbol binario	
		@GetMapping(value = "/tree")
		public ResponseEntity<?> getTree() {
			List<Cliente> clientes = null;			
			clientes = new ArrayList<Cliente>(clienteTree.values());							
			Map<String, Object> response = new HashMap<>();
			if (clientes.isEmpty()) {
				response.put("Error", "al realizar la consulta en la base de datos!");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);	
		}
	//---------------------------------------------------------------------------------------------------------------	
	
//---------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {
		List<Cliente> clientes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clientes = clienteService.getAll();			
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (clientes != null) {
			return new ResponseEntity<>(clientes, HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
//---------------------------------------------------------------------------------------------------------------
/* 3. Consulta La consulta de un objeto se debe hacer contra el árbol binario 
 * y no directamente contra la base de datos.*/	
//---------------------------------------------------------------------------------------------------------------
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Cliente result = clienteTree.get(id);
		Map<String, Object> response = new HashMap<>();
		if (result == null) {
			response.put("error", "El cliente con Id: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(result, HttpStatus.OK);	
	}
//---------------------------------------------------------------------------------------------------------------	
//Metodo find con consulta directa a la base de datos incluye manejo de excepciones
//---------------------------------------------------------------------------------------------------------------	
	/*public ResponseEntity<?> find(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.get(id);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la consulta en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("error", "El cliente con la cédula: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}*/	
//---------------------------------------------------------------------------------------------------------------	
	/*1. Inserción y actualización Al insertar o actualizar el objeto, 
	 * debe primero agregarlo al árbol binario y luego almacenarlo a la base de datos. 
	 * De esta forma los datos quedan en memoria almacenados en el árbol 
	 * y en la base de datos de forma permanente.*/		
//---------------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		Cliente customer = null;
		Map<String, Object> response = new HashMap<>();
		if (cliente.validate() != null) {
			response.put("error", cliente.validate());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if (cliente.getCus_nmcliente()==0 || (clienteTree.get(cliente.getNmid())==null))
			{cliente.setCus_nmcliente(clienteTree.size()+1);}
			//---------------------------------------------------------------------------------------------------------------		
			//primero agregar al árbol binario		
			clienteTree.put(cliente.getNmid(), cliente);					
			//---------------------------------------------------------------------------------------------------------------
			customer=clienteService.save(cliente);
		} catch (DataAccessException e) {
			//---------------------------------------------------------------------------------------------------------------		
			//ante cualquier error al guardar en base de datos, se borra del arbol binario		
			clienteTree.remove(cliente.getNmid());					
			//---------------------------------------------------------------------------------------------------------------
			response.put("error", "Error al realizar el insert en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", customer);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
//---------------------------------------------------------------------------------------------------------------	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente updateCustomer = null;
		Map<String, Object> response = new HashMap<>();
		try {
//--------------------------------------------------------------------------------------------------------			
			//La consulta de un objeto se debe hacer contra el árbol binario
			Cliente currentCustomer = clienteTree.get(id);
 //--------------------------------------------------------------------------------------------------------           
			//Cliente currentCustomer = clienteService.get(id);
			if (currentCustomer == null) {
				response.put("error",
						"El cliente con Id: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}			
			if (cliente.validate() != null) {
				response.put("error", cliente.validate());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			currentCustomer.setNmid(cliente.getNmid());			
			currentCustomer.setCus_nmcliente(cliente.getCus_nmcliente());
			currentCustomer.setCus_dsnombres(cliente.getCus_dsnombres());
			currentCustomer.setCus_dsapellidos(cliente.getCus_dsapellidos());
			currentCustomer.setCus_dsdireccion(cliente.getCus_dsdireccion());
			currentCustomer.setCus_dscorreo(cliente.getCus_dscorreo());
			currentCustomer.setCus_cdtelefono(cliente.getCus_cdtelefono());
			currentCustomer.setCus_cdtelefonoalter(cliente.getCus_cdtelefonoalter());
			currentCustomer.setCus_cdcelular(cliente.getCus_cdcelular());
			currentCustomer.setCus_nmcargo(cliente.getCus_nmcargo());
			currentCustomer.setCus_dscargo(cliente.getCus_dscargo());
			currentCustomer.setCus_nmciudad(cliente.getCus_nmciudad());
			currentCustomer.setCus_dsciudad(cliente.getCus_dsciudad());
			currentCustomer.setCus_fenacimiento(cliente.getCus_fenacimiento());
			currentCustomer.setCus_genero(cliente.getCus_genero());
			currentCustomer.setCus_nmcomunidad(cliente.getCus_nmcomunidad());
			currentCustomer.setCus_dscomunidad(cliente.getCus_dscomunidad());
			currentCustomer.setCus_dsempresalabora(cliente.getCus_dsempresalabora());
			currentCustomer.setCus_nmdivision(cliente.getCus_nmdivision());
			currentCustomer.setCus_dsdivision(cliente.getCus_dsdivision());
			currentCustomer.setCus_nmpais(cliente.getCus_nmpais());
			currentCustomer.setCus_dspais(cliente.getCus_dspais());
			currentCustomer.setCus_hobbies(cliente.getCus_hobbies());
			currentCustomer.setCus_febaja(cliente.getCus_febaja());
			currentCustomer.setCus_feregistro(cliente.getCus_feregistro());
	//--------------------------------------------------------------------------------------------------------
			//primero agregar al árbol binario	
			
			clienteTree.put(currentCustomer.getNmid(), currentCustomer);
    //--------------------------------------------------------------------------------------------------------		
			updateCustomer = clienteService.save(currentCustomer);
		} catch (DataAccessException e) {
			response.put("error", "Error al realizar la actualización en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", updateCustomer);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
//---------------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
 //--------------------------------------------------------------------------------------------------------			
			//La consulta de un objeto se debe hacer contra el árbol binario
			cliente = clienteTree.get(id);
 //--------------------------------------------------------------------------------------------------------
			//cliente = clienteService.get(id);
			if (cliente == null) {
				response.put("error", "El cliente con Id: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
	//---------------------------------------------------------------------------------------------------------------		
			//primero se borra del arbol binario		
			clienteTree.remove(cliente.getNmid());					
	//---------------------------------------------------------------------------------------------------------------
			
			clienteService.delete(id);
		} catch (DataAccessException e) {
	//---------------------------------------------------------------------------------------------------------------		
			//ante algun error de borrado en base de datos se agrega nuevamente al arbol binario		
			clienteTree.put(cliente.getNmid(),cliente);					
	//---------------------------------------------------------------------------------------------------------------
			response.put("error", "Error al realizar la eliminación en la base de datos!");
			response.put("codigo", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
//---------------------------------------------------------------------------------------------------------------

