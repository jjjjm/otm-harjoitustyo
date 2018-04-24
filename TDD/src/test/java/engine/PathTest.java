package engine;

import fi.defence.engine.Path;
import java.util.ArrayList;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathTest {

    Path p;

    public PathTest() {
    }

    @Before
    public void setUp() {
        p = new Path(10);
    }

    @Test
    public void testGetCoords() {
        ArrayList<Pair<Integer, Integer>> al = new ArrayList<>();
        al.add(new Pair(0, 0));
        p.setCoords(al);
        assertEquals(p.getCoords(), al);
    }

    @Test
    public void testSetCoords() {
        ArrayList<Pair<Integer, Integer>> al = new ArrayList<>();
        al.add(new Pair(0, 0));
        p.setCoords(al);
        assertEquals(p.getCoords(), al);
    }

    @Test
    public void testGenerateRandomPathFirstCoordinateCorrect() {
        p.generateRandomPath(10, 20, 5);
        assertTrue(0 == p.getCoords().get(0).getValue());
    }
    
    @Test
    public void testGenerateRandomPathLastCoordinateCorrect() {
        p.generateRandomPath(10, 20, 5);
        assertTrue(10 == p.getCoords().get(p.getCoords().size() - 1).getValue());
    }
    
    @Test
    public void testGenerateRandomPathNotOverOrUnderBounds() {
        p.generateRandomPath(10, 20, 5);
        for(Pair<Integer,Integer> t : p.getCoords()){
            assertTrue(t.getKey() >= 0 && t.getValue() <= 20);
        }
    }

    @Test
    public void testSetDefaultPath() {
        p.setDefaultPath();
        assertEquals(p.getCoords().get(0).getKey(), (Integer) 400);
    }

}
