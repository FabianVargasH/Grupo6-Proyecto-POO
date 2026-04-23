CREATE DATABASE bd_grupo6;
USE bd_grupo6;

CREATE TABLE t_usuario (
   identificacion VARCHAR(20) PRIMARY KEY,
   nombre_completo VARCHAR(100) NOT NULL,
   fecha_nacimiento DATE NOT NULL,
   contrasena VARCHAR(100) NOT NULL,
   correo_electronico VARCHAR(100) NOT NULL UNIQUE,
   tipo_usuario ENUM('MODERADOR', 'VENDEDOR', 'COLECCIONISTA') NOT NULL,
   puntuacion DOUBLE DEFAULT 0,
   direccion VARCHAR(200)
);

CREATE TABLE t_interes (
   id INT PRIMARY KEY AUTO_INCREMENT,
   usuario_id VARCHAR(20) NOT NULL,
   interes VARCHAR(100) NOT NULL,
   FOREIGN KEY (usuario_id) REFERENCES t_usuario(identificacion) ON DELETE CASCADE
);

CREATE TABLE t_objeto (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  estado VARCHAR(50) NOT NULL,
  fecha_compra DATE NOT NULL,
  propietario_id VARCHAR(20) NOT NULL,
  FOREIGN KEY (propietario_id) REFERENCES t_usuario(identificacion) ON DELETE CASCADE
);

CREATE TABLE t_subasta (
   id INT PRIMARY KEY AUTO_INCREMENT,
   creador_id VARCHAR(20) NOT NULL,
   precio_minimo DOUBLE NOT NULL,
   fecha_creacion DATE NOT NULL,
   fecha_cierre DATE NOT NULL,
   estado VARCHAR(50) DEFAULT 'Activa',
   FOREIGN KEY (creador_id) REFERENCES t_usuario(identificacion) ON DELETE CASCADE
);

CREATE TABLE t_subasta_objeto (
  subasta_id INT NOT NULL,
  objeto_id INT NOT NULL,
  PRIMARY KEY (subasta_id, objeto_id),
  FOREIGN KEY (subasta_id) REFERENCES t_subasta(id) ON DELETE CASCADE,
  FOREIGN KEY (objeto_id) REFERENCES t_objeto(id) ON DELETE CASCADE
);

CREATE TABLE t_oferta (
  id INT PRIMARY KEY AUTO_INCREMENT,
  subasta_id INT NOT NULL,
  oferente_id VARCHAR(20) NOT NULL,
  precio_ofertado DOUBLE NOT NULL,
  fecha_oferta DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (subasta_id) REFERENCES t_subasta(id) ON DELETE CASCADE,
  FOREIGN KEY (oferente_id) REFERENCES t_usuario(identificacion) ON DELETE CASCADE
);

CREATE TABLE t_orden_adjudicacion (
  id INT PRIMARY KEY AUTO_INCREMENT,
  subasta_id INT NOT NULL UNIQUE,
  ganador_id VARCHAR(20) NOT NULL,
  fecha_orden DATE NOT NULL,
  precio_total DOUBLE NOT NULL,
  FOREIGN KEY (subasta_id) REFERENCES t_subasta(id) ON DELETE CASCADE,
  FOREIGN KEY (ganador_id) REFERENCES t_usuario(identificacion) ON DELETE CASCADE
);

CREATE TABLE t_orden_objeto (
    orden_id INT NOT NULL,
    objeto_id INT NOT NULL,
    PRIMARY KEY (orden_id, objeto_id),
    FOREIGN KEY (orden_id) REFERENCES t_orden_adjudicacion(id) ON DELETE CASCADE,
    FOREIGN KEY (objeto_id) REFERENCES t_objeto(id) ON DELETE CASCADE
);

CREATE TRIGGER validar_unico_moderador
    BEFORE INSERT ON t_usuario
    FOR EACH ROW
BEGIN
    DECLARE moderador_count INT;
    IF NEW.tipo_usuario = 'MODERADOR' THEN
    SELECT COUNT(*) INTO moderador_count FROM t_usuario WHERE tipo_usuario = 'MODERADOR';
    IF moderador_count >= 1 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un moderador en el sistema. Solo puede haber uno.';
END IF;
END IF;
END;

CREATE TRIGGER validar_oferta_vendedor
    BEFORE INSERT ON t_oferta
    FOR EACH ROW
BEGIN
    DECLARE tipo_oferente VARCHAR(20);
    SELECT tipo_usuario INTO tipo_oferente FROM t_usuario WHERE identificacion = NEW.oferente_id;
    IF tipo_oferente = 'VENDEDOR' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un vendedor no puede realizar ofertas.';
END IF;
END;

CREATE TRIGGER validar_subasta_moderador
    BEFORE INSERT ON t_subasta
    FOR EACH ROW
BEGIN
    DECLARE tipo_creador VARCHAR(20);
    SELECT tipo_usuario INTO tipo_creador FROM t_usuario WHERE identificacion = NEW.creador_id;
    IF tipo_creador = 'MODERADOR' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El moderador no puede crear subastas.';
END IF;
END;

CREATE TRIGGER validar_oferente_coleccionista
    BEFORE INSERT ON t_oferta
    FOR EACH ROW
BEGIN
    DECLARE tipo_oferente VARCHAR(20);
    SELECT tipo_usuario INTO tipo_oferente FROM t_usuario WHERE identificacion = NEW.oferente_id;
    IF tipo_oferente != 'COLECCIONISTA' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo los coleccionistas pueden realizar ofertas.';
END IF;
END;

CREATE TRIGGER validar_oferta_propia_subasta
    BEFORE INSERT ON t_oferta
    FOR EACH ROW
BEGIN
    DECLARE creador_subasta VARCHAR(20);
    SELECT creador_id INTO creador_subasta FROM t_subasta WHERE id = NEW.subasta_id;
    IF creador_subasta = NEW.oferente_id THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Un coleccionista no puede ofertar en una subasta creada por el mismo.';
END IF;
END;