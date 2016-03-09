

import java.util.*;

/**
 *
 * @author patrickmurrow
 */
public class RedGreen {
    private static final HashMap<Integer, Integer> redGreenHash = new HashMap<>();
    private static final ArrayList<String> inputStrings = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        redGreenHash.put(1, 1);
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String temp = input.nextLine();
            //String comment = temp.replace(" ", "");
            if (!(temp.startsWith("#") && !temp.equals(""))){
                inputStrings.add(temp);
            }
        }
            for(String i : inputStrings){
                String[] tempList = i.split(" ");
                if (i.matches("\\d+ +\\d+")){
                    System.out.println(i);
                    System.out.print("# ");
                    redGreenPrint(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
                    System.out.println("\n");
                }
            }
                      
    }

    
    private static Integer nFC(int n){
        int k = 1;
        Integer result = 0;
        while (k <= (n/2)) {
            int d = n/k;
            if (n/d == k){
                if (redGreenHash.containsKey(k)){
                    result += redGreenHash.get(k);
                }
                else{
                    result += nFC(k);
                }
            }
            k++;
        }
        if (result <= 0){
            redGreenHash.put(n, 1);          
            return 1;
        } else {
            redGreenHash.put(n, -1);
            return -1;
        }
    }
    
    
    private static void redGreenPrint(int sta, int len) {
        for (int i = sta; i < sta + len; i++) {
            if (!redGreenHash.containsKey(i)) {
                //redGreenCalc(i, nearFactorCalc(i));
                nFC(i);
            }
            if (redGreenHash.get(i) > 0) {
                System.out.print("G");
            } else {
                System.out.print("R");
            }
        }
    
    }

}