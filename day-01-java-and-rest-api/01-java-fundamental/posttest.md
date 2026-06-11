# Posttest - Java Fundamental

Jawab pertanyaan berikut setelah membaca materi dan mengerjakan exercise Java Fundamental.

### 1. Apa itu variable?

Jawaban:

```text
Variable adalah tempat untuk menyimpan data yang nilainya dapat berubah-ubah. 
Contoh: String fullName = "Budi Santoso";
```

### 2. Apa perbedaan String, int, Long, dan boolean?

Jawaban:

```text
String --> tipe data yang digunakan untuk menyimpan teks. Dalam Java, String termasuk tipe data non-primitif karena memiliki method di dalam class-nya. Contoh: "Budi Santoso", "budi@mail.com".
- int --> tipe data yang digunakan untuk menyimpan bilangan bulat (primitif). Contoh: 10, 25, 50.
- Long --> tipe data non-primitif dari long dimana long merupakan tipe data yang digunakan untuk menyimpan bilangan bulat yang lebih besar dari int. Contoh: 9999999L
- boolean merupakan tipe data yang digunakan untuk menyimpan nilai logika, seperti true dan false (primitif). Contoh: True, False.
```

### 3. Kenapa Java menggunakan camelCase untuk variable?

Jawaban:

```text
camelCase digunakan agar nama variabel yang terdiri dari beberapa kata mudah dibaca dan membantu menjaga konsistensi penulisan kode sehingga lebih mudah dipahami. Kata pertama diawali huruf kecil, sedangkan kata berikutnya diawali huruf besar. 
Contohnya: customerService, customerStorage, fullName.
```

### 4. Apa perbedaan class dan object?

Jawaban:

```text
Class adalah blueprint untuk membuat object, sedangkan object adalah hasil yang dibuat dari class.
```

### 5. Apa itu field?

Jawaban:

```text
Field adalah atribut yang dimiliki oleh sebuah class dan digunakan untuk menyimpan data dari object yang dibuat dari class. 
Contoh: id, full name, dan email adalah field  untuk menyimpan informasi customer
private Long id;
private String fullName;
private String email;
```

### 6. Apa itu method?

Jawaban:

```text
Method adalah fungsi yang berada di dalam class dan digunakan untuk menjalankan suatu proses. 
```

### 7. Apa itu parameter?

Jawaban:

```text
Parameter adalah input yang diterima method untuk digunakan dalam menjalankan suatu proses.
```

### 8. Apa itu return value?

Jawaban:

```text
Return value adalah nilai yang dikembalikan oleh method setelah prosesnya selesai dijalankan, biasanya di awali dengan kata 'return'.
```

### 9. Apa fungsi constructor?

Jawaban:

```text
Constructor adalah method yang digunakan untuk memberikan nilai awal saat sebuah object dibuat. Constructor akan dipanggil pertama kali secara otomatis ketika object dibuat.
```

### 10. Apa fungsi `this`?

Jawaban:

```text
'this' berfungsi merujuk pada object yang digunakan saat ini, biasanya 'this' untuk membedakan field dan parameter yang biasanya memiliki nama yang sama.
```

### 11. Kenapa field dibuat private?

Jawaban:

```text
Biasanya untuk menjaga keamanan data agar tidak bisa diakses atau diubah secara semabarangan di class lain, melainkan menggunakan fungsi setter dan getter jika ingin mengaksesnya.
```

### 12. Apa fungsi getter dan setter?

Jawaban:

```text
Getter berfungsi untuk mengambil data dari field private, sedangkan setter digunakan untuk mengubah nilai field private.
```

### 13. Apa itu encapsulation?

Jawaban:

```text
Encapsulation adalah pembatasan akses terhadap data dengan cara menyembunyikan field dengan private dan menyediakan fungsi seperti getter dan setter untuk mengaksesnya.
```

### 14. Apa perbedaan List dan Map?

Jawaban:

```text
List digunakan untuk menyimpan kumpulan data secara berurutan, biasanya dituliskan dalam []. Sedangkan Map digunakan untuk menyimpan data dalam bentuk pasangan key dan value, biasanya dituliskan dalam {}.
```

### 15. Kenapa CustomerService menggunakan Map<Long, Customer>?

Jawaban:

```text
Karena data customer disimpan dengan pasangan key dan value, di mana Long sebagai key (ID) dan Customer sebagai value (data customer). Setiap customer bisa diakses langsung menggunakan ID tanpa harus melakukan pencarian satu per satu.
```

### 16. Kenapa getAllCustomers mengembalikan List<Customer>?

Jawaban:

```text
Karena method getAllCustomers ini bertujuan mengambil semua data customer dalam bentuk kumpulan yang bisa diiterasi (looping). Data disimpan di Map<Long, Customer>, tetapi saat ditampilkan hanya butuh nilai Customer saja. Oleh karena itu, customerStorage.values() diubah menjadi List<Customer>.
```

### 17. Apa itu interface?

Jawaban:

```text
Interface adalah kumpulan aturan yang harus diimplementasikan oleh child class yang menggunakannya (implement).
```

### 18. Apa perbedaan interface dan abstract class?

Jawaban:

```text
Interface digunakan untuk menentukan aturan yang harus diikuti child class, sedangkan abstract class digunakan sebagai class dasar yang dapat memiliki implementasi method.
```

### 19. Dari exercise, jelaskan flow createCustomer.

Jawaban:

```text
1) Flow createCustomer dimulai ketika method ini dipanggil dari file Main untuk Customer 1 (Budi Santoso).
2) Method menerima input data Customer 1 dari parameter berupa fullName, email, dan phoneNumber.
3) Setelah masuk ke dalam method, sistem langsung melakukan validasi sederhana untuk memastikan fullName tidak kosong atau null.
4) Kalau data valid, sistem lanjut membuat object Customer baru berdasarkan input yang diterima.
5) Sistem mengambil nilai sequence yang pertama kali bernilai 1, lalu membuat object Customer dengan ID 1 dan data Budi Santoso.
6) Customer Budi disimpan ke dalam Map dengan key 1, lalu sequence dinaikkan menjadi 2.
7) Proses yang sama berulang saat createCustomer dipanggil untuk Customer 2 (Siti Aminah).
8) Kalau semua proses berhasil, data customer yang baru dibuat akan dikembalikan sebagai hasil.
9) Kalau ada error di validasi, proses langsung berhenti dan masuk ke catch berupa message.
```

### 20. Bagian mana yang paling sulit?

Jawaban:

```text
Bagian yang paling sulit bagi saya adalah menerjemahkan logika yang sudah saya pahami ke dalam kode yang berjalan sesuai kebutuhan. Saya juga cukup kesulitan saat membaca dan memahami error yang muncul, serta menentukan alur program seperti bagaimana data customer disimpan dan bagaimana ID bisa bertambah otomatis setiap kali ada customer baru menggunakan sequence.
```
