## Sovelluslogiikka
Käytännössä luokka Map vastaa sovelluksen pelinmoottorillisesta logiikasta(esim. tornien ampuma-alueiden laskemisesta ja kuolleiden vihollisten poistosta).  
Main luokka taas UI pakkauksen luokkia hyväksikäyttäen vastaa graaffisen käyttöliittymän rakennuksesta ylläpidosta ja päivituksestä. Main kutsuu myös map luokan metodeja jotta ne saadaan synkronoitua yhtäaikasiksi UI luokan visuaalisten komponenttien kanssa.  
IO pakkauksen io luokka vastaa pysyväistallennuksesta ja tiedostojen käsittelystä (tiedostot luodaan jos niitä ei ole olemassa jne.)
  
    
  
## Rakenne  
![Pakkauskuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausdiagrammi.png)  
  
UI sisältää javafx:ää käyttävät käyttöliittymän rakentavat luokat, Engine peli moottorin eri osat ja IO tiedoston käsittelyyn tarkoitetut osat. Main pakkauksen luokat kokoavat kokonaisuuden yhteen.   
  
Alla kuvaus pelin loogisista luokista ja niiden välisistä yhteyksistä. Kuten kaaviosta näkyy Map luokka huolehtii lähes kaikesta toiminallisuudesta  
  
![Kaaviokuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/kaavio.png)
  
    
  
## Päätoiminnallisuudesta  
Ohjelman toiminta pelin aikana  
![Sekvenssikuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png)  
Main metodi kutsuu jatkuvasti map-luokan resolve-metodia joka selvittää mahdolliset tilanteet esimerkiksi missä pitää ampua tai milloin vihollinen on tullut kartan loppuun  
  
Alla olevassa kaaviossa kuvaus main metodin toiminnasta kun peli käynnistetään ja käyttäjä lataa vanhan kartan tiedostosta käyttöön.
  
![sekvenssi2](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssi2.png)
  
  

## Tiedostot  
Sovellus tallentaa tietoja kartoista, tai oikeastaan vain niiden sisältämien polkujen koordinaattipisteistä erilliseen tiedostoon.  
Sovellus luo itse tarvittaessa *maps.txt* tiedoston jos sitä ei löydy.  
  
Tallennettu formaatti on seuraavanlainen:  
  
_save:532,0:532,53:513,53:513,119:300,119:300,...(lisää pisteitä)...,:62,900:end_  
  
Alussa on pisteille annettu nimi, sitä seuraa kaksoispista jonka jälkeen tulevat koordinaattipisteet x-y muodossa joista jokainen on erotettu kaksoispisteillä.   
  
Lopussa oleva _:end_ merkkaa aina sitä edeltäneen nimen tietojen loppumista ja seuraavan alkamista/tiedoston päättymistä.

## Rakenteeseen jääneet heikkoudet  
Entities joutuu kutsuaan reduntatisti torneja saadakseen tietää mitä ne ovat ampumassa. Tämän olisi voinut erittää esim. luomalla vielä loogisen luokan ammukselle jota käyttöliittymän graaffiset elementit olisivat voineet seurata, tällä hetkelle itse looginen osa ei tiedä mitään ammusten olemessa olosta.
  
    
Map luokkaa olisi siitä ehkä syytä refaktoroida pienimmiksi kokonaisuusiksi, nyt Map luokka vastaa vähän liian suuren kokonaisuuden hallinnasta
