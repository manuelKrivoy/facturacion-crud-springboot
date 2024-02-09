# CRUD Java

Este proyecto implementa un CRUD utilizando Java Spring con JPA y MySQL para gestionar productos, clientes y facturas.

## Diagrama Entidad-Relación (DER)

![DER TP Java](https://github.com/manuelKrivoy/facturacion-crud-springboot/assets/63810676/35442648-3e4f-4a67-b3e5-16454fab10be)

## Script de la Base de Datos

### Script de inserción de Productos
```sql
INSERT INTO producto (name, description, price, stock) VALUES
('Shampoo Dove', 'Shampoo nutritivo para el cabello', 5.99, 88),
('Arroz Integral', 'Arroz integral de grano largo', 2.99, 50),
('Detergente Tide', 'Detergente líquido para ropa', 8.49, 30),
('Cereal Kellogg''s', 'Cereal con avena y frutas', 4.79, 37),
('Pasta Barilla', 'Pasta italiana de trigo duro', 1.99, 60);
```

### Script de inserción de Clientes
```sql
INSERT INTO cliente (name, email) VALUES
('María Rodríguez', 'maria.rodriguez@example.com'),
('Juan Pérez', 'juan.perez@example.com'),
('Ana Gómez', 'ana.gomez@example.com');
```

### Script de inserción de Facturas
```sql
INSERT INTO factura (created_at, total, cantidad_productos, cliente_id) VALUES
('2024-02-01', 0, 0, 3), -- Factura 1 para Ana Gómez
('2024-02-01', 0, 0, 2); -- Factura 2 para Juan Pérez
```

## Notas
### Facturas:
- Las facturas están inicializadas con valores de **0** para **total** y **cantidad_productos** debido a que la validación no está implementada en MySQL.
- Si se realiza una solicitud POST desde Postman, no es necesario inicializar estos valores en **0**, ya que serán manejados adecuadamente por la lógica de la aplicación.

### Detalles:
- No se incluyen scripts para crear detalles de factura ya que no se realiza la lógica para ello en MySQL (como la disminución de stock, el chequeo de stock disponible, el aumento del total de la factura o el aumento de la cantidad de productos totales).
- Aquí dejo un ejemplo de un body de Postman para cargar un detalle:
```json
{
    "amount": 3,
    "factura": {
        "id": 2
    },
    "producto": {
        "id": 4
    }
}
```






