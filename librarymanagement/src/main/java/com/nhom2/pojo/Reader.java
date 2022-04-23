
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.pojo;

import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author ASUS
 */
public class Reader {
    private int reader_id;
    private String reader_name;
    private String username;
    private String password;
    private String email;
    private boolean active;
    private int department_id;
    
    private String sex;
    private String phone;
    private String address;
    private String object;
    private String user_role;
    private Date date_of_birth;
    
    
    public Reader() {
    }

    public Reader(int reader_id, String reader_name, String username, String password, String email, boolean active, int department_id) {
        this.reader_id = reader_id;
        this.reader_name = reader_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.department_id = department_id;
    }

    public Reader(String reader_name, String username, String password, String email, boolean active, int department_id, String sex, String phone, String address, String object, String user_role, Date date_of_birth) {
        this.reader_name = reader_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.department_id = department_id;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.object = object;
        this.user_role = user_role;
        this.date_of_birth = date_of_birth;
    }
    
    
    
    public Reader(int reader_id, String reader_name) {
        this.reader_id = reader_id;
        this.reader_name = reader_name;
    }


//    @Override
//    public String toString() {
//        return this.reader_name;
//    }
    
    

    /**
     * @return the reader_id
     */
    public int getReader_id() {
        return reader_id;
    }

    /**
     * @param reader_id the reader_id to set
     */
    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    /**
     * @return the reader_name
     */
    public String getReader_name() {
        return reader_name;
    }

    /**
     * @param reader_name the reader_name to set
     */
    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the department_id
     */
    public int getDepartment_id() {
        return department_id;
    }

    /**
     * @param department_id the department_id to set
     */
    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @return the user_role
     */
    public String getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    /**
     * @return the date_of_birth
     */
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @param date_of_birth the date_of_birth to set
     */
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    
    
     
}
