CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255)
);


INSERT INTO users (username, password, role) VALUES ('mrbricolage@spacyx.com', '33raptor', 'Technicien');
INSERT INTO users (username, password, role) VALUES ('voyagevoyage@spaceyx.com', 'ihaveaplan', 'Planificateur');
INSERT INTO users (username, password, role) VALUES ('romain@mail.com', 'çavaaller', 'Voyageur');


