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
