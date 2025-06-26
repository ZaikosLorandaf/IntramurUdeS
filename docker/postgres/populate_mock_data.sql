SET search_path = intramurudes;


--Ajouter les saisons
INSERT INTO season (season_year, time_precision) VALUES
        (2023, 'Hiver'),
        (2023, 'Printemps'),
        (2023, 'Été'),
        (2023, 'Automne'),
        (2024, 'Hiver'),
        (2024, 'Printemps'),
        (2024, 'Été'),
        (2024, 'Automne'),
        (2025, 'Hiver'),
        (2025, 'Printemps'),
        (2025, 'Été'),
        (2025, 'Automne');


INSERT INTO sport(name) VALUES
        ('Ultimate'),
        ('Basketball'),
        ('Volley-ball');

INSERT INTO league (name, begin_date, end_date, id_sport) VALUES
        ('Ligue A', '2025-05-09', '2025-08-12', 1),
        ('Ligue B', '2025-05-09', '2025-08-12',   1),
        ('Ligue A', '2025-05-09', '2025-08-12',  2),
        ('Ligue B', '2025-05-09', '2025-08-12',  2),
        ('Ligue A', '2025-05-09', '2025-08-12',  3),
        ('Ligue B', '2025-05-09', '2025-08-12',  3);



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




-- Lier chaque ligue à chaque saison
INSERT INTO league_season (id_league, id_season) VALUES
-- Ligue 1 (Ultimate League 1)
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),

-- Ligue 2 (Ultimate League 2)
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12),

-- Ligue 3 (Basketball League 1)
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10), (3, 11), (3, 12),

-- Ligue 4 (Basketball League 2)
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (4, 10), (4, 11), (4, 12),

-- Ligue 5 (Volleyball League 1)
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7), (5, 8), (5, 9), (5, 10), (5, 11), (5, 12),

-- Ligue 6 (Volleyball League 2)
(6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7), (6, 8), (6, 9), (6, 10), (6, 11), (6, 12);




-- Match
-- Matchs Ultimate Ligue A
INSERT INTO match_ (date_match, begin_time, end_time, id_league, id_season) VALUES
    ('2025-06-01', '14:00', '15:30', 1, 11),  -- Match 1
    ('2025-06-02', '16:00', '17:30', 1,11),  -- Match 2
    ('2025-06-03', '18:00', '19:30', 1,11),  -- Match 3
    ('2025-06-04', '18:00', '19:30', 1,11);   -- Match vide sans équipe

INSERT INTO match_team (id_team, id_match) VALUES
    (1, 1), (2, 1),    -- SFB vs Empire
    (3, 2), (4, 2),    -- Royal vs Glory
    (1, 3), (3, 3);    -- SFB vs Royal

-- Matchs Ultimate Ligue B
INSERT INTO match_ (date_match, begin_time, end_time, id_league, id_season) VALUES
    ('2025-06-04', '14:00', '15:30', 2, 11),  -- Match 4
    ('2025-06-05', '16:00', '17:30', 2, 11);  -- Match 5

INSERT INTO match_team (id_team, id_match) VALUES
    (5, 5), (6, 5),    -- Rush vs Phoenix
    (7, 6), (8, 6);    -- Breeze vs Flyers

-- Matchs Basketball Ligue A
INSERT INTO match_ (date_match, begin_time, end_time, id_league, id_season) VALUES
    ('2025-06-06', '14:00', '15:30', 3, 11),  -- Match 6
    ('2025-06-07', '16:00', '17:30', 3, 11);  -- Match 7

INSERT INTO match_team (id_team, id_match) VALUES
    (9, 7), (10, 7),   -- Aces vs Storm
    (11, 8), (12, 8);  -- Wings vs Mercury

-- Matchs Basketball Ligue B
INSERT INTO match_ (date_match, begin_time, end_time, id_league, id_season) VALUES
    ('2025-06-08', '14:00', '15:30', 4, 11),  -- Match 8
    ('2025-06-09', '16:00', '17:30', 4, 11);  -- Match 9

INSERT INTO match_team (id_team, id_match) VALUES
    (13, 9), (14, 9),  -- Lynx vs Sparks
    (15, 10), (16, 10);  -- Sky vs Mystics

-- Matchs Volleyball Ligue A
INSERT INTO match_ (date_match, begin_time, end_time, id_league) VALUES
    ('2025-06-10', '14:00', '15:30', 5),  -- Match 10
    ('2025-06-11', '16:00', '17:30', 5);  -- Match 11

INSERT INTO match_team (id_team, id_match) VALUES
    (17, 11), (18, 11),  -- Vibe vs Fury
    (19, 12), (20, 12);  -- Rise vs Mercury

-- Matchs Volleyball Ligue B
INSERT INTO match_ (date_match, begin_time, end_time, id_league) VALUES
    ('2025-06-12', '14:00', '15:30', 6),  -- Match 12
    ('2025-06-13', '16:00', '17:30', 6);  -- Match 13

INSERT INTO match_team (id_team, id_match) VALUES
    (21, 13), (22, 13),  -- Ignite vs Supernovas
    (23, 14), (24, 14);  -- Valkyries vs Thrill











