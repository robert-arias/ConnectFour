package view;

import controller.Controller;
import controller.Movements;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import model.Point;

/**
 *
 * @author Robert
 */
public class Board extends javax.swing.JPanel {
    
    private final int column = 5;
    private final int row = 112;
    private final Color colors[] = {Color.red, new Color(255, 211, 000)};
    private int color = 0;
    private int x = 45;
    private int y = 12;
    private final int diameter = 90;
    private final List<Point> chips;
    private boolean animation = false;
    private boolean standBy = false;
    private int goal;
    
    private final Movements movements;
    
    private Game game;
    private Controller controller;
    
    public Board() {
        initComponents();
        chips = new ArrayList<>();
        movements = new Movements();
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colors[color]);
        if(!animation)
            g.fillOval(x - 45, y, diameter, diameter);
        
        if(controller.getPlay()) {
            if(!animation)
                drawChips(g);
            else
                drawFall(g);
        }
    }
    
    public void addListeners(Controller controller) {
        this.controller = controller;
        super.addMouseMotionListener(controller);
        super.addMouseListener(controller);
    }
    
    public void moveChip(int x) {
        this.x = x;
        repaint();
    }
    
    public void colorOption() {
        changeColor();
        repaint();
    }
    
    public void changeColor() {
       color = (color == 0) ? 1 : 0;
    }
    
    public void setChip(int column, int row) {
        int coordenatesOnBoard[] = movements.getCoordenates(column, row);
        
        if(coordenatesOnBoard != null) {
            
            /*
                As the position the player selected is available, a chip must be set in its right place.
                To drop a chip, an animation must be played; this is why animation is set to true.
                Whenever paintComponent() is run through repaint(), there are two states to be drawn:
                    1. Whenever the mouse is moved and the table must be drawn with its chips again.
                    2. When a chip is falling.
                As the computer needs to know what to draw, the animation variable tells it what to.
            */
            animation = true;
            
            /*
                The "x" variable holds two different purposes:
                    1. When the mouse is moved and the floating chip must move along with it,
                        it holds the currently position of the mouse: the x position. See method moveChip(int x).
                    2. When a chip is dropped, to know where to draw the chip, the coordinates must be known.
                        It's for this reason, "x" holds the value of the column where the chip must be drawn, too.
            
                For this case, the x variable holds the position of the column where the chip must be drawn.
                As the table is symetric, each chip column distance varies only for 100 px from the previous one.
            */
            x = this.column + (100 * coordenatesOnBoard[1]);
            
            /*
                The "goal" variable only holds one purpose:
                    To define the limit where the chip must stop falling; the row where the chip must stop falling.
                    It is only used for the falling animation.
                
                As the table is symetric, each chip row distance varies only for 100 px from the previous one.
            */
            goal = this.row + (100 * coordenatesOnBoard[0]);
            
            /*
                As a chip must be set, the paintComponent() must be fired, now knowing that a chip is falling
                    (according to the animation variable, which was set to true.
            */
            repaint();
            
            if(movements.verifyWin(coordenatesOnBoard[0], coordenatesOnBoard[1]))
                gameOver(game.showWinner((movements.getTurn())));
        }
    }
    
    private void drawChips(Graphics g) {
        chips.forEach((chip) -> {
            if(chips.size() % 2 == 0) {
                g.fillOval(chip.getX(), chip.getY(), diameter, diameter);
                changeColor();
                g.setColor(colors[color]);
            }
            else {
                changeColor();
                g.setColor(colors[color]);
                g.fillOval(chip.getX(), chip.getY(), diameter, diameter);
                if(chip == chips.get(chips.size() - 1))
                    changeColor();
            }
        });
    }
    
    private void drawFall(Graphics g) {
        try {
            y += 10;
            g.fillOval(x, y, diameter, diameter);
            
            drawChips(g);
        
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("view.Board.drawFall() \n" +
                    e.getMessage());
        }
        
        if(y == goal) {
            animation = false;
            chips.add(new Point(x, y));
            y = 12;
            changeColor();
        }
        
        repaint();
    }
    
    public void reset() {
        chips.clear();
        movements.reset();
        standBy = false;
    }
    
    private void gameOver(int option) {
        if(option == 0)
            reset();
        else {
            standBy = true;
            game.enableOptions();
        }
    }
    
    public boolean isAnimation() {
        return animation;
    }
    
    public boolean isStandBy() {
        return standBy;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jl_board = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_board.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/board.png"))); // NOI18N
        add(jl_board, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 107, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jl_board;
    // End of variables declaration//GEN-END:variables

}