package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String image;
    private String type;

    public Song(Long id, String name, String author, String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
    }

    public Song(Long id, String name, String author, String image, String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.image = image;
        this.type = type;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.isAssignableFrom(clazz); //class này đc validate
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song) target;
        String name =song.getName();
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        if (name.length()<5){
            errors.rejectValue("name", "name.length");
        }
//        if (!name.matches("[A-Za-z0-9_ ]")){
//            errors.rejectValue("name", "name.matches");
//        }

        String author =song.getAuthor();
        ValidationUtils.rejectIfEmpty(errors, "author", "author.empty");
        if (author.length()<5){
            errors.rejectValue("author", "author.length");
        }
//        if (!author.matches("[A-Za-z0-9_ ]")){
//            errors.rejectValue("author", "author.matches");
//        }
        String type =song.getType();
        ValidationUtils.rejectIfEmpty(errors, "type", "type.empty");
        if (type.length()<5){
            errors.rejectValue("type", "type.length");
        }
//        if (!type.matches("[A-Za-z0-9_ ]")){
//            errors.rejectValue("type", "type.matches");
//        }
    }
}
