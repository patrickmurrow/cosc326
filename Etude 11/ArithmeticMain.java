/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arithmetic;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author patrickmurrow
 */
public class ArithmeticMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //AmpleInteger t = new AmpleInteger("36982446908246098350985");
        //AmpleInteger i = new AmpleInteger("24609716097509825098246426098");
        
        //AmpleInteger i = new AmpleInteger("12312");
        //AmpleInteger t = new AmpleInteger("213");
        //AmpleInteger[] pA = divide(i, t);
        //pA[0].printNum();
        //pA[1].printNum();
        //AmpleInteger r = gcd(i, t);
        //t.half().printNum();
        //AmpleInteger m = multiply(i, t);
        //m.printNum();
        //AmpleInteger r = i.add(t);
        //r.printNum();
        //AmpleInteger r = i.subtract(t);
        //AmpleInteger r = i.subtract(t);
        //i.subtract(t).add(i.subtract(t)).printNum();
        //System.out.println(i.subtract(t).add(i.subtract(t)).compareTo(t));
        //System.out.println(i.compareTo(t));
        //System.out.println(r.convertNumber());
        //System.out.println(m.convertNumber());
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            AmpleInteger[] answer = new AmpleInteger[2];
            String numInput = input.nextLine();
            Pattern p = Pattern.compile("[a-zA-z]");
            Pattern comment = Pattern.compile("[#]");
            Pattern nS = Pattern.compile("\\d");
            Pattern gcd = Pattern.compile("[g][c][d]");
            Matcher m = p.matcher(numInput);
            Matcher mgcd = gcd.matcher(numInput);
            Matcher mcomment = comment.matcher(numInput);
            Matcher mnS = nS.matcher(numInput);
            int operator = 0;
            if (mcomment.find()) {
            } else if (m.find()) {                
                if (mgcd.find()) {
                    String numStr = whiteSpace(numInput);
                    System.out.println(numInput);
                    String[] nums = numStr.split("[g][c][d]");
                    operator = 2;
                    answer = operate(nums[0], nums[1], operator);
                    System.out.println("# " + answer[0].convertNumber());
                } else {
                    System.out.println(numInput);
                    System.out.println("# Syntax error");
                }
            } else if (mnS.find()) {
                String numStr = whiteSpace(numInput);
                System.out.println(numInput);
                if (numStr.contains("/")){
                    operator = 1;
                    String[] nums = numStr.split("[/]");
                    answer = operate(nums[0], nums[1], operator);
                    System.out.println("# " + answer[0].convertNumber() + " "+ answer[1].convertNumber());
                }else{                     
                    String[] nums = numStr.split("[*]");
                    answer = operate(nums[0], nums[1], operator);
                    System.out.println("# " + answer[0].convertNumber());
                }
            }           
        }
    }
    
    
    public static String whiteSpace(String numInput) {
        String numStr = "";
        for (int i = 0; i < numInput.length(); i++) {
            if (numInput.charAt(i) != ' ') {
                numStr = numStr + numInput.substring(i, i + 1);
            }
        }
        return numStr;
    }
    
    private static AmpleInteger[] operate(String inNum1, String inNum2, int operator) {        
        AmpleInteger[] result = new AmpleInteger[2];
        AmpleInteger num1 = new AmpleInteger(inNum1);
        AmpleInteger num2 = new AmpleInteger(inNum2);
        if (operator == 0){
            result[0] = multiply(num1, num2);
        }
        else if (operator == 1){
            result = divide(num1, num2);
        }
        else{
            result[0] = gcd(num1, num2);
        }
        return result;
    }

    public static AmpleInteger multiply(AmpleInteger aI1, AmpleInteger aI2) {
        AmpleInteger result = new AmpleInteger();
        if (aI1.compareTo(new AmpleInteger("0")) == 0 || aI2.compareTo(new AmpleInteger("0")) == 0) {
            return new AmpleInteger("0");
        }

        if (aI1.compareTo(aI2) == -1) {
            AmpleInteger temp = new AmpleInteger(aI2);
            aI2 = new AmpleInteger(aI1);
            aI1 = new AmpleInteger(temp);
        }
        AmpleInteger rem = new AmpleInteger("0");
        while (aI1.compareTo(new AmpleInteger("2")) != 0 || aI1.compareTo(new AmpleInteger("2")) != 0) {
            if (aI1.compareTo(aI1.half().add(aI1.half())) != 0) {
                aI1 = aI1.subtract(new AmpleInteger("1"));
                AmpleInteger[] dAIs = divide(aI2, aI1);
                aI2 = aI2.add(dAIs[0]);
                rem = rem.add(dAIs[1]);
            } else {
                aI1 = aI1.half();
                aI2 = aI2.add(aI2);
            }
        }
        result = aI2.add(aI2);
        result = result.add(rem);
        return result;
    }
    
    public static AmpleInteger[] divide(AmpleInteger aI1, AmpleInteger aI2){
        AmpleInteger[] result = new AmpleInteger[2];
        AmpleInteger nume = new AmpleInteger(aI2);
        AmpleInteger deno = new AmpleInteger("1");
        AmpleInteger rNum = new AmpleInteger("0");
        AmpleInteger temp = new AmpleInteger(aI1);
        
        while (aI1.subtract(nume).add(aI1.subtract(nume)).compareTo(aI2) == -1) {
            while (nume.add(nume).compareTo(aI1) >= 0) {                

                nume = nume.add(nume);
                deno = deno.add(deno);                
            }
            rNum = rNum.add(deno);
            deno = new AmpleInteger("1");
            temp = new AmpleInteger(aI1);
            aI1 = new AmpleInteger(aI1.subtract(nume));
            nume = new AmpleInteger(aI2);
        }
        result[0] = rNum; 
        result[1] = aI1;
        
        return result;
    }
    
    public static AmpleInteger gcd(AmpleInteger aI1, AmpleInteger aI2) {
        AmpleInteger[] divide; 
        AmpleInteger dividend;
        AmpleInteger divisor;
        if (aI1.compareTo(aI2) == -1) {
            divide = bigIntDivide(aI1, aI2);
            dividend = aI1;
            divisor = aI2;
        } else {
            divide = bigIntDivide(aI2, aI1);
            dividend = aI2;
            divisor = aI1;
        }
        
        AmpleInteger remainder = divide[1];

        String szero = "0";
        AmpleInteger zero = new AmpleInteger(szero);

        while (remainder.compareTo(zero) == -1) {
            dividend = divisor;
            divisor = remainder;
            divide = bigIntDivide(dividend, divisor);
            remainder = divide[1];
        }
        return divisor;
    }
    
    public static AmpleInteger[] bigIntDivide(AmpleInteger aI1, AmpleInteger aI2){
        String s1 = aI1.convertNumber();
        String s2 = aI2.convertNumber();
        BigInteger b = new BigInteger(s1);
        BigInteger c = new BigInteger(s2);
        
        BigInteger[] temp = b.divideAndRemainder(c);
        AmpleInteger r1 = new AmpleInteger(temp[0].toString());
        AmpleInteger r2 = new AmpleInteger(temp[1].toString());
        
        AmpleInteger[] res = {r1, r2};
        
        return res;
    }
    
}
