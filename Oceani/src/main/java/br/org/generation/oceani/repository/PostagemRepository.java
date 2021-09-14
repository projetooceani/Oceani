package br.org.generation.oceani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.oceani.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
	
	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String Titulo);

}