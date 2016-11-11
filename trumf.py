#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import yaml
import random
import os.path

number_of_players = 2
card_root = "./cards"

number_of_total_cards = len([f for f in os.listdir(card_root)
                if os.path.isfile(os.path.join(card_root, f))])

drawn_card = random.randint(1,number_of_total_cards)

fn = card_root + "/" + "card" + '{:03d}'.format(drawn_card) + ".yaml"
### print "DBG: Filename: %s" % fn

stream = file(fn, 'r')
card_data = yaml.load(stream)
#print yaml.dump(card_data)
print "Character: %s" % card_data['character']['name']
