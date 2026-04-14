/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

/**
 *
 * @author izaelb
 */
import java.util.ArrayList;

import java.awt.Graphics;
import java.util.*;

public class Snake {
    private List<node> list;
    private Direction direction;
    private int toGrow;
 
    public Snake(int numNodes) {
        list = new ArrayList<>();
        int row = board.NUM_ROWS / 2;
        int col = board.NUM_COLS / 3;
        for (int i = 0; i < numNodes; i ++) {
            list.add(new node(row, col - i));
        }
        direction = Direction.RIGHT;
        toGrow = 0;
    }
    
    public void Paint(Graphics g, int squareWidth, int squareHeight) {
        boolean first = true;
        for (node node: list) {
            int row = node.getRow();
            int col = node.getCol();            
            Util.DrawSquare(g, row, col, first ? NodeType.HEAD : NodeType.BODY, squareWidth, squareHeight);
            first = false;
        }
    }
    
    public int getHeaderRow() {
        return list.get(0).getRow();
    }
    
    public int getHeaderCol() {
        return list.get(0).getCol();
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public boolean Contain(int row, int col) {       
        for (node node: list) {
            if (node.getRow() == row && node.getCol() == col) {
                return true;
            }
        }
        return false;
    }
    
    public boolean canMove() {
        int row = getHeaderRow();
        int col = getHeaderCol();
        switch (direction) {
            case UP:
                if (row - 1 < 0 || Contain(row - 1, col)) {
                    return false;
                }
                break;
            case DOWN:
                if (row + 1 >= board.NUM_ROWS || Contain(row + 1, col)) {
                    return false;
                }
                break;
            case LEFT:
                if (col - 1 < 0 || Contain(row, col - 1)) {
                    return false;
                }
                break;
            case RIGHT:
                if (col + 1 >= board.NUM_COLS || Contain(row, col + 1)) {
                    return false;
                }
                break;
            default:
                throw new AssertionError();
        }
        return true;
    }
    
    public void move() {
        int row = getHeaderRow();
        int col = getHeaderCol();
        switch (direction) {
            case UP:
                list.add(0, new node(row - 1, col));
                break;
            case DOWN:
                list.add(0, new node(row + 1, col));
                break;
            case LEFT:
                list.add(0, new node(row, col - 1));
                break;
            case RIGHT:
                list.add(0, new node(row, col + 1));
                break;
            default:
                throw new AssertionError();
        }
        if (toGrow <= 0) {
            list.remove(list.size() - 1);
        } else {
            toGrow --;
        }
    }
    
    
    public boolean eat(Food food) {
        if (getHeaderRow() == food.getRow() && getHeaderCol() == food.getCol()) {
            toGrow += food.getNodesWhenEat();
            return true;
        }
        return false;
    }
}