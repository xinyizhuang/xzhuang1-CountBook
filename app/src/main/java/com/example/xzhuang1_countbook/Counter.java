package com.example.xzhuang1_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by xzhuang1 on 2017-10-02.
 */

public class Counter {

    private String name;
    private Date date;
    private Integer ini_value;
    private Integer curr_value;
    private String comment;

    public Counter(String name, Integer ini_value, String comment){
        this.name = name;
        this.date = new Date();
        this.ini_value = ini_value;
        this.curr_value = ini_value;
        this.comment = comment;

    }

    public Counter(String name, Date date, Integer ini_value, String comment){
        this.name = name;
        this.date = date;
        this.ini_value = ini_value;
        this.curr_value = ini_value;
        this.comment = comment;

    }

    /*Setter*/
    public void setName(String name){
        this.name = name;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setIni_value(Integer ini_value){
        this.ini_value = ini_value;
    }

    public void setCurr_value(Integer curr_value){
        this.curr_value = curr_value;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    /*Getter*/

    public String getName(){
        return name;
    }

    public String getDate(){
        return new SimpleDateFormat("yyyy-mm-dd").format(this.date);
    }

    public Integer getIni_value(){
        return ini_value;
    }

    public Integer getCurr_value(){
        return curr_value;
    }

    public String getComment(){
        return comment;
    }

    /* decrement */
    public void drcrement() {
        if (this.curr_value > 0) {
            this.curr_value -= 1;
        }
    }

    /* increment */
    public void increment() {
        this.curr_value += 1;
    }

    /* reset */
    public void reset() {
        this.curr_value = this.ini_value;
    }

    @Override
    public String toString() {
        return getDate() + "\n" + name + "\n" + curr_value + "\n";
    }
}
