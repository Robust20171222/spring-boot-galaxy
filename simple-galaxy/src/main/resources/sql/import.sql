--
-- Sample dataset containing a number of Hotels in various Cities across the world.  The reviews are entirely fictional :)
--

-- =================================================================================================
-- AUSTRALIA

-- Brisbane
INSERT INTO city (id, country, name, state, map)
VALUES (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (1, 1, 'Conrad Treasury Place', 'William & George Streets', '4001')

-- Melbourne
INSERT INTO city (id, country, name, state, map)
VALUES (2, 'Australia', 'Melbourne', 'Victoria', '-37.813187, 144.96298')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (2, 2, 'The Langham', '1 Southgate Ave, Southbank', '3006')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (1, 2, 0, '2005-05-10', 2, 4, 'Pretty average', 'I stayed in 2005, the hotel was nice enough but nothing special.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (2, 2, 1, '2006-01-12', 4, 2, 'Bright hotel with big rooms',
   'This hotel has a fantastic lovely big windows.  The room we stayed in had lots of space.  Recommended.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (3, 2, 2, '2006-05-25', 3, 1, 'Pretty good', 'I liked this hotel and would stay again.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (4, 2, 3, '2009-01-20', 3, 2, 'Nice clean rooms',
   'The rooms are maintained to a high standard and very clean, the bathroom was spotless!!')

-- Sydney
INSERT INTO city (id, country, name, state, map)
VALUES (3, 'Australia', 'Sydney', 'New South Wales', '-33.868901, 151.207091')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (3, 3, 'Swissotel', '68 Market Street', '2000')


-- =================================================================================================
-- CANADA

-- Montreal
INSERT INTO city (id, country, name, state, map) VALUES (4, 'Canada', 'Montreal', 'Quebec', '45.508889, -73.554167')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (4, 4, 'Ritz Carlton', '1228 Sherbrooke St', 'H3G1H6')


-- =================================================================================================
-- ISRAEL

-- Tel Aviv
INSERT INTO city (id, country, name, state, map) VALUES (5, 'Israel', 'Tel Aviv', '', '32.066157, 34.777821')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (5, 5, 'Hilton Tel Aviv', 'Independence Park', '63405')


-- =================================================================================================
-- JAPAN

-- Tokyo
INSERT INTO city (id, country, name, state, map) VALUES (6, 'Japan', 'Tokyo', '', '35.689488, 139.691706')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (6, 6, 'InterContinental Tokyo Bay', 'Takeshiba Pier', '105')


-- =================================================================================================
-- SPAIN

-- Barcelona
INSERT INTO city (id, country, name, state, map) VALUES (7, 'Spain', 'Barcelona', 'Catalunya', '41.387917, 2.169919')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (7, 7, 'Hilton Diagonal Mar', 'Passeig del Taulat 262-264', '08019')

-- =================================================================================================
-- SWITZERLAND

-- Neuchatel
INSERT INTO city (id, country, name, state, map) VALUES (8, 'Switzerland', 'Neuchatel', '', '46.992979, 6.931933')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (8, 8, 'Hotel Beaulac', ' Esplanade Leopold-Robert 2', '2000')


-- =================================================================================================
-- UNITED KINGDOM

-- Bath
INSERT INTO city (id, country, name, state, map) VALUES (9, 'UK', 'Bath', 'Somerset', '51.381428, -2.357454')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (9, 9, 'The Bath Priory Hotel', 'Weston Road', 'BA1 2XT')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (5, 9, 0, '2000-01-23', 4, 1, 'A lovely hotel',
   'We stayed here after a wedding and it was fantastic.  Recommend to all.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (6, 9, 1, '2000-08-04', 3, 1, 'Very special', 'A very special hotel with lovely staff.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (7, 9, 2, '2001-01-01', 2, 4, 'Nice but too hot',
   'Stayed during the summer heat wave (exceptional for England!) and the room was very hot.  Still recommended.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (8, 9, 3, '2002-01-20', 3, 1, 'Big rooms and a great view',
   'Considering how central this hotel is the rooms are a very good size.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (9, 9, 4, '2002-11-03', 2, 1, 'Good but pricey', 'A nice hotel but be prepared to pay over the odds for your stay.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (10, 9, 5, '2003-09-18', 4, 1, 'Fantastic place', 'Just lovely.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (11, 9, 6, '2004-03-21', 4, 3, 'A very special place',
   'I stayed here in 2004 and found it to be very relaxing, a nice pool and good gym is cherry on the cake.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (12, 9, 7, '2004-04-10', 0, 0, 'Terrible',
   'I complained after I was told I could not check out after 11pm.  Ridiculous!!!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (13, 9, 8, '2004-12-20', 4, 4, 'A perfect location',
   'Central location makes this a perfect hotel.  Be warned though, it''s not cheap.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (14, 9, 9, '2005-04-19', 3, 2, 'Expensive but worth it',
   'Dig deep into your pockets and enjoy this lovely City and fantastic hotel.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (15, 9, 10, '2005-05-21', 4, 1, 'The best hotel in the area', 'Top hotel in the area, would not stay anywhere else.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (16, 9, 11, '2005-11-17', 4, 2, 'Lovely hotel, fantastic grounds',
   'The garden upkeep run into thousands (perhaps explaining why the rooms are so much) but so lovely and relaxing.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (17, 9, 12, '2006-01-04', 3, 4, 'Gorgeous Top Quality Hotel', 'Top draw stuff.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (18, 9, 13, '2006-01-21', 4, 1, 'Fabulous Hotel and Restaurant',
   'The food at this hotel is second to none, try the peppered steak!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (19, 9, 14, '2006-01-29', 4, 4, 'Feels like home', 'A lovely home away from home.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (20, 9, 15, '2006-03-21', 1, 1, 'Far too expensive', 'Overpriced, Overpriced, Overpriced!!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (21, 9, 16, '2006-05-10', 4, 1, 'Excellent Hotel, Wonderful Staff',
   'The staff went out of their way to help us after we missed our last train home, organising a Taxi back to Newport even after we had checked out.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (22, 9, 17, '2007-09-11', 3, 2, 'The perfect retreat', 'If you want a relaxing stay, this is the place.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (23, 9, 18, '2008-06-01', 3, 3, 'Lovely stay, fantastic staff',
   'As other reviews have noted, the staff in this hotel really are the best in Bath.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (24, 9, 19, '2009-05-14', 4, 2, 'Can''t Wait to go back', 'We will stay again for sure.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (25, 9, 20, '2010-04-26', 4, 1, 'Amazing Hotel',
   'We won a trip here after entering a competition.  Not sure we would pay the full price but such a wonderful place.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (26, 9, 21, '2010-10-26', 2, 2, 'Dissapointed',
   'The pool was closed, the chief was ill, the staff were rude my wallet is bruised!')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (10, 9, 'Bath Travelodge', 'Rossiter Road, Widcombe Basin', 'BA2 4JP')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (27, 10, 0, '2002-08-21', 0, 2, 'Terrible hotel', 'One of the worst hotels that I have ever stayed in.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (28, 10, 1, '2003-01-28', 0, 0, 'Rude and unpleasant staff',
   'The staff refused to help me with any aspect of my stay, I will not stay here again.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (29, 10, 2, '2004-06-17', 1, 0, 'Below par', 'Don''t stay here!!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (30, 10, 3, '2005-07-12', 0, 1, 'Small and Unpleasant',
   'The room was far too small and felt unclean.  Not recommended.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (31, 10, 4, '2006-01-07', 1, 4, 'Cheap if you are not fussy',
   'This hotel has some rough edges but I challenge you to find somewhere cheaper.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (32, 10, 5, '2006-01-13', 0, 2, 'Terrible', 'Just terrible!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (33, 10, 6, '2006-03-25', 0, 0, 'Smelly and dirty room',
   'My room smelt of damp and I found the socks of the previous occupant under my bed.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (34, 10, 7, '2006-04-09', 0, 4, 'Grim', 'Grim.  I would try elsewhere before staying here.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (35, 10, 8, '2006-08-01', 1, 3, 'Very Noisy', 'Building work during the day and a disco at night.  Good grief!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (36, 10, 9, '2009-01-03', 1, 4, 'Tired and falling down',
   'This hotel is in serious need of refurbishment, the windows are rotting, the paintwork is tired and the carpets are from the 1970s.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (37, 10, 10, '2009-07-20', 0, 0, 'Not suitable for human habitation', 'I would not put my dog up in this hotel.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (38, 10, 11, '2010-05-20', 1, 0, 'Conveient for the railway', 'Average place but useful if you need to commute')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (39, 10, 12, '2010-01-22', 2, 2, 'Not as bad as the reviews',
   'Some of the reviews seem a bit harsh, it''s not too bad for the price.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (40, 10, 13, '2011-01-10', 3, 1, 'Reburished and nice',
   'Looks like this hotel has had a major facelift.  If you have stayed before 2011 perhaps it''s time to give this hotel another try.  Very good value for money and pretty nice.')

-- London
INSERT INTO city (id, country, name, state, map) VALUES (10, 'UK', 'London', '', '51.500152, -0.126236')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (11, 10, 'Melia White House', 'Albany Street', 'NW1 3UP')

-- Southampton
INSERT INTO city (id, country, name, state, map) VALUES (11, 'UK', 'Southampton', 'Hampshire', '50.902571, -1.397238')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (12, 11, 'Chilworth Manor', 'The Cottage, Southampton Business Park', 'SO16 7JF')


-- =================================================================================================
-- USA

-- Atlanta
INSERT INTO city (id, country, name, state, map) VALUES (12, 'USA', 'Atlanta', 'GA', '33.748995, -84.387982')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (13, 12, 'Marriott Courtyard', 'Tower Place, Buckhead', '30305')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (41, 13, 0, '2009-01-20', 3, 0, 'Better than most',
   'Most other hotels is this area are a bit ropey, this one is actually pretty good.')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (14, 12, 'Ritz Carlton', 'Peachtree Rd, Buckhead', '30326')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (15, 12, 'Doubletree', 'Tower Place, Buckhead', '30305')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (42, 15, 0, '2006-01-12', 2, 3, 'No fuss hotel',
   'Cheap, no fuss hotel.  Good if you are travelling on business and just need a place to stay.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (43, 15, 1, '2009-01-20', 2, 2, 'Nice area but small rooms',
   'The area felt nice and safe but the rooms are a little on the small side')

-- Chicago
INSERT INTO city (id, country, name, state, map) VALUES (13, 'USA', 'Chicago', 'IL', '41.878114, -87.629798')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (16, 13, 'Hotel Allegro', '171 West Randolph Street', '60601')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (44, 16, 0, '2009-12-15', 3, 2, 'Cheap and Recommended', 'Good value for money, can''t really fault it.')

-- Eau Claire
INSERT INTO city (id, country, name, state, map) VALUES (14, 'USA', 'Eau Claire', 'WI', '44.811349, -91.498494')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (17, 14, 'Sea Horse Inn', '2106 N Clairemont Ave', '54703')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (18, 14, 'Super 8 Eau Claire Campus Area', '1151 W Macarthur Ave', '54701')

-- Hollywood
INSERT INTO city (id, country, name, state, map) VALUES (15, 'USA', 'Hollywood', 'FL', '26.011201, -80.14949')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (19, 15, 'Westin Diplomat', '3555 S. Ocean Drive', '33019')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (45, 19, 0, '2006-01-11', 0, 0, 'Avoid', 'The hotel has a very bad reputation.  I would avoid it if I were you.')

-- Miami
INSERT INTO city (id, country, name, state, map) VALUES (16, 'USA', 'Miami', 'FL', '25.788969, -80.226439')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (20, 16, 'Conrad Miami', '1395 Brickell Ave', '33131')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (46, 20, 0, '2010-01-09', 3, 2, 'Close to the local attractions',
   'Fantastic access to all the local attractions mean you won''t mind the small rooms.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (47, 20, 1, '2010-09-10', 2, 2, 'Good value and friendly',
   'Not expensive and very welcoming staff. I would stay again.')

-- Melbourne
INSERT INTO city (id, country, name, state, map) VALUES (17, 'USA', 'Melbourne', 'FL', '28.083627, -80.608109')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (21, 17, 'Radisson Suite Hotel Oceanfront', '3101 North Hwy', '32903')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (48, 21, 0, '2005-06-15', 3, 3, 'A very nice hotel',
   'I can''t fault this hotel and I have stayed here many times.  Always friendly staff and lovely atmosphere.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (49, 21, 1, '2006-01-20', 2, 4, 'Comfortable and good value', 'To complaints at all.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (50, 21, 2, '2007-08-21', 3, 1, 'Above average', 'Better than a lot of hotels in the area and not too pricey.')

-- New York
INSERT INTO city (id, country, name, state, map) VALUES (18, 'USA', 'New York', 'NY', '40.714353, -74.005973')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (22, 18, 'W Union Hotel', 'Union Square, Manhattan', '10011')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (51, 22, 0, '2002-01-19', 0, 1, 'Too noisy, too small',
   'The city never sleeps and neither will you if you say here.  The rooms are small and the sound insulation is poor!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (52, 22, 1, '2004-03-10', 1, 4, 'Overpriced', 'Far too much money for such a tiny room!')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (53, 22, 2, '2007-04-11', 2, 0, 'So so, nothing special', 'Not brilliant but not too bad either.')
INSERT INTO hotel (id, city_id, name, address, zip)
VALUES (23, 18, 'W Lexington Hotel', 'Lexington Ave, Manhattan', '10011')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (54, 23, 0, '2004-07-21', 3, 2, 'Excellent location', 'So close to the heart of the city.  Recommended.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (55, 23, 1, '2006-05-20', 3, 1, 'Very nice', 'I can''t fault this hotel, clean, good location and nice staff.')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (24, 18, '70 Park Avenue Hotel', '70 Park Avenue', '10011')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (56, 24, 0, '2003-11-10', 4, 1, 'Great!!', 'I own this hotel and I think it is pretty darn good.')

-- Palm Bay
INSERT INTO city (id, country, name, state, map) VALUES (19, 'USA', 'Palm Bay', 'FL', '28.034462, -80.588665')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (25, 19, 'Jameson Inn', '890 Palm Bay Rd NE', '32905')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (57, 25, 0, '2005-10-20', 3, 2, 'Fantastical', 'This is the BEST hotel in Palm Bay, not complaints at all.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (58, 25, 1, '2006-01-12', 4, 1, 'Top marks', 'I rate this hotel 5 stars, the best in the area by miles.')

-- San Francisco
INSERT INTO city (id, country, name, state, map) VALUES (20, 'USA', 'San Francisco', 'CA', '37.77493, -122.419415')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (26, 20, 'Marriot Downtown', '55 Fourth Street', '94103')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (59, 26, 0, '2006-07-02', 2, 3, 'Could be better',
   'I stayed in late 2006 with work, the room was very small and the restaurant does not stay open very late.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details)
VALUES (60, 26, 1, '2008-07-01', 1, 4, 'Brrrr cold!', 'My room was freezing cold, I would not recommend this place.')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (61, 26, 2, '2009-01-05', 3, 2, 'Nice for money',
   'You can''t really go wrong here for the money.  There may be better places to stay but not for this price.')

-- Washington
INSERT INTO city (id, country, name, state, map) VALUES (21, 'USA', 'Washington', 'DC', '38.895112, -77.036366')
INSERT INTO hotel (id, city_id, name, address, zip) VALUES (27, 21, 'Hotel Rouge', '1315 16th Street NW', '20036')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (62, 27, 0, '2000-01-29', 0, 2, 'Never again',
   'I will never ever stay here again!!  They wanted extra cash to get fresh batteries for the TV remote')
INSERT INTO review (id, hotel_id, idx, check_in_date, rating, trip_type, title, details) VALUES
  (63, 27, 1, '2006-02-20', 0, 0, 'Avoid',
   'This place is the pits, they charged us twice for a single night stay.  I only got refunded after contacting my credit card company.')