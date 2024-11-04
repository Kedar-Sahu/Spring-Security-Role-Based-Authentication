package in.main.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN') and hasAuthority('CREATE')")
	public ResponseEntity<?> saveProduct(){
		String msg="Admin :: Create Product";
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER','CUSTOMER') and hasAuthority('READ')")
	public ResponseEntity<?> getProduct(){
		String msg="Admin,Seller,Customer :: View Product";
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	@PreAuthorize("hasAnyRole('ADMIN','SELLER') and hasAuthority('UPDATE')")
	public ResponseEntity<?> editProduct(){
		String msg="Admin,Seller :: Edit Product";
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAnyRole('ADMIN') and hasAuthority('DELETE')")
	public ResponseEntity<?> deleteProduct(){
		String msg="Admin :: Delete Product";
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
}
