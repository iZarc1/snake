/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

/**
 *
 * @author izaelb
 */
import java.util.LinkedList;

public class Snake {
    private LinkedList<node> nodes;
    private Direction direction;
    private int growCount; 

    public Snake(int startRow, int startCol) {
        nodes = new LinkedList<>();
        nodes.add(new node(startRow, startCol)); 
        direction = Direction.RIGHT;
        growCount = 0;
    }

    public void move() {
        node head = nodes.getFirst();
        int newRow = head.getRow();
        int newCol = head.getCol();

        switch (direction) {
            case UP    -> newRow--;
            case DOWN  -> newRow++;
            case LEFT  -> newCol--;
            case RIGHT -> newCol++;
        }

        nodes.addFirst(new node(newRow, newCol));

        if (growCount > 0) {
            growCount--;
        } else {
            nodes.removeLast();
        }
    }

    public boolean canMove(int rows, int cols) {
    node head = nodes.getFirst();
    int newRow = head.getRow();
    int newCol = head.getCol();

    switch (direction) {
        case UP    -> newRow--;
        case DOWN  -> newRow++;
        case LEFT  -> newCol--;
        case RIGHT -> newCol++;
    }

    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols)
        return false;

    for (node node : nodes) {
        if (node.getRow() == newRow && node.getCol() == newCol)
            return false;
    }

    return true;
}

    public void grow(int amount) {
        growCount += amount; 
    }

    public node getHead() {
        return nodes.getFirst();
    }

    public LinkedList<node> getNodes() {
        return nodes;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}