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
public class ReserveBook {
    private int reserve_id;
    private Date activation_date;
    private Date expiration_date;
    private int amount;
    private int book_id;
    private int card_id;
    
    public ReserveBook() {}
    
    public ReserveBook(int reserve_id, Date activation_date, Date expiration_date, int amount, int book_id, int card_id) {
        this.reserve_id = reserve_id;
        this.activation_date = activation_date;
        this.expiration_date = expiration_date;
        this.amount = amount;
        this.book_id = book_id;
        this.card_id = card_id;
    }

    public ReserveBook(java.sql.Date activation_date, java.sql.Date expiration_date, int amount, int card_id, int book_id) {
        this.activation_date = activation_date;
        this.expiration_date = expiration_date;
        this.amount = amount;
        this.book_id = book_id;
        this.card_id = card_id;//To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the reserve_id
     */
    public int getReserve_id() {
        return reserve_id;
    }

    /**
     * @param reserve_id the reserve_id to set
     */
    public void setReserve_id(int reserve_id) {
        this.reserve_id = reserve_id;
    }

    /**
     * @return the activation_date
     */
    public String getActivation_date() {
        SimpleDateFormat f =new SimpleDateFormat ("dd-MM-yyyy");
        return f.format(activation_date);
    }

    /**
     * @param activation_date the activation_date to set
     */
    public void setActivation_date(Date activation_date) {
        this.activation_date = activation_date;
    }

    /**
     * @return the expiration_date
     */
    public String getExpiration_date() {
        SimpleDateFormat f =new SimpleDateFormat ("dd-MM-yyyy");
        return f.format(expiration_date);
    }

    /**
     * @param expiration_date the expiration_date to set
     */
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
     * @return the card_id
     */
    public int getCard_id() {
        return card_id;
    }

    /**
     * @param card_id the card_id to set
     */
    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    /**
     * @return the reserve_id
     */
}

