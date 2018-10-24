package com.pierreya.resources;

import javax.jms.Queue;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pierreya.model.Funcionario;
import com.pierreya.repository.FuncionarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST FUNCIONARIOS")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	@ApiOperation(value="listar funcionarios")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Funcionario> listaFuncionarios() {
		Iterable<Funcionario> listaFuncionarios = fr.findAll();
		return listaFuncionarios;
	}
	
	@ApiOperation(value="cadastra funcionario")
	@PostMapping()
	public Funcionario cadastrarFuncionario(@RequestBody @Valid Funcionario funcionario) {
		Funcionario salvo = null;
		try {
			salvo = fr.save(funcionario);
			if(salvo != null) {
				jmsTemplate.convertAndSend(queue,salvo.getCodigo());
			}
		}catch(Exception e) {
			jmsTemplate.convertAndSend(queue,"falha ao incluir");
		}
		
		return salvo;
	}
	
	@ApiOperation(value="deleta funcionario")
	@DeleteMapping()
	public Funcionario deletaFuncionario(@RequestBody Funcionario funcionario) {
		fr.delete(funcionario);
		return funcionario;
	}
	
}
