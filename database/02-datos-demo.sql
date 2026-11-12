-- Datos de demostración (ficticios) para poder probar la aplicación al instante.
-- Las credenciales de estos usuarios están documentadas en el README.

SET NAMES utf8mb4;

INSERT INTO usuarios (identificacion, clave, tipo_usuario) VALUES
    ('00100000001', 'admin123',  'Admin'),
    ('00100000002', 'secre123',  'Secretaria'),
    ('40200000001', 'medico123', 'Medico');

INSERT INTO medicos (id_medico, nombre, cedula, especialidad, disponibilidad) VALUES
    (1, 'Laura Méndez',   '40200000001', 'Cardiología',  'Lunes y miércoles'),
    (2, 'Pedro Santana',  '40200000002', 'Pediatría',    'Martes y jueves'),
    (3, 'Ana Rosario',    '40200000003', 'Dermatología', 'Lunes a viernes'),
    (4, 'Carlos Jiménez', '40200000004', 'Cardiología',  'Viernes');

INSERT INTO enfermedad (id_enfermedad, nombre, cronico) VALUES
    (1, 'Hipertensión arterial', 'Si'),
    (2, 'Diabetes tipo 2',       'Si'),
    (3, 'Asma',                  'Si'),
    (4, 'Gripe',                 'No'),
    (5, 'Dermatitis',            'No');

INSERT INTO vacuna (id_vacuna, nombre) VALUES
    (1, 'Influenza'),
    (2, 'Hepatitis B'),
    (3, 'Tétanos'),
    (4, 'COVID-19');

INSERT INTO citas (id_cita, id_medico, cedula, nombre, telefono, direccion, fecha_cita) VALUES
    (1, 1, '00111111111', 'María Pérez',   '809-555-0101', 'Calle Duarte 12, Santiago',     '12/11/2026'),
    (2, 1, '00122222222', 'José Ramírez',  '809-555-0102', 'Av. Estrella Sadhalá 45',       '12/11/2026'),
    (3, 1, '00133333333', 'Carmen Núñez',  '829-555-0103', 'Calle del Sol 8, Santiago',     '13/11/2026'),
    (4, 2, '00144444444', 'Luis Torres',   '849-555-0104', 'Los Jardines, calle 3 #21',     '13/11/2026'),
    (5, 2, '00111111111', 'María Pérez',   '809-555-0101', 'Calle Duarte 12, Santiago',     '16/11/2026'),
    (6, 3, '00155555555', 'Sofía Guzmán',  '809-555-0105', 'Villa Olga, calle 7 #3',        '16/11/2026'),
    (7, 1, '00111111111', 'María Pérez',   '809-555-0101', 'Calle Duarte 12, Santiago',     '20/10/2026');

INSERT INTO paciente (nombre, cedula, direccion, telefono) VALUES
    ('María Pérez', '00111111111', 'Calle Duarte 12, Santiago', '809-555-0101');

INSERT INTO consulta (id_cita, id_enfermedad, id_vacuna, fecha_consulta) VALUES
    (7, 1, 1, '20/10/2026');
