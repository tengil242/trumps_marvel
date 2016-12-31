# trumps_marvel

## Purpose
Get a TRUMPS CARD GAME digitally with more and up to date cards.
Present the cards for the players graphically (via webui?).
Card data is scraped mainly from marvel.com (eventually addional data from http://comicvine.gamespot.com)

##Function
Will retrieve "all" superheroes / supervillains from marvel.com
Cards will be stored locally with all data per character and an image file
See TODO section for additional functions.

Part1 Is a separate script that prepares and collects data from inet. Files are stored as YAML+image 4mat.
Part2 is the UI front end. Possibly as a webui for convenience.

## Setup dependencies (OS X) Python
 - sudo easy_install pip
 - sudo pip install pyyaml
 - sudo pip install requests
 - sudo pip install bs4
 - sudo pip install pandas
 - sudo xcodebuild -license
 - sudo pip install lxml

## Setup deps (Java)
 - Java8 (latest)
 - Maven3.3.9 (latest 3.x)
 - Karaf 4.0.8 (latest 4.0.x)

## Setup dependencies Karaf
 - NOTE! Bug in 4.0.8 preventing xsd?! : https://issues.apache.org/jira/browse/ARIES-1540
 - feature:install webconsole
 - feature:repo-add cxf 3.0.6
 - feature:install cxf

## Personal notes
 - Initally using http://olivier-rozier.developpez.com/tutoriels/rest/restful-webservice-karaf-eclipse/

---
# TODO

Also, add Star Wars characters / ships
Also, add cars
Hmm, see this: http://cdon.se/spel/spel-downloads/top-trumps-turbo-33245877


See updated issue list at Github :: https://github.com/tengil242/trumps_marvel/issues
