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
    public void testGenerateRandomPath() {

    }

    @Test
    public void testSetDefaultPath() {
        p.setDefaultPath();
        assertEquals(p.getCoords().get(0).getKey(), (Integer) 400);
    }

}
