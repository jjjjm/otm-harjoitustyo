package engine;

import java.util.ArrayList;
import javafx.util.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jermusto
 */
public class PathTest {

    Path p;

    @Before
    public void setUp() {
        p = new Path();
    }

    /**
     * Test of getCoords method, of class Path.
     */
    @Test
    public void testGetCoords() {
        ArrayList<Pair<Integer, Integer>> al = new ArrayList<>();
        al.add(new Pair(0, 0));
        p.setCoords(al);
        assertEquals(p.getCoords(), al);
    }

    /**
     * Test of setCoords method, of class Path.
     */
    @Test
    public void testSetCoords() {
        ArrayList<Pair<Integer, Integer>> al = new ArrayList<>();
        al.add(new Pair(0, 0));
        p.setCoords(al);
        assertEquals(p.getCoords(), al);
    }

    /**
     * Test of generateRandomPath method, of class Path.
     */
    @Test
    public void testGenerateRandomPath() {
        p.generateRandomPath(0, 100, 0, 0, 100, 100, 1);
        assertEquals(p.getCoords().size(), 3);
    }

    /**
     * Test of setDefaultPath method, of class Path.
     */
    @Test
    public void testSetDefaultPath() {
        p.setDefaultPath();
        assertEquals(p.getCoords().get(0).getKey(), (Integer) 400);
    }

}
