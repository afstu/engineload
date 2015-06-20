BEGIN
	INSERT INTO Rol	(ROL_ID, ROL_NAAM, ROL_BESCHRIJVING ) VALUES (0, 'AdminRol', 'Dit is de AdminRol');
	INSERT INTO Rol	(ROL_ID, ROL_NAAM, ROL_BESCHRIJVING ) VALUES (1, 'TestRol', 'Dit is een TestRol');
	INSERT INTO Gebruiker (GEBRUIKER_CORPKEY, GEBRUIKER_VOORNAAM, GEBRUIKER_ACHTERNAAM, GEBRUIKER_BESCHRIJVING, GEBRUIKER_WACHTWOORD ) 	VALUES ('admin', 'The', 'Boss', 'Demo Portal Admin', 'admin123');
	INSERT INTO gebruiker_rol VALUES ('admin', 0)
	INSERT INTO Cluster (CLUSTER_ID, CLUSTER_NAAM, CLUSTER_STATUS, CLUSTER_BESCHRIJVING) VALUES (0, 'cluster1', 'D',  'Demo Cluster');
	INSERT INTO cluster_rol (CLUSTER_ID,  ROL_ID) VALUES (0,   0)
	INSERT INTO cluster_rol (CLUSTER_ID,  ROL_ID) VALUES (0,   1)
	INSERT INTO Director (DIRECTOR_NAAM, DIRECTOR_POORT, DIRECTOR_BESCHRIJVING, DRIVER_NAAM, DRIVER_WACHTWOORD ) VALUES ('director1', 8000, 'Demo Director', 'DemoDriver', 'demowachtwoord');
	INSERT INTO cluster_director (DIRECTOR_NAAM, CLUSTER_ID) VALUES ('director1', 0 )
	INSERT INTO Graphite (GRAPHITE_URL) VALUES ('http://graphite');
END