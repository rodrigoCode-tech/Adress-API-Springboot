package com.spring.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spring.projeto.model.Endereco;
import com.spring.projeto.repository.EnderecoRepository;

@Service
public class EnderecoService {

	
	@Autowired
	private EnderecoRepository repository;
	
	public List<Endereco> findAll(){
		return repository.findAll();
	}
	
	public Endereco findById(Long id) {
		Endereco end = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		if(end == null ) {
			throw new EmptyResultDataAccessException(1);
		}else {
			return end;
		}
	} 
	
	public Endereco insert(Endereco novoEndereco) {
		Endereco novo = repository.save(novoEndereco);
		return novo;
	}
	
	public void delete(Long id) throws Exception {
		
		Optional<Endereco> endereco = repository.findById(id);;
		repository.deleteById(id);
		
		
	}
	
	public Endereco update(Long codigo, Endereco atualizar) throws Exception{
		Endereco enderecoSalvo = repository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(atualizar, enderecoSalvo, "id");
		return repository.save(enderecoSalvo);
		
	}
	
	
}
