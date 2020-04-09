package com.tactfactory.mdsb3dev20192020.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

  @Test
  public void userTestEmptyConstructor() {
    assertNotNull(new User());
  }

  @Test
  public void userTestTwoArgsConstructor() {
    boolean isValid = false;
    User user = new User("jean","michel");
    if(user.getFirstname().equals("jean") && user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestSetters() {
    boolean isValid = false;
    User user = new User();
    user.setFirstname("jean");
    user.setLastname("michel");

    if(user.getFirstname().equals("jean") && user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersFirstnameVariation1() {
    boolean isValid = false;
    User user = new User();
    user.setFirstname("Jean");

    if(user.getFirstname().equals("jean")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersFirstnameVariation2() {
    boolean isValid = false;
    User user = new User();
    user.setFirstname("jEan");

    if(user.getFirstname().equals("jean")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersFirstnameVariation3() {
    boolean isValid = false;
    User user = new User();
    user.setFirstname("JEAN");

    if(user.getFirstname().equals("jean")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersFirstnameVariation4() {
    boolean isValid = false;
    User user = new User();
    user.setFirstname("jEaN");

    if(user.getFirstname().equals("jean")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation1() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("michel");

    if(user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation2() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("mIchel");

    if(user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation3() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("MICHEL");

    if(user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation4() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("mIcHeL");

    if(user.getLastname().equals("Michel")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation5() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("");

    if(user.getLastname().equals("")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation6() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("a");

    if(user.getLastname().equals("A")) {
      isValid = true;
    }
    assertTrue(isValid);
  }

  @Test
  public void userTestGettersLastnameVariation7() {
    boolean isValid = false;
    User user = new User();
    user.setLastname("A");

    if(user.getLastname().equals("A")) {
      isValid = true;
    }
    assertTrue(isValid);
  }
}
