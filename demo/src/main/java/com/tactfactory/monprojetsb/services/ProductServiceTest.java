package com.tactfactory.monprojetsb.services;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tactfactory.monprojetsb.Tp1Application;
import com.tactfactory.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.repository.ProductRepository;
import com.tactfactory.monprojetsb.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringBootTest(classes = Tp1Application.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void clear() {
        this.productRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void TestInsertOne() {
        Long before = productRepository.count();
        productService.save(new Product());
        Long after = productRepository.count();

        assertEquals(before + 1, after);
    }

    @Test
    public void TestInsertProduct() {
        Product product = new Product(null, "product1", (float) 10);
        Long id = productService.save(product).getId();
        Product productFetch = productRepository.getProductById(id);

        assertTrue(compare(product, productFetch));
    }

    @Test
    public void TestUpdateProduct() {
        // Create Product
        Product product = new Product(null, "product1", (float) 10);
        Long id = productService.save(product).getId();

        // Get Product and set new value
        Product productFetch = productRepository.getProductById(id);
        productFetch.setPrice((float) 20);

        // Update user and get id to check modifications
        Long idUpdated = productService.save(productFetch).getId();
        Product productFetchUpdated = productRepository.getProductById(id);

        assertTrue(compare(productFetch, productFetchUpdated));
    }

    @Test
    public void TestGetProduct() {
        Product productBase = new Product(null, "product1", (float) 10);
        Long id = productRepository.save(productBase).getId();
        Product productFetch = productService.getProductById(id);

        assertTrue(compare(productBase, productFetch));
    }

    @Test
    public void TestGetProducts() {
        List<Product> products = new ArrayList<Product>();
        Product product1 = new Product(null, "product1", (float) 10);
        products.add(product1);
        Product product2 = new Product(null, "product2", (float) 10);
        products.add(product2);

        productService.saveList(products);

        List<Product> productsFetch = productService.findAll();

        for (int i = 0; i < productsFetch.size(); i++) {
            assertTrue(compare(products.get(i), productsFetch.get(i)));
        }
    }

    @Test
    public void TestDeleteOne() {
        Product productTemp = new Product();
        productService.save(productTemp);
        Long before = productRepository.count();
        productService.delete(productTemp);
        Long after = productRepository.count();

        assertEquals(before - 1, after);
    }

    @Test
    public void TestDeleteProduct() {
        Product productBase = new Product(null, "product1", (float) 10);
        Long id = productRepository.save(productBase).getId();
        productService.delete(productBase);

        Product deletedProduct = productService.getProductById(id);
        assertNull(deletedProduct);
    }

    public Boolean compare(Product product1, Product product2) {
        boolean result = true;

        if (!product1.getId().equals(product2.getId())) {
            result = false;
            System.out.println("id: " + product1.getId() + " " + product2.getId());
        }
        if (!product1.getName().equals(product2.getName())) {
            result = false;
            System.out.println("name: " + product1.getName() + " " + product2.getName());
        }
        if (!product1.getPrice().equals(product2.getPrice())) {
            result = false;
            System.out.println("price: " + product1.getPrice() + " " + product2.getPrice());
        }

        return result;
    }
}

