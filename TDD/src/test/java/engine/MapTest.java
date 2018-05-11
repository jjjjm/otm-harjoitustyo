package engine;

import fi.defence.engine.Map;
import fi.defence.engine.NPC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class MapTest {

    Map map;

    public MapTest() {
    }

    @Before
    public void setUp() {
        this.map = new Map(100, 100);
        List<Pair<Integer, Integer>> testCoords = new ArrayList();
        testCoords.add(new Pair(50, 0));
        testCoords.add(new Pair(50, 100));
        map.getPath().setCoords(testCoords);
    }

    @Test
    public void addingTowersWorksIfDoesntIntersectPath() {
        assertTrue(map.addTower(0, 0).isPresent());
    }

    @Test
    public void towerNotAddedIfIntersectsPath() {
        assertFalse(map.addTower(50, 0).isPresent());
    }

    @Test
    public void towerNotAddedIfIntersectsAnother() {
        map.addTower(100, 100);
        map.resolve();
        assertFalse(map.addTower(100, 100).isPresent());
    }

    @Test
    public void addingTowersWorksIfDoesntIntersectAnother() {
        map.addTower(25, 25);
        assertTrue(map.addTower(0, 0).isPresent());
    }

    @Test
    public void towerNotRemovedIfCoordinatesDoesntHitTower() {
        map.addTower(0, 0);
        map.addTower(100, 100);
        map.resolve();
        assertFalse(map.removeTower(25, 25));
    }

    @Test
    public void towerRemovedIfCoordinatesHitTower() {
        map.addTower(0, 0);
        map.resolve();
        assertTrue(map.removeTower(1, 1)); //little offsets 
    }

    @Test
    public void willAddEnemyIfPossible() {
        assertTrue(map.addEnemy().isPresent());
    }

    @Test
    public void willNotAddEnemyIfNotPossible() {
        for (int i = 0; i < 15; i++) {
            map.addEnemy();
        }
        assertFalse(map.addEnemy().isPresent());
    }

    @Test
    public void deadEnemiesRemoved() {
        NPC npc = map.addEnemy().get();
        npc.setHealth(0);
        map.resolve();
        boolean[] test = new boolean[14];
        for (int i = 0; i < 14; i++) {
            test[i] = map.addEnemy().isPresent();
        }
        assertTrue(Arrays.equals(new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true}, test));
    }
}
