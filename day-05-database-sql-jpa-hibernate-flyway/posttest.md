# Posttest - Database, SQL, JPA, Hibernate, Flyway & Query Relationship

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari Database, SQL, JPA, Hibernate, Flyway, dan Query Relationship.

1. Apa perbedaan in-memory Map dan database?

Jawaban:

```text
Data di Map
- Data tersimpan sementara, data akan hilang saat di aplikasi.
- Data tersimpan di memory aplikasi
- Kapasitasi dibatasi oleh ukuran RAM
- Data lebih cepat diproses

Data di Database
- Data tersimpan permanen
- Kapasitas lebih besar daibandingkan di Map
- Data lebih lama diproses
- Data mendukung query, relationship antar data, transaksi, dan cocok untuk production.
```

2. Kenapa database diperlukan pada aplikasi backend production?

Jawaban:

```text
Karena database dapat menyimpan data secara permanen, menjaga integritas data, mendukung query yang kompleks, relationship antar data, transaksi, dan dapat digunakan sebagai source of truth aplikasi.
```

3. Apa fungsi SELECT, INSERT, UPDATE, dan DELETE pada SQL?

Jawaban:

```text
- SELECT digunakan untuk mengambil data.
- INSERT digunakan untuk menambahkan data baru.
- UPDATE digunakan untuk mengubah data yang sudah ada.
- DELETE digunakan untuk menghapus data dari tabel.
```

4. Apa fungsi WHERE dan ILIKE di PostgreSQL?

Jawaban:

```text
WHERE --> digunakan untuk memfilter data berdasarkan kondisi tertentu. 
ILIKE --> digunakan untuk pencarian teks yang tidak membedakan huruf besar dan kecil.
```

5. Apa itu primary key dan foreign key?

Jawaban:

```text
Primary key --> kolom yang mengidentifikasi setiap row secara unik dalam sebuah tabel. 
Foreign key --> kolom yang menghubungkan suatu tabel dengan tabel lain.
```

6. Apa itu JPA dan Hibernate, serta apa perbedaannya?

Jawaban:

```text
JPA --> spesifikasi atau standar untuk ORM di Java. 
Hibernate -->  implementasi dari JPA yang menjalankan proses mapping object Java ke database.
Perbedaan keduanya adalah: 
JPA adalah aturan atau kontrak, sedangkan Hibernate adalah implementasi yang menjalankan aturan tersebut.
```

7. Apa itu Entity dan apa fungsi anotasi `@Entity`, `@Id`, serta `@GeneratedValue`?

Jawaban:

```text
Entity adalah class dari Java yang merepresentasikan table database.
@Entity --> class sebagai entitas.
@Id --> field sebagai primary key.
@GeneratedValue --> untuk menghasilkan nilai primary key secara otomatis.
```

8. Apa fungsi `@Table` dan `@Column`?

Jawaban:

```text
@Table --> untuk menentukan nama table yang dipetakan oleh entitas.
@Column --> untuk mengatur mapping field di Java ke kolom database beserta atributnya (contoh: nama kolom, panjang data, unique, dan nullable).
```

9. Apa itu Repository dan apa manfaat `JpaRepository`?

Jawaban:

```text
Repository adalah layer yang digunakan untuk mengakses data dari database.
JpaRepository berisi CRUD dan query dasar (contoh: save, findById, findAll, existsById, dll) tanpa perlu menulis secara manual.
```

10. Apa itu derived query method? Berikan contoh method untuk mencari customer berdasarkan email.

Jawaban:

```text
Derived query method adalah query yang dibuat otomatis oleh Spring Data JPA berdasarkan nama method.
Contoh method untuk mencari customer berdasarkan email:
Optional<CustomerEntity> findByEmail(String email);
```

11. Apa fungsi `@Query`? Jelaskan perbedaan JPQL dan native query.

Jawaban:

```text
@Query digunakan untuk menuliskan query secara manual pada repository.
Perbedaan JPQL dan Native Query:
- JPQL menggunakan nama entity dan field Java.
Native query menggunakan SQL asli beserta nama table dan kolom database.
- JPQL lebih portable, sedangkan native query cocok untuk query SQL yang kompleks atau spesifik database.
```

12. Apa itu Flyway dan kenapa database migration penting?

Jawaban:

```text
Flyway adalah tool untuk mengelola perubahan schema database menggunakan file migration.
Database migration penting agar perubahan schema dapat dilacak, konsisten di semua environment, memiliki riwayat perubahan.
```

13. Apa maksud penamaan file migration seperti `V1__create_customers_table.sql`? Kenapa migration lama sebaiknya tidak diubah setelah dijalankan?

Jawaban:

```text
V1 menunjukkan versi migration pertama dan create_customers_table menjelaskan tujuan migration tersebut.
Migration yang sudah dijalankan tidak sebaiknya diubah karena Flyway menyimpan history migration. Mengubah migration lama dapat menyebabkan perbedaan schema dan error pada environment lain.
```

14. Jelaskan relationship one-to-many dan many-to-one dengan contoh Customer dan Order.

Jawaban:

```text
- One-to-many berarti satu Customer dapat memiliki banyak Order.
- Many-to-one berarti banyak Order dimiliki oleh satu Customer.

Contoh:
Customer A memiliki Order 1, Order 2, dan Order 3.
Maka:
Customer --> Order (one-to-many)
Order --> Customer adalah (many-to-one).
```

15. Apa fungsi `@ManyToOne`, `@OneToMany`, dan `@JoinColumn`?

Jawaban:

```text
- @ManyToOne digunakan untuk mendefinisikan relasi banyak ke satu.
- @OneToMany digunakan untuk mendefinisikan relasi satu ke banyak.
- @JoinColumn digunakan untuk menentukan kolom foreign key yang menghubungkan entitas dengan entitas lain.
```

16. Apa perbedaan lazy loading dan eager loading? Kenapa `FetchType.LAZY` sering lebih aman sebagai default?

Jawaban:

```text
Lazy loading memuat data relasi hanya ketika data tersebut diakses.
Eager loading memuat data relasi bersamaan dengan entity utama.
FetchType.LAZY lebih aman karena menghindari pengambilan data yang tidak diperlukan sehingga penggunaan memory dan query database menjadi lebih efisien.
```

17. Apa itu SQL join? Jelaskan perbedaan `INNER JOIN` dan `LEFT JOIN`.

Jawaban:

```text
SQL join digunakan untuk menggabungkan data dari beberapa table yang memiliki hubungan.
INNER JOIN hanya mengembalikan data yang memiliki pasangan di kedua table.
LEFT JOIN mengembalikan semua data dari table kiri meskipun tidak memiliki pasangan di table kanan.
```

18. Apa itu N+1 query problem dan bagaimana cara sederhana menguranginya?

Jawaban:

```text
N+1 query problem terjadi ketika satu query utama menghasilkan banyak query tambahan untuk mengambil data relasi.
Contohnya mengambil 100 loan lalu melakukan query customer sebanyak 100 kali.
Cara sederhana menguranginya adalah menggunakan JOIN FETCH, DTO projection, atau query yang mengambil relasi dalam satu query.
```

19. Kenapa Entity sebaiknya tidak langsung dikembalikan sebagai API response? Apa manfaat DTO?

Jawaban:

```text
Entity sebaiknya tidak langsung dikembalikan karena dapat membocorkan struktur internal database, menyebabkan masalah lazy loading, dan membuat API contract sulit dikontrol.
DTO digunakan untuk menentukan data yang benar-benar ingin ditampilkan ke client sehingga API lebih aman, fleksibel, dan terstruktur.
```
```

20. Apa fungsi `@Transactional` dan kapan menggunakan `@Transactional(readOnly = true)`?

Jawaban:

```text
@Transactional digunakan untuk memastikan beberapa operasi database berjalan dalam satu transaction dan dapat di-rollback jika terjadi error.
@Transactional(readOnly = true) digunakan pada operasi yang hanya membaca data karena lebih optimal dan tidak melakukan perubahan pada database.
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Perbedaan JPA sebagai specification dan Hibernate sebagai implementation.
2. Cara membuat relationship antar entity menggunakan @ManyToOne dan @OneToMany.
3. Pentingnya Flyway untuk mengelola perubahan schema database secara terkontrol.
```

Apa 2 hal yang masih membingungkan?

```text
1. Kapan sebaiknya menggunakan JPQL dibanding native query pada kasus nyata.
2.
```

Apa 1 pertanyaan untuk mentor?

```text
Kapan sebaiknya menggunakan JPQL dibanding native query pada kasus nyata.
```