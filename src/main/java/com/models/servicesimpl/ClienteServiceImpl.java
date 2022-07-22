package com.models.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.commons.GenericImpl;
import com.models.dao.IClienteDao;
import com.models.entity.Cliente;
import com.models.services.IClienteService;

@Service
public class ClienteServiceImpl extends GenericImpl<Cliente, Long> implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	public ClienteServiceImpl(IClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	protected CrudRepository<Cliente, Long> getDao() {
		return clienteDao;
	}

	

}
