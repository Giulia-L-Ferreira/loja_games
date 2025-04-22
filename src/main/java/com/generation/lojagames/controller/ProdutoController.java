package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
    private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
	
	@GetMapping("/nomejogo/{nomejogo}")
	public ResponseEntity<List<Produto>> getByNomejogo(@PathVariable String nomejogo) {
	    return ResponseEntity.ok(produtoRepository.findAllByNomejogoContainingIgnoreCase(nomejogo));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produtos){
		if (!produtoRepository.existsById(produtos.getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if (!categoriaRepository.existsById(produtos.getCategorias().getId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(produtoRepository.save(produtos));
		}
	
	@PutMapping
	public ResponseEntity<Produto> putEntity(@Valid @RequestBody Produto produtos){
		return produtoRepository.findById(produtos.getId())
				.map(produto -> ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produtos)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <Produto> categorias = produtoRepository.findById(id);
		
		if(categorias.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
		
	}
	
}
