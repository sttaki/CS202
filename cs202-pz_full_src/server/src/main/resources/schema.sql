CREATE DATABASE IF NOT EXISTS esports_db DEFAULT CHARACTER SET utf8mb4;
USE esports_db;

DROP TABLE IF EXISTS match_tbl;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS arena;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS tournament;

CREATE TABLE tournament (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  start_date DATE
);

CREATE TABLE game (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE arena (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  city VARCHAR(100)
);

CREATE TABLE team (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  game_id INT,
  FOREIGN KEY (game_id) REFERENCES game(id)
);

CREATE TABLE player (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nickname VARCHAR(100) NOT NULL,
  team_id INT,
  FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE match_tbl (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tournament_id INT NOT NULL,
  home_team_id INT NOT NULL,
  away_team_id INT NOT NULL,
  arena_id INT,
  match_date DATE,
  home_score INT DEFAULT 0,
  away_score INT DEFAULT 0,
  status VARCHAR(20) DEFAULT 'SCHEDULED',
  FOREIGN KEY (tournament_id) REFERENCES tournament(id),
  FOREIGN KEY (home_team_id) REFERENCES team(id),
  FOREIGN KEY (away_team_id) REFERENCES team(id),
  FOREIGN KEY (arena_id) REFERENCES arena(id)
);
