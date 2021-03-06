package com.tactfactory.monprojetsb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tactfactory.monprojetsb.Tp1Application;
import com.tactfactory.monprojetsb.entities.Product;
import com.tactfactory.monprojetsb.entities.User;
import com.tactfactory.monprojetsb.repository.MockitoUserRepository;
import com.tactfactory.monprojetsb.repository.ProductRepository;
import com.tactfactory.monprojetsb.repository.UserRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = { "classpath:application-test.properties" })
@SpringBootTest(classes = Tp1Application.class)
public class UserServiceTest {

	@MockBean
    private UserService userService;

	@MockBean
    private UserRepository userRepository;
	
	private User entity;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() throws Exception {
      final MockitoUserRepository mock = new MockitoUserRepository(this.userRepository);
      mock.intialize();
      this.entity = mock.entity;
    }

    @Test
    public void TestInsertOne() {
        Long before = userRepository.count();
        userService.save(new User());
        // Add one
        Long after = userRepository.count();

        assertEquals(before + 1, after);
    }

    @Test
    public void TestInsertUser() {
        User userBase = new User(null, "LastName", "FirstName", new ArrayList<Product>());
        Long id = userService.save(userBase).getId();
        User userFetch = userRepository.getUserById(id);

        assertTrue(compare(userBase, userFetch));
    }

    @Test
    public void TestUpdateUser() {
        User userBase = new User(null, "LastName", "FirstName", new ArrayList<Product>());
        Long id = userService.save(userBase).getId();

        Product product1 = new Product(null, "p1",(float) 10);
        Long p1 = productRepository.save(product1).getId();
        Product productFetch1 = productRepository.getProductById(p1);

        Product product2 = new Product(null, "p2",(float) 10);
        Long p2 = productRepository.save(product2).getId();
        Product productFetch2 = productRepository.getProductById(p2);

        List<Product> products = new ArrayList<Product>();
        products.add(productFetch1);
        products.add(productFetch2);

        User userFetch = userRepository.getUserById(id);
        userFetch.setProducts(products);

        Long idUpdated = userService.save(userFetch).getId();
        User userFetchUpdated = userRepository.getUserById(id);

        assertTrue(compare(userFetch, userFetchUpdated));
    }

    @Test
    public void TestGetUser() {
        User userBase = new User(null , "LastName", "FirstName", new ArrayList<Product>());
        Long id = userRepository.save(userBase).getId();
        User userFetch = userService.getUserById(id);

        assertTrue(compare(userBase, userFetch));
    }

    @Test
    public void TestGetUsers() {
        List<User> users = new ArrayList<User>();
        
        User user1 = new User(null, "LastName", "FirstName", new ArrayList<Product>());
        users.add(user1);
        
        User user2 = new User(null, "LeDieu", "Brandon", new ArrayList<Product>());
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
        User userBase = new User(null , "LastName", "FirstName", new ArrayList<Product>());
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
