/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.pojo;

import java.text.ParseException;
import java.util.Date;
import javafx.scene.control.CheckBox;
import java.text.SimpleDateFormat;


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

    private CheckBox select;
    
    public Book() {
    }

    public Book(int book_id, String book_name, String description, 
            String publishing_company, Date import_date, boolean active, 
            String location, Date publishing_year, String category, String author) {
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
    
    public Book(String book_name, String description, String publishing_company, Date import_date,
             String location, Date publishing_year, String category, String author) {
        this.book_name = book_name;
        this.description = description;
        this.publishing_company = publishing_company;
        this.import_date = import_date;
        this.location = location;
        this.publishing_year = publishing_year;
        this.category = category;
        this.author = author;
    }

    
//    public Book(String book_name, String description, String publishing_company, Date publishing_year, String category, String author) {
//        this.book_name = book_name;
//        this.description = description;
//        this.publishing_company = publishing_company;
//        this.publishing_year = publishing_year;
//        this.category = category;
//        this.author = author;
//    }
    
    
    public Book(String book_name, String category, String author, 
            String description, String publishing_company, Date publishing_year) {
       
        this.book_name = book_name;
        this.category = category;
        this.author = author;
        this.description = description;
        this.publishing_company = publishing_company;
        this.publishing_year = publishing_year;
        this.select = new CheckBox();
        this.select.setDisable(true);
    }
    
    public Book(int book_id, String book_name, boolean active) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.active = active;
        
    }

    @Override
    public String toString() {
        return this.publishing_company;
    }
    
            
    /**hàm get_set ManagementBook
    /**
     * @return the publishing_year
     * @throws java.text.ParseException
     */
    public String getPublishing_year_default() throws ParseException {
        SimpleDateFormat f =new SimpleDateFormat ("yyyy-MM-dd");
        String publishing_date = f.format(getPublishing_year());
        Date publishing_date_value = new Date(f.parse(publishing_date).getTime());
        return f.format(publishing_date_value);
    }
    
    public String getPublishing_year_sub() throws ParseException {
        SimpleDateFormat f =new SimpleDateFormat ("dd-MM-yyyy");
        String value = f.format(getPublishing_year());
        return value;
    }

    /**
     * @param publishing_year the publishing_year to set
     */
    public void setPublishing_year_default(Date publishing_year) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(publishing_year);
        this.setPublishing_year((Date) format1.parse(date1));
    }
    
    /**
     * @return the import_date
     */
    
    public String getImport_date_default() throws ParseException {
        SimpleDateFormat f =new SimpleDateFormat ("yyyy-MM-dd");
        String date1 = f.format(getImport_date());
        Date import_date_2 = new Date(f.parse(date1).getTime());
        return f.format(import_date_2);
    }
    
    
    public String getImport_date_sub() throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat ("dd-MM-yyyy");
        String value = f.format(getImport_date());
        return value;
    }

    /**
     * @param import_date the import_date to set
     */
    
    public void setImport_date_default(Date import_date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(import_date);
        this.setImport_date((Date) format1.parse(date1));
    }

    
    /**Mặc định
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

    /**
     * @return the import_date
     */
    public Date getImport_date() {
        return import_date;
    }

    /**
     * @param publishing_year the publishing_year to set
     */
    public void setPublishing_year(Date publishing_year) {
        this.publishing_year = publishing_year;
    }

    /**
     * @param import_date the import_date to set
     */
    public void setImport_date(Date import_date) {
        this.import_date = import_date;
    }

    /**
     * @return the publishing_year
     */
    public Date getPublishing_year() {
        return publishing_year;
    }
    /**
     * @return the select
     */
    public CheckBox getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(CheckBox select) {
        this.select = select;
    }
   
}
