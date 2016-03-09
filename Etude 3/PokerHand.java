import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author patrickmurrow
 */
public class PokerHand {

    private static final String[] numbersList = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "a", "k", "q", "j", "t", "1"};
    private static final String[] suitsList = {"c", "d", "h", "s"};
    private static final ArrayList<Character> separators = new ArrayList<>();
    private static final ArrayList<String> numbers = new ArrayList<>();
    private static final ArrayList<String> suits = new ArrayList<>();
    private static ArrayList<String> cards;
    private static final ArrayList<Card> valid = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        separators.add('-');
        separators.add('/');
        separators.add(' ');
        numbers.addAll(Arrays.asList(numbersList));
        suits.addAll(Arrays.asList(suitsList));
        //File file = new File("test.txt");
        //Scanner input = new Scanner(file);
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String inputHand = input.nextLine();
            if (validSeparation(inputHand.toLowerCase()) && validCards(cards) && add(changeNum(cards))) {
                ArrayList<Card> toPrint = sortCards(valid);
                printCards(toPrint);
            } else {
                System.out.println("Invalid: " + inputHand);
            }
        }
    }

    private static boolean validSeparation(String hand) {
        boolean separatorCheck = true;
        char separator = '.';
        if (hand.length()<13||hand.length()>20){
            return false;
        }
        if (!(suits.contains(hand.charAt(hand.length()-1)+""))){
            return false;
        }
        for (int i = 0; separatorCheck; i++){
            if(separators.contains(hand.charAt(i))){
                separator = hand.charAt(i);
                separatorCheck = false;
            }
            else if(i == 4){
                return false;
            }
        }
        
        if (!separatorCheck && separator == ' '){
            String[] cardsList = hand.split("\\s");
            cards = new ArrayList<String>(Arrays.asList(cardsList));
        }else if (separator != '.'){
            String[] cardsList = hand.split(""+separator);
            cards = new ArrayList<String>(Arrays.asList(cardsList));
        } else{
            return false;
        }
        return true;
    }
    
    private static ArrayList<String> changeNum(ArrayList<String> inputCards){
        ArrayList<String> result = new ArrayList<>();
        for (String s : inputCards){
            String temp = "";
            if(s.length() == 2){
               temp = s.replaceAll("1", "z").replaceAll("a", "z").replaceAll("t", "v").replaceAll("j", "w").replaceAll("q", "x").replaceAll("k","y");
            }
            else{
                temp = s.replaceAll ("10", "v").replaceAll ("11", "w").replaceAll ("12", "x").replaceAll ("13", "y");
            }
            if(!result.contains(temp)){
                result.add(temp); 
            }
        }
        return result;
    }
    
    private static boolean validCards(ArrayList<String> inputCards){
        boolean check = true;
        for (String card: inputCards){
            if(card.length() == 2 && numbers.contains(card.charAt(0)+"") && suits.contains(card.charAt(1)+"")){
                check = true;
            }
            else if(card.length() == 3 && numbers.contains(card.substring(0,2))&& suits.contains(card.charAt(2)+"")){
                check = true;
            }
            else{
                check = false;
            }
        }
        return check;
    }
    private static boolean add(ArrayList<String> input){
        valid.clear();
        if (input.size() != 5){
            return false;
        }
        for (String card: input){
            if(card.isEmpty()){
                return false;
            }
            valid.add(new Card((card.charAt(0)+""), (card.charAt(1)+"")));
        }
        for(Card c: valid){
            c.number = c.number.replaceAll ("v", "10").replaceAll ("w", "j").replaceAll ("x", "q").replaceAll ("y", "k").replaceAll("z", "a");
            if (!numbers.contains(c.number)||!(suits.contains(c.suit))){
                return false;
            }
        }
        for(Card c:valid){
            c.number = c.number.replaceAll ("10", "v").replaceAll ("j", "w").replaceAll ("q", "x").replaceAll ("k", "y").replaceAll("a", "z");
        }
        if (!(valid.size()==5)){
            return false;
        }
        return true;
    }
    
    private static ArrayList<Card> sortCards(ArrayList<Card> toBeSorted){
        int i,j;        
        for (i = 1; i < toBeSorted.size(); i++) {
            Card key = new Card("", "");
            key.number = toBeSorted.get(i).number;
            key.suit = toBeSorted.get(i).suit;
            j = i;
            boolean stop = true;
            while((j > 0) && stop) {
                String str1 = toBeSorted.get(j - 1).number + toBeSorted.get(j - 1).suit;
                String str2 = key.number + key.suit;
                if(str1.compareTo(str2)> 0){
                    toBeSorted.set(j,toBeSorted.get(j - 1));
                    j--;
                }else{
                    stop = false;
                }
            }
            toBeSorted.set(j,key);
        }
        return toBeSorted;
    }

    private static void printCards(ArrayList<Card> toPrint) {
        for (Card c : toPrint) {
            c.number = c.number.replaceAll ("v", "10").replaceAll ("w", "J").replaceAll ("x", "Q").replaceAll ("y", "K").replaceAll("z", "A");
            c.suit = c.suit.toUpperCase();
            c.number = c.number.toUpperCase();
            System.out.print(c.toString() + " ");
        }  
        System.out.println();
    }
    
    private static class Card {
        private String number;
        private String suit;

        public Card(String number, String suit) {
            this.number = number;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return number + suit;
        }
       
        
        
    }
    
}
