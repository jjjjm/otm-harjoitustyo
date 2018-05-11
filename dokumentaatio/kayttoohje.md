# Käyttöohje
Lataa [releaseista](https://github.com/jjjjm/otm-harjoitustyo/releases) jar tiedosto.  
_Ohjelma olettaa että se voi luoda tiedostoja_(tai tarvittaessa voidaan luoda maps.txt samaan kansioon jarin kanssa)  
Ohjelma myös olettaa _style.css_ löytyvän samasta kansiosta jarin kanssa  
JavaFX:n pitäisi tulla JDK:n mukana ja ilmeisesti myös JVM:n kanssa
# Käynnistäminen 
Ohjelman voi käynnistää komennolla  
  `java -jar TDD.jar`  
## Käyttöohje
Pelin voi käynnistää _Start_ nappia painamalla jolloin sinut viedeään peli näkymään.  
Voit myös ladata tallennettuja karttoja _Load Map_ napilla. Jos ei ole tallennettuja karttoja ei nappi tee mitään.  
![alku](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/alku.png)
  
Tallennetut kartat tulevat näkyviin alkuvalikon alalaitaan josta voit valita kartan klikkaamalla sitä ja tämän jälkeen aloittaa  
kartan pelaamisen painamalla _Start_ nappia.  
![lataus](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/lataus.png)
    
Peli näkymässä voit sijoittaa torneja painamalla _Tower_ nappia ja klikkaamalla jonnekkin valkoiselle alueelle kartalla.  
Torni ilmestyy kartalle kunhan sinulla oli vähintään 5 rahaa joka ilmoitetaan yläpalkin vasemmanpuoleisella luvulla.  
Torni ampuu polulla kulkevia vihollisia jos sen ampumaetäisyys riittää siihen.  
Saata lisää rahaa jos onnistut tuhoamaan vihollisen ja menetät elämän jos vihollinen pääsee polun loppuun kartan alakulmaan.
Tornin voi poistaa valitsemalla delete napin ja klikkaamalla tornia.  
![kuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/poisto.png)  
  
Pelatessa kartan voi tallentaa kirjoittamalla nimen tekstilaatikkoon ja painamalla save-nappia
![kuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/tallennus.png)
