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




-- SFB
INSERT INTO player (name, last_name, id_team) VALUES
                                                  ('Bernard', 'Beaulieu', 1),
                                                  ('Frédéric', 'Mailhot', 1),
                                                  ('Domingo', 'Palao Munoz', 1);

-- Empire
INSERT INTO player (name, last_name, id_team) VALUES
        ('Ben', 'Jagt', 2),
        ('Jack', 'Williams', 2),
        ('Ryan', 'Osgar', 2);

-- Royal
INSERT INTO player (name, last_name, id_team) VALUES
        ('Quentin', 'Bonnaud', 3),
        ('Kevin', 'Quinlan', 3),
        ('Yannick', 'Lemire', 3);

-- Glory
INSERT INTO player (name, last_name, id_team) VALUES
        ('Benjamin', 'Sadok', 4),
        ('Simon', 'Carapella', 4),
        ('Luke', 'Webb', 4);

-- Rush
INSERT INTO player (name, last_name, id_team) VALUES
        ('Andrew', 'Carroll', 5),
        ('Remi', 'Ojo', 5),
        ('Cam', 'Harris', 5);

-- Phoenix
INSERT INTO player (name, last_name, id_team) VALUES
        ('Sean', 'Mott', 6),
        ('Ethan', 'Peck', 6),
        ('James', 'Pollard', 6);

-- Breeze
INSERT INTO player (name, last_name, id_team) VALUES
        ('AJ', 'Merriman', 7),
        ('Jacques', 'Nissen', 7),
        ('Andrew', 'Roy', 7);

INSERT INTO player (name, last_name, id_team) VALUES
        ('Henry', 'Fisher', 8),
        ('Matt', 'Gouchoe-Hanas', 8),
        ('Sol', 'Yanuck', 8);



-- Aces
INSERT INTO player (name, last_name, id_team) VALUES
        ('Aja', 'Wilson', 9),
        ('Chelsea', 'Gray', 9),
        ('Kelsey', 'Plum', 9);

-- Storm
INSERT INTO player (name, last_name, id_team) VALUES
        ('Jewell', 'Loyd', 10),
        ('Skylar', 'Diggins-Smith', 10),
        ('Nneka', 'Ogwumike', 10);

-- Wings
INSERT INTO player (name, last_name, id_team) VALUES
        ('Arike', 'Ogunbowale', 11),
        ('Satou', 'Sabally', 11),
        ('Teaira', 'McCowan', 11);

-- Mercury
INSERT INTO player (name, last_name, id_team) VALUES
        ('Kahleah', 'Copper', 12),
        ('Alyssa', 'Thomas', 12),
        ('Sophie', 'Cunningham', 12);

-- Lynx
INSERT INTO player (name, last_name, id_team) VALUES
        ('Napheesa', 'Collier', 13),
        ('Courtney', 'Williams', 13),
        ('Kayla', 'McBride', 13);

-- Sparks
INSERT INTO player (name, last_name, id_team) VALUES
        ('Odyssey', 'Sims', 14),
        ('Rickea', 'Jackson', 14),
        ('Sania', 'Feagin', 14);

-- Sky
INSERT INTO player (name, last_name, id_team) VALUES
        ('Angel', 'Reese', 15),
        ('Marina', 'Mabrey', 15),
        ('Elizabeth', 'Williams', 15);

-- Mystics
INSERT INTO player (name, last_name, id_team) VALUES
        ('Brittney', 'Sykes', 16),
        ('Sonia', 'Citron', 16),
        ('Kiki', 'Iriafen', 16);




-- Vibe
INSERT INTO player (name, last_name, id_team) VALUES
        ('Tori', 'Dilfer', 17),
        ('Samantha', 'Seliger-Swenson', 17),
        ('Krystal', 'Rivers', 17);

-- Fury
INSERT INTO player (name, last_name, id_team) VALUES
        ('Taylor', 'Mims', 18),
        ('Kaitlyn', 'Hord', 18),
        ('Ali', 'Bastianelli', 18);

-- Rise
INSERT INTO player (name, last_name, id_team) VALUES
        ('Aliyah', 'Carter', 19),
        ('Megan', 'Courtney', 19),
        ('Jordyn', 'Poulter', 19);

-- Mercury
INSERT INTO player (name, last_name, id_team) VALUES
        ('Ronika', 'Stone', 20),
        ('Lauren', 'Carlini', 20),
        ('Jordan', 'Larson', 20);

-- Ignite
INSERT INTO player (name, last_name, id_team) VALUES
        ('Ali', 'Stumler', 21),
        ('Kendall', 'White', 21),
        ('Danielle', 'Cuttino', 21);

-- Supernovas
INSERT INTO player (name, last_name, id_team) VALUES
        ('Brooke', 'Nuneviller', 22),
        ('Natalia', 'Valentín-Anderson', 22),
        ('Justine', 'Wong-Orantes', 22);

-- Valkyries
INSERT INTO player (name, last_name, id_team) VALUES
        ('Deja', 'McClendon', 23),
        ('Molly', 'McCage', 23),
        ('Kelsey', 'Robinson', 23);

-- Thrill
INSERT INTO player (name, last_name, id_team) VALUES
        ('Khalia', 'Lanier', 24),
        ('Madi', 'Kubik', 24),
        ('Haleigh', 'Washington', 24);