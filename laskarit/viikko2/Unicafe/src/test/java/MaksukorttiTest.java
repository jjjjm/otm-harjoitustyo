

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void kortinSaldonKasvattaminenToimii() {
        kortti.lataaRahaa(10);
        assertEquals(kortti.saldo(), 20);
    }

    @Test
    public void kortinSaldoVaheneeJosRahaaTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals(kortti.saldo(), 5);
    }

    @Test
    public void kortinSaldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals(kortti.saldo(), 10);
    }

    @Test
    public void korttiPalauttaTrueJosRahaaTarpeeksi() {
        assertTrue(kortti.otaRahaa(10));
    }

    @Test
    public void korttiPalauttaFalseJosRahaaEiTarpeeksi() {
        assertFalse(kortti.otaRahaa(20));
    }
}
