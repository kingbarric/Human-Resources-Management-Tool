/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barricrebirthsystem.rebirtherp.util;

/**
 *
 * @author Barima
 */
public class Messager {

    public static String success ="Proccess completed successfully";
    public static String error = " Sorry error occured please try again!";
    private String message;
    private int code;

    public Messager(){
    }
    public Messager(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString(){
    return this.code+" "+this.message;
    }
}
