package br.com.eduardolpsss.javaCrud.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.eduardolpsss.javaCrud.model.entities.Funcionario;
import br.com.eduardolpsss.javaCrud.model.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// Cadastro de funcionários no banco
	@PostMapping
	public @ResponseBody Funcionario novoFuncionario(@Valid Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return funcionario;
	}

	// Get all funcionários
	@GetMapping
	public Iterable<Funcionario> obterFuncionarios() {
		return funcionarioRepository.findAll();
	}

	// Buscando no banco por id
	@GetMapping(path = "/{id}")
	public Optional<Funcionario> obterFuncionario(@PathVariable int id) {
		return funcionarioRepository.findById(id);
	}

	// Buscando no banco por páginas
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Funcionario> obterFuncionarioPorPagina(@PathVariable int numeroPagina,
			@PathVariable int qtdePagina) {
		if (qtdePagina >= 5)
			qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return funcionarioRepository.findAll(page);
	}
	
	// Obter funcionário por nome
	@GetMapping(path = "/nome/{parteNome}")
	public Iterable<Funcionario> obterFuncionarioPorNome(@PathVariable String parteNome){
		return funcionarioRepository.findByNomeContainingIgnoreCase(parteNome);
	}

	// Editando no banco
	@PutMapping
	public Funcionario alterarFuncionario(@Valid Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return funcionario;
	}

	// Excluindo no banco por id
	@DeleteMapping(path = "/{id}")
	public void excluirFuncionario(@PathVariable int id) {
		funcionarioRepository.deleteById(id);
	}
}
