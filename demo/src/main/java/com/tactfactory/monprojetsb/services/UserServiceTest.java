package com.tactfactory.monprojetsb.services;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.tactfactory.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.repository.ProductRepository;
import com.tactfactory.monprojetsb.repository.UserRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@EntityScan(basePackages="com.tactfactory.qualitelogiciel")
@ComponentScan(basePackages="com.tactfactory.qualitelogiciel")
public class UserServiceTest {

	@Autowired
    private UserService userService;

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
        Long before = userRepository.count();
        userService.save(new User());
        Long after = userRepository.count();

        assertEquals(before + 1, after);
    }

    @Test
    public void TestInsertUser() {
        User userBase = new User(null, "Tardif", "Dylan", new ArrayList<Product>());
        Long id = userService.save(userBase).getId();
        User userFetch = userRepository.getUserById(id);

        assertTrue(compare(userBase, userFetch));
    }

    @Test
    public void TestUpdateUser() {
        // Create User
        User userBase = new User(null, "Tardif", "Dylan", new ArrayList<Product>());
        Long id = userService.save(userBase).getId();

        //Create Product 1
        Product product1 = new Product(null, "product1",(float) 10);
        Long p1 = productRepository.save(product1).getId();
        Product productFetch1 = productRepository.getProductById(p1);

        // Create product 2
        Product product2 = new Product(null, "product2",(float) 10);
        Long p2 = productRepository.save(product2).getId();
        Product productFetch2 = productRepository.getProductById(p2);

        // Add products 1 and 2 to products
        List<Product> products = new ArrayList<Product>();
        products.add(productFetch1);
        products.add(productFetch2);

        // Get user and set products
        User userFetch = userRepository.getUserById(id);
        userFetch.setProducts(products);

        // Update user and get id to check modifications
        Long idUpdated = userService.save(userFetch).getId();
        User userFetchUpdated = userRepository.getUserById(id);

        assertTrue(compare(userFetch, userFetchUpdated));
    }

    @Test
    public void TestGetUser() {
        User userBase = new User(null , "Tardif", "Dylan", new ArrayList<Product>());
        Long id = userRepository.save(userBase).getId();
        User userFetch = userService.getUserById(id);

        assertTrue(compare(userBase, userFetch));
    }

    @Test
    public void TestGetList() {
        List<User> users = new ArrayList<User>();
        User user1 = new User(null, "Tardif", "Dylan", new ArrayList<Product>());
        users.add(user1);
        User user2 = new User(null, "Gantois", "Emilien", new ArrayList<Product>());
        users.add(user2);

        userService.saveList(users);

        List<User> usersFetch = userService.findAll();

        for (int i = 0; i < usersFetch.size(); i++) {
            assertTrue(compare(users.get(i), usersFetch.get(i)));
        }
    }

    @Test
    public void TestDeleteOne() {
        User userTemp = new User();
        userService.save(userTemp);
        Long before = userRepository.count();
        userService.delete(userTemp);
        Long after = userRepository.count();

        assertEquals(before - 1, after);
    }

    @Test
    public void TestDeleteUser() {
        User userBase = new User(null , "Tardif", "Dylan", new ArrayList<Product>());
        Long id = userRepository.save(userBase).getId();
        userService.delete(userBase);

        User deletedUser = userService.getUserById(id);
        assertNull(deletedUser);
    }

    public Boolean compare(User user1, User user2) {
        boolean result = true;

        if (!user1.getId().equals(user2.getId())) {
            result = false;
            System.out.println("id: " + user1.getId() + " " + user2.getId());
        }
        if (!user1.getFirstname().equals(user2.getFirstname())) {
            result = false;
            System.out.println("firstname: " + user1.getFirstname() + " " + user2.getFirstname());
        }
        if (!user1.getLastname().equals(user2.getLastname())) {
            result = false;
            System.out.println("lastname: " + user1.getLastname() + " " + user2.getLastname());
        }

        return result;
    }

}
