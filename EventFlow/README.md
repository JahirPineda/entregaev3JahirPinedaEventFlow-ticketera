# EventFlow - Sistema de Ticketera

## Contexto

EventFlow es una plataforma de venta y gestión de tickets para eventos, desarrollada con arquitectura de microservicios usando Spring Boot. El sistema permite gestionar clientes, eventos, recintos, exponentes, precios, órdenes de compra, tickets y reseñas, todo a través de un API Gateway centralizado.

## Créditos

| Nombre | Rol |
|--------|-----|
| Jahir Pineda | Desarrollador Full Stack |

## Arquitectura

El sistema está compuesto por los siguientes microservicios:

| Microservicio | Descripción |
|---------------|-------------|
| eureka-server | Servidor de registro y descubrimiento de servicios |
| config-server | Servidor de configuración centralizada |
| api-gateway | Puerta de enlace que centraliza el enrutamiento |
| msvc-clientes | Gestión de clientes registrados en la plataforma |
| msvc-eventos | Gestión de eventos y categorías |
| msvc-recintos | Gestión de recintos y venues |
| msvc-exponentes | Gestión de exponentes y artistas |
| msvc-precios | Gestión de precios y tipos de entrada |
| msvc-ordenes | Gestión de órdenes de compra |
| msvc-tickets | Generación y gestión de tickets con QR |
| msvc-resenas | Gestión de reseñas y preguntas frecuentes |

## Networking - Rutas del API Gateway

Todas las rutas se acceden a través del API Gateway en `http://localhost:8090`

| Servicio | Ruta |
|----------|------|
| Clientes | http://localhost:8090/api/clientes |
| Eventos | http://localhost:8090/api/eventos |
| Categorías | http://localhost:8090/api/categorias |
| Recintos | http://localhost:8090/api/recintos |
| Exponentes | http://localhost:8090/api/exponentes |
| Precios | http://localhost:8090/api/precios |
| Órdenes | http://localhost:8090/api/ordenes |
| Tickets | http://localhost:8090/api/tickets |
| Reseñas | http://localhost:8090/api/resenas |
| Preguntas Frecuentes | http://localhost:8090/api/preguntas |

## Documentación Swagger

La documentación está disponible de forma unificada desde el API Gateway:

- **Swagger UI:** http://localhost:8090/swagger-ui/index.html
- **Clientes API Docs:** http://localhost:8090/clientes/api-docs
- **Ordenes API Docs:** http://localhost:8090/ordenes/api-docs

## Guía de Despliegue

### Requisitos previos
- Java 17 o superior
- Maven 3.9+
- MySQL 8.0 (XAMPP o similar)
- Docker Desktop (para despliegue con Docker)

### Ejecución Local

1. Iniciar XAMPP y activar MySQL
2. Crear las bases de datos necesarias (Flyway las inicializa automáticamente)
3. Iniciar los servicios en este orden:

```bash
# 1. Eureka Server
cd eureka-server && mvn spring-boot:run

# 2. Config Server
cd config-server && mvn spring-boot:run

# 3. Microservicios (en cualquier orden)
cd msvc-clientes && mvn spring-boot:run
cd msvc-eventos && mvn spring-boot:run
cd msvc-recintos && mvn spring-boot:run
cd msvc-exponentes && mvn spring-boot:run
cd msvc-precios && mvn spring-boot:run
cd msvc-ordenes && mvn spring-boot:run
cd msvc-tickets && mvn spring-boot:run
cd msvc-resenas && mvn spring-boot:run

# 4. API Gateway
cd api-gateway && mvn spring-boot:run
```

4. Verificar que todos los servicios estén registrados en Eureka: http://localhost:8761

### Ejecución con Docker

1. Asegurarse de tener Docker Desktop corriendo
2. Desde la carpeta `EventFlow/` ejecutar:

```bash
docker-compose up --build
```

3. Esperar a que todos los servicios inicien correctamente
4. Verificar Eureka en: http://localhost:8761
5. Acceder al API Gateway en: http://localhost:8090

### Detener Docker

```bash
docker-compose down
```

### Detener Docker y eliminar volúmenes

```bash
docker-compose down -v
```