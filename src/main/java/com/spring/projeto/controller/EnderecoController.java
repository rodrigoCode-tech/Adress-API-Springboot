package com.spring.projeto.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projeto.event.RecursoCriadoEvent;
import com.spring.projeto.model.Endereco;
import com.spring.projeto.repository.EnderecoRepository;
import com.spring.projeto.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired 
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Endereco> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id){
		Endereco end = service.findById(id);
		if(end == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(end);
		}
		
	}
	@PostMapping
	public ResponseEntity<Endereco> insert (@Valid @RequestBody Endereco novoEndereco, HttpServletResponse response){
		Endereco newEndereco = service.insert(novoEndereco);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newEndereco.getId() ));
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(newEndereco);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long id) throws Exception{
		service.delete(id);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> update (@PathVariable Long id, @Valid @RequestBody Endereco atualizar) throws Exception{
		Endereco atualizarEndereco = service.update(id, atualizar);
		return ResponseEntity.ok(atualizarEndereco);
		}
	}
