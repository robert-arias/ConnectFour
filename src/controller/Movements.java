/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author rober
 */
public class Movements {
    
    private char[][] board;
    private char turn;
    
    public Movements() {
        board = new char[6][7];
        turn = 'x';
    }
    
    public char getTurn() {
        return turn;
    }
    
    public int[] getCoordenates(int column, int row) {
        int coordenates[] = null;
        int col = getColumn(column);
        
        if(col >= 0 && range(row, 108, 705)) {
            int r = getRow(col);
            if(r >= 0) {
                coordenates = new int[2];
                coordenates[0] = r;
                coordenates[1] = col;
            }
        }
        return coordenates;
    }
    
    private int getColumn(int column) {
        int col = -1;
        
        if(range(column, 2, 99))
            col = 0;
        else if(range(column, 102, 199))
            col = 1;
        else if(range(column, 202, 299))
            col = 2;
        else if(range(column, 302, 399))
            col = 3;
        else if(range(column, 402, 499))
            col = 4;
        else if(range(column, 502, 599))
            col = 5;
        else if(range(column, 602, 799))
            col = 6;
        
        return col;
    }
    
    private int getRow(int column) {
        int row = -1;
        for(int i = board.length - 1; i >= 0; i--) {
            if(board[i][column] == 0) {
                row = i;
                board[i][column] = turn;
                changeTurn();
                break;
            }
        }
        return row;
    }
    
    private boolean range(int x, int xMin, int xMax) {
        return x >= xMin && x <= xMax;
    }
    
    private void changeTurn() {
        turn = (turn == 'x') ? 'y' : 'x';
    }
    
    public boolean verifyWin(int row, int column) {
        return down(row, column, 0) || left(row, column, 0) || upLeft(row, column, 0) || upRight(row, column, 0);
    }
    
    private boolean down(int row, int col, int found) {
        changeTurn();
        
        for(int i = 1; i < 4; i++) {
            if(row + i < board.length)
                if(board[row + i][col] == turn)
                    found++;
                else
                    break;
        }
        
        changeTurn();
        return found == 3;
    }
    
    private boolean left(int row, int col, int found) {
        changeTurn();
        
        for(int i = 1; i < 4; i++) {
            if(col - i >= 0)
                if(board[row][col - i] == turn)
                    found++;
                else
                    break;
        }
        
        if(found != 3)
            found = right(row, col, found);
        
        changeTurn();
        return found == 3;
    }

    private int right(int row, int col, int found) {
        for(int i = 1; i < 4; i++) {
            if(col + i < board[0].length)
                if(board[row][col + i] == turn) {
                    if(++found == 3)
                        break;
                }
                else
                    break;
        }
        
        return found;
    }
    
    private boolean upLeft(int row, int col, int found) {
        changeTurn();
        
        for(int i = 1; i < 4; i++) {
            if(row - i >= 0 && col - i >= 0)
                if(board[row - i][col - i] == turn)
                    found++;
                else
                    break;
        }
        
        if(found != 3)
            found = downRight(row, col, found);
        
        changeTurn();
        return found == 3;
    }

    private int downRight(int row, int col, int found) {
        for(int i = 1; i < 4; i++) {
            if(row + i < board.length && col + i < board[0].length)
                if(board[row + i][col + i] == turn) {
                    if(++found == 3)
                        break;
                }
                else
                    break;
        }
        return found;
    }
    
    private boolean upRight(int row, int col, int found) {
        changeTurn();
        
        for(int i = 1; i < 4; i++){
            if(row - i >= 0 && col + i < board[0].length)
                if(board[row - i][col + i] == turn)
                    found++;
                else
                    break;
        }
        
        if(found != 3)
            found = downLeft(row, col, found);
        
        changeTurn();
        return found == 3;
    }

    private int downLeft(int row, int col, int found) {
        for(int i = 1; i < 4; i++) {
            if(row + i < board.length && col - i >= 0)
                if(board[row + i][col - i] == turn) {
                    if(++found == 3)
                        break;
                }
                else
                    break;
        }
        return found;
    }
    
    public void reset() {
        board = null;
        board = new char[6][7];
    }
    
}