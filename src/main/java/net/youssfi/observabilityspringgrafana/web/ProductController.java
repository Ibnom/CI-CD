package net.youssfi.observabilityspringgrafana.web;

import io.micrometer.observation.annotation.Observed;
import net.youssfi.observabilityspringgrafana.entities.Product;
import net.youssfi.observabilityspringgrafana.model.Post;
import net.youssfi.observabilityspringgrafana.repository.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author mohamedyoussfi
 **/
@RestController
public class ProductController {
    private ProductRepository productRepository;
    private RestClient restClient;

    public ProductController(ProductRepository productRepository,
                             RestClient.Builder restClient) {
        this.productRepository = productRepository;
        this.restClient = restClient
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
    @GetMapping("/posts")
    public List<Post> allPosts(){
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @PostMapping()
    public Product save(@RequestBody Product product){
      return productRepository.save(product);
    }
    @DeleteMapping("/{id}")
    public void save(@PathVariable long id){
        Product product = productRepository.findById(id).get();
        productRepository.deleteById(id);
    }
}
