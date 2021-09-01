package br.org.generation.oceani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.oceani.model.TemaModel;
import br.org.generation.oceani.repository.OceaniRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OceaniController {
	
	@Autowired
	private OceaniRepository oceaniRepository;
	
	@GetMapping
	public ResponseEntity <List <TemaModel>> getAll(){
		return ResponseEntity.ok(oceaniRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <TemaModel> getById (@PathVariable long id){
		return oceaniRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity <List <TemaModel>> getByDescricao (@PathVariable String descricao){
		return ResponseEntity.ok(oceaniRepository.findAllByDescricaoIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<TemaModel> postTemaModel (@RequestBody TemaModel temaModel){
		return ResponseEntity.status(HttpStatus.CREATED).body(oceaniRepository.save(temaModel));
	}
	
	@PutMapping
	public ResponseEntity <TemaModel> putTemaModel (@RequestBody TemaModel temaModel){
		return ResponseEntity.status(HttpStatus.OK).body(oceaniRepository.save(temaModel));
	}
	
	@DeleteMapping("/{id}")
	public void deleteTemaModel (@PathVariable long id) {
		oceaniRepository.deleteById(id);
	}

}
