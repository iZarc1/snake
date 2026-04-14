/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;
import java.awt.Graphics;

/**
 *
 * @author izaelb
 */
public class Food extends node {
    public Food(Snake snake) {
        super(0,0);
        int row = (int) (Math.random() * board.NUM_ROWS);
        int col = (int) (Math.random() * board.NUM_COLS);
        while (snake.Contain(row, col)) {
            row = (int) (Math.random() * board.NUM_ROWS);
            col = (int) (Math.random() * board.NUM_COLS);
        }
        setRow(row);
        setCol(col);
    }
    
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        Util.DrawSquare(g, getRow(), getCol(), NodeType.FOOD, squareWidth, squareHeight);
    }
    public int getPoints() {
        return 10;
    }
    public int getNodesWhenEat() {
        return 1;
    }
    public boolean hasToBeErased() {
        return false;
    }
}
    

