/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antsonaplane;

import java.util.ArrayList;


/**
 *
 * @author patrickmurrow
 */
public class Ant {
    private ArrayList<DNA> dna;
    private int xValue = 0;
    private int yValue = 0;
    private int currentDir = 0;
    private boolean first =true;
    char[] direction = {};
    char[] state = {};


    public Ant() {
    }

    public Ant(ArrayList<DNA> dna) {
        this.dna = dna;
    }

    public char nextMove(char current){
        char next = 'a';
        if (first) {
            direction = dna.get(0).getArrive();
            state = dna.get(0).getStateChange();
            first = false;
            next = state[currentDir];
        }
        //System.out.println("Current: "+ current);

        for (DNA d : dna) {
            if (current == d.getKey()){
                state = d.getStateChange();
                direction = d.getArrive();
            }
        }
        if (!first){
            next = state[currentDir];
        }
        //System.out.println("next: " + next);
        //System.out.println("BEFORE - x: " + xValue + "   y: " + yValue);
        //System.out.println("Direction: " + direction[currentDir]);
        switch(direction[currentDir]){
            case 'n':
                yValue++;
                currentDir = 0;
                break;
            case 'e':
                xValue++;
                currentDir = 1;
                break;
            case 's':
                yValue--;
                currentDir = 2;
                break;
            case 'w':
                xValue--;
                currentDir = 3;
                break;
            
        }
        //System.out.println("AFTER - x: " + xValue + "   y: " + yValue);
        //System.out.println("Next Direction: " + currentDir);
        //System.out.println("Next: " + next);
        return next;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }
    
    
    
    
}
