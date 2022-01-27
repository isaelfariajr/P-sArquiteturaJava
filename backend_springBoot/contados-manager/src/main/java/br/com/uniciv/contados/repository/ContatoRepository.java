package br.com.uniciv.contados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uniciv.contados.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer>{

}
