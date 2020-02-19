/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dillonb.baroptimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author dillonb
 */
public class Runner {
    
    private final int[] maleBars = {275,255,225,205,185,175,155,135,115};
    private final int[] womenBars = {185,165,155,145,135,125,115,95,75};
    
    private final int numOfMensBars = maleBars.length;
    private final int numOfWomensBars = womenBars.length;
    
    private int numOf45s = 20;
    private int numOf35s = 16;
    private int numOf25s = 17;
    private int numOf15s = 21;
    private int numOf10s = 26;
    private int numOf5s = 25;
    private int numOf2andahalfs = 20;
     
    public static void main(String[] args){
        Runner runner = new Runner();
        List<Bar> barList = runner.populateList("forwards");
        //List<Bar> barList = runner.populateList("backwards");
        boolean isThereEnough = runner.howMuchWeightIsThere(barList);
        if(isThereEnough){
            runner.calculate(barList);
            runner.report(barList);
        }
    }
    
    public void calculate(List<Bar> barList){
        ListIterator listIter = barList.listIterator();
        
        while(listIter.hasNext()){
            Bar currentBar = (Bar)listIter.next();
            int weight = currentBar.getWeight();
            
            if(currentBar.getBarType().equalsIgnoreCase("male")){
                    weight-=45;
                    currentBar.setBarMakeup(modMath(weight));
            }
            else if(currentBar.getBarType().equalsIgnoreCase("women")){
                    weight-=35;
                    currentBar.setBarMakeup(modMath(weight));
            }
        }
    }
    
    public String modMath(int weight){
        String barMakeup = "\tBar Makeup:";
        
        if(numOf45s>1){
            int num = Math.floorDiv(weight, 90)*2;
            while(num>0){
                barMakeup+="\t45s: "+num;
                weight-=num*45;
                numOf45s = numOf45s-num;
                num = Math.floorDiv(weight, 90)*2;
            }
        }
        if(numOf35s>1){
            int num = Math.floorDiv(weight, 70)*2;
            while(num>0){
                barMakeup+="\t35s: "+num;
                weight-=num*35;
                numOf35s = numOf35s-num;
                num = Math.floorDiv(weight, 70)*2;
            }
        }
        if(numOf25s>1){
            int num = Math.floorDiv(weight, 50)*2;
            while(num>0){
                barMakeup+="\t25s: "+num;
                weight-=num*25;
                numOf25s = numOf25s-num;
                num = Math.floorDiv(weight, 50)*2;
            }
        }
        if(numOf15s>1){
            int num = Math.floorDiv(weight, 30)*2;
            while(num>0){
                barMakeup+="\t15s: "+num;
                weight-=num*15;
                numOf15s = numOf15s-num;
                num = Math.floorDiv(weight, 30)*2;
            }
        }
        if(numOf10s>1){
            int num = Math.floorDiv(weight, 20)*2;
            while(num>0){
                barMakeup+="\t10s: "+num;
                weight-=num*10;
                numOf10s = numOf10s-num;
                num = Math.floorDiv(weight, 20)*2;
            }
        }
        if(numOf5s>1){
            int num = Math.floorDiv(weight, 10)*2;
            while(num>0){
                barMakeup+="\t5s: "+num;
                weight-=num*5;
                numOf5s = numOf5s-num;
                num = Math.floorDiv(weight, 10)*2;
            }
        }
        if(numOf2andahalfs>1){
            int num = Math.floorDiv(weight, 5)*2;
            while(num>0){
                barMakeup+="\t2.5s: "+num;
                weight-=num*2.5;
                numOf2andahalfs = numOf2andahalfs-num;
                num = Math.floorDiv(weight, 5)*2;
            }
        }
        return barMakeup;
    }
    
    public void report(List<Bar> barList){
        ListIterator listIter = barList.listIterator();
    
        while(listIter.hasNext()){
            Bar currentBar = (Bar)listIter.next();
            System.out.println(currentBar.getBarType()+" Bar weight: "+currentBar.getWeight()+"\t"+currentBar.getBarMakeup());
        }
    }
    
    public List populateList(String direction){
        List<Bar> barList = new ArrayList<>();
        
        
        if(direction.equalsIgnoreCase("backwards"))
        {
            for(int i = maleBars.length-1; i >= 0; i--){
                barList.add(new Bar(maleBars[i], "Male", null));
                barList.add(new Bar(womenBars[i], "Women", null));
            }
        }
        else{
            for(int i = 0; i < maleBars.length; i++){
                barList.add(new Bar(maleBars[i], "Male", null));
                barList.add(new Bar(womenBars[i], "Women", null));
            }
        }
        return barList;
    }

    private boolean howMuchWeightIsThere(List<Bar> barList) {
        double totalWeightAvailable = numOf45s*45 + numOf35s*35 + numOf25s*25 
                + numOf15s*15 + numOf10s*10 + numOf5s*5 + numOf2andahalfs*2.5 
                + numOfMensBars*45 + numOfWomensBars*35;
        
        double totalWeightNeeded = 0.0;
        ListIterator listIter = barList.listIterator();
    
        while(listIter.hasNext()){
            Bar currentBar = (Bar)listIter.next();
            totalWeightNeeded += currentBar.getWeight();
        }
        boolean isThereEnough = totalWeightNeeded < totalWeightAvailable;
        if(!isThereEnough)
            System.err.println("Not enough Weight");
        else
            System.err.println("There is enough weight");
        
        return isThereEnough;
    }
}
