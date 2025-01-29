## Market-Flow, an E-Commerce Microservices Architecture

This repository contains the source code for an e-commerce system built using a microservices architecture. The project leverages **Java Spring Boot**, **RabbitMQ**, and **MySQL** to handle the core business logic and communication between services.

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Microservices](#microservices)
  - [Order Service](#order-service)
  - [Stock Service](#stock-service)
  - [Payment Service](#payment-service)
  - [Status Service](#status-service)
  - [Send Service](#send-service)
- [Architecture](#architecture)
- [Message Queue (RabbitMQ)](#message-queue-rabbitmq)
- [Database](#database)
- [Setup](#setup)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The e-commerce system consists of five core microservices:

- **Order Service**: Manages customer orders.
- **Stock Service**: Tracks inventory levels.
- **Payment Service**: Handles payments for orders.
- **Status Service**: Provides real-time tracking and order updates.
- **Send Service**: Manages order dispatch and notifications.

These services communicate asynchronously using RabbitMQ, ensuring decoupling and scalability.

---

## Technologies Used

- **Java Spring Boot**: For building REST APIs and managing business logic.
- **RabbitMQ**: Message broker for asynchronous communication.
- **MySQL**: Relational database for data storage.

---

## Microservices

### Order Service
- **Responsibilities**:
  - Receives customer orders.
  - Validates order details (e.g., client information, products, etc.).
  - Publishes order events to RabbitMQ for processing by other services.
  - Sends confirmation emails to customers regarding their order status.
  - Manages client and address information related to orders.

- **Endpoints**:
  - `POST /order`: Creates a new order in the system.
    - Response:
      - 201 Created: Order placed successfully.
      - 400 Bad Request: Invalid input or missing required fields.
  - `GET /order/{id}`: Retrieves an order by its ID.
    - Response:
      - 200 OK: Returns the order details.
      - 404 Not Found: Order not found.
  - `GET /orders`: Retrieves a list of all orders.
    - Response:
      - 200 OK: Returns a list of all orders.
      - 404 Not Found: No orders found.
  - `DELETE /order/{id}`: Deletes an order by its ID.
    - Response:
      - 200 OK: Order deleted successfully.
      - 404 Not Found: Order not found.
  - `GET /client/{id}`: Retrieves a client by its ID.
    - Response:
      - 200 OK: Returns the client details.
      - 404 Not Found: Client not found.
  - `GET /clients`: Retrieves a list of all clients.
    - Response:
      - 200 OK: Returns a list of all clients.
      - 404 Not Found: No clients found.
  - `GET /address/{id}`: Retrieves an address by its ID.
    - Response:
      - 200 OK: Returns the address details.
      - 404 Not Found: Address not found.
  - `GET /addresses`: Retrieves a list of all addresses.
    - Response:
      - 200 OK: Returns a list of all addresses.
      - 404 Not Found: No addresses found.
- **Running the Service**
  - Database: Ensure a database(MySQL) is connected for storing orders.
  - Java: Make sure you have JDK 21 or above installed.
- **To start application**
  - http://localhost:8080/swagger-ui/index.html#

### Stock Service
- **Responsibilities**:
  - Manages inventory levels.
  - Calculate total value of order.
  - Updates stock based on order events.
- **Endpoints**:
  - To-do.

### Payment Service
- **Responsibilities**:
  - Processes payments for orders.
  - Validates payment information.
  - Publishes payment events to RabbitMQ.
- **Endpoints**:
  - `POST /payments`: Process a payment.
  - `GET /payments`: List all payments.
  - `GET /payments/{id}`: Retrieve payment entity.
  - `PUT /payments/{id}`: Update payment entity.

### Send Service
- **Responsibilities**:
  - Handles order dispatch and delivery notifications.
  - Updates order status after dispatch.
- **Endpoints**:
  - `GET /addresses`: List all addresses.
  - `GET /addresses/{id}`: Return address by id.

  - `GET /clients`: List all clients.
  - `GET /client/{id}`: Return client by id.

  - `GET /orders`: List all orders.
  - `GET /order/{id}`: Return order by id.
  - `DELETE /order/{id}`: Delete order by id.

  - `GET /products`: List all products.
  - `GET /product/{id}`: Return product by id.

  - `GET /orders`: List all orders. **(Conflict!)**

### Status Service
- **Responsibilities**:
  - Provides real-time tracking and updates for orders.
  - Consumes events from other services to update order status.
- **Endpoints**:
  - `GET /status/{orderId}`: Retrieve the current order status.
  - `GET /status/all`: Get all order statuses.

---

## Architecture
![market-flow-architecture](https://github.com/user-attachments/assets/851f6300-be37-4532-a9d2-2b28ec3e3c23)

The system employs a microservices architecture where each service operates independently and communicates through RabbitMQ.

---

## Message Queue (RabbitMQ)

RabbitMQ acts as the backbone of communication between services. Key exchange and queue details:

- **Exchanges**:
  - `exchange.direct`: For direct events.
  - `exchange.fanout`: For broadcast events.

- **Queues**:
  - `queue.payment.order`: New orders will arrive at 'Payment' service through ``exchange.fanout``.
  - `queue.stock.order`: Orders newly created will be set to 'Stock' service through `exchange.fanout`.
  - `queue.payment.stock`: Processed orders from stock will be sent to 'Payment' service.
  - `queue.payment.order.pay`: 'Payment' and 'Order' services queue of monetary communcation.
  - `queue.send.payment`: Suceed payment will be sent to 'Send' service.

---

## Database

Each service has its own database schema in **MySQL**, following the **Database Per Service** pattern.

- **Order Service**: `orders` table.
- **Stock Service**: `products` table.
- **Payment Service**: `payments` table.
- **Send Service**: `dispatch` table.
- **Status Service**: `order_status` table.

---

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Elderes/market-flow.git
   cd market-flow
