package uz.pdp.appexam_demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productManagement")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    private ResponseEntity<?> getProducts(){
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    private ResponseEntity<?> addProduct(@RequestBody Product product){
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getProduct(@PathVariable Integer id){
        Optional<Product> product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted)
        return  ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    private ResponseEntity<?> editProduct(@PathVariable Integer id, @RequestBody Product product){
        Product editedProduct = ProductService.editProduct(id, product);
        return ResponseEntity.ok(editedProduct);
    }
}
