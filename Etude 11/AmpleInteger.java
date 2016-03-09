/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import java.util.Arrays;

/**
 *
 * @author patrickmurrow
 */
public class AmpleInteger {

    //ArrayList<Number> numL = new ArrayList<Number>();
    private int[] numL;
    private boolean neg = false;

    public AmpleInteger() {
    }
    
    public AmpleInteger(AmpleInteger a){
        this.numL = a.getNumL();
        this.neg = a.isNeg();
    }

    public AmpleInteger(String num) {
        int count = 0;
        if (num.startsWith("-")) {
            numL = new int[(num.length()) - 1];
            neg = true;
            for (int i = num.length() - 1; i > 0; i--) {
                numL[Math.abs(i - (num.length() - 1))] = Character.getNumericValue(num.charAt(i));
            }
        } else {
            numL = new int[(num.length())];
            for (int i = num.length() - 1; i >= 0; i--) {

                numL[Math.abs(i - (num.length() - 1))] = Character.getNumericValue(num.charAt(i));
            }
        }
    }

    public String convertNumber() {
        String result = new String();
        int[] list = this.getNumL();
        for (int i = list.length - 1; i >= 0; i--) {
            result += list[i];
        }
        if (this.isNeg()) {
            result = "-" + result;
        }
        return result;
    }

    public AmpleInteger add(AmpleInteger num2) {
        //System.out.println("w");
        AmpleInteger result = new AmpleInteger();
        boolean twoNegs = false;
        
        if (num2.isNeg() && !this.isNeg()){
            return this.subtract(num2);
        }
        else if(!num2.isNeg() && this.isNeg()){
            return num2.subtract(this);
        }       
        else if (num2.isNeg() && this.isNeg()){
            twoNegs = true;
            num2.setNeg(false);
            this.setNeg(false);
        }        
        int smallest = num2.getNumL().length;
        int biggest = this.getNumL().length;
        boolean num2B = false;
        if (this.getNumL().length < smallest) {
            smallest = this.getNumL().length;
            biggest = num2.getNumL().length;
            num2B = true;
        }
        if (!this.isNeg() && !num2.isNeg()) {
            result.setNumL(new int[biggest + 1]);
            int carry = 0;
            int index = 0;
            for (int i = 0; i < smallest; i++) {
                int tN = this.getNumL()[i] + num2.getNumL()[i] + carry;
                if (tN > 9) {
                    carry = 1;
                    result.set(i, tN % 10);
                    index = i + 1;
                } else {
                    carry = 0;
                    result.set(i, tN);
                    index = 0;
                }
            }
            if (smallest == biggest && index > 0) {
                result.set(index, carry);
            }
            for (int i = smallest; i < biggest; i++) {
                if (num2B) {
                    int tN = num2.getNumL()[i] + carry;
                    if (tN > 9) {
                        carry = 1;
                        result.set(i, tN % 10);
                    } else {
                        carry = 0;
                        result.set(i, tN);
                    }
                } else {
                    int tN = this.getNumL()[i] + carry;
                    if (tN > 9) {
                        carry = 1;
                        result.set(i, tN % 10);
                    } else {
                        carry = 0;
                        result.set(i, tN);
                    }
                }
            }
            if (carry != 0){
                result.set(biggest, carry);
            }
        }
        //result.printNum();
        int end = result.getNumL().length;
        if (result.getNumL()[end-1] == 0) {
            boolean stop = true;
            int i = result.getNumL().length-1;
            while (stop && i >= 0) {
                if (result.getNumL()[i] == 0) {
                    end--;
                } else {
                    stop = false;
                }
                i--;
            }

        }
        //result.printNum();
        int[] temp = Arrays.copyOfRange(result.getNumL(), 0, end);
        result.setNumL(temp);
        if (twoNegs){
            result.setNeg(true);
        }
        return result;
    }
    
    public AmpleInteger subtract(AmpleInteger num2){

        AmpleInteger result = new AmpleInteger();
        if (num2.isNeg() && !this.isNeg()){
            num2.setNeg(false);
            return this.add(num2);
        }else if (!num2.isNeg() && this.isNeg()){
            num2.setNeg(true);
            return this.add(num2);
        } else if (num2.isNeg() && this.isNeg()){
            AmpleInteger temp = new AmpleInteger(num2);
            num2 = new AmpleInteger(this);
            this.setNeg(temp.isNeg());
            this.setNumL(temp.getNumL());
        }else if(this.compareTo(num2) == 0){
            return new AmpleInteger("0");
        }
        
        int smallest = num2.getNumL().length;
        int biggest = this.getNumL().length;
        boolean num2B = false;
        if (this.getNumL().length < smallest) {
            smallest = this.getNumL().length;
            biggest = num2.getNumL().length;
            num2B = true;
        }
        
        result.setNumL(new int[biggest]);
        boolean rNeg = false;
        if (this.compareTo(num2) == 1){
            rNeg = true;
            AmpleInteger temp = new AmpleInteger(num2);
            num2 = new AmpleInteger(this);
            this.setNeg(temp.isNeg());
            this.setNumL(temp.getNumL());                    
        }
        
        int carry = 0;
        for (int i = 0; i < smallest; i++) {
            int tNum = this.getNumL()[i];
            int nNum = num2.getNumL()[i];
            //System.out.println(tNum + " " + nNum);
            if (nNum >= tNum) {
                int sum = Math.abs((tNum+10) - nNum) - carry;
                if (sum == 10){
                    sum = 0;
                    carry = 0;
                }else{
                    carry = 1;
                }
                result.set(i, sum);
            } else{
                //System.out.println(tNum + " " + nNum + " "+ carry);
                int sum = tNum - nNum - carry;
                carry = 0;
                result.set(i, sum);
            }
        }
        

        if(rNeg){
            AmpleInteger temp = new AmpleInteger(num2);
            num2 = new AmpleInteger(this);
            this.setNeg(temp.isNeg());
            this.setNumL(temp.getNumL());
        }
        
       for(int i = smallest; i<biggest; i++){
           if (!num2B){
               if (this.getNumL()[i]-carry < 0){
               result.set(i, 9);
               carry = 1;
           }else{
               result.set(i, this.getNumL()[i]-carry);
           }
           }else{
           if (num2.getNumL()[i]-carry < 0){
               result.set(i, 9);
               carry = 1;
           }else{
               result.set(i, num2.getNumL()[i]-carry);
           }
           }
           
           
       }
        
        int end = result.getNumL().length;
        if (result.getNumL()[end-1] == 0) {
            boolean stop = true;
            int i = result.getNumL().length-1;
            while (stop && i >= 0) {
                if (result.getNumL()[i] == 0) {
                    end--;
                } else {
                    stop = false;
                }
                i--;
            }

        }
        int[] temp = Arrays.copyOfRange(result.getNumL(), 0, end);
        result.setNumL(temp);
        if (rNeg){
            result.setNeg(true);
        }
        return result;
    }
    
    public AmpleInteger half(){
        AmpleInteger result = new AmpleInteger();
        result.numL = new int[numL.length];
        int sec = 0;
        int fir = 0;
        for(int i = numL.length-1; i >= 0; i--){
            int t = numL[i] * 5;
            fir = t/10;
            result.set(i, fir + sec);
            sec = t%10;
        }
        result = removeZeros(result);
        return result;
    }
    
    public int compareTo(AmpleInteger num){
        if (this.isNeg() && !num.isNeg()){
            return 1;
        }else if (!this.isNeg() && num.isNeg()){
            return -1;
        }else if (this.isNeg() && num.isNeg()){
            AmpleInteger temp = new AmpleInteger(num);
            num = new AmpleInteger(this);
            this.setNeg(temp.isNeg());
            this.setNumL(temp.getNumL());
        }
        if (this.getNumL().length < num.getNumL().length){
            return 1;
        } else if (this.getNumL().length > num.getNumL().length){
            return -1;
        }else{
            for(int i = this.getNumL().length-1; i>=0; i--){
                if (this.getNumL()[i] < num.getNumL()[i]){
                    return 1;
                }
                else if (this.getNumL()[i] > num.getNumL()[i]){
                    return -1;
                }
            }
        }
        return 0;
    }
    
    private AmpleInteger removeZeros(AmpleInteger a){
        int end = a.numL.length;
        if (a.numL[end-1] == 0) {
            boolean stop = true;
            int i = a.numL.length-1;
            while (stop && i >= 0) {
                if (a.numL[i] == 0) {
                    end--;
                } else {
                    stop = false;
                }
                i--;
            }

        }
       AmpleInteger result = new AmpleInteger();
       result.setNumL(Arrays.copyOfRange(a.numL, 0, end));
       result.setNeg(a.isNeg());
       return result;
        
    }

    public void printNum() {
        for (int i = 0; i < numL.length; i++) {
            System.out.print(numL[i] + " ");
        }
        System.out.println();
    }

    public int[] getNumL() {
        return numL;
    }

    public void setNumL(int[] numL) {
        this.numL = numL;
    }

    public boolean isNeg() {
        return neg;
    }

    public void setNeg(boolean neg) {
        this.neg = neg;
    }

    private void set(int i, int n) {
        this.numL[i] = n;
    }

}
