package br.org.generation.oceani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.oceani.model.TemaModel;

@Repository
public interface OceaniRepository  extends JpaRepository <TemaModel, Long>{
	public List<TemaModel> findAllByDescricaoIgnoreCase(String descricao);
}
