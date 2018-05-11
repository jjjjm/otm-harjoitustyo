## Sovelluslogiikka
Käytännössä luokka Map vastaa sovelluksen pelinmoottorillisesta logiikasta(esim. tornien ampuma-alueiden laskemisesta ja kuolleiden vihollisten poistosta).  
Main luokka taas UI pakkauksen luokkia hyväksikäyttäen vastaa graaffisen käyttöliittymän rakennuksesta ylläpidosta ja päivituksestä. Main kutsuu myös map luokan metodeja jotta ne saadaan synkronoitua yhtäaikasiksi UI luokan visuaalisten komponenttien kanssa.  
IO pakkauksen io luokka vastaa pysyväistallennuksesta ja tiedostojen käsittelystä (tiedostot luodaan jos niitä ei ole olemassa jne.)
![Kaaviokuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/alustavakaavio.png)  
  
    
  
## Rakenne  
![Pakkauskuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausdiagrammi.png)  
  
UI sisältää javafx:ää käyttävät käyttöliittymän rakentavat luokat, Engine peli moottorin eri osat ja IO tiedoston käsittelyyn tarkoitetut osat. Main pakkauksen luokat kokoavat kokonaisuuden yhteen. 
  
## Päätoiminnallisuudesta  
Ohjelman toiminta pelin aikana  
![Sekvenssikuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png)  
Main metodi kutsuu jatkuvasti map-luokan resolve-metodia joka selvittää mahdolliset tilanteet esimerkiksi missä pitää ampua tai milloin vihollinen on tullut kartan loppuun  

## Tiedostot  
Sovellus tallentaa tietoja kartoista, tai oikeastaan vain niiden sisältämien polkujen koordinaattipisteistä erilliseen tiedostoon.  
Sovellus luo itse tarvittaessa maps.txt tiedoston jos sitä ei löydy.  
Tallennettu formaatti on seuraavanlainen:  
_save:532,0:532,53:513,53:513,119:300,119:300,195:413,195:413,261:67,261:67,355:578,355:578,401:237,401:237,501:766,501:766,559:911,559:911,629:564,629:564,699:233,699:233,777:597,777:597,840:62,840:62,900:end_  
Alussa on pisteille annettu nimi, sitä seuraa kaksoispista jonka jälkeen tulevat koordinaattipisteet x-y muodossa joista jokainen on erotettu kaksoispisteillä. Lupussa oleva _:end_ merkkaa aina sitä edeltäneen nimen tietojen loppumista ja seuraavan alkamista/tiedoston päättymistä.
