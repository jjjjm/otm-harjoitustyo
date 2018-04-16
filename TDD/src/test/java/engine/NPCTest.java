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
        npc = new NPC(1, 1, p, 5, 10, 1);
    }

    @Test
    public void HitBoxIsSetCorrectly() {
        assertTrue(npc.hitbox().getBoundsInLocal().intersects(new Circle(1, 1, 5).getBoundsInLocal()));
    }
    
    @Test
    public void TraverselWorks(){
            assertTrue(true); //Work in progress
}
}
