# Posttest - Validation & Error Handling

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari Validation & Error Handling.

### 1. Apa itu validasi request?

Jawaban:

```text
Validasi request adalah proses pengecekan data yang dikirim oleh user sebelum diproses oleh sistem, untuk memastikan data tersebut sudah sesuai dengan aturan yang telah ditentukan (seperti format email, field wajib, panjang karakter).
```

### 2. Kenapa backend tetap perlu validasi walaupun frontend sudah validasi?

Jawaban:

```text
Karena backend bukan hanya dikonsumsi oleh frontend tetapi juga oleh service lainnya. Frontend validation bisa dilewati oleh user teknis (misalnya mengirim request langsung via Postman). Oleh karena itu, backend harus menjadi gerbang utama untuk memastikan data tetap valid dan sistem terlindungi.
```

### 3. Apa fungsi @Valid?

Jawaban:

```text
@Valid digunakan untuk menjalankan proses validasi pada request object (DTO) saat diterima oleh Controller.
```

### 4. Apa fungsi @NotBlank?

Jawaban:

```text
@NotBlank digunakan secara khusus untuk tipe String, guna memastikan nilai field tersebut tidak null, tidak kosong (""), dan tidak hanya berisi spasi.
```

### 5. Apa perbedaan @NotBlank dan @NotNull?

Jawaban:

```text
@NotNull hanya mengecek apakah value tidak null, namun string kosong ("") masih bisa lolos. Sedangkan @NotBlank jauh lebih ketat karena memastikan string tidak null, tidak kosong, dan tidak hanya berisi spasi.
```

### 6. Apa fungsi @Email?

Jawaban:

```text
@Email digunakan untuk memastikan bahwa nilai dari suatu field benar-benar mengikuti format email yang valid (misalnya memiliki username dan domain yang dipisahkan simbol, seperti: user@email.com).
```

### 7. Apa fungsi @Size?

Jawaban:

```text
@Size digunakan untuk memvalidasi batas panjang karakter minimum dan maksimum dari sebuah teks.
```

### 8. Apa yang terjadi jika request gagal validasi?

Jawaban:

```text
Request tidak akan diteruskan ke layer Service. Spring akan otomatis melempar MethodArgumentNotValidException, yang kemudian akan mengembalikan response HTTP 400 Bad Request.
```

### 9. Apa itu MethodArgumentNotValidException?

Jawaban:

```text
MethodArgumentNotValidException adalah exception bawaan dari Spring Boot yang terlempar ketika sebuah input request gagal memenuhi aturan validasi yang ada di dalam objek yang menggunakan anotasi @Valid.
```

### 10. Apa itu standard error response?

Jawaban:

```text
Standard error response adalah format struktur JSON yang seragam untuk mengembalikan informasi error ke client, biasanya berisi kode error sistem (code), pesan ringkasan (message), dan daftar detail error secara spesifik.
```

### 11. Kenapa error response perlu konsisten?

Jawaban:

```text
Agar client lebih mudah melakukan parsing dan mapping terhadap pesan error yang diberikan. Hal ini akan mempercepat proses debugging dan menjaga user experience tetap baik karena format JSON tidak berubah-ubah di tiap endpoint.
```

### 12. Apa itu field-level error?

Jawaban:

```text
Field-level error adalah detail yang memberi tahu client field/kolom mana saja yang bermasalah beserta pesannya. Ini membantu frontend untuk langsung menampilkan error tepat di bawah kolom input terkait.
```

### 13. Apa itu custom exception?

Jawaban:

```text
Custom exception adalah exception yang kita definisikan dan buat sendiri untuk menangani kondisi error tertentu secara spesifik sesuai kebutuhan proses bisnis aplikasi.
```

### 14. Kenapa CustomerNotFoundException lebih baik daripada RuntimeException biasa?

Jawaban:

```text
Agar kita mengetahui error yang terjadi secara lebih spesifik dan jelas maksudnya. Selain itu, pengecualian ini memudahkan kita mengaturnya di GlobalExceptionHandler untuk menentukan HTTP status yang tepat (yaitu 404 Not Found).
```

### 15. Apa fungsi @ControllerAdvice?

Jawaban:

```text
@ControllerAdvice adalah komponen Spring Boot yang digunakan untuk menangani exception secara terpusat (menjadi global exception handler), sehingga error dari banyak Controller bisa ditangani di satu tempat.
```

### 16. Apa fungsi @ExceptionHandler?

Jawaban:

```text
@ExceptionHandler adalah anotasi yang digunakan untuk menentukan method mana yang bertugas menangani jenis exception tertentu.
```

### 17. Kenapa error handling sebaiknya centralized?

Jawaban:

```text
Agar kode tidak ditulis berulang-ulang di setiap Controller (sehingga code lebih mudah di-maintain dan Controller tetap bersih), serta memastikan semua penanganan error menghasilkan format standard yang konsisten.
```

### 18. Kapan menggunakan 400 Bad Request?

Jawaban:

```text
Ketika request yang dikirim oleh client tidak valid, salah format, atau melanggar aturan validasi (misalnya format email salah atau kolom wajib diisi tapi kosong).
```

### 19. Kapan menggunakan 404 Not Found?

Jawaban:

```text
Ketika data atau resource yang diminta oleh client tidak ditemukan di dalam sistem.
```

### 20. Kapan menggunakan 500 Internal Server Error?

Jawaban:

```text
Ketika terjadi error yang tidak terduga atau masalah teknis di sisi server yang membuat proses gagal berjalan.
```

### 21. Kenapa stack trace tidak boleh dikirim ke client?

Jawaban:

```text
Karena dapat menyebabkan kebocoran informasi sensitif terkait arsitektur internal aplikasi, versi library framework, hingga alur logika kode. Informasi ini sangat rentan dieksploitasi dan dijadikan celah keamanan oleh pihak yang tidak bertanggung jawab.
```

### 22. Jelaskan flow ketika POST /api/v1/customers menerima email invalid.

Jawaban:

```text
1) Request masuk ke Controller
2) Anotasi @Valid mengecek request
3) Validasi @Email gagal
4) Spring melempar MethodArgumentNotValidException
5) Ditangkap oleh GlobalExceptionHandler
6) API mengembalikan 400 Bad Request berserta list error-nya.
```

### 23. Jelaskan flow ketika GET /api/v1/customers/999 tidak menemukan data.

Jawaban:

```text
1) Request masuk ke Controller
2) Controller memanggil method di layer Service
3) Service mencari ID 999
4) Service mengecek ke penyimpanan namun bernilai null
5) Service melempar CustomerNotFoundException
6) Exception tersebut ditangkap oleh GlobalExceptionHandler 
7) API mengembalikan 404 Not Found.
```

### 24. Apa perbedaan validation error, business error, dan system error?

Jawaban:

```text
Validation error --> Error yang terjadi paling awal jika input format tidak valid atau tidak mematuhi aturan format teknis DTO (HTTP Status: 400).
Business error --> Error yang terjadi karena tidak sesuai dengan aturan pada proses bisnis (HTTP Status: 404).
System error --> Error yang terjadi jika terjadi masalah teknis tak terduga pada server (HTTP Status: 500).
```

### 25. Bagian mana yang paling sulit dari exercise Day 2?

Jawaban:

```text
Bagian tersulitnya adalah memahami alur kerja Exception Handler terpusat.
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Pentingnya melakukan validasi terhadap bad request (@NotBlank, @Email, dsb).
2. Cara membuat dan menggunakan Custom Exception spesifik.
3. Cara memusatkan seluruh error code agar tidak menumpuk di Controller menggunakan @ControllerAdvice.
```

Apa 2 hal yang masih membingungkan?

```text
1. Kapan harus benar-benar bikin Custom Exception baru vs menggunakan exception bawaan Java?
2. 
```

Apa 1 pertanyaan untuk mentor?

```text
Jika kedepannya API ini disambungkan ke Database, apakah validasi error yang datang langsung dari kegagalan query database (seperti duplikat email) akan di-handle juga oleh @ControllerAdvice yang sama?
```
