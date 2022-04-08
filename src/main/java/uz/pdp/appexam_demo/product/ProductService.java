package uz.pdp.appexam_demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    static
    ProductRepository productRepository;

    public static Product editProduct(Integer id, Product product) {
        Optional<Product> editingProduct = productRepository.findById(id);
        if(editingProduct.isPresent()){
            editingProduct.get().setPrice(product.getPrice());
            editingProduct.get().setName(product.getName());
        }
        return editingProduct.get();

    }

    public List<Product> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    public Product addProduct(Product product) {
        Product saved = productRepository.save(product);
        return saved;
    }

    public Optional<Product> getProduct(Integer id) {
        Optional<Product> searchingProduct = productRepository.findById(id);
        if (searchingProduct.isPresent()){
            return searchingProduct;
        }else{
            return Optional.empty();
        }
    }

    public boolean deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return true;
    }
}
