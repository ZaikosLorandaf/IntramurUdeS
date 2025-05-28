SET search_path = intramurudes;

INSERT INTO sport(name) VALUES
        ('Ultimate'),
        ('Basketball'),
        ('Volley-ball');

INSERT INTO league (name, week_day, time_start, time_end, id_sport) VALUES
        ('Ligue A', 'Mercredi', '18:00', '20:00', 1),
        ('Ligue B', 'Jeudi', '17:00', '19:00', 1),
        ('Ligue A', 'Lundi', '19:00', '21:00', 2),
        ('Ligue B', 'Mardi', '20:00', '22:00', 2),
        ('Ligue A', 'Vendredi', '17:00', '19:00', 3),
        ('Ligue B', 'Jeudi', '17:00', '19:00', 3);



-- Ultimate
INSERT INTO team (name, id_league) VALUES
        ('SFB', 1),
        ('Empire', 1),
        ('Royal', 1),
        ('Glory', 1),
        ('Rush', 2),
        ('Phoenix', 2),
        ('Breeze', 2),
        ('Flyers', 2);

-- Basketball
INSERT INTO team (name, id_league) VALUES
        ('Aces', 3),
        ('Storm', 3),
        ('Wings', 3),
        ('Mercury', 3),
        ('Lynx', 4),
        ('Sparks', 4),
        ('Sky', 4),
        ('Mystics', 4);

-- Volleyball
INSERT INTO team (name, id_league) VALUES
        ('Vibe', 5),
        ('Fury', 5),
        ('Rise', 5),
        ('Mercury', 5),
        ('Ignite', 6),
        ('Supernovas', 6),
        ('Valkyries', 6),
        ('Thrill', 6);


--Ultimate

-- SFB
-- Ultimate Frisbee

-- Outlaws
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Bernard', 'Beaulieu', 1, 7),
  ('Frédéric', 'Mailhot', 1, 11),
  ('Domingo', 'Palao Munoz', 1, 23);

-- Empire
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Ben', 'Jagt', 2, 10),
  ('Jack', 'Williams', 2, 13),
  ('Ryan', 'Osgar', 2, 21);

-- Royal
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Quentin', 'Bonnaud', 3, 8),
  ('Kevin', 'Quinlan', 3, 14),
  ('Yannick', 'Lemire', 3, 30);

-- Glory
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Benjamin', 'Sadok', 4, 4),
  ('Simon', 'Carapella', 4, 12),
  ('Luke', 'Webb', 4, 22);

-- Rush
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Andrew', 'Carroll', 5, 9),
  ('Remi', 'Ojo', 5, 17),
  ('Cam', 'Harris', 5, 27);

-- Phoenix
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Sean', 'Mott', 6, 3),
  ('Ethan', 'Peck', 6, 15),
  ('James', 'Pollard', 6, 20);

-- Breeze
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('AJ', 'Merriman', 7, 6),
  ('Jacques', 'Nissen', 7, 19),
  ('Andrew', 'Roy', 7, 25);

-- Flyers
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Henry', 'Fisher', 8, 5),
  ('Matt', 'Gouchoe-Hanas', 8, 16),
  ('Sol', 'Yanuck', 8, 24);



-- Basketball

-- Aces
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Aja', 'Wilson', 9, 22),
  ('Chelsea', 'Gray', 9, 12),
  ('Kelsey', 'Plum', 9, 3);

-- Storm
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Jewell', 'Loyd', 10, 24),
  ('Skylar', 'Diggins-Smith', 10, 6),
  ('Nneka', 'Ogwumike', 10, 15);

-- Wings
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Arike', 'Ogunbowale', 11, 2),
  ('Satou', 'Sabally', 11, 11),
  ('Teaira', 'McCowan', 11, 35);

-- Mercury
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Kahleah', 'Copper', 12, 8),
  ('Alyssa', 'Thomas', 12, 17),
  ('Sophie', 'Cunningham', 12, 13);

-- Lynx
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Napheesa', 'Collier', 13, 5),
  ('Courtney', 'Williams', 13, 9),
  ('Kayla', 'McBride', 13, 20);

-- Sparks
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Odyssey', 'Sims', 14, 1),
  ('Rickea', 'Jackson', 14, 14),
  ('Sania', 'Feagin', 14, 25);

-- Sky
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Angel', 'Reese', 15, 7),
  ('Marina', 'Mabrey', 15, 23),
  ('Elizabeth', 'Williams', 15, 31);

-- Mystics
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Brittney', 'Sykes', 16, 4),
  ('Sonia', 'Citron', 16, 16),
  ('Kiki', 'Iriafen', 16, 19);



-- Volleyball

-- Vibe
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Tori', 'Dilfer', 17, 1),
  ('Samantha', 'Seliger-Swenson', 17, 12),
  ('Krystal', 'Rivers', 17, 18);

-- Fury
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Taylor', 'Mims', 18, 13),
  ('Kaitlyn', 'Hord', 18, 4),
  ('Ali', 'Bastianelli', 18, 21);

-- Rise
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Aliyah', 'Carter', 19, 10),
  ('Megan', 'Courtney', 19, 15),
  ('Jordyn', 'Poulter', 19, 22);

-- Mercury
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Ronika', 'Stone', 20, 9),
  ('Lauren', 'Carlini', 20, 2),
  ('Jordan', 'Larson', 20, 17);

-- Ignite
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Ali', 'Stumler', 21, 5),
  ('Kendall', 'White', 21, 19),
  ('Danielle', 'Cuttino', 21, 20);

-- Supernovas
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Brooke', 'Nuneviller', 22, 3),
  ('Natalia', 'Valentín-Anderson', 22, 14),
  ('Justine', 'Wong-Orantes', 22, 25);

-- Valkyries
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Deja', 'McClendon', 23, 6),
  ('Molly', 'McCage', 23, 11),
  ('Kelsey', 'Robinson', 23, 16);

-- Thrill
INSERT INTO player (name, last_name, id_team, number) VALUES
  ('Khalia', 'Lanier', 24, 8),
  ('Madi', 'Kubik', 24, 13),
  ('Haleigh', 'Washington', 24, 24);