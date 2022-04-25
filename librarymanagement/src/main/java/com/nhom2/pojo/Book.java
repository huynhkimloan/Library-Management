/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Book {
    private int book_id;
    private String book_name;
    private String description;
    private String publishing_company;
    private Date import_date;
    private boolean active;
    private String location;
    private Date publishing_year;
    private String category;
    private String author;
    
    public Book() {}
    
    public Book(int book_id, String book_name, String description, 
                 String publishing_company, 
                Date import_date, boolean active, String location, 
                Date publishing_year, String category, String author) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.description = description;  
        this.publishing_company = publishing_company;
        this.import_date = import_date;
        this.active = active;
        this.location = location;
        this.publishing_year = publishing_year;
        this.category = category;
        this.author = author;      
    }

    public Book(String book_name, String description, String publishing_company, Date import_date, String location, Date publishing_year, String category, String author) {
        this.book_name = book_name;
        this.description = description;  
        this.publishing_company = publishing_company;
        this.import_date = import_date;
        this.location = location;
        this.publishing_year = publishing_year;
        this.category = category;
        this.author = author;
    }

    /**
     * @return the book_id
     */
    public int getBook_id() {
        return book_id;
    }

    /**
     * @param book_id the book_id to set
     */
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active=true;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the book_name
     */
    public String getBook_name() {
        return book_name;
    }

    /**
     * @param book_name the book_name to set
     */
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    /**
     * @return the publishing_company
     */
    public String getPublishing_company() {
        return publishing_company;
    }

    /**
     * @param publishing_company the publishing_company to set
     */
    public void setPublishing_company(String publishing_company) {
        this.publishing_company = publishing_company;
    }

    /**
     * @return the import_date
     */
    public String getImport_date() {
        SimpleDateFormat f =new SimpleDateFormat ("dd-MM-yyyy");
        return f.format(import_date);
    }

    /**
     * @param import_date the import_date to set
     */
    public void setImport_date(Date import_date) {
        this.import_date = import_date;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the publishing_year
     */
    public String getPublishing_year() {
        SimpleDateFormat f =new SimpleDateFormat ("dd-MM-yyyy");
        return f.format(publishing_year);
    }

    /**
     * @param publishing_year the publishing_year to set
     */
    public void setPublishing_year(Date publishing_year) {
        this.publishing_year = publishing_year;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

   
}
