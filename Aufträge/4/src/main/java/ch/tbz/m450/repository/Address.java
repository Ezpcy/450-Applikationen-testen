package ch.tbz.m450.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "address")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
  @Id
  @Column(name = "user_id")

  private int id;
  private String firstname;
  private String lastname;
  private String phonenumber;
  private Date registrationDate;

  public Address(int id, String firstname, String lastname, String phonenumber, Date registrationDate) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.registrationDate = registrationDate;
  }

  public String getLastname() {
    return this.lastname;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public int getId() {
    return this.id;
  }
}
