package fi.defence.engine;

/**
 * Kuvaa pelin pelaajaa
 */
public class Player {

    private int money, health, destroyedTowers, playedMaps;

    /**
     * Lue uuden palaajan ja asettaa alun raha , elämätilanteen annettujen
     * parametrien mukaan sekä tiedot pelaajan menestyksestä.
     *
     * @param money pelaajan rahatilanne alussa
     * @param health pelaajan elämäpisteet alussa
     */
    public Player(int money, int health) {
        this.money = money;
        this.health = health;
        this.destroyedTowers = 0;
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

    /**
     * Kasvattaa pelaajantuhottujen tornien määrää sekä pelaajan raha määrää
     */
    public void addScore() {
        this.money += 5;
        this.destroyedTowers++;
    }

    /**
     * Vähentää pelaajan elämäpisteitä yhdellä
     */
    public void takeDamage() {
        this.health -= 1;
    }

    /**
     * Vähentää pelaajan rahoja tornin rakennuksen johdosta
     */
    public void buildTower() {

    }

    public int getScore() {
        return this.destroyedTowers;
    }

}
