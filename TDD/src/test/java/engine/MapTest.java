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

    @Test
    public void testGenerateNewRandomPath() {
        
    }

    @Test
    public void testGetObjects() {
    }

    @Test
    public void testAddObject() {
    }

    @Test
    public void testAddTower() {
        map.addTower(0, 0);
    }

    @Test
    public void testSetPath() {
    }

    @Test
    public void testGetPath() {
    }

    @Test
    public void testGetLength() {
        assertEquals(map.getLength(), 100);
    }

    @Test
    public void testGetWidth() {
        assertEquals(map.getWidth(), 100);
    }

}
