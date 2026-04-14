/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

/**
 *
 * @author izaelb
 */


import java.awt.Graphics;
import java.util.Calendar;
import java.util.Random;

public class SpecialFood extends Food {
    
    private int timeVisible;
    private long startingTime;
    
    public SpecialFood(Snake snake) {
        super(snake);
        Random r = new Random();
        timeVisible = r.nextInt(10)*1000 + 3000;
        startingTime = Calendar.getInstance().getTimeInMillis();
    }
    
    @Override
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        Util.DrawSquare(g, getRow(), getCol(), NodeType.SPECIAL_FOOD, squareWidth, squareHeight);
    }
    
    @Override
    public int getNodesWhenEat() {
        return 3;
    }
    
    @Override
    public int getPoints() {
        return 50;
    }
    
    public int getTimeVisible() {
        return timeVisible;
    }
    
    @Override
    public boolean hasToBeErased() {
        if (Calendar.getInstance().getTimeInMillis() - 
                startingTime >= timeVisible)
            return true;
        return false;
    }

    
}
