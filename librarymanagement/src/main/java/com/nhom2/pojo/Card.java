/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.pojo;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Card {
    private int card_id;
    private boolean active;
    private Date activation_date;
    private Date expiration_date;
    private String total_money_penalty;
    
    
    public Card() {}
    
    public Card(int card_id, boolean active, Date activation_date, Date expiration_date, String total_money_penalty) {
        this.card_id = card_id;
        this.active = active;
        this.activation_date = activation_date;
        this.expiration_date = expiration_date;
        this.total_money_penalty = total_money_penalty;
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
     * @return the activation_date
     */
    public Date getActivation_date() {
        return activation_date;
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
    public Date getExpiration_date() {
        return expiration_date;
    }

    /**
     * @param expiration_date the expiration_date to set
     */
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    /**
     * @return the total_money_penalty
     */
    public String getTotal_money_penalty() {
        return total_money_penalty;
    }

    /**
     * @param total_money_penalty the total_money_penalty to set
     */
    public void setTotal_money_penalty(String total_money_penalty) {
        this.total_money_penalty = total_money_penalty;
    }

    /**
     * @return the borrow_id
     */
   
    
}
