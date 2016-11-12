#!/usr/bin/env python
# -*- coding: UTF-8 -*-

## Will retrieve "all" superheroes / supervillians from marvel.com
##  Cards will be stored locally with all data per character and an image file
##
##  Author: Ola TeNGiL Tenglin
##
##  Projects homepage : https://github.com/tengil242/trumps_marvel
##
## 0.0.1 2016-11-11 : First version seems to do the basics for generation
## 0.0.2 2016-11-12 : Creation of YAML files. Issue #1 - Github
## 0.0.3 2016-11-12 : Padding of filenumbering. Issue #5 - Github
##

import yaml
import random
import string
import pprint
import os
import re
import sys
import time

from bs4 import BeautifulSoup
import urllib

base_url = 'http://marvel.com'
toc_url = base_url + '/universe3zx/index.php?title=Category:People&from='
card_root = "./cards/generated"
VERBOSE_MODE = False


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

		if VERBOSE_MODE:
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

		current_time = time.strftime("%Y-%m-%d %H:%M")

		card = dict(
    		deck = 'Marvel Universe',
    		modified = current_time,
    		character = dict(
        		name = entry.a['title'],
				real_name = card_data_real_name,
				identity = card_data_identity,
				citizenship = card_data_citizenship,
				place_of_birth = card_data_placeofbirth,
				first_apperance = card_data_firstapparance,
				first_apperance_year = card_data_firstapparance_year,
				origin = card_data_origin,
				height = card_data_height,
				weight = card_data_weight,
				eye_color = card_data_eyecolor,
				hair_color = card_data_haircolor,
				weapons = "0",
        		bio = div2_card.get_text(),
        		character_type = 'zum',
    			),
			attributes = dict(
				movies = "0",
				series = "0",
				issues = "0",
				arch_enemies = "0",
			),
			powers = dict(
  				durability = card_data_durability,
  				energy_projection = card_data_energy,
  				fighting_skills = card_data_fighting,
  				intelligence = card_data_intelligence,
  				speed = card_data_speed,
  				strength = card_data_strength,
			),
			main_url = "http://marvel.com/universe/Spider-Man_(Peter_Parker)",
			additional_url = "http://comicvine.gamespot.com/spider-man/4005-1443/",
			image_url = "https://i.annihil.us/u/prod/marvel//universe3zx/images/f/f8/SpiderMan0001.jpg"
			)
		cntr='{0:06d}'.format(entry_cntr)
		fn_image = card_root + "/" + "card" + str(cntr) + ".jpg"
		print " Downloading image file of %s to %s" % (entry.a['title'],fn_image)
		urllib.urlretrieve (card_data_image_url, fn_image)
		fn_card = card_root + "/" + "card" + str(cntr) + ".yaml"
		print " Generating card of %s to %s" % (entry.a['title'],fn_card)

		with open(fn_card, 'w') as outfile:
			yaml.safe_dump(card, outfile, allow_unicode=True, default_flow_style=False)

		os._exit(1)


os._exit(1)
