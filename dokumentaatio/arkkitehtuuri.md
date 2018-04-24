## Sovelluslogiikka
Käytännössä luokka Map vastaa sovelluksen logiista. 
Käyttöliittymä kutsuu vain Map luokkaa tarkistaakseen esim. mahdolliset viholliset tornien ampumaalueella.
Map myös tallentaa kaikki muut sovelluslogiikan kannalta olennaiset oliot ja vastaa niiden käsittelystä.  
![Kaaviokuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/alustavakaavio.png)  
  
  
## Päätoiminnallisuudesta
Ohjelmantoimnta pelin aikana
![Sekvenssikuva](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png)  
Main metodi kutsuu jatkuvasti map-luokan resolve-metodia joka selvittää mahdolliset tilanteet esimerkiksi missä pitää ampua tai milloin vihollinen on tullut kartan loppuun
