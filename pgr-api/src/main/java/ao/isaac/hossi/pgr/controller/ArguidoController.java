package ao.isaac.hossi.pgr.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ao.isaac.hossi.pgr.model.Arguido;
import ao.isaac.hossi.pgr.model.Crime;
import ao.isaac.hossi.pgr.model.SituacaoArguido;
import ao.isaac.hossi.pgr.service.IArguidoService;
import ao.isaac.hossi.pgr.service.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" }) // Cor para acessar a api pelo angular via http
@RestController
@RequestMapping("/api") // mapear o rest controller
public class ArguidoController {

	@Autowired
	private IArguidoService arguidoService;
	@Autowired
	private IUploadFileService uploadService;

	// metodo index para listar arguidos
	@GetMapping("/arguidos") // mapear url
	public List<Arguido> index() {
		return arguidoService.findAll();
	}

// arguido com paginacao
	@GetMapping("/arguidos/page/{page}") // mapear url
	public Page<Arguido> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 7);
		return arguidoService.findAll(pageable);
	}

	// buscar por id
	@GetMapping("/arguidos/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Arguido arguido = null;
		Map<String, Object> response = new HashMap<>();
		try {
			arguido = arguidoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Erro ao realizar  a consulta na base de dados");
			response.put("error", e.getMessage().concat(" : ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (arguido == null) {// id.toString().concat
			response.put("mensaje", "O arguido ID:".concat(("Não existe na base de dados!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Arguido>(arguido, HttpStatus.OK);
	}

	// salvar

	// adcionado regras de permições
	@PostMapping("/arguidos")
	public ResponseEntity<?> create(@Valid @RequestBody Arguido arguido, BindingResult result) {
		Arguido arguidoNew = null;
		Map<String, Object> response = new HashMap<>();

		// adicionando validação
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> " O campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			arguidoNew = arguidoService.save(arguido);
		} catch (DataAccessException e) {

			response.put("mensaje", "Erro ao inserir os dados na base de dados");
			response.put("error", e.getMessage().concat(" : ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Arguido criado com sucesso!");
		response.put("arguido", arguidoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// actualizar
	@PutMapping("/arguidos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Arguido arguido, BindingResult result, @PathVariable Long id) {
		Arguido arguidoActual = arguidoService.findById(id);
		Arguido arguidoUpdate = null;
		Map<String, Object> response = new HashMap<>();

		// adicionando validação
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> " O campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		// mensagem
		if (arguidoActual == null) {// id.toString().concat
			response.put("mensaje", "Erro: não foi posivel editar O Arguido ID:"
					.concat(id.toString().concat("Não existe na base de dados!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
							
			arguidoActual.setNome(arguido.getNome());
			arguidoActual.setBi(arguido.getBi());
			arguidoActual.setEmail(arguido.getEmail());
			arguidoActual.setCondicaoRecepcao(arguido.getCondicaoRecepcao());
			arguidoActual.setContactoA(arguido.getContactoA());
			arguidoActual.setContactoP(arguido.getContactoP());
			arguidoActual.setQualidade(arguido.getQualidade());
			arguidoActual.setDataDetencao(arguido.getDataDetencao());
			arguidoActual.setDataLegalizacao(arguido.getDataLegalizacao());
			arguidoActual.setDataNascimento(arguido.getDataNascimento());
			arguidoActual.setCrime(arguido.getCrime());
			arguidoActual.setSituacao(arguido.getSituacao());
			arguidoActual.setEstadoCivil(arguido.getEstadoCivil());
			arguidoActual.setNaturalidade(arguido.getNaturalidade());
			arguidoActual.setFilhoDe(arguido.getFilhoDe());
			arguidoActual.setEfilhoDe(arguido.getEfilhoDe());
			arguidoActual.setPais(arguido.getPais());
			
			arguidoUpdate = arguidoService.save(arguidoActual);
		
		} catch (DataAccessException e) {

			response.put("mensaje", "Erro ao atualizar o arguido na base de dados");
			response.put("error", e.getMessage().concat(" : ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Arguido actualizado com sucesso!");
		response.put("arguido", arguidoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// delecte
	@DeleteMapping("/arguidos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Arguido arguido = arguidoService.findById(id);

			String nomeFotoAnterior = arguido.getFoto();
			uploadService.eliminar(nomeFotoAnterior);

			arguidoService.delete(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Erro ao eliminar o Arguido na base de dados");
			response.put("error", e.getMessage().concat(" : ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "O arguido foi eliminado sucesso!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	// upload de arquivo e imagens

	@PostMapping("/arguidos/upload")
	public ResponseEntity<?> upload(@RequestParam("arquivo") MultipartFile arquivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();
		Arguido arguido = arguidoService.findById(id);

		if (!arquivo.isEmpty()) {
			String nomeArquivo = null;
			try {
				nomeArquivo = uploadService.copiar(arquivo);
			} catch (IOException e) {
				response.put("mensaje", "Erro ao carregar a imagem do arguido");
				response.put("error", e.getMessage().concat(" : ".concat(e.getCause().getMessage())));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nomeFotoAnterior = arguido.getFoto();

			uploadService.eliminar(nomeFotoAnterior);

			arguido.setFoto(nomeArquivo);
			arguidoService.save(arguido);
			response.put("arguido", arguido);
			response.put("mensaje", "A imagem foi carregada correctamente:" + nomeArquivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// metodo para mostrar arquivo ou imagem
	@GetMapping("/uploads/img/{nomeFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nomeFoto) {

		Resource recurso = null;
		try {
			recurso = uploadService.carregar(nomeFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	// metodo index para listar arguido

	@GetMapping("/arguidos/crimes") // mapear url
	public List<Crime> listarCrimes() {
		return arguidoService.findAllCrimes();
	}

	@GetMapping("/arguidos/situacoes") // mapear url
	public List<SituacaoArguido> listarSituacao() {
		return arguidoService.findAllSituacoes();
	}

}
