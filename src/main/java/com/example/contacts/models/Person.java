package com.example.contacts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max=120)
    private String name;

    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;

    @NotBlank
    @Email
    @Size(max=254)
    private String email;

    // public String getName(){
    //     return this.name;
    // }

    // public Integer getAge(){
    //     return this.age;
    // }

    // public String getEmail(){
    //     return this.email;
    // }
    
    // public void setName(String name){
    //     this.name = name;
    // }

    // public void setAge(Integer age){
    //     this.age = age;
    // }

    // public void setEmail(String email){
    //     this.email = email;
    // }
}
