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
public class DNA {
    private char key;
    private char[] arrive;
    private char[] stateChange;

    public DNA(char key, char[] arrive, char[] stateChange) {
        this.key = key;
        this.arrive = arrive;
        this.stateChange = stateChange;
    }

    public char getKey() {
        return key;
    }

    public char[] getArrive() {
        return arrive;
    }

    public char[] getStateChange() {
        return stateChange;
    }
    
    
    
    
}
