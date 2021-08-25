
-- Init new schema & tables for Postgre DB for BattleFlyServer, existing ones would be dropped
-- Copy this script to Beaver console & execute

DROP TABLE IF exists aircombat.pilotresult;
DROP TABLE IF exists aircombat.pilot;
DROP TABLE IF exists aircombat.sortie;
DROP TABLE IF exists aircombat.event;
DROP SEQUENCE IF exists aircombat.pilot_pilotid;
DROP SEQUENCE IF exists aircombat.sortie_sortieid;
DROP SEQUENCE IF exists aircombat.event_eventid;
DROP schema IF exists aircombat;

CREATE SCHEMA aircombat AUTHORIZATION postgres;

CREATE SEQUENCE aircombat.pilot_pilotid
	INCREMENT BY 1;

CREATE SEQUENCE aircombat.sortie_sortieid
	INCREMENT BY 1;

CREATE SEQUENCE aircombat.event_eventid
	INCREMENT BY 1;

CREATE TABLE aircombat.pilot (
	pilotid int4 NOT NULL DEFAULT nextval('aircombat.pilot_pilotid'),
	name varchar(100) NOT NULL,

	CONSTRAINT pilot_pkey PRIMARY KEY (pilotid)
);

CREATE TABLE aircombat.pilotresult (
	pilotid int not NULL,
	sortieid int not NULL,
	team int,
	roundsfired int,
	hitsachieved int,
	hitstaken int,
	hitsground int,
	ffair int,
	ffground int,
	solokills int,
	killed int,
	groupkills int,
	killedby varchar(100),

	CONSTRAINT pilotresult_fkey1 FOREIGN KEY (pilotid) REFERENCES aircombat.pilot (pilotid),
	CONSTRAINT pilotresult_pkey PRIMARY KEY (pilotid,sortieid)
);

CREATE TABLE aircombat.event (
	eventid int4 NOT NULL DEFAULT nextval('aircombat.event_eventid'),
	name varchar(100) NOT NULL,
	location varchar(255),
	eventdate date,
	eventstatus varchar(100),
	filename varchar(255),

	CONSTRAINT event_pkey PRIMARY KEY (eventid)
);

CREATE TABLE aircombat.sortie (
	sortieid int4 NOT NULL DEFAULT nextval('aircombat.sortie_sortieid'),
	eventid int NOT NULL,
	sortieno int NOT null,

	CONSTRAINT sortie_fkey1 FOREIGN KEY (eventid) REFERENCES aircombat.event (eventid),
	CONSTRAINT sortie_pkey PRIMARY KEY (sortieid)
);


