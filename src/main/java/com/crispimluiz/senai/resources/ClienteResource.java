package com.crispimluiz.senai.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crispimluiz.senai.domain.Cliente;
import com.crispimluiz.senai.services.ClienteService;

@RestController//Para serem Serializados e transformados em Json ou Xml
@RequestMapping(value="/clientes")//caminho que será encontrado.
public class ClienteResource {
	
	//Buscando o os médotos que estão em Cliente Service
	@Autowired// Faz a instanciação sem precisar do new
	private ClienteService service;
	
	//Nesse Resquesti pediremos o id do cliente
	//method requesitado é o get
	//Resposta da entidade será do Cliente em Domain
	//find e encontrar
	//@PathVariable - O valor vira da URL
	//obj - service (ClienteService metodo buscar)
	//Retorna no corpo do objeto todas as informações.
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	//RequestMapping - buscar get
	//ResponseEntity - Classe Domain @Entity
	//List - lista onde <Cliente>
	//body - lista no corpo do componente
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = service.findAll();  
		return ResponseEntity.ok().body(list);
	}	
//fala que ser inserito um valor pelo frontEndd
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Cliente obj) {
//Resposta volta vazia, insere valida e volta com um valor no corpo da requição
		obj = service.insert(obj);//Service - ClienteService
		//Uri é o trabalho do Browser em enviar para o BackEnd
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(uri).build();
	}
//Tem que buscar o id de quem será atualizado
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Integer id) {
		obj.setIdCliente(id);//garantir que veio o Id do Objeto que será mudado.
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
}
