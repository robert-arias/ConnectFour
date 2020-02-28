package model;

/**
 *
 * @author rober
 */
public class Player {
    
    private String name;
    private String color;
    private String mode;
    private String agaisnt;

    public Player(String name, String color, String mode, String agaisnt) {
        this.name = name;
        this.color = color;
        this.mode = mode;
        this.agaisnt = agaisnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
    public String getAgaisnt() {
        return agaisnt;
    }

    public void setAgaisnt(String agaisnt) {
        this.agaisnt = agaisnt;
    }
    
}
