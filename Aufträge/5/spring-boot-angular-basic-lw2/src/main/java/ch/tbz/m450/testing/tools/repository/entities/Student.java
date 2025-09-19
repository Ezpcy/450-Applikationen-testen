package ch.tbz.m450.testing.tools.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name ist erforderlich")
  private String name;

  @NotBlank(message = "Email ist erfoderlich")
  @Email(message = "Ungütlige Emai")
  private String email;

  // Required no-argument constructor for JPA
  public Student() {
  }

  // Constructor for creating students with name and email
  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  // Getters (required for JSON serialization)
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  // Setters (required for JPA)
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // toString for logging
  @Override
  public String toString() {
    return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}";
  }
}
