package com.models.servicesimpl;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.models.dao.IClienteDao;
import com.models.entity.Cliente;
import com.data.Datos;

@SpringBootTest
class ClienteServiceImplTest {
		
	IClienteDao iClienteDao;	
	ClienteServiceImpl clienteServiceImpl;
	
	@BeforeEach
	void setUp() {
		iClienteDao=mock(IClienteDao.class);
		clienteServiceImpl=new ClienteServiceImpl(iClienteDao);
	}

	@Test
	void testGet() {
		when(iClienteDao.findById(Datos.datos.getNmid())).thenReturn(Optional.of(Datos.datos));		
		Cliente result = clienteServiceImpl.get(Datos.datos.getNmid());		
		assertEquals(Datos.datos,result);	
		verify(iClienteDao).findById(Datos.datos.getNmid());
	}

	
	@Test
	void testSave() {
		when(iClienteDao.save(Datos.datos)).thenReturn(Datos.datos);
		Cliente result = clienteServiceImpl.save(Datos.datos);		
		assertEquals(Datos.datos,result);
		verify(iClienteDao).save(Datos.datos);
	}
	
	@Test
	void testGetAll() {
		when(iClienteDao.findAll()).thenReturn(List.of(Datos.datos));
		List<Cliente> resultAll = clienteServiceImpl.getAll();
		assertEquals(List.of(Datos.datos),resultAll);
		verify(iClienteDao).findAll();
	}
		

	@Test
	void testDelete() {
		when(iClienteDao.findById(Datos.datos.getNmid())).thenReturn(Optional.of(Datos.datos));
		clienteServiceImpl.delete(Datos.datos.getNmid());
		verify(iClienteDao).deleteById(Datos.datos.getNmid());
	}

}
