# TrainFixer Project

## Gruppemedlemmer
- EK-id: jakj0001

## Kørsel af tests
1. Clone projektet fra GitHub.
2. Kør tests med maven i TrainFixer_v2 root mappen:
```
$ mvn test
```
3. Test kan også køres med maven i intellij. 


## Køretidskompleksitet

- addWagon: O(1), fordi vi altid tilføjer til slutningen af listen via tail.

- validate: O(n), hvor n er antallet af vogne, da vi tjekker hver vogn i rækkefølge.

- fix: O(n), da vi gennemgår alle vogne og omarrangerer dem.


## Antal gennemløb i fix funktionen

1. Første gennemløb: Tæller antallet af hver vogn (1 gennemløb).
2. Oprettelse af nyt tog med de samme vogne i ny rækkefølge (1 gennemløb for at tilføje hver vogn).
3. I alt: 2 gennemløb (et for at tælle, et for at opbygge det nye tog).


## Fordele og ulemper ved antal gennemløb

### Ét gennemløb
**Fordele:**
- Hurtigere i praksis, da vi kun traverserer listen én gang.
- Mindre CPU-brug og lavere tidskompleksitet per operation.
- Mindre midlertidigt lager, da man kan opdatere/omarrangere direkte under ét gennemløb.

**Ulemper:**
- Koden bliver ofte mere kompleks, fordi man skal håndtere både tælling og omarrangering i samme gennemløb.
- Risiko for fejl, hvis man overskrider rækkefølgen eller manipulerer listen forkert.
- Sværere at læse og vedligeholde.


### Flere gennemløb (fx 2 gennemløb i fix-funktion)
**Fordele:**
- Koden bliver enklere og mere overskuelig.
- Lettere at teste og debugge, da hvert gennemløb har en klar og enkelt funktion (tælle vs. opbygge nyt tog).
- Mindre risiko for logiske fejl, da vi ikke blander operationer.

**Ulemper:**
- Traverserer listen flere gange, hvilket kan være langsommere for meget store tog.
- Lidt højere CPU-brug og eventuelt lidt mere hukommelse, hvis man skal opbevare midlertidige data.