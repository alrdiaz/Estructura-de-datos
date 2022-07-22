package com.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
