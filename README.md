# Courier Tracking System

## Project Overview

This project is a RESTful web application developed with Java & Spring Boot. It processes real-time geolocation data from couriers and provides:

**Store entrance detection:** When a courier enters a radius of 100 meters from a Migros store, the system logs this entrance.

**Smart filtering:** Re-entries within 1 minute to the same store are ignored.

**Distance tracking:** Total travel distance of each courier can be queried.

**Batch processing:** A nightly job calculates and stores the previous day's total distance and store entrance count for all couriers.

## 🚀 Technologies Used

**Java 17**

**Spring Boot**

**Spring Batch**

**Spring Application Events**

**Spring AOP (Aspect Logging)**

**MapStruct**

**Lombok**

**MySQL**

**Maven**

**Docker**

**Swagger / OpenAPI**

**JUnit & Mockito**

**Custom Exception Handling**

## ⚙️ How It Works

➔ **Real-time Courier Location**

Courier sends location (lat, lng, timestamp) to the server.

Location is instantly saved to the database.

A Spring Application Event is triggered.

Event listener checks if the courier:

Is within 100 meters of any Migros store.

Has not entered the same store within the past minute.

If criteria are met:

A new CourierStoreEntrance record is created.

If an error occurs:

The event is retried up to 3 times.

➔ **Batch Job**

Every night at 00:15, a Spring Batch job runs to:

Calculate total distance traveled by each courier for the previous day.

Count how many store entrances each courier made.

Save results to the CourierDistance table.

## 🗄️ Database Schema Overview

| Table                      | Description                                             |
|----------------------------| ------------------------------------------------------- |
| `couriers`                 | Stores courier information.                             |
| `courier_locations`        | Records all received location data from couriers.       |
| `courier_store_entrances`  | Stores records of valid store entrances per courier.    |
| `courier_distances`        | Contains daily total distance and store entry count.    |
| `stores`                   | Static list of Migros stores loaded from `stores.json`. |
| `error_log`, `service_log` | Logs captured via AOP using custom aspects.             |

##  Design Patterns Used

**Strategy Pattern:** Used for flexible distance calculation strategies.

**Observer Pattern:** Applied with Spring Application Events to decouple location logging from business logic.

## 📁 stores.json

Initial store locations are loaded from a static JSON file (resources/stores.json).

These define all Migros store positions.

## 🐳 Quick Start with Docker

If you have Docker installed, you can start the entire system (application + database) with one command:

"docker-compose up --build"

## **Access Swagger UI:**

http://localhost:8081/swagger-ui/index.html

## **Request:**
curl -X 'POST' \
'http://localhost:8081/api/couriers' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"name": "string",
"surname": "string"
}'

url -X 'GET' \
'http://localhost:8081/api/couriers/2' \
-H 'accept: */*'

curl -X 'DELETE' \
'http://localhost:8081/api/couriers/2' \
-H 'accept: */*'

curl -X 'POST' \
'http://localhost:8081/api/courier-locations' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"courierId": 2,
"lat": 233.13213,
"lng": 42141.214141
}'

curl -X 'GET' \
'http://localhost:8081/api/courier-distance/1' \
-H 'accept: */*'