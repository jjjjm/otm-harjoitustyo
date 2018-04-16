package engine;

import fi.defence.engine.Path;
import fi.defence.engine.Tower;
import fi.defence.engine.NPC;
import javafx.scene.shape.Circle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TowerTest {

    Tower t;
    NPC npc;

    public TowerTest() {
    }

    @Before
    public void setUp() {
        t = new Tower(1, 1, 1);
        npc = new NPC(1, 1, new Path(0), 5, 10, 1);
    }

    @Test
    public void TowerShootsIfNPCInRange() {
        npc = new NPC(1, 1, new Path(0), 5, 10, 1);
        assertTrue(t.shootableInRange(npc));

    }

    @Test
    public void TowerDoesntShootIfNPCNotInRange() {
        NPC n = new NPC(100, 100, new Path(0), 5, 10, 1);
        assertFalse(t.shootableInRange(n));
    }

    @Test
    public void TowerDoesDamageToNpc() {
        t.engageShootable(npc);
        assertEquals(npc.getHealth(), 9);
    }

    @Test
    public void HitBoxSetUpCorrectly() {
        assertTrue(t.getHitBox().getBoundsInLocal().intersects(new Circle(1, 1, 5).getBoundsInLocal()));
    }
}
