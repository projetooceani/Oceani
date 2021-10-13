package br.org.generation.oceani.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.oceani.model.UsuarioLogin;
import br.org.generation.oceani.model.UsuarioModel;
import br.org.generation.oceani.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioModel> listarUsuarios(){

		return usuarioRepository.findAll();

	}

	public Optional<UsuarioModel> buscarUsuarioId(long id){

		return usuarioRepository.findById(id);

	}

	public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(usuario));

	}

public Optional <UsuarioModel> atualizarUsuario(UsuarioModel usuario){
		
	
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			
		
			Optional<UsuarioModel> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if( buscaUsuario.isPresent() ){

			
				if(buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			
			return Optional.of(usuarioRepository.save(usuario));
		
		}else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			
		}
		
	}
		

public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

	if (usuario.isPresent()) {
		if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

			String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodedAuth);

			usuarioLogin.get().setId(usuario.get().getId());				
			usuarioLogin.get().setNome(usuario.get().getNome_completo());
			usuarioLogin.get().setSenha(usuario.get().getSenha());
			usuarioLogin.get().setFoto(usuario.get().getFoto());
			usuarioLogin.get().setTipo(usuario.get().getTipo());
			usuarioLogin.get().setToken(authHeader);

			return usuarioLogin;

		}
	}
	throw new ResponseStatusException(
			HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
}

}


