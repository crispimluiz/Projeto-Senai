package com.crispimluiz.senai;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crispimluiz.senai.domain.Cidade;
import com.crispimluiz.senai.domain.Cliente;
import com.crispimluiz.senai.domain.Endereco;
import com.crispimluiz.senai.domain.Estado;
import com.crispimluiz.senai.domain.enuns.TipoCliente;
import com.crispimluiz.senai.repositories.CidadeRepository;
import com.crispimluiz.senai.repositories.ClienteRepository;
import com.crispimluiz.senai.repositories.EnderecoRepository;
import com.crispimluiz.senai.repositories.EstadoRepository;

@SpringBootApplication
public class CursoSenaiApplication implements CommandLineRunner {

	// Uma dependencia para instanciar todo o objeto.
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoSenaiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Instacio estado e preencho
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		// Foi chamado acima e aqui salva.
		estadoRepository.saveAll(Arrays.asList(est1, est2));

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		cidadeRepository.saveAll(Arrays.asList(c1, c2));

		Cliente cli1 = new Cliente(null, "Crispim Luiz", "12345678987", "c.luiz@fiemg.com.br",
				TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Godofredo", "987654321", "teste@fiemg.com.br", 
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("34900000000", "98765432112"));
		cli1.getTelefones().addAll(Arrays.asList("34900000123", "98765400000"));
		cli2.getTelefones().addAll(Arrays.asList("7777777777", "99999999999"));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));

		Endereco e1 = new Endereco(null, "Rua das Emboabas", "55b", "Ap 310", "Jardim", "38447587", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Alameda Nasciute", "234", null, "Centro", "34555098", cli2, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
