package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table (name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer author_id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    private String street;

    private String city;

    private String state;

    private String postal_code;

    private String phone;

    private String email;

    public Author(String firstName, String lastName, String street, String city, String state, String postal_code, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.phone = phone;
        this.email = email;
    }

    public Author() {
    }

    //Getters
    public Integer getAuthor_id(){
        return author_id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getStreet(){
        return street;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getPostal_code(){
        return postal_code;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    //Setters
    public void setAuthor_id(Integer author_id){
        this.author_id = author_id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public void setPostal_code(String postal_code){
        this.postal_code = postal_code;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getAuthor_id(), author.getAuthor_id()) &&
                Objects.equals(getFirstName(), author.getFirstName()) &&
                Objects.equals(getLastName(), author.getLastName()) &&
                Objects.equals(getCity(), author.getCity()) &&
                Objects.equals(getEmail(), author.getEmail()) &&
                Objects.equals(getPhone(), author.getPhone()) &&
                Objects.equals(getState(), author.getState()) &&
                Objects.equals(getStreet(), author.getStreet()) &&
                Objects.equals(getPostal_code(), author.getPostal_code());
    }

    @Override
    public int hashCode(){return Objects.hash(getAuthor_id(), getFirstName(), getLastName(), getCity(), getEmail(), getPhone(), getState(), getStreet(), getPostal_code());};
}