/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author humza
 */
public class UnoCard extends Card{
    
    private String color;
    private String cardValue;
    private String ability;
    
    public UnoCard(){}

    public UnoCard(String color, String cardValue) {
        this.color = color;
        this.cardValue = cardValue;
    }

    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
    
    
    

    @Override
    public String toString() {
        return getColor()+" : "+getCardValue();
    }
    
}
