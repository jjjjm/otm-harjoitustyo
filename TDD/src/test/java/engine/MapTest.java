package engine;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest {

    Map map;

    @Before
    public void setUp() {
        this.map = new Map(100, 100);
    }

    /**
     * Test of generateNewRandomPath method, of class Map.
     */
    @Test
    public void testGenerateNewRandomPath() {
        map.generateNewRandomPath(0, 100, 0, 100);
        assertEquals(map.getPath().getCoords().get(0).getKey(), (Integer) 0);
    }

    /**
     * Test of getObjects method, of class Map.
     */
    @Test
    public void testGetObjects() {
        assertEquals(map.getObjects().size(), 0);
    }

    /**
     * Test of addObject method, of class Map.
     */
    @Test
    public void testAddObject() {
        map.addObject(0, 0);
        assertEquals(map.getObjects().size(), 1);
    }

    /**
     * Test of addTower method, of class Map.
     */
    @Test
    public void testAddTower() {
        map.addTower(0, 0);
        assertEquals(map.getObjects().size(), 1);
    }

    /**
     * Test of setPath method, of class Map.
     */
    @Test
    public void testSetPath() {
        Path p = new Path();
        map.setPath(p);
        assertEquals(p, map.getPath());
    }

    /**
     * Test of getPath method, of class Map.
     */
    @Test
    public void testGetPath() {
        Path p = new Path();
        map.setPath(p);
        assertEquals(p, map.getPath());
    }

    /**
     * Test of getLength method, of class Map.
     */
    @Test
    public void testGetLength() {
        assertEquals(map.getLength(), 100);
    }

    /**
     * Test of getWidth method, of class Map.
     */
    @Test
    public void testGetWidth() {
        assertEquals(map.getWidth(), 100);
    }

}
