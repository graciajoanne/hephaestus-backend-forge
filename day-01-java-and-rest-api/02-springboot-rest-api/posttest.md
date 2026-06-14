# Posttest - Spring Boot REST API

Jawab pertanyaan berikut setelah membaca materi dan membuat Customer REST API.

### 1. Apa itu Spring Boot?

Jawaban:

```text
Spring Boot adalah framework yang membantu developer membuat aplikasi backend Java dengan lebih cepat dan mudah. Spring Boot menyediakan konfigurasi awal dan dependency yang memudahkan pembuatan REST API.
```

### 2. Apa fungsi Spring Initializr?

Jawaban:

```text
Spring Initializr digunakan untuk membuat project Spring Boot secara otomatis dengan struktur dasar dan dependency yang dibutuhkan. Dengan Spring Initializr, kita tidak perlu membuat project dari nol karena konfigurasi awal sudah disiapkan.
```

### 3. Saat membuat project, kenapa memilih Maven?

Jawaban:

```text
Karena Maven membantu mengelola dependency, build project, dan menjalankan aplikasi dengan lebih mudah. Kita cukup menambahkan dependency di pom.xml dan Maven akan mengunduh library yang dibutuhkan secara otomatis.
```

### 4. Apa fungsi dependency Spring Web?

Jawaban:

```text
Spring Web digunakan untuk membuat REST API dan menangani request & response HTTP. Dependency ini menyediakan annotation seperti @RestController, @GetMapping, @PostMapping, dan fitur web lainnya.
```

### 5. Kenapa Day 1 belum menambahkan dependency database?

Jawaban:

```text
Karena pada Day 1 fokus pembelajaran masih pada Java Fundamental, seperti class, object, constructor, getter/setter, method, encapsulation, serta penggunaan List dan Map. Data customer disimpan menggunakan Map di memory agar lebih mudah memahami logika program terlebih dahulu. Setelah memahami dasar pemrograman dan alur pengelolaan data, barulah pada tahap berikutnya diperkenalkan Spring Boot.
```

### 6. Apa fungsi file pom.xml?

Jawaban:

```text
File pom.xml adalah file konfigurasi Maven yang berisi informasi tentang project, dependency yang digunakan, versi library, dan pengaturan build aplikasi.
```

### 7. Apa fungsi TrainingApplication.java?

Jawaban:

```text
TrainingApplication.java adalah class utama yang menjadi titik awal menjalankan aplikasi Spring Boot. Di dalamnya terdapat method main yang digunakan untuk menjalankan aplikasi.
```

### 8. Apa fungsi @SpringBootApplication?

Jawaban:

```text
@SpringBootApplication menandai class utama Spring Boot dan memberitahu Spring untuk melakukan konfigurasi otomatis dan melakukan scanning terhadap component seperti Controller dan Service.
```

### 9. Bagaimana cara menjalankan Spring Boot dari IDE?

Jawaban:

```text
Buka class utama aplikasi, misalnya TrainingApplication.java (atau ExercisespringApplication.java), kemudian klik tombol Run yang tersedia di IDE seperti VS Code.
```

### 10. Bagaimana cara menjalankan Spring Boot dari terminal?

Jawaban:

```text
Melalui terminal dapat dijalankan menggunakan perintah mvn spring-boot:run di Windows.
```

### 11. Jika membuka http://localhost:8080 dan hasilnya 404, apakah selalu error? Jelaskan.

Jawaban:

```text
Tidak selalu error. Jika aplikasi berhasil berjalan tetapi belum memiliki endpoint pada path "/", maka browser akan menampilkan 404. Artinya server sudah aktif, hanya saja route yang diminta belum tersedia.
```

### 12. Apa itu REST API?

Jawaban:

```text
REST API adalah cara aplikasi backend menyediakan endpoint agar client dapat mengirim request dan menerima response melalui HTTP. Data yang dikirim biasanya menggunakan format JSON.
```

### 13. Apa itu endpoint?

Jawaban:

```text
Endpoint adalah alamat URL yang dapat diakses oleh client untuk berinteraksi dengan API. Contohnya adalah GET /api/v1/customers atau POST /api/v1/customers.
```

### 14. Apa perbedaan request dan response?

Jawaban:

```text
Request adalah data atau permintaan yang dikirim froent end ke server. Response adalah hasil yang dikirim kembali oleh server kepada front end setelah request diproses.
```

### 15. Kenapa JSON menggunakan snake_case, sedangkan Java menggunakan camelCase?

Jawaban:

```text
Karena keduanya mengikuti konvensi yang umum digunakan pada masing-masing lingkungan. JSON sering menggunakan snake_case agar mudah dibaca, sedangkan Java menggunakan camelCase sebagai standar penulisan nama variabel dan method.
```

### 16. Apa fungsi @JsonProperty?

Jawaban:

```text
@JsonProperty digunakan untuk menghubungkan nama field JSON dengan nama field di Java. Misalnya JSON menggunakan full_name sedangkan Java menggunakan fullName.
```

### 17. Apa tugas Controller?

Jawaban:

```text
Controller bertugas menerima request, membaca parameter atau request body, memanggil Service, menentukan status code, dan mengembalikan response.
```

### 18. Apa tugas Service?

Jawaban:

```text
Service bertugas menangani business logic aplikasi. Pada latihan ini Service digunakan untuk membuat customer, menyimpan data ke Map, mengambil data customer, mengubah data customer, dan menghapus data customer.
```

### 19. Apa itu DTO?

Jawaban:

```text
DTO atau Data Transfer Object adalah object yang digunakan untuk mengirim dan menerima data antara client dan aplikasi. DTO membantu memisahkan data API dengan model internal aplikasi.
```

### 20. Apa itu Model?

Jawaban:

```text
Model adalah representasi data yang digunakan di dalam aplikasi. Pada latihan ini Customer merupakan model yang menyimpan data seperti id, fullName, email, dan phoneNumber.
```

### 21. Kenapa request body tidak langsung menggunakan model?

Jawaban:

```text
Karena request dari client tidak selalu memiliki struktur yang sama dengan model internal aplikasi. Menggunakan DTO membuat aplikasi lebih fleksibel dan mengurangi ketergantungan antara API dan model.
```

### 22. Kenapa response tidak langsung menggunakan model?

Jawaban:

```text
Karena model merupakan struktur internal aplikasi yang sebaiknya tidak langsung diekspos ke client. Dengan Response DTO kita bisa mengatur data apa saja yang ingin dikirim tanpa memengaruhi model.
```

### 23. Kenapa business logic tidak boleh ditaruh di Controller?

Jawaban:

```text
Karena Controller seharusnya hanya menangani request dan response. Jika business logic diletakkan di Controller, kode akan menjadi sulit dirawat dan sulit digunakan kembali. Business logic lebih tepat ditempatkan di Service.
```

### 24. Kapan menggunakan 200 OK?

Jawaban:

```text
200 OK digunakan ketika request berhasil diproses, misalnya saat mengambil data customer atau mengubah data customer yang berhasil.
```

### 25. Kapan menggunakan 201 Created?

Jawaban:

```text
201 Created digunakan ketika berhasil membuat data baru, misalnya saat menjalankan endpoint POST /api/v1/customers.
```

### 26. Apa bedanya path parameter dan query parameter?

Jawaban:

```text
Path parameter merupakan bagian dari URL yang biasanya digunakan untuk mengidentifikasi resource tertentu, misalnya /api/v1/customers/1. Query parameter digunakan untuk filter atau pencarian, misalnya /api/v1/customers?name=budi.
```

### 27. Jelaskan flow POST /api/v1/customers dari request sampai response.

Jawaban:

```text
Client mengirim request POST beserta JSON customer ke endpoint /api/v1/customers. Controller menerima request tersebut melalui @RequestBody dan mengubahnya menjadi CreateCustomerRequest. Selanjutnya Controller memanggil CustomerService untuk membuat customer baru. Service membuat object Customer, memberikan id, menyimpan data ke Map, lalu mengubahnya menjadi CustomerResponse. CustomerResponse dikembalikan ke Controller dan Controller mengirim response JSON ke client dengan status 201 Created.
```

### 28. Bagian mana yang paling sulit saat membuat Spring Boot REST API?

Jawaban:

```text
Bagian yang paling sulit bagi saya adalah memahami bagaimana data mengalir dari request, masuk ke Controller, diproses di Service, lalu diubah menjadi response. Selain itu saya juga perlu membiasakan diri menggunakan annotation seperti @RestController, @RequestBody, dan @JsonProperty agar sesuai dengan cara kerja Spring Boot.
```

### 29. Error apa yang kamu temui dan bagaimana cara menyelesaikannya?

Jawaban:

```text
Salah satu error yang saya temui adalah port 8080 sudah digunakan oleh aplikasi lain sehingga Spring Boot gagal dijalankan. Saya menyelesaikannya dengan mengecek proses yang menggunakan port tersebut atau mengganti port aplikasi menjadi 8081 pada application.properties.
```

### 30. Apa yang ingin kamu pelajari berikutnya?

Jawaban:

```text
Saya ingin mempelajari integrasi database menggunakan Spring Data JPA, vaslidasi request, error handling yang lebih baik, serta bagaimana membuat aplikasi Spring Boot dengan struktur yang lebih lengkap dan mengikuti praktik yang digunakan di dunia kerja.
```
