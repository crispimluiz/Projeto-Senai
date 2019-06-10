package com.crispimluiz.senai.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.crispimluiz.senai.domain.enuns.TipoCliente;

@Entity // Avisa ao JPA que será uma tabela.
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // Anotação avisando que esse atributo Será uma id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	// Atributos serão Campos na tabela
	private String nome;
	private String cpfOuCnpj;
	//Será usado no emum.
	private Integer tipo;

	// O banco de dados não deixa repetir os dados
	@Column(unique = true)
	private String email;

	@ElementCollection // Cria uma tabela telefone, tabela fraca
	@CollectionTable(name = "TELEFONE") // Nome da tabela
	private Set<String> telefones = new HashSet<>();
	// set não aceita repetição

	// Cascade e para se excluir cliente o endereço seja excuido
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) // Varios endereços um cliente
	private List<Endereco> enderecos = new ArrayList<>();
	// mappedBy = Onde foi mapeado em Endereço ou instanciado

	// Constutor
	public Cliente() {
	}

	public Cliente(Integer idCliente, String nome, String cpfOuCnpj, String email, 
			/*Atributo busca a Class*/		
			TipoCliente tipo) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.email = email;
		//Aqui não vai telefone
		//Dentro da class busca o cod
		this.tipo = tipo.getCod();
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
		// esse to enum vem da classe enum tipo
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();// add .get também
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

}
