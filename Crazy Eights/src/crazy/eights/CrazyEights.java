package crazy.eights;
import java.util.*;

class Card{ //made new class
    String rank;
    String suite;
    Card(String rank, String suite){ // when object is created, I will have the following attributes created
        this.rank = rank; //allows me to access the attribute
        this.suite = suite;
    }
}
public class CrazyEights {
    String allranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; // putting it out of the main method allows me to use it in all methods inside that class
    String allsuites[] = {"Spades", "Clubs", "Hearts", "Diamonds"};
    public static void main(String[] args) {
        CrazyEights c = new CrazyEights(); //creates an object of the public class
        c.crazyeights(); // calls the method as an object of the class which lets me call a non static method into a static method
    }
    
    public void crazyeights(){
        List<Card> deck = new ArrayList<>();
        for(int i = 0; i<allranks.length; i++){ //added 52 objects to my deck array. Each object can be added without clearly defining a name
            deck.add(new Card(allranks[i], "Spades")); // these variables are non-static
            deck.add(new Card(allranks[i], "Clubs"));
            deck.add(new Card(allranks[i], "Hearts"));
            deck.add(new Card(allranks[i], "Diamonds"));
        }
        List<Card> flip = new ArrayList<>();
        System.out.println("Welcome to crazy 8's! This is your starting hand:");
        List<Card> phand = new ArrayList<>();
        List<Card> chand = new ArrayList<>();
        int counter = 1;
        for(int n = 0; n<5; n++){ 
            Collections.shuffle(deck); // shuffles the deck array with the cards
            phand.add(deck.get(0)); // takes top card and adds it to phand
            deck.remove(0);
            System.out.println(counter+". "+phand.get(n).rank + " of " + phand.get(n).suite); //prints out the 5 cards of the phand array
            counter++;
        }
        for(int i = 0; i<5; i++){ 
            Collections.shuffle(deck); // shuffles the deck array with the cards
            chand.add(deck.get(0)); // takes top card and adds it to chand
            deck.remove(0);
        }
        Collections.shuffle(deck);
        flip.add(deck.get(0));
        deck.remove(0);
        System.out.println("The top card is " + flip.get(0).rank  + " of " + flip.get(0).suite);
        while(phand.size()>0 && chand.size()>0){ //loops infinitely if both players have cards in their hand
            int drawn = 1;
            int cdrawn=1;
            int x=0;
            while(drawn<=3 && x!=1){ // loop ends once the player has drawn 3 cards
                System.out.println("How many cards would you like to play? Press enter to draw a card.");
                Scanner input = new Scanner(System.in); //gets user input for how many cards the player wants to play
                String index = input.nextLine();
                if(index.equals("")){ // .equals same as == to compare strings
                    Collections.shuffle(deck); // shuffles the array deck
                    phand.add(deck.get(0)); // adds a card to the player's hand
                    deck.remove(0); // removes that card from the deck
                    System.out.println("This is your new hand:");
                    counter = 1;
                    for(int a = 0; a<phand.size();a++){
                        System.out.println(counter+". "+phand.get(a).rank + " of " + phand.get(a).suite); //prints out the cards of the phand array or the player's hand
                        counter++;
                    }
                    System.out.println("The top card is " + flip.get(0).rank  + " of " + flip.get(0).suite);
                }else if(index.equals("1")){
                    System.out.println("Which card would you like to play? Please select their index #.");
                    int index2 = input.nextInt();
                    index2--;
                    if(phand.get(index2).rank.equals("8")){
                        System.out.println("Which suite would you like to change it to? Please select the index number."); // since 8 is an special card, this shows which suites 
                        int var = 1;
                        for(int c = 0; c<allsuites.length; c++){
                            System.out.println(var+". " + allsuites[c]);
                            var++;
                        }
                        int selectsuite = input.nextInt();
                        if(!flip.get(0).rank.equals("the suite")){ //make sure we are not adding "the suite" cards back to the deck since they are not real cards
                            deck.add(flip.get(0));
                        }
                        flip.remove(0);
                        selectsuite--;
                        flip.add(new Card("the suite", allsuites[selectsuite])); // creates a new card object 
                        System.out.println("The suite is now "+flip.get(0).suite);
                        deck.add(phand.get(index2));
                        phand.remove(index2);
                        System.out.println("This is your new hand:");
                        counter = 1;
                        for(int a = 0; a<phand.size();a++){
                            System.out.println(counter+". "+phand.get(a).rank + " of " + phand.get(a).suite); //prints out the cards of the phand array or the player's hand
                            counter++;
                        }
                        x=1;
                    }else if(phand.get(index2).suite.equals(flip.get(0).suite) || phand.get(index2).rank.equals(flip.get(0).rank)){
                        if(!flip.get(0).rank.equals("the suite")){ //make sure we are not adding "the suite" cards back to the deck since they are not real cards
                            deck.add(flip.get(0));
                        }
                        flip.remove(0);
                        flip.add(phand.get(index2));
                        phand.remove(index2);
                        System.out.println("This is your new hand:");
                        counter = 1;
                        drawn++;
                        for(int a = 0; a<phand.size();a++){
                            System.out.println(counter+". "+phand.get(a).rank + " of " + phand.get(a).suite); //prints out the cards of the phand array or the player's hand
                            counter++;
                        }
                        System.out.println("The new top card is " +flip.get(0).rank+" of "+flip.get(0).suite);
                        x=1;
                    }else{
                        System.out.println("Sorry the selected card does not match the rank or the suite. Please try again.");
                    }
                }else{
                    int number = Integer.parseInt(index); //converts the string(index) to an int(number)
                    System.out.println("Which card would you like to play? Please select their index #.");
                    int index2 = input.nextInt();
                    index2--;
                    if(phand.get(index2).suite.equals(flip.get(0).suite) || phand.get(index2).rank.equals(flip.get(0).rank)){ //checks if the selected card can be played
                        if(!flip.get(0).rank.equals("the suite")){ //make sure we are not adding "the suite" cards back to the deck since they are not real cards
                            deck.add(flip.get(0));
                        }
                        flip.remove(0);
                        flip.add(phand.get(index2));
                        phand.remove(index2);
                        for(int b=1; b<number;){
                            System.out.println("Please enter another card with the same rank. Please select the index number.");
                            System.out.println("This is your new hand:");
                            counter = 1;
                            for(int a = 0; a<phand.size();a++){
                            System.out.println(counter+". "+phand.get(a).rank + " of " + phand.get(a).suite); //prints out the cards of the phand array or the player's hand
                            counter++;
                            }
                            int index4 = input.nextInt();
                            index4--;
                            if(phand.get(index4).rank.equals(flip.get(0).rank)){ //continues letting the user select cards
                                deck.add(phand.get(index4));
                                phand.remove(index4);
                                b++;
                            }else{
                                System.out.println("Sorry that does not match the same rank! Please try again.");
                            }
                        }
                        x=1;
                    }else{
                        System.out.println("Sorry the selected card does not match the suite or rank of the flipped card. Please try again.");
                    }
                }
                drawn++;
                if(phand.isEmpty()){ // isEmpty method means if the # of elements is 0 in the array
                System.out.println("Congratulations you win!");
                break;
                }
            }
            int y=0;
            while(cdrawn<=3 && y!=1){ //new set of codes for the computer
                for(int i=0; i<chand.size()&&y!=1; i++){ // for loop to loop through thge pc's phand so that I can check whether the PC can play a card
                    if(chand.get(i).rank.equals(flip.get(0).rank) || chand.get(i).suite.equals(flip.get(0).suite) && !chand.get(i).rank.equals("8")){
                        if(!flip.get(0).rank.equals("the suite")){ //make sure we are not adding "the suite" cards back to the deck since they are not real cards
                            deck.add(flip.get(0));
                        }
                        flip.remove(0);
                        flip.add(chand.get(i));
                        chand.remove(i);
                        System.out.println("The computer has played "+flip.get(0).rank+" of "+flip.get(0).suite); //tells player what card is on top now
                        System.out.println("The computer has "+chand.size()+" cards left"); //tells player how much cards the computer has left
                        for(int j=0; j<chand.size();){ // allows the computer to play more than one card if possible by checking all combinations
                            if(flip.get(0).rank.equals(chand.get(j))){
                                deck.add(flip.get(0));
                                flip.remove(0);
                                flip.add(chand.get(j));
                                chand.remove(j);
                                System.out.println("The computer has played "+flip.get(0).rank+" of "+flip.get(0).suite);
                                System.out.println("The computer has "+chand.size()+" cards left.");
                                j=0;
                            }else{
                                j++;
                            }
                        }
                        y =1; // use variable "y" to end the loop
                        System.out.println("It is now your turn!");
                    }else if(chand.get(i).rank.equals("8")){
                        flip.remove(0);
                        flip.add(new Card("the suite", chand.get(0).suite));
                        deck.add(chand.get(i));
                        chand.remove(i);
                        System.out.println("The computer has played an 8 of " +chand.get(i).suite);
                        System.out.println("The suite is now "+flip.get(0).suite);
                        System.out.println("The computer has "+chand.size()+" cards left");
                        y=1;
                        System.out.println("It is now your turn!");
                    }
                }
                if(y==0){
                    System.out.println("The computer draws a card!");
                    Collections.shuffle(deck);
                    chand.add(deck.get(0));
                    deck.remove(0);
                    System.out.println("The computer has "+chand.size()+" cards left!");
                    cdrawn++;
                }
                if(chand.isEmpty()){
                    System.out.println("The computer wins! Better luck next time.");
                }
            }
        }
    }
}
