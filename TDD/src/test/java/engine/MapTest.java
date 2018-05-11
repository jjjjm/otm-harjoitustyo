package engine;

import fi.defence.engine.Map;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import org.junit.Before;
import static org.junit.Assert.*;

public class MapTest {

    Map map;

    @Before
    public void setUp() {
        this.map = new Map(100, 100);
    }

    public void addingTowersWorksIfPossible() {
        List<Pair<Integer, Integer>> testCoords = new ArrayList();
        testCoords.addAll((new Pair(50, 0)), (new Pair(50, 100)));
        map.getPath().setCoords(testCoords);
        assertTrue(map.addTower(0, 0).isPresent());
    }
    
    public void towerNotAddedIfNotPossible(){
        List<Pair<Integer, Integer>> testCoords = new ArrayList();
        testCoords.addAll((new Pair(50, 0)), (new Pair(50, 100)));
        map.getPath().setCoords(testCoords);
        assertTrue(map.addTower(50, 0).isPresent());
    }
    
    public void towerNotRemovedIfCoordinatesDoesntHitTower(){
        map.addTower(50, 0);
        map.addTower(50, 100);
        assertFalse(map.removeTower(0, 0));
    }
    
    public void towerRemovedIfCoordinatesHitTower(){
        map.addTower(50, 0);
        map.addTower(50, 100);
        assertTrue(map.removeTower(50,99) && map.removeTower(50, 1));
    }
}
