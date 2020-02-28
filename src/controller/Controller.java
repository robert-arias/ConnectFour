package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.Board;
import view.Preferences;

/**
 *
 * @author robert
 */
public class Controller extends MouseAdapter implements ActionListener, FocusListener {

    private final Preferences preferences;
    private final Board board;
    
    private boolean play = false;
    
    public Controller(Preferences preferences, Board board) {
        this.preferences = preferences;
        this.board = board;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("START")) {
            board.reset();
            preferences.disableAll();
            preferences.setPlayers();
            play = true;
        }
        
        if(e.getActionCommand().equals("Red") || e.getActionCommand().equals("Yellow"))
            board.colorOption();
        
        if(e.getActionCommand().equals("Manual"))
            preferences.visible(true);
        
        if(e.getActionCommand().equals("Automatic"))
            preferences.visible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(play && !board.isAnimation() && !board.isStandBy())
            board.setChip(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!board.isAnimation())
            board.moveChip(e.getX());
    }

    @Override
    public void focusGained(FocusEvent e) {
        preferences.focusOn(e.getSource());
    }

    @Override
    public void focusLost(FocusEvent e) {
        preferences.focusOff(e.getSource());
    }
    
    public boolean getPlay() {
        return play;
    }
    
    public void setPlay(boolean play) {
        this.play = play;
    }
}