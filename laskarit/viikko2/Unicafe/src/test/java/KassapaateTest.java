
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }

    @Test
    public void luodessaRahatOikein() {
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void luodessaMaaraOikein() {
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void kateisOstossaRiittavaMaaraKasvattaaRahaaMaukas() {
        assertEquals(kassapaate.syoMaukkaasti(500), 100);
        assertEquals(kassapaate.kassassaRahaa(), 100400);
    }

    @Test
    public void kateisOstossaRiittavaMaaraKasvattaaRahaaEdullinen() {
        assertEquals(kassapaate.syoEdullisesti(480), 240);
        assertEquals(kassapaate.kassassaRahaa(), 100240);
    }

    @Test
    public void kateinenMaukkaideMyytyjenMaaraKasvaaJosRahaaRiittavasti() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void kateinenEdullistenMyytyjenMaaraKasvaaJosRahaaRiittavasti() {
        kassapaate.syoEdullisesti(400);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void kateinenJosEiTarpeeksiRahaaRahaMaaraEiMuutuEdullinen() {
        assertEquals(kassapaate.syoEdullisesti(200), 200);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void kateinenJosEiTarpeeksiRahaaRahaMaaraEiMuutuMaukas() {
        assertEquals(kassapaate.syoMaukkaasti(300), 300);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void korttiOstossaRiittavaMaaraKasvattaaRahaaMaukas() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(maksukortti.saldo(), 600);
    }

    @Test
    public void korttiOstossaRiittavaMaaraKasvattaaRahaaEdullinen() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
        assertEquals(maksukortti.saldo(), 760);
    }

    @Test
    public void korttiEdullistenMyytyjenMaaraKasvaaJosRahaaRiittavasti() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void korttiMaukkaidenMyytyjenMaaraKasvaaJosRahaaRiittavasti() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void korttiJosEiTarpeeksiRahaaRahaMaaraJaMaaraEiMuutuEdullinen() {
        maksukortti = new Maksukortti(100);
        assertFalse(kassapaate.syoEdullisesti(maksukortti));
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void korttiJosEiTarpeeksiRahaaRahaMaaraEiMuutuMaukas() {
        maksukortti = new Maksukortti(100);
        assertFalse(kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void korttillaOstetteassaKassaRahaEiMuutu() {
        kassapaate.syoEdullisesti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void kortilleLadattessaSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(maksukortti.saldo(), 1100);
    }

    @Test
    public void kortilleLadattessaKassaKasvaa() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(kassapaate.kassassaRahaa(), 100100);
    }

    @Test
    public void eiMuutuJosLadataanNegatiivinen() {
        kassapaate.lataaRahaaKortille(maksukortti, -1);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
}
