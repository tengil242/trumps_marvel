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

## Setup dependencies (OS X)
 - sudo easy_install pip
 - sudo pip install pyyaml
 - sudo pip install requests
 - sudo pip install bs4
 - sudo pip install pandas
 - sudo xcodebuild -license
 - sudo pip install lxml


---
# TODO

Also, add Star Wars characters / ships
Also, add cars
Hmm, see this: http://cdon.se/spel/spel-downloads/top-trumps-turbo-33245877


See updated issue list at Github :: https://github.com/tengil242/trumps_marvel/issues
