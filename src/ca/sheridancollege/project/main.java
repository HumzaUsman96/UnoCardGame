/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author humza
 */
public class main {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
         Game uno= new UnoGame("UNO");
         
        Card red[]= new UnoCard[10];
        Card blue[]= new UnoCard[10];
        Card yellow[]= new UnoCard[10];
        Card green[]= new UnoCard[10];
         GroupOfCards Deck= new GroupOfCards();
         
        for (int x = 0; x < 10; x++) {
            red[x] = new UnoCard("Red", String.valueOf(x));
            Deck.addCard(red[x]);
        }
        
        for (int x = 0; x < 10; x++) {
            green[x] = new UnoCard("Green", String.valueOf(x));
            Deck.addCard(green[x]);
        }
        
        for (int x = 0; x < 10; x++) {
            yellow[x] = new UnoCard("Yellow", String.valueOf(x));
            Deck.addCard(yellow[x]);
        }
        
        for (int x = 0; x < 10; x++) {
            blue[x] = new UnoCard("Blue", String.valueOf(x));
            Deck.addCard(blue[x]);
        }
        
        Card redSpec[]= new UnoCard[3];
        Card greenSpec[]= new UnoCard[3];
        Card yellowSpec[]= new UnoCard[3];
        Card blueSpec[]= new UnoCard[3];
        
        
//            redSpec[0]= new UnoCard("Red","Switch");
//            greenSpec[0]=new UnoCard("Green","Switch");
//            yellowSpec[0]=new UnoCard("Yellow","Switch");
//            blueSpec[0]=new UnoCard("Blue","Switch");
            
            redSpec[0]= new UnoCard("Red","Cancel");
            greenSpec[0]=new UnoCard("Green","Cancel");
            yellowSpec[0]=new UnoCard("Yellow","Cancel");
            blueSpec[0]=new UnoCard("Blue","Cancel");
            
            redSpec[1]= new UnoCard("Red","Plus2");
            greenSpec[1]=new UnoCard("Green","Plus2");
            yellowSpec[1]=new UnoCard("Yellow","Plus2");
            blueSpec[1]=new UnoCard("Blue","Plus2");
            
        for (int i = 0; i < 2; i++) {
            Deck.addCard(redSpec[i]);
            Deck.addCard(greenSpec[i]);
            Deck.addCard(yellowSpec[i]);
            Deck.addCard(blueSpec[i]);
            
        }
        Card anySpec[]= new UnoCard[8];
        
        for (int i = 0; i < 4; i++) {
            anySpec[i]=new UnoCard("Any","Plus 4 Change color");
        }
        
        for (int i = 4; i < 8; i++) {
            anySpec[i]=new UnoCard("Any","Change color");
        }
        
        for (int i = 0; i < 8; i++) {
            Deck.addCard(anySpec[i]);
        }
        
Deck.shuffle();

//THIS DECK IS FOR WEHN THE DECK RUNS OUT OF CARDS
GroupOfCards topUpDeck= new GroupOfCards();
topUpDeck.setCards(Deck.getCards());
//TESTING PURPOSES TO SEE DECK
//        for (int i = 0; i < Deck.getSize(); i++) {
//            
//             System.out.println(Deck.getCards().get(i));
//        }
        
       
        //Start of program
        //Assign number of players
        System.out.println("How many players will be playing: ");
        int numOfPlayers=scan.nextInt();
      scan.nextLine();
       
      //create list of players and make arraylist
        Player [] players=new UnoPlayer[numOfPlayers];
        ArrayList<Player> listOfPlayers=new ArrayList();
        
        //Get users to input their names
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Enter name, Player "+(i+1)+":");
            String name=scan.nextLine();
            
            //create player with inputted name and then add player to list
            players[i]=new UnoPlayer(name);
            listOfPlayers.add(players[i]);
        }
        //add list of players to arraylist
        uno.setPlayers(listOfPlayers);
        
        //Makes Each Players hand and removes from the deck
         GroupOfCards hand[] = new GroupOfCards[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            hand[i]= new GroupOfCards();
            for (int j = 0; j < 7; j++) {
                hand[i].addCard(Deck.getCards().get(j));
                Deck.getCards().remove(j);
            }
        }
        
        //create a discard pile
        GroupOfCards discard=new GroupOfCards();
        discard.addCard(new UnoCard("Any","Start"));
        
        //Start the game play
        boolean endGame=false;
        int turnIndex=0;
        int turnSwitcher=1;
        do{
            
            //turn
            
            System.out.println(uno.getPlayers().get(turnIndex).getName());
            //shows hand
            for (int j = 0; j < hand[turnIndex].getSize(); j++) {
                System.out.println("Card "+j+": "+hand[turnIndex].getCards().get(j).toString());
            }
            System.out.println("[If no card is avalaible just pick any card to skip and pick up card from deck]");
            
           //select card
                System.out.println("Choose which card you want to play: (Current Card: "
                        +discard.getCards().get(discard.getSize()-1).toString()+")");
                
                int selectedIndex=scan.nextInt();
                scan.nextLine();
                
                //process card selection
                if(hand[turnIndex].getCards().get(selectedIndex).getColor().equals
        (discard.getCards().get(discard.getSize()-1).getColor())||
                        
        hand[turnIndex].getCards().get(selectedIndex).getCardValue().equals
        (discard.getCards().get(discard.getSize()-1).getCardValue()) ||
                        
         hand[turnIndex].getCards().get(selectedIndex).getColor().equals("Any") ||
         discard.getCards().get(discard.getSize()-1).getCardValue().equals("Start")){
                    
                   //put down card into dicard pile
                  discard.addCard(hand[turnIndex].getCards().get(selectedIndex));  
                  Deck.addCard(hand[turnIndex].getCards().get(selectedIndex));
                    hand[turnIndex].getCards().remove(selectedIndex);
                    
                    //switch ability
//                     if (discard.getCards().get(discard.getSize()-1).getCardValue().equals("Switch")) {
//                        turnSwitcher=-1;
//                        if(turnSwitcher==-1){
//                            turnSwitcher=1;
//                        }
//                    }
                     
                     //keep old index
                     int oldIndex=turnIndex;
                    //Change turn // turnSwitcher is for when switching works
                    turnIndex = turnIndex + (turnSwitcher);
                  //TESTING  System.out.println("SIZE ---" +uno.getPlayers().size() +"Turn Index "+turnIndex);
                    
                    //if turn goes into Negatives
                    if (turnIndex == -1) {
                        turnIndex = uno.getPlayers().size() - 1;
                    }
                    if (turnIndex == uno.getPlayers().size()) {
                        turnIndex = 0;
                    }
                    
                    //code for plus 2
                     if (discard.getCards().get(discard.getSize()-1).getCardValue().equals("Plus2")) {
                        hand[turnIndex].addCard(Deck.getCards().get(0));
                        Deck.getCards().remove(0);
                        hand[turnIndex].addCard(Deck.getCards().get(0));
                        Deck.getCards().remove(0);
                         System.out.println("Two cards added to "+uno.getPlayers().get(turnIndex).getName());
                    }
                     
                     //code for plus 4 and change color card
                     if (discard.getCards().get(discard.getSize()-1).getCardValue().equals("Plus 4 Change color")) {
                         for (int i = 0; i < 4; i++) {
                             hand[turnIndex].addCard(Deck.getCards().get(0));
                         Deck.getCards().remove(0);
                         }
                         System.out.println("Four cards added to "+uno.getPlayers().get(turnIndex).getName());
                         String color;
                         do{
                             System.out.println("Pick a color");
                            color= scan.nextLine();

                         }while(!(color.equals("Red") ||color.equals("Green")||color.equals("Yellow")||color.equals("Blue")) );
                         discard.getCards().get(discard.getSize()-1).setColor(color);
                     }
                     
                     //code for change color card
                      if (discard.getCards().get(discard.getSize()-1).getCardValue().equals("Change color")){
                          String color;
                         do{
                             System.out.println("Pick a color");
                            color= scan.nextLine();
                         
                         
                         
                         }while(!(color.equals("Red") ||color.equals("Green")||color.equals("Yellow")||color.equals("Blue")) );
                         discard.getCards().get(discard.getSize()-1).setColor(color);
                      }
                      //code for cancel card
                      if(discard.getCards().get(discard.getSize()-1).getCardValue().equals("Cancel")){
                          turnIndex=turnIndex+1;
                            if (turnIndex == uno.getPlayers().size()) {
                        turnIndex = 0;
                    }
                      }
                    
                }else{
                    hand[turnIndex].getCards().add(Deck.getCards().get(0));
                    Deck.getCards().remove(0);
                    System.out.println("You had to pick up a card, not available");
                    turnIndex=turnIndex+1;
                     if (turnIndex == uno.getPlayers().size()) {
                        turnIndex = 0;
                    }
                }
                System.out.println("Deck Size: " +Deck.getSize());
            
           
            //Check if any hand is empty at which point end the game
            for (int i = 0; i < numOfPlayers; i++) {
                if(hand[i].getSize()==0){
                    endGame=true;
                    System.out.println(uno.getPlayers().get(i).getName()+" is the Winner!!");
                }
            }
        }while(endGame==false);
        
        
        
        
        
        
        
        
        
        
//        for (int i = 0; i < numOfPlayers; i++) {
//            System.out.println(uno.getPlayers().get(i).getName());
//            for (int j = 0; j < hand[i].getSize(); j++) {
//                System.out.println("Card "+j+": "+hand[i].getCards().get(j).toString());
//            }
//            
//        }
        
       
        
        
       
        
//        do{
//            for (int i = 0; i < numOfPlayers; i++) {
//                uno.getPlayers().get(i).getName();
//                
//            }
//            
//        }while(true);
        
        
//      for (int i = 0; i < uno.getPlayers().size(); i++) {
//            System.out.println(uno.getPlayers().get(i).getName());
//        }
       
    
}
   
    
    
    
}
