/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antsonaplane;

import java.util.Objects;

/**
 *
 * @author patrickmurrow
 */
public class Coordinate {
    private Integer xValue;
    private Integer yValue;
    //private char state;
    
    public Coordinate(Integer xValue, Integer yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }

//    public Coordinate(Integer xValue, Integer yValue, char state) {
//        this.xValue = xValue;
//        this.yValue = yValue;
//        this.state = state;
//    }

    public Integer getxValue() {
        return xValue;
    }

    public void setxValue(Integer xValue) {
        this.xValue = xValue;
    }

    public Integer getyValue() {
        return yValue;
    }

    public void setyValue(Integer yValue) {
        this.yValue = yValue;
    }

//    public char getState() {
//        return state;
//    }
//
//    public void setState(char state) {
//        this.state = state;
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.xValue);
        hash = 31 * hash + Objects.hashCode(this.yValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (!Objects.equals(this.xValue, other.xValue)) {
            return false;
        }
        if (!Objects.equals(this.yValue, other.yValue)) {
            return false;
        }
        return true;
    }
    
    
    
}
