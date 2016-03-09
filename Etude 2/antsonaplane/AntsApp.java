/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antsonaplane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author patrickmurrow
 */
public class AntsApp {

    private static ConcurrentHashMap<Integer, Coordinate> positions = new ConcurrentHashMap<>();
    private static HashSet<Coordinate> pHS = new HashSet<Coordinate>();
    private static HashMap<Coordinate, Character> pHM = new HashMap<Coordinate, Character>();
    private static ArrayList<DNA> dna = new ArrayList<>();
    private static int height = 10;
    private static int width = 10;
    private static int moves = 10;
    static char initialC = 'a';
    static int count = 0;
    private static ArrayList<String> inputStrings = new ArrayList<>();
    private static String inputString = "";
    private static int antX;
    private static int antY;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean stop = true;
        while (input.hasNextLine()) {
            inputString = input.nextLine();
            String tempString = inputString.toLowerCase();
            if (tempString.equals("s") || tempString.matches("^-?\\d+$")) {
                inputStrings.add(inputString);
                moves = Integer.parseInt(tempString) - 1;
                Ant ant = new Ant(dna);
                DNA initial = dna.get(0);

                for (int i = 0; i <= moves; i++) {
                    boolean pContains = false;
                    int changeX = ant.getxValue();
                    int changeY = ant.getyValue();
                    
                    Coordinate antC = new Coordinate(changeX, changeY);
                    
                    if(!pHM.containsKey(antC)){                       
                        pHM.put(antC, initialC);
                        pHM.put(antC, ant.nextMove(initialC));
                        
                    }else{
                        Character n = pHM.get(antC);
                    //System.out.println("n: "+ n);
                        pHM.put(antC, ant.nextMove(n));
                    }
                    
                    
//                    Iterator it = positions.entrySet().iterator();
//                    while (it.hasNext()) {
//                        Map.Entry pair = (Map.Entry) it.next();
//                        Coordinate temp = (Coordinate) pair.getValue();
//                        if (temp.getxValue() == changeX && temp.getyValue() == changeY) {
//                            char n = ant.nextMove(temp.getState());
//                            positions.replace((Integer) pair.getKey(), new Coordinate(temp.getxValue(), temp.getyValue(), n));
//                            
//                            break;
//                        }
//                    }
//                    if (!pContains) {
//                        positions.put(count++, new Coordinate(changeX, changeY, initialC));
//                        char n2 = ant.nextMove(initialC);
//                        positions.replace(count - 1, new Coordinate(changeX, changeY, n2));
//                    }

                }
                antX = ant.getxValue();
                antY = ant.getyValue();
                for (String s : inputStrings) {
                    System.out.println(s);
                }
                System.out.println("# " + antX + " " + antY+"\n");
                inputStrings.clear();
                positions.clear();
                pHM.clear();
                dna.clear();
                count = 0;
            } else if (tempString.matches(".\\s[NESWnesw]{4}\\s.{4}")) {
                inputStrings.add(inputString);
                String[] spl = tempString.split("\\s+");
                char[] splitDir = spl[1].toCharArray();
                char[] splitCha = spl[2].toCharArray();

                if (count == 0) {
                    initialC = spl[0].charAt(0);
                    positions.put(count++, new Coordinate(0, 0));
                    pHM.put(new Coordinate(0,0),  initialC);
                    count++;
                }

                DNA d = new DNA(spl[0].charAt(0), splitDir, splitCha);
                dna.add(d);

            }
        }
                        
    }
}
