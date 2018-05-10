
package fi.defence.engine;

/**
 * Kuvaa pelin pelaajaa
 */
public class Player {
    private int money,health;
/**
 * Lue uuden palaajan ja asettaa alun raha ja elämätilanteen annettujen parametrien mukaan
 * @param money pelaajan rahatilanne alussa
 * @param health pelaajan elämäpisteet alussa
 */
    public Player(int money, int health) {
        this.money = money;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
}
