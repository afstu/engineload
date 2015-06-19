BEGIN
INSERT INTO Gebruiker ( id , achternaam , corpkey , gebruikerbeschrijving , voornaam , wachtwoord) 	VALUES (0, 'Boss', 'admin', 'Demo Portal Admin', 'The', 'admin123');
INSERT INTO Director ( id , directorbeschrijving , directornaam , directorpoort , drivernaam , driverwachtwoord) VALUES (0, 'Demo Director', 'director1', '8000', 'DemoDriver', 'demowachtwoord');
INSERT INTO Rol	( id , rolbeschrijving , rolnaam) VALUES (0, 'Admin Rol', 'AdminRol');
INSERT INTO Cluster (id , clusterbeschrijving , clusternaam , clusterstatus , rol , director) VALUES (0, 'Demo Cluster', 'cluster1', 'D', 'AdminRol', 'director1');
INSERT INTO Graphite (id, graphiteurl) VALUES (0, 'http://graphite');
END