package engine;

import fi.defence.engine.Path;
import fi.defence.engine.NPC;
import javafx.scene.shape.Circle;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class NPCTest {

    NPC npc;

    public NPCTest() {
    }

    @Before
    public void setUp() {
        Path p = new Path(10);
        p.setDefaultPath();
        npc = new NPC(400, 0, p, 5, 10, 1);
    }

    @Test
    public void hitBoxIsSetCorrectly() {
        assertTrue(npc.getHitbox().getBoundsInLocal().intersects(new Circle(400, 1, 5).getBoundsInLocal()));
    }

    @Test
    public void traverselWorks() {
        npc.traverseToNextNode();
        assertTrue(npc.getX() == 400 && npc.getY() == 1);
    }

    @Test
    public void traversalChangesDirection() {
        for (int i = 0; i < 151; i++) {
            npc.traverseToNextNode();
        }
        assertEquals(399, npc.getX(),0.1);
    }
}
