/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

/**
 *
 * @author phamt
 */
public class Utils {
    //xoá khoảng trắng
    public static String remove_Whitespace(String txt){
        return txt.trim().replaceAll(" +", " ");        
    }
    
    //xoá ký tự đặc biệt
    public static String clear_SpecialChar(String s){
        return s.replaceAll("[^\\w\\s]", "").replaceAll(" ", "");
        // +-*/<>?;l -> /
    }
}
