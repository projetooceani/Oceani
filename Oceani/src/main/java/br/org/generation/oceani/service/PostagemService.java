package br.org.generation.oceani.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.oceani.model.PostagemModel;
import br.org.generation.oceani.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	// Metodo curitr
	public PostagemModel curtir(Long id) {

		PostagemModel postagem = buscarPostagemPeloId(id);

		postagem.setCurtida(postagem.getCurtida() + 1);

		return postagemRepository.save(postagem);
	}
	
	public PostagemModel descurtir(Long id) {
		
		PostagemModel postagem = buscarPostagemPeloId(id);
		
		if(postagem.getCurtida() > 0) {
			
			postagem.setCurtida(postagem.getCurtida() - 1);
		} else {
			postagem.setCurtida(0);
		}
		return postagemRepository.save(postagem);
	}

	private PostagemModel buscarPostagemPeloId(Long id) {

		PostagemModel postagemSalva = postagemRepository.findById(id).orElse(null);

		if (postagemSalva == null) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não encontrada!", null);

		}
		return postagemSalva;
	}
}
