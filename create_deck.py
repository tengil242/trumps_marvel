#!/usr/bin/env python
# -*- coding: UTF-8 -*-

## Will retrieve "all" superheroes / supervillians from marvel.com
##  Cards will be stored locally with all data per character and an image file
##
##  Author: Ola TeNGiL Tenglin
##
## 0.0.1 2016-11-11 : First version seems to do the basics for generation
##
##
## TODO: Create YAML file per card
## TODO: Add error handling and default values. Like Aardwolf is missing hair attributes
## TODO: Add more info from marvel.com like Powers, Education, Occupation
## TODO: Stretch power attributes from 0 - 100 instead of 0-7
## TODO: Consider naming of cards. Pad with like six digits? like card000242.(jpg|yaml)
## TODO: Not sure if all images are in jpg format, .jpg extension is hardcoded to the path...
## TODO: Web interface, store player information with cookies on client side?
## TODO: What to do if marvel.com changes?
## TODO: Review the scraping of A-Z pages / mediawiki URLs ... Good enough?
## TODO: Player end needs to be implemented.
## TODO: Consider google translate / localization ... Height / Weight / Bio
## TODO: Make a "Mark for review" button! Galactus have full score. Remove this card?
## 
import yaml
import random
import string
import pprint
import os
import re

from bs4 import BeautifulSoup
import urllib

base_url = 'http://marvel.com'
toc_url = base_url + '/universe3zx/index.php?title=Category:People&from='
card_root = "./cards/generated"


def calc_power_attrib(style):    # Raw CSS style value
	"""Convert CSS style value to a decent number"""
	power = "1"   # Default ...
	fuzzy = random.randint(0,9)
	if style == "width:21px;":
		power = "1"
	elif style == "width:42px;":
		power = "2"
	elif style == "width:63px;":
		power = "3"
	elif style == "width:84px;":
		power = "4"
	elif style == "width:105px;":
		power = "5"
	elif style == "width:126px;":
		power = "6"
	elif style == "width:146px;":
		power = "7"
	else:
		print "*** WARN: No power attribute mapped for %s !" % style

	return power + str(fuzzy)

alpha_string=list(string.ascii_uppercase)
alpha_string=['A']
for i in range(len(alpha_string)):
	url=toc_url + alpha_string[i]

	response = urllib.urlopen(url)
	soup = BeautifulSoup(response.read().decode('utf-8'), "lxml")
	div = soup('div',{'id':'mw-pages'})[0]

	entries = div.findAll('li')
	entry_cntr = 0
	for entry in entries:
		entry=entries[64]
		entry_cntr += 1
##		print "DBG ::: title=%s" % entry.a['title']
##		print "DBG ::: href=%s" % entry.a['href']
##		print "DBG ::: entry_cntr=%d" %entry_cntr
##		print "********"
		print "::: Found card %d : %s" % (entry_cntr, entry.a['title'])
		url_card = base_url + entry.a['href']
##		print "DBG ::: %s" % url_card
		response_card = urllib.urlopen(url_card)
		soup_card = BeautifulSoup(response_card.read().decode('utf-8'), "lxml")
		div1_card = soup_card('div',{'id':'powerbox'})[0]
		div2_card = soup_card('div',{'id':'biobody'})[0]

		div3_card = soup_card('div',{'class':'powergrid-row'})[1]
		card_data_durability_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_durability=calc_power_attrib(card_data_durability_raw)

		div3_card = soup_card('div',{'class':'powergrid-row'})[2]
		card_data_energy_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_energy=calc_power_attrib(card_data_energy_raw)

		div3_card = soup_card('div',{'class':'powergrid-row'})[3]
		card_data_fighting_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_fighting=calc_power_attrib(card_data_fighting_raw)

		div3_card = soup_card('div',{'class':'powergrid-row'})[4]
		card_data_intelligence_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_intelligence=calc_power_attrib(card_data_intelligence_raw)

		div3_card = soup_card('div',{'class':'powergrid-row'})[5]
		card_data_speed_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_speed=calc_power_attrib(card_data_speed_raw)

		div3_card = soup_card('div',{'class':'powergrid-row'})[6]
		card_data_strength_raw=div3_card('div',{'class':'p-right ratingvalue'})[0].attrs['style']
		card_data_strength=calc_power_attrib(card_data_strength_raw)

		div4_card = soup_card('div',{'id':'headshot'})[0]
		div4_card_img=div4_card('a',{'class':'image'})[0]
		card_data_image_url=div4_card_img('img')[0].attrs['src']

# Internal assistance while debugging. Things on the other end might change ...
##		print type(div1_card)
##		print div3_card_durability.prettify()
##		pp = pprint.PrettyPrinter()
##		pp.pprint(div3_card_durability)

		data1_entries = div1_card.findAll('p')
		card_data_real_name = data1_entries[1].get_text().replace("Real Name","").strip()
		card_data_identity = data1_entries[3].get_text().replace("Identity","").strip()
		card_data_citizenship = data1_entries[4].get_text().replace("Citizenship","").strip()
		card_data_placeofbirth = data1_entries[5].get_text().replace("Place of Birth","").strip()
		card_data_firstapparance = data1_entries[6].get_text().replace("First Appearance","").strip()
		card_data_firstapparance_year = card_data_firstapparance.strip()
		card_data_firstapparance_year = re.sub('.+\((\d\d\d\d)\).*','\\1', card_data_firstapparance_year.rstrip())
		card_data_origin = data1_entries[7].get_text().replace("Origin","").strip()
		card_data_height = data1_entries[8].get_text().replace("Height","").strip()
		card_data_weight = data1_entries[9].get_text().replace("Weight","").strip()
		card_data_eyecolor = data1_entries[10].get_text().replace("Eyes","").strip()
		card_data_haircolor = data1_entries[11].get_text().replace("Hair","").strip()

		print "  Real name : %s" % card_data_real_name
		print "  Identity : %s" % card_data_identity
		print "  Citizenship : %s" % card_data_citizenship
		print "  Place of Birth : %s" % card_data_placeofbirth
		print "  First Appearance : %s" % card_data_firstapparance
		print "  Year of first appearance : %s" % card_data_firstapparance_year
		print "  Origin : %s" % card_data_origin
		print "  Height : %s" % card_data_height
		print "  Weight : %s" % card_data_weight
		print "  Eye color : %s" % card_data_eyecolor
		print "  Hair : %s" % card_data_haircolor
		print "   Bio : %s" % div2_card.get_text()
		print "  Durability : %s" % card_data_durability
		print "  Energy Projection : %s" % card_data_energy

		print "  Fighting skills : %s" % card_data_fighting
		print "  Intelligence : %s" % card_data_intelligence
		print "  Speed : %s" % card_data_speed
		print "  Strength : %s" % card_data_strength

		local_fn = card_root + "/" + "card" + str(entry_cntr) + ".jpg"
		print " (Downloading image file to %s )" % local_fn
		urllib.urlretrieve (card_data_image_url, local_fn)

		os._exit(1)




	os._exit(1)
