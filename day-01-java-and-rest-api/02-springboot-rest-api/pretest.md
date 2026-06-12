# Pretest - Spring Boot REST API

Jawab pertanyaan berikut dengan bahasa sendiri sebelum membaca materi Spring Boot REST API.

### 1. Apa itu Spring Boot?

Jawaban:

```text
Spring Boot adalah framework berbasis Java untuk membuat aplikasi.
```

### 2. Apa itu REST API?

Jawaban:

```text
REST API adalah pola arsitektur yang memastikan request diproses secara jelas dimana setiap request melewati jalur yang terdefinisi dengan baik, data boundary terkontrol, responsibility terpisah, API mudah dikonsumsi client. Gaya arsitektur ini menjadi standar untuk membangun API dengan memanfaatkan protokol HTTP.
```

### 3. Apa itu HTTP?

Jawaban:

```text
HTTP adalah protokol komunikasi yang digunakan di web tanpa keamanan (tidak seperti HTTPS).
```

### 4. Apa itu JSON?

Jawaban:

```text
JSON adalah jenis file yang digunakan untuk perantara komunikasi, biasanya isinya file yang berpasangan key dan value atau yang disebut Dictionary.
```

### 5. Apa itu endpoint?

Jawaban:

```text
Tulis jawaban di sini.
```

### 6. Apa itu request?

Jawaban:

```text
Permintaan yang dikirim ke server yang nantinya akan mengembalikan response.
```

### 7. Apa itu response?

Jawaban:

```text
Pesan balasan dari server untuk menjawab request yang masuk.
```

### 8. Apa fungsi GET?

Jawaban:

```text
Metode yang digunakan untuk mengambil data.
```

### 9. Apa fungsi POST?

Jawaban:

```text
Metode yang digunakan untuk menambah data baru.
```

### 10. Apa fungsi PUT?

Jawaban:

```text
Metode yang digunakan untuk memperbaharui data.
```

### 11. Apa fungsi DELETE?

Jawaban:

```text
Metode yang digunakan untuk menghapus data.
```

### 12. Kapan menggunakan 200 OK?

Jawaban:

```text
Ketika request berhasil diproses dan dikembalikan atau status request "sukses".
```

### 13. Kapan menggunakan 201 Created?

Jawaban:

```text
Ketika request untuk membuat data baru (biasanya menggunakan POST) berhasil diproses dan data tersebut sukses disimpan di server.
```

### 14. Kapan menggunakan 400 Bad Request?

Jawaban:

```text
Ketika request salah, tidak lengkap, atau tidak valid, sehingga server tidak bisa memprosesnya.
```

### 15. Kapan menggunakan 404 Not Found?

Jawaban:

```text
Ketika URL yang diakses salah, atau data yang dicari tidak ditemukan di dalam database.
```

### 16. Kapan menggunakan 500 Internal Server Error?

Jawaban:

```text
Ketika terjadi error, sehingga server gagal memproses permintaan meskipun request sudah benar.
```



### 17. Apa itu path parameter?

Jawaban:

```text
Tulis jawaban di sini.
```

### 18. Apa itu query parameter?

Jawaban:

```text
Tulis jawaban di sini.
```

### 19. Apa itu request body?

Jawaban:

```text
Tulis jawaban di sini.
```

### 20. Apa tugas Controller?

Jawaban:

```text
Sebagai pintu awal yang menerima request, menentukan fungsi apa yang dipanggil, memvalidasi input awal, dan mengembalikan response kembali.
```

### 21. Apa tugas Service?

Jawaban:

```text
Memproses request yang sudah masuk melalui controller yang nantinya akan 
```

### 22. Apa itu DTO?

Jawaban:

```text
Data Transfer Object adalah class yang digunakan untuk transfer data antar layer.
```

### 23. Apa itu Model?

Jawaban:

```text
Model adalah representatif tabel ke dalam Java.
```

### 24. Kenapa business logic tidak ditaruh di Controller?

Jawaban:

```text
Karena Controller itu hanya perantara, dan business logic adalahs sesuatu yang tidak boleh berubah. Sehingga jika ada perubahan di Controller business logic tidak berubah. Maka dari itu, business logic dimasukkan di Service.
```

### 25. Kenapa request/response perlu DTO?

Jawaban:

```text
Agar request dapat ditransfer dari layer satu ke layer lainnya.
```
