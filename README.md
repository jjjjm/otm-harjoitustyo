# OTM/K18
## TDD
Eli tornipuolustuspeli
### Dokumentaatiot
[Tuntikirjanpito](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)  
[Vaatimusmäärittely](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  
[Arkkitehtuuri](https://github.com/jjjjm/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
### Komentorivitoiminnot
#### Testaus  
Testit voi suorittaa kommennolla   
  
`mvn test`  
  
Testikattavuuden saa luotua komennolla   
  
`mvn jacoco:report`  
  
Kattavuus on nähtävissä raportissa _target/site/jacoco/index.html_  
  
### Checkstyle
Tiedosto [checkstyle.xml](https://github.com/jjjjm/otm-harjoitustyo/blob/master/TDD/checkstyle.xml) määrittelee tyylitarkistukset ja raportti voidaan koostaa komennolla  
  
`mvn jxr:jxr checkstyle:checkstyle`  
  
Raportti voidaan nähdä tiedostossa _target/site/checkstyle.html_  
  
## Suoritettavan jarin generointi  
Komento  
  
`mvn package`  
  
generoi suoritettavan jar tiedoston _target_ hakemistoon
