-- Crear base de datos y usarla
CREATE DATABASE IF NOT EXISTS escalada_db;
USE escalada_db;

-- Tabla de Escoles
CREATE TABLE Escoles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE,
    lloc VARCHAR(100),
    aproximacio TEXT,
    num_vies INT DEFAULT 0,
    popularitat ENUM('baixa', 'mitjana', 'alta'),
    restriccions TEXT
) ENGINE=InnoDB;

-- Tabla de Sectors
CREATE TABLE Sectors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    escola_id INT NOT NULL,
    coordenades POINT,
    aproximacio TEXT,
    num_vies INT DEFAULT 0,
    popularitat ENUM('baixa', 'mitjana', 'alta'),
    restriccions TEXT,
    FOREIGN KEY (escola_id) REFERENCES Escoles(id) ON DELETE CASCADE,
    UNIQUE (nom, escola_id)
) ENGINE=InnoDB;

-- Tabla de Escaladors
CREATE TABLE Escaladors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    alias VARCHAR(100),
    edat INT,
    nivell_max ENUM('4','4+','5','5+','6a','6a+','6b','6b+','6c','6c+','7a','7a+','7b','7b+','7c','7c+','8a','8a+','8b','8b+','8c','8c+','9a','9a+','9b','9b+','9c','9c+') DEFAULT NULL,
    via_max_id INT DEFAULT NULL,
    estil_preferit ENUM('esportiva', 'clàssica', 'gel'),
    historial TEXT,
    fita TEXT
) ENGINE=InnoDB;

-- Tabla de Vies
CREATE TABLE Vies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    llargada INT,
    dificultat ENUM('4','4+','5','5+','6a','6a+','6b','6b+','6c','6c+','7a','7a+','7b','7b+','7c','7c+','8a','8a+','8b','8b+','8c','8c+','9a','9a+','9b','9b+','9c','9c+','WI1','WI2','WI3','WI4','WI5','WI6','WI7'),
    orientacio ENUM('N','NE','NO','SE','SO','E','O','S'),
    estat ENUM('Apte','Construcció','Tancada') DEFAULT 'Apte',
    data_no_apta_until DATE DEFAULT NULL,
    escola_id INT NOT NULL,
    sector_id INT NOT NULL,
    tipus ENUM('esportiva', 'clàssica', 'gel'),
    ancoratge ENUM('spits','parabolts','quimics','friends','tascons','bagues','pitons','Tricams','BigBros'),
    tipus_roca ENUM('conglomerat','granit','calcaria','arenisca','altres'),
    creador_id INT,
    FOREIGN KEY (escola_id) REFERENCES Escoles(id) ON DELETE CASCADE,
    FOREIGN KEY (sector_id) REFERENCES Sectors(id) ON DELETE CASCADE,
    FOREIGN KEY (creador_id) REFERENCES Escaladors(id) ON DELETE SET NULL,
    UNIQUE(nom, sector_id)
) ENGINE=InnoDB;

-- Tabla de Llargs
CREATE TABLE Llargs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    via_id INT NOT NULL,
    numero_llarg INT NOT NULL,
    llargada INT,
    dificultat ENUM('4','4+','5','5+','6a','6a+','6b','6b+','6c','6c+','7a','7a+','7b','7b+','7c','7c+','8a','8a+','8b','8b+','8c','8c+','9a','9a+','9b','9b+','9c','9c+','WI1','WI2','WI3','WI4','WI5','WI6','WI7'),
    orientacio ENUM('N','NE','NO','SE','SO','E','O','S'),
    estat ENUM('Apte','Construcció','Tancada') DEFAULT 'Apte',
    data_no_apta_until DATE DEFAULT NULL,
    ancoratge ENUM('spits','parabolts','quimics','friends','tascons','bagues','pitons','Tricams','BigBros'),
    FOREIGN KEY (via_id) REFERENCES Vies(id) ON DELETE CASCADE,
    UNIQUE(via_id, numero_llarg)
) ENGINE=InnoDB;

-- Cambiar delimitador para definir triggers
DELIMITER //

-- Trigger para validar llargada según tipus de via (INSERT)
CREATE TRIGGER validar_llargada_via_insert
BEFORE INSERT ON Vies
FOR EACH ROW
BEGIN
    IF NEW.llargada IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada no pot ser NULL';
    END IF;

    IF NEW.tipus = 'esportiva' AND (NEW.llargada < 5 OR NEW.llargada > 30) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies esportives ha d\'estar entre 5 i 30 metres';
    END IF;

    IF NEW.tipus = 'clàssica' AND (NEW.llargada < 50 OR NEW.llargada > 150) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies clàssiques ha d\'estar entre 50 i 150 metres';
    END IF;

    IF NEW.tipus = 'gel' AND (NEW.llargada < 15 OR NEW.llargada > 150) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies de gel ha d\'estar entre 15 i 150 metres';
    END IF;
END;
//

-- Trigger para validar llargada según tipus de via (UPDATE)
CREATE TRIGGER validar_llargada_via_update
BEFORE UPDATE ON Vies
FOR EACH ROW
BEGIN
    IF NEW.llargada IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada no pot ser NULL';
    END IF;

    IF NEW.tipus = 'esportiva' AND (NEW.llargada < 5 OR NEW.llargada > 30) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies esportives ha d\'estar entre 5 i 30 metres';
    END IF;

    IF NEW.tipus = 'clàssica' AND (NEW.llargada < 50 OR NEW.llargada > 150) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies clàssiques ha d\'estar entre 50 i 150 metres';
    END IF;

    IF NEW.tipus = 'gel' AND (NEW.llargada < 15 OR NEW.llargada > 150) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La llargada per a vies de gel ha d\'estar entre 15 i 150 metres';
    END IF;
END;
//

-- Trigger para validar data_no_apta_until sólo si la via no es 'Apte'
CREATE TRIGGER validar_estat_data_insert
BEFORE INSERT ON Vies
FOR EACH ROW
BEGIN
    IF NEW.estat = 'Apte' AND NEW.data_no_apta_until IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Una via "Apte" no pot tenir data_no_apta_until';
    END IF;
END;
//

CREATE TRIGGER validar_estat_data_update
BEFORE UPDATE ON Vies
FOR EACH ROW
BEGIN
    IF NEW.estat = 'Apte' AND NEW.data_no_apta_until IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Una via "Apte" no pot tenir data_no_apta_until';
    END IF;
END;
//

-- Restaurar delimitador predeterminado
DELIMITER ;