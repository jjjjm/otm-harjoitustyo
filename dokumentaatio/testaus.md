# Testausdokumenti  
Ohjelmaa on testattu automatisoiduin yksikkötestein JUnitilla ja manuaalisesti järjestelmätasolla.

## Yksikkö- ja integraatiotestaus
  
### Sovellus logiikka   
Ohjelman sovelluslogiikkaa (pakkaus _fi.defence.engie_) testaa testipakkauksen engine testitluokat.  
Ne simuloivat tilanteita mitä ohjelman käytönaikana voisi syntyä ja tarkistavat automaattisesti 
että ohjelma toimii halutulla tavalla.
  
Testeillä on pyritty kuvaamaan mahdollisimman hyvin myös eri luokkien välisiä interaktioita.
  
### IO  
IO:n testauksesta vastaa IO testi pakkauksen luokka IOTest.  
Tämä luo tiedostoja ja poistaa ne testien lopussa mikäli on sinänsä huono sillä samalla poistuu mahdolliset sovelluksen ajaon aikana tallennetut tiedot.

### Testikattavuus  
Testien rivikattavuus on 91% ja haaraumakattavuus 82%.  
(https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png)  
  
  
Jotain Map luokankokonaisuuksia jäi.

## Järjestelmätestaus  
  
Järjestelmätestaus on suoritettu manuaalisesti
  
### Asennus ja konfigurointi  
Sovellus on testattu hakemalla se ja toimimalla käyttöohjeen mukaan. Sovellus on testattu toimivaksi Linux-ympäristössä.

### Toiminnallisuus  
Kaikki määrittelydokumentin toiminallisuudet on testattu toimivaksi. Myös mahdollisia virhetilanteita on pyritty käymään läpi,
kuten virheellisiä arvoja maps.txt tiedostossa.


## Sovellukseen jääneet laatuongelmat
_todo_
