# CRUD Java

Este proyecto implementa un CRUD utilizando Java Spring con JPA y MySQL para gestionar productos, clientes y facturas.

## Diagrama Entidad-Relación (DER)

![DER TP Java](https://github.com/manuelKrivoy/facturacion-crud-springboot/assets/63810676/35442648-3e4f-4a67-b3e5-16454fab10be)

## Documentación API:

### Gestión de Clientes
Esta API proporciona endpoints para la gestión de clientes en un sistema de facturación.

### Endpoints Disponibles

#### Obtener todos los clientes

- **Método:** `GET`
- **URL:** `/clientes`
- **Descripción:** Obtiene una lista de todos los clientes registrados en el sistema.

#### Agregar un nuevo cliente

- **Método:** `POST`
- **URL:** `/alta/cliente`
- **Descripción:** Registra un nuevo cliente en el sistema.


#### Modificar un cliente existente

- **Método:** `PUT`
- **URL:** `/modificar/cliente/{id}`
- **Descripción:** Actualiza los detalles de un cliente existente identificado por su ID.
- **Parámetros de solicitud:**
    - `id`: Identificador único del cliente a modificar.
   

#### Eliminar un cliente existente

- **Método:** `DELETE`
- **URL:** `/baja/cliente/{id}`
- **Descripción:** Elimina un cliente existente del sistema.
- **Parámetros de solicitud:**
    - `id`: Identificador único del cliente a eliminar.

## Gestión de Productos

Esta API proporciona endpoints para la gestión de productos en un sistema de facturación.

### Endpoints Disponibles

#### Obtener todos los productos

- **Método:** `GET`
- **URL:** `/productos`
- **Descripción:** Obtiene una lista de todos los productos registrados en el sistema.

#### Agregar un nuevo producto

- **Método:** `POST`
- **URL:** `/alta/producto`
- **Descripción:** Registra un nuevo producto en el sistema.


#### Modificar un producto existente

- **Método:** `PUT`
- **URL:** `/modificar/producto/{id}`
- **Descripción:** Actualiza los detalles de un producto existente identificado por su ID.
- **Parámetros de solicitud:**
    - `id`: Identificador único del producto a modificar.

#### Eliminar un producto existente

- **Método:** `DELETE`
- **URL:** `/baja/producto/{id}`
- **Descripción:** Elimina un producto existente del sistema.
- **Parámetros de solicitud:**
    - `id`: Identificador único del producto a eliminar.

## Gestión de Facturas

Esta API proporciona endpoints para la gestión de facturas en un sistema de facturación.

### Endpoints Disponibles

#### Obtener todas las facturas

- **Método:** `GET`
- **URL:** `/facturas`
- **Descripción:** Obtiene una lista de todas las facturas registradas en el sistema.

#### Agregar una nueva factura

- **Método:** `POST`
- **URL:** `/alta/factura`
- **Descripción:** Registra una nueva factura en el sistema.

#### Modificar una factura existente

- **Método:** `PUT`
- **URL:** `/modificar/factura/{id}`
- **Descripción:** Actualiza los detalles de una factura existente identificada por su ID.
- **Parámetros de solicitud:**
    - `id`: Identificador único de la factura a modificar.

#### Eliminar una factura existente

- **Método:** `DELETE`
- **URL:** `/baja/factura/{id}`
- **Descripción:** Elimina una factura existente del sistema.
- **Parámetros de solicitud:**
    - `id`: Identificador único de la factura a eliminar.

#### Generar comprobante de una factura

- **Método:** `GET`
- **URL:** `/comprobante/{id}`
- **Descripción:** Genera un comprobante para una factura específica identificada por su ID.
- **Parámetros de solicitud:**
    - `id`: Identificador único de la factura para la cual se genera el comprobante.

## Gestión de Detalles

Esta API proporciona endpoints para la gestión de detalles en un sistema de facturación.

### Endpoints Disponibles

#### Obtener todos los detalles

- **Método:** `GET`
- **URL:** `/detalles`
- **Descripción:** Obtiene una lista de todos los detalles registrados en el sistema.

#### Agregar un nuevo detalle

- **Método:** `POST`
- **URL:** `/alta/detalle`
- **Descripción:** Registra un nuevo detalle en el sistema.

#### Modificar un detalle existente

- **Método:** `PUT`
- **URL:** `/modificar/detalle/{id}`
- **Descripción:** Actualiza los detalles de un detalle existente identificado por su ID.
- **Parámetros de solicitud:**
    - `id`: Identificador único del detalle a modificar.

#### Eliminar un detalle existente

- **Método:** `DELETE`
- **URL:** `/baja/detalle/{id}`
- **Descripción:** Elimina un detalle existente del sistema.
- **Parámetros de solicitud:**
    - `id`: Identificador único del detalle a eliminar.


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









