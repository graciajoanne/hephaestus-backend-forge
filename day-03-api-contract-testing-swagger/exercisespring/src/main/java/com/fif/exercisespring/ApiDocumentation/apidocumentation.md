# API Documentation - Customer Management API

## Base URL

```text
http://localhost:8080
```

---

# Create Customer

## Method

```text
POST
```

## URL

```text
/api/v1/customers
```

## Description

Membuat customer baru.

## Request Body

```json
{
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

## Success Response

Status:

```text
201 Created
```

Body:

```json
{
  "id": 1,
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789",
  "created_at": "2026-06-17T15:30:45",
  "updated_at": "2026-06-17T15:30:45"
}
```

## Error Response

Status:

```text
400 Bad Request
```

Body:

```json
{
  "code": "VALIDATION_ERROR",
  "message": "Invalid request",
  "errors": [
    {
      "field": "email",
      "message": "email format is invalid"
    }
  ]
}
```

---

# Get All Customers

## Method

```text
GET
```

## URL

```text
/api/v1/customers
```

## Description

Mengambil seluruh data customer.

## Query Parameters

| Parameter | Type | Required | Description |
|------------|------|----------|-------------|
| name | String | No | Search customer by name |
| email | String | No | Search customer by email |
| page | Integer | No | Page number for pagination |
| size | Integer | No | Number of records per page |

## Examples

Get all customers:

```text
GET /api/v1/customers
```

Search by name:

```text
GET /api/v1/customers?name=budi
```

Search by email:

```text
GET /api/v1/customers?email=budi@mail.com
```

Pagination:

```text
GET /api/v1/customers?page=0&size=10
```

## Success Response

Status:

```text
200 OK
```

Body:

```json
[
  {
    "id": 1,
    "full_name": "Budi Santoso",
    "email": "budi@mail.com",
    "phone_number": "08123456789",
    "created_at": "2026-06-17T15:30:45",
    "updated_at": "2026-06-17T15:30:45"
  },
  {
    "id": 2,
    "full_name": "Siti Aminah",
    "email": "siti@mail.com",
    "phone_number": "08222222222",
    "created_at": "2026-06-17T15:31:10",
    "updated_at": "2026-06-17T15:31:10"
  }
]
```

---

# Get Customer By ID

## Method

```text
GET
```

## URL

```text
/api/v1/customers/{id}
```

## Description

Mengambil data customer berdasarkan ID.

## Path Parameter

| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | Customer ID |

## Success Response

Status:

```text
200 OK
```

Body:

```json
{
  "id": 1,
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789",
  "created_at": "2026-06-17T15:30:45",
  "updated_at": "2026-06-17T15:30:45"
}
```

## Error Response

Status:

```text
404 Not Found
```

Body:

```json
{
  "code": "CUSTOMER_NOT_FOUND",
  "message": "Customer not found with id: 999",
  "errors": []
}
```

---

# Update Customer

## Method

```text
PUT
```

## URL

```text
/api/v1/customers/{id}
```

## Description

Mengubah seluruh data customer.

## Path Parameter

| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | Customer ID |

## Request Body

```json
{
  "full_name": "Budi Santoso Updated",
  "email": "budiupdated@mail.com",
  "phone_number": "08999999999"
}
```

## Success Response

Status:

```text
200 OK
```

Body:

```json
{
  "id": 1,
  "full_name": "Budi Santoso Updated",
  "email": "budiupdated@mail.com",
  "phone_number": "08999999999",
  "created_at": "2026-06-17T15:30:45",
  "updated_at": "2026-06-17T16:00:00"
}
```

## Error Response

Status:

```text
404 Not Found
```

Body:

```json
{
  "code": "CUSTOMER_NOT_FOUND",
  "message": "Customer not found with id: 999",
  "errors": []
}
```

---

# Patch Customer

## Method

```text
PATCH
```

## URL

```text
/api/v1/customers/{id}
```

## Description

Mengubah sebagian data customer tanpa harus mengirim seluruh field.

## Path Parameter

| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | Customer ID |

## Request Body

Update email saja:

```json
{
  "email": "newemail@mail.com"
}
```

Update nama dan nomor telepon:

```json
{
  "full_name": "Budi Baru",
  "phone_number": "08111111111"
}
```

## Success Response

Status:

```text
200 OK
```

Body:

```json
{
  "id": 1,
  "full_name": "Budi Baru",
  "email": "newemail@mail.com",
  "phone_number": "08111111111",
  "created_at": "2026-06-17T15:30:45",
  "updated_at": "2026-06-17T16:10:00"
}
```

## Error Response

Status:

```text
404 Not Found
```

Body:

```json
{
  "code": "CUSTOMER_NOT_FOUND",
  "message": "Customer not found with id: 999",
  "errors": []
}
```

---

# Delete Customer

## Method

```text
DELETE
```

## URL

```text
/api/v1/customers/{id}
```

## Description

Menghapus customer berdasarkan ID.

## Path Parameter

| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | Customer ID |

## Success Response

Status:

```text
204 No Content
```

Body:

```text
No Response Body
```

## Error Response

Status:

```text
404 Not Found
```

Body:

```json
{
  "code": "CUSTOMER_NOT_FOUND",
  "message": "Customer not found with id: 999",
  "errors": []
}
```

---

# Timestamp Fields

| Field | Description |
|---------|------------|
| created_at | Waktu saat customer dibuat |
| updated_at | Waktu terakhir customer diperbarui |

Notes:

- Saat customer dibuat pertama kali, `created_at` dan `updated_at` bernilai sama.
- Saat customer diubah menggunakan PUT atau PATCH, hanya `updated_at` yang berubah.

---

# Validation Rules

## CreateCustomerRequest

| Field | Validation |
|---------|------------|
| full_name | Required, minimum 3 characters, maximum 100 characters |
| email | Required, valid email format |
| phone_number | Required, minimum 10 characters |

## UpdateCustomerRequest

| Field | Validation |
|---------|------------|
| full_name | Required, minimum 3 characters, maximum 100 characters |
| email | Required, valid email format |
| phone_number | Required, minimum 10 characters |

---

# Simple Pagination Contract

## Example Request

```text
GET /api/v1/customers?page=0&size=10
```

## Example Response

```json
{
  "page": 0,
  "size": 10,
  "total_elements": 25,
  "total_pages": 3,
  "content": [
    {
      "id": 1,
      "full_name": "Budi Santoso",
      "email": "budi@mail.com",
      "phone_number": "08123456789",
      "created_at": "2026-06-17T15:30:45",
      "updated_at": "2026-06-17T15:30:45"
    }
  ]
}
```

Note:

- Pagination contract saat ini hanya dokumentasi.
- Implementasi pagination belum dilakukan.

---

# Error Codes

| Code | Description |
|--------|------------|
| VALIDATION_ERROR | Request validation failed |
| CUSTOMER_NOT_FOUND | Customer with specified ID does not exist |
| INTERNAL_SERVER_ERROR | Unexpected server error |

---

# Swagger UI

Swagger UI can be accessed at:

```text
http://localhost:8080/swagger-ui.html
```

or

```text
http://localhost:8080/swagger-ui/index.html
```

---

# OpenAPI JSON

OpenAPI specification can be accessed at:

```text
http://localhost:8080/v3/api-docs
```