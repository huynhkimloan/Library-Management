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
public class BorrowBook {
    
    private int borrow_id;
    private int card_id ;
    private int book_id;
    private Date start_date;
    private Date end_date;
    private int amount;
    private float fine;
    private int status;
    private String note;
    
    public BorrowBook()
    {
        
    }
    
    public BorrowBook(int borrow_id, Date start_date, Date end_date, int amount,
            int card_id, int book_id, float fine, int status)
    {
        this.borrow_id = borrow_id;
        this.card_id = card_id;
        this.book_id = book_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.fine = fine;
        this.status = status;
    }
    
    public BorrowBook(int borrow_id, Date start_date,int amount,
            int card_id, int book_id, float fine, int status)
    {
        this.borrow_id = borrow_id;
        this.card_id = card_id;
        this.book_id = book_id;
        this.start_date = start_date;
        this.amount = amount;
        this.fine = fine;
        this.status = status;
    }
    
    public BorrowBook(int borrow_id, int card_id, int book_id, 
            Date start_date, int amount, int status)
    {
        this.borrow_id = borrow_id;
        this.card_id = card_id;
        this.book_id = book_id;
        this.start_date = start_date;
        this.amount = amount;
        this.status = status;
    }
    

    /**
     * @return the borrow_id
     */
    public int getBorrow_id() {
        return borrow_id;
    }

    /**
     * @param borrow_id the borrow_id to set
     */
    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
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
     * @return the start_date
     */
    public String getStart_date() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(start_date);
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
     * @return the fine
     */
    public float getFine() {
        return fine;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(float fine) {
        this.fine = fine;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    
}
