/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dillonb.baroptimizer;

/**
 *
 * @author dillonb
 */
public class Bar {
    private int weight;
    private String barType;
    private String barMakeup;

    
    public Bar(int weight, String barType, String barMakeup){
        this.weight = weight;
        this.barType = barType;
        this.barMakeup = barMakeup;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBarType(String barType) {
        this.barType = barType;
    }

    public void setBarMakeup(String barMakeup) {
        this.barMakeup = barMakeup;
    }

    public int getWeight() {
        return weight;
    }

    public String getBarType() {
        return barType;
    }

    public String getBarMakeup() {
        return barMakeup;
    }
}
