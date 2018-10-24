package com.pierreya.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.pierreya.model.Funcionario;
import com.pierreya.repository.FuncionarioRepository;

@Component
public class ActiveMQConsumer {

	@Autowired
	private FuncionarioRepository fr;
	
	@JmsListener(destination="standalone.mq.funcionario")
	public void consumer(String mensagem) {
		Long codigoFuncionario = null;
		try {
			codigoFuncionario = Long.parseLong(mensagem);
			Funcionario funcionario = fr.findById(Long.parseLong(mensagem)).get();
			funcionario.setSincronizado(true);
			fr.saveAndFlush(funcionario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
