package com.pierreya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pierreya.model.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
