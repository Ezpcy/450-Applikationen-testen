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

  public Address() {
  }

  public Address(int id, String firstname, String lastname, String phonenumber, Date registrationDate) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.registrationDate = registrationDate;
  }

  public void setLastname(String name) {
    this.lastname = name;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setFirstname(String name) {
    this.firstname = name;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public Date getRegistrationDate() {
    return this.registrationDate;
  }

  public void setRegistrationDate(Date date) {
    this.registrationDate = date;
  }

  public String getPhonenumber() {
    return this.phonenumber;
  }

  public void setPhonenumber(String num) {
    this.phonenumber = num;
  }

}
