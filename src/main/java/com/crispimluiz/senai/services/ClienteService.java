package com.crispimluiz.senai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crispimluiz.senai.domain.Cliente;
import com.crispimluiz.senai.repositories.ClienteRepository;
import com.crispimluiz.senai.repositories.EnderecoRepository;
import com.crispimluiz.senai.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Veja que está instanciando de Repositore que tem DML
	//Repo - abaixo - é o objeto criado
	@Autowired //faz a instancia para mim sem o new.
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Metodo que faz a busca de cliente por id
	public Cliente buscar(Integer idCliente) {
		/*Optional é para se vier vazio não de o erro
		 NullPointerException - Mas me traga uma msg de erro */
		Optional<Cliente> obj = repo.findById(idCliente);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Número de id não encontrado. Id: " + idCliente + ", tipo: " 
				+ Cliente.class.getName()));
	}
	
	//Buscar todos os clientes.
	//Retornar uma lista - list
	//findAll - encobtrar todos
	//repo -private ClienteRepository repo;
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setIdCliente(null);//Set Cliente do Domain
		obj = repo.save(obj);/*Save do Repository Cliente
		Lembre-se que dei o nome de ClienteRespository de repo*/
		//usando o Repositories de save (Post)
		enderecoRepository.saveAll(obj.getEnderecos());
		//Salvar o Endereço digitado.
		return obj;
	}
//Para Atualizar um Cliente - o método vai se chamar update
	public Cliente update(Cliente obj) {
		//Buscar já criado por nós
		buscar(obj.getIdCliente());
		//repo é a instancia de ClienteRepository
		return repo.save(obj);
	}
}
