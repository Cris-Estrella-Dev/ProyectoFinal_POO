-- Esquema de la base de datos de Clínica Sunny (MySQL 8).
--
-- Docker lo ejecuta automáticamente la primera vez que se crea el contenedor.
-- Para cargarlo a mano en un MySQL propio:
--   mysql -u <usuario> -p clinicasunny < database/01-esquema.sql

SET NAMES utf8mb4;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario     INT AUTO_INCREMENT PRIMARY KEY,
    identificacion CHAR(11)     NOT NULL,
    clave          VARCHAR(100) NOT NULL,
    tipo_usuario   VARCHAR(20)  NOT NULL,  -- 'Admin', 'Secretaria' o 'Medico'
    UNIQUE KEY uq_usuario (identificacion, tipo_usuario)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- Un usuario de tipo 'Medico' se asocia a su médico por la cédula.
CREATE TABLE IF NOT EXISTS medicos (
    id_medico      INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    cedula         CHAR(11)     NOT NULL,
    especialidad   VARCHAR(100) NOT NULL,
    disponibilidad VARCHAR(100) NOT NULL,
    UNIQUE KEY uq_medico_cedula (cedula)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    cedula      VARCHAR(20)  NOT NULL,
    direccion   VARCHAR(200),
    telefono    VARCHAR(20),
    UNIQUE KEY uq_paciente_cedula (cedula)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS enfermedad (
    id_enfermedad INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    cronico       VARCHAR(2)   NOT NULL DEFAULT 'No'  -- 'Si' o 'No'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS vacuna (
    id_vacuna INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- La secretaria registra la cita con los datos del paciente tal como llegan;
-- el paciente se da de alta en su tabla cuando el médico guarda la primera consulta.
CREATE TABLE IF NOT EXISTS citas (
    id_cita    INT AUTO_INCREMENT PRIMARY KEY,
    id_medico  INT          NOT NULL,
    cedula     VARCHAR(20)  NOT NULL,
    nombre     VARCHAR(100) NOT NULL,
    telefono   VARCHAR(20)  NOT NULL,
    direccion  VARCHAR(200) NOT NULL,
    fecha_cita VARCHAR(20)  NOT NULL,
    KEY idx_citas_cedula (cedula),
    CONSTRAINT fk_citas_medico FOREIGN KEY (id_medico) REFERENCES medicos (id_medico)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS consulta (
    id_consulta    INT AUTO_INCREMENT PRIMARY KEY,
    id_cita        INT         NOT NULL,
    id_enfermedad  INT         NOT NULL,
    id_vacuna      INT         NULL,
    fecha_consulta VARCHAR(20) NOT NULL,
    CONSTRAINT fk_consulta_cita       FOREIGN KEY (id_cita)       REFERENCES citas (id_cita),
    CONSTRAINT fk_consulta_enfermedad FOREIGN KEY (id_enfermedad) REFERENCES enfermedad (id_enfermedad),
    CONSTRAINT fk_consulta_vacuna     FOREIGN KEY (id_vacuna)     REFERENCES vacuna (id_vacuna)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
