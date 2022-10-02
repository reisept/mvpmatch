package com.riscovirtual.mvpmatch.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.riscovirtual.mvpmatch.model.Product;
import com.riscovirtual.mvpmatch.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository repo;

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
		Product savedProduct = repo.save(product);
		// URI productURI = URI.create("/products/" + savedProduct.getId());
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@GetMapping
	public List<Product> list() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> list(@PathVariable int id) {

		Optional<Product> user = repo.findById(id);

		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/qty/{id}")
	public ResponseEntity<?> addQty(@PathVariable int id, @RequestParam int qty) {
		//TODO: only seller of the product can update qty
		Optional<Product> user = repo.findById(id);

		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/cost/{id}")
	public ResponseEntity<?> updateCost(@PathVariable int id, @RequestParam int qty) {
		//TODO: only seller of the product can update product cost
		Optional<Product> user = repo.findById(id);

		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
		}
	}	
}
