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

## Setup dependencies
 - sudo easy_install pip
 - sudo pip install pyyaml
 - sudo pip install requests
 - sudo pip install bs4
 - sudo pip install pandas
 - sudo xcodebuild -license
 - sudo pip install lxml


---
# TODO

- [ ] TODO: Create YAML file per card
- [ ] TODO: Add error handling and default values. Like Aardwolf is missing hair attributes
- [ ] TODO: Add more info from marvel.com like Powers, Education, Occupation
- [ ] TODO: Stretch power attributes from 0 - 100 instead of 0-7
- [ ] TODO: Consider naming of cards. Pad with like six digits? like card000242.(jpg|yaml)
- [ ] TODO: Not sure if all images are in jpg format, .jpg extension is hardcoded to the path...
- [ ] TODO: Web interface, store player information with cookies on client side?
- [ ] TODO: What to do if marvel.com changes?
- [ ] TODO: Review the scraping of A-Z pages / mediawiki URLs ... Good enough?
- [ ] TODO: Player end needs to be implemented.
- [ ] TODO: Consider google translate / localization ... Height / Weight / Bio
- [ ] TODO: Make a "Mark for review" button! Galactus have full score. Remove this card?
- [ ] TODO: Consider scraping from http://comicvine.gamespot.com
