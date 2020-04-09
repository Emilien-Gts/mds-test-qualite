package com.tactfactory.mdsb3dev20192020.entities;

public class User {
  private String firstname;
  private String lastname;

  public User() {
    super();
  }

  public User(String firstname, String lastname) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getFirstname() {

    return firstname.toLowerCase();
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    StringBuilder builder = new StringBuilder();
    if (lastname.length()>0) {
      builder.append(lastname.substring(0,1).toUpperCase());
      builder.append(lastname.substring(1,lastname.length()).toLowerCase());
    }

    return builder.toString();
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
}
