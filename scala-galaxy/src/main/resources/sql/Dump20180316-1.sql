CREATE DATABASE IF NOT EXISTS `galaxy` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `galaxy`;
-- MySQL dump 10.13  Distrib 5.7.16, for osx10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: galaxy
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45)      DEFAULT NULL,
  `name`    VARCHAR(45)      DEFAULT NULL,
  `state`   VARCHAR(45)      DEFAULT NULL,
  `map`     VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city`
  DISABLE KEYS */;
INSERT INTO `city` VALUES (1, 'Australia', 'Brisbane', 'Queensland', '-27.470933, 153.023502'),
  (2, 'Australia', 'Melbourne', 'Victoria', '-37.813187, 144.96298'),
  (3, 'Australia', 'Sydney', 'New South Wales', '-33.868901, 151.207091'),
  (4, 'Canada', 'Montreal', 'Quebec', '45.508889, -73.554167'), (5, 'Israel', 'Tel Aviv', '', '32.066157, 34.777821'),
  (6, 'Japan', 'Tokyo', '', '35.689488, 139.691706'), (7, 'Spain', 'Barcelona', 'Catalunya', '41.387917, 2.169919'),
  (8, 'Switzerland', 'Neuchatel', '', '46.992979, 6.931933'), (9, 'UK', 'Bath', 'Somerset', '51.381428, -2.357454'),
  (10, 'UK', 'London', '', '51.500152, -0.126236'), (11, 'UK', 'Southampton', 'Hampshire', '50.902571, -1.397238'),
  (12, 'USA', 'Atlanta', 'GA', '33.748995, -84.387982'), (13, 'USA', 'Chicago', 'IL', '41.878114, -87.629798'),
  (14, 'USA', 'Eau Claire', 'WI', '44.811349, -91.498494'), (15, 'USA', 'Hollywood', 'FL', '26.011201, -80.14949'),
  (16, 'USA', 'Miami', 'FL', '25.788969, -80.226439'), (17, 'USA', 'Melbourne', 'FL', '28.083627, -80.608109'),
  (18, 'USA', 'New York', 'NY', '40.714353, -74.005973'), (19, 'USA', 'Palm Bay', 'FL', '28.034462, -80.588665'),
  (20, 'USA', 'San Francisco', 'CA', '37.77493, -122.419415'), (21, 'USA', 'Washington', 'DC', '38.895112, -77.036366');
/*!40000 ALTER TABLE `city`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `city_id` INT(11)          DEFAULT NULL,
  `name`    VARCHAR(45)      DEFAULT NULL,
  `address` VARCHAR(45)      DEFAULT NULL,
  `zip`     VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel`
  DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1, 1, 'Conrad Treasury Place', 'William & George Streets', '4001'),
  (2, 2, 'The Langham', '1 Southgate Ave, Southbank', '3006'), (3, 3, 'Swissotel', '68 Market Street', '2000'),
  (4, 4, 'Ritz Carlton', '1228 Sherbrooke St', 'H3G1H6'), (5, 5, 'Hilton Tel Aviv', 'Independence Park', '63405'),
  (6, 6, 'InterContinental Tokyo Bay', 'Takeshiba Pier', '105'),
  (7, 7, 'Hilton Diagonal Mar', 'Passeig del Taulat 262-264', '08019'),
  (8, 8, 'Hotel Beaulac', ' Esplanade Leopold-Robert 2', '2000'),
  (9, 9, 'The Bath Priory Hotel', 'Weston Road', 'BA1 2XT'),
  (10, 9, 'Bath Travelodge', 'Rossiter Road, Widcombe Basin', 'BA2 4JP'),
  (11, 10, 'Melia White House', 'Albany Street', 'NW1 3UP'),
  (12, 11, 'Chilworth Manor', 'The Cottage, Southampton Business Park', 'SO16 7JF'),
  (13, 12, 'Marriott Courtyard', 'Tower Place, Buckhead', '30305'),
  (14, 12, 'Ritz Carlton', 'Peachtree Rd, Buckhead', '30326'), (15, 12, 'Doubletree', 'Tower Place, Buckhead', '30305'),
  (16, 13, 'Hotel Allegro', '171 West Randolph Street', '60601'),
  (17, 14, 'Sea Horse Inn', '2106 N Clairemont Ave', '54703'),
  (18, 14, 'Super 8 Eau Claire Campus Area', '1151 W Macarthur Ave', '54701'),
  (19, 15, 'Westin Diplomat', '3555 S. Ocean Drive', '33019'), (20, 16, 'Conrad Miami', '1395 Brickell Ave', '33131'),
  (21, 17, 'Radisson Suite Hotel Oceanfront', '3101 North Hwy', '32903'),
  (22, 18, 'W Union Hotel', 'Union Square, Manhattan', '10011'),
  (23, 18, 'W Lexington Hotel', 'Lexington Ave, Manhattan', '10011'),
  (24, 18, '70 Park Avenue Hotel', '70 Park Avenue', '10011'), (25, 19, 'Jameson Inn', '890 Palm Bay Rd NE', '32905'),
  (26, 20, 'Marriot Downtown', '55 Fourth Street', '94103'), (27, 21, 'Hotel Rouge', '1315 16th Street NW', '20036');
/*!40000 ALTER TABLE `hotel`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id`            INT(11) NOT NULL,
  `hotel_id`      INT(11)      DEFAULT NULL,
  `idx`           INT(11)      DEFAULT NULL,
  `check_in_date` VARCHAR(45)  DEFAULT NULL,
  `rating`        INT(11)      DEFAULT NULL,
  `trip_type`     INT(11)      DEFAULT NULL,
  `title`         VARCHAR(45)  DEFAULT NULL,
  `details`       VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review`
  DISABLE KEYS */;
INSERT INTO `review` VALUES
  (1, 2, 0, '2005-05-10', 2, 4, 'Pretty average', 'I stayed in 2005, the hotel was nice enough but nothing special.'),
  (2, 2, 1, '2006-01-12', 4, 2, 'Bright hotel with big rooms', 'This hotel has a fantastic lovely big windows.  The room we stayed in had lots of space.  Recommended.'),
  (3, 2, 2, '2006-05-25', 3, 1, 'Pretty good', 'I liked this hotel and would stay again.'),
  (4, 2, 3, '2009-01-20', 3, 2, 'Nice clean rooms', 'The rooms are maintained to a high standard and very clean, the bathroom was spotless!!'),
  (5, 9, 0, '2000-01-23', 4, 1, 'A lovely hotel', 'We stayed here after a wedding and it was fantastic.  Recommend to all.'),
  (6, 9, 1, '2000-08-04', 3, 1, 'Very special', 'A very special hotel with lovely staff.'),
  (7, 9, 2, '2001-01-01', 2, 4, 'Nice but too hot', 'Stayed during the summer heat wave (exceptional for England!) and the room was very hot.  Still recommended.'),
  (8, 9, 3, '2002-01-20', 3, 1, 'Big rooms and a great view', 'Considering how central this hotel is the rooms are a very good size.'),
  (9, 9, 4, '2002-11-03', 2, 1, 'Good but pricey', 'A nice hotel but be prepared to pay over the odds for your stay.'),
  (10, 9, 5, '2003-09-18', 4, 1, 'Fantastic place', 'Just lovely.'),
  (11, 9, 6, '2004-03-21', 4, 3, 'A very special place', 'I stayed here in 2004 and found it to be very relaxing, a nice pool and good gym is cherry on the cake.'),
  (12, 9, 7, '2004-04-10', 0, 0, 'Terrible', 'I complained after I was told I could not check out after 11pm.  Ridiculous!!!'),
  (13, 9, 8, '2004-12-20', 4, 4, 'A perfect location', 'Central location makes this a perfect hotel.  Be warned though, it\'s not cheap.'),
  (14, 9, 9, '2005-04-19', 3, 2, 'Expensive but worth it', 'Dig deep into your pockets and enjoy this lovely City and fantastic hotel.'),
  (15, 9, 10, '2005-05-21', 4, 1, 'The best hotel in the area', 'Top hotel in the area, would not stay anywhere else.'),
  (16, 9, 11, '2005-11-17', 4, 2, 'Lovely hotel, fantastic grounds', 'The garden upkeep run into thousands (perhaps explaining why the rooms are so much) but so lovely and relaxing.'),
  (17, 9, 12, '2006-01-04', 3, 4, 'Gorgeous Top Quality Hotel', 'Top draw stuff.'),
  (18, 9, 13, '2006-01-21', 4, 1, 'Fabulous Hotel and Restaurant', 'The food at this hotel is second to none, try the peppered steak!'),
  (19, 9, 14, '2006-01-29', 4, 4, 'Feels like home', 'A lovely home away from home.'),
  (20, 9, 15, '2006-03-21', 1, 1, 'Far too expensive', 'Overpriced, Overpriced, Overpriced!!'),
  (21, 9, 16, '2006-05-10', 4, 1, 'Excellent Hotel, Wonderful Staff', 'The staff went out of their way to help us after we missed our last train home, organising a Taxi back to Newport even after we had checked out.'),
  (22, 9, 17, '2007-09-11', 3, 2, 'The perfect retreat', 'If you want a relaxing stay, this is the place.'),
  (23, 9, 18, '2008-06-01', 3, 3, 'Lovely stay, fantastic staff', 'As other reviews have noted, the staff in this hotel really are the best in Bath.'),
  (24, 9, 19, '2009-05-14', 4, 2, 'Can\'t Wait to go back', 'We will stay again for sure.'),
  (25, 9, 20, '2010-04-26', 4, 1, 'Amazing Hotel', 'We won a trip here after entering a competition.  Not sure we would pay the full price but such a wonderful place.'),
  (26, 9, 21, '2010-10-26', 2, 2, 'Dissapointed', 'The pool was closed, the chief was ill, the staff were rude my wallet is bruised!'),
  (27, 10, 0, '2002-08-21', 0, 2, 'Terrible hotel', 'One of the worst hotels that I have ever stayed in.'),
  (28, 10, 1, '2003-01-28', 0, 0, 'Rude and unpleasant staff', 'The staff refused to help me with any aspect of my stay, I will not stay here again.'),
  (29, 10, 2, '2004-06-17', 1, 0, 'Below par', 'Don\'t stay here!!'),
  (30, 10, 3, '2005-07-12', 0, 1, 'Small and Unpleasant', 'The room was far too small and felt unclean.  Not recommended.'),
  (31, 10, 4, '2006-01-07', 1, 4, 'Cheap if you are not fussy', 'This hotel has some rough edges but I challenge you to find somewhere cheaper.'),
  (32, 10, 5, '2006-01-13', 0, 2, 'Terrible', 'Just terrible!'),
  (33, 10, 6, '2006-03-25', 0, 0, 'Smelly and dirty room', 'My room smelt of damp and I found the socks of the previous occupant under my bed.'),
  (34, 10, 7, '2006-04-09', 0, 4, 'Grim', 'Grim.  I would try elsewhere before staying here.'),
  (35, 10, 8, '2006-08-01', 1, 3, 'Very Noisy', 'Building work during the day and a disco at night.  Good grief!'),
  (36, 10, 9, '2009-01-03', 1, 4, 'Tired and falling down', 'This hotel is in serious need of refurbishment, the windows are rotting, the paintwork is tired and the carpets are from the 1970s.'),
  (37, 10, 10, '2009-07-20', 0, 0, 'Not suitable for human habitation', 'I would not put my dog up in this hotel.'),
  (38, 10, 11, '2010-05-20', 1, 0, 'Conveient for the railway', 'Average place but useful if you need to commute'),
  (39, 10, 12, '2010-01-22', 2, 2, 'Not as bad as the reviews', 'Some of the reviews seem a bit harsh, it\'s not too bad for the price.'),
  (40, 10, 13, '2011-01-10', 3, 1, 'Reburished and nice', 'Looks like this hotel has had a major facelift.  If you have stayed before 2011 perhaps it\'s time to give this hotel another try.  Very good value for money and pretty nice.'),
  (41, 13, 0, '2009-01-20', 3, 0, 'Better than most', 'Most other hotels is this area are a bit ropey, this one is actually pretty good.'),
  (42, 15, 0, '2006-01-12', 2, 3, 'No fuss hotel', 'Cheap, no fuss hotel.  Good if you are travelling on business and just need a place to stay.'),
  (43, 15, 1, '2009-01-20', 2, 2, 'Nice area but small rooms', 'The area felt nice and safe but the rooms are a little on the small side'),
  (44, 16, 0, '2009-12-15', 3, 2, 'Cheap and Recommended', 'Good value for money, can\'t really fault it.'),
  (45, 19, 0, '2006-01-11', 0, 0, 'Avoid', 'The hotel has a very bad reputation.  I would avoid it if I were you.'),
  (46, 20, 0, '2010-01-09', 3, 2, 'Close to the local attractions', 'Fantastic access to all the local attractions mean you won\'t mind the small rooms.'),
  (47, 20, 1, '2010-09-10', 2, 2, 'Good value and friendly', 'Not expensive and very welcoming staff. I would stay again.'),
  (48, 21, 0, '2005-06-15', 3, 3, 'A very nice hotel', 'I can\'t fault this hotel and I have stayed here many times.  Always friendly staff and lovely atmosphere.'),
  (49, 21, 1, '2006-01-20', 2, 4, 'Comfortable and good value', 'To complaints at all.'),
  (50, 21, 2, '2007-08-21', 3, 1, 'Above average', 'Better than a lot of hotels in the area and not too pricey.'),
  (51, 22, 0, '2002-01-19', 0, 1, 'Too noisy, too small', 'The city never sleeps and neither will you if you say here.  The rooms are small and the sound insulation is poor!'),
  (52, 22, 1, '2004-03-10', 1, 4, 'Overpriced', 'Far too much money for such a tiny room!'),
  (53, 22, 2, '2007-04-11', 2, 0, 'So so, nothing special', 'Not brilliant but not too bad either.'),
  (54, 23, 0, '2004-07-21', 3, 2, 'Excellent location', 'So close to the heart of the city.  Recommended.'),
  (55, 23, 1, '2006-05-20', 3, 1, 'Very nice', 'I can\'t fault this hotel, clean, good location and nice staff.'),
  (56, 24, 0, '2003-11-10', 4, 1, 'Great!!', 'I own this hotel and I think it is pretty darn good.'),
  (57, 25, 0, '2005-10-20', 3, 2, 'Fantastical', 'This is the BEST hotel in Palm Bay, not complaints at all.'),
  (58, 25, 1, '2006-01-12', 4, 1, 'Top marks', 'I rate this hotel 5 stars, the best in the area by miles.'),
  (59, 26, 0, '2006-07-02', 2, 3, 'Could be better', 'I stayed in late 2006 with work, the room was very small and the restaurant does not stay open very late.'),
  (60, 26, 1, '2008-07-01', 1, 4, 'Brrrr cold!', 'My room was freezing cold, I would not recommend this place.'),
  (61, 26, 2, '2009-01-05', 3, 2, 'Nice for money', 'You can\'t really go wrong here for the money.  There may be better places to stay but not for this price.'),
  (62, 27, 0, '2000-01-29', 0, 2, 'Never again',
   'I will never ever stay here again!!  They wanted extra cash to get fresh batteries for the TV remote'),
  (63, 27, 1, '2006-02-20', 0, 0, 'Avoid',
   'This place is the pits, they charged us twice for a single night stay.  I only got refunded after contacting my credit card company.');
/*!40000 ALTER TABLE `review`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-03-16 11:36:52
