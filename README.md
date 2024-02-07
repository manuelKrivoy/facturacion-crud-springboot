# CRUD Java
CRUD utilizando Java Spring con JPA y MySQL.

# DER:

![DER TP Java](https://github.com/manuelKrivoy/facturacion-crud-springboot/assets/63810676/35442648-3e4f-4a67-b3e5-16454fab10be)



# Script DB:
> [!IMPORTANT]  
> No se utilizó un data.sql para evitar repetir datos cada vez que se reinicie el programa.


### Script de inserción de productos
INSERT INTO producto ( name, description,  price, stock) VALUES
('Shampoo Dove', 'Shampoo nutritivo para el cabello', 5.99, 88),
('Arroz Integral', 'Arroz integral de grano largo', 2.99, 50),
('Detergente Tide', 'Detergente líquido para ropa', 8.49, 30),
('Cereal Kellogg''s', 'Cereal con avena y frutas', 4.79, 37),
('Pasta Barilla', 'Pasta italiana de trigo duro', 1.99, 60);

### Script de inserción de clientes
INSERT INTO cliente ( name, email ) VALUES
('María Rodríguez', 'maria.rodriguez@example.com'),
('Juan Pérez', 'juan.perez@example.com'),
('Ana Gómez', 'ana.gomez@example.com');

### Script de inserción de facturas
INSERT INTO factura (created_at, total, cliente_id) VALUES
('2024-02-01', 29.95, 3), -- Factura 1 para Ana Gómez
('2024-02-01', 80.26, 2); -- Factura 2 para Juan Pérez

### Script de inserción de detalles de factura
INSERT INTO detalle (amount, factura_id, producto_id) VALUES
(5, 1, 1), -- Detalle 1 para la factura 1, 5 unidades del producto Shampoo Dove
(8, 2, 1), -- Detalle 2 para la factura 2, 8 unidades del producto Shampoo Dove
(3, 2, 1), -- Detalle 3 para la factura 2, 3 unidades del producto Shampoo Dove
(3, 2, 4); -- Detalle 4 para la factura 2, 3 unidades del producto Cereal Kellogg's






