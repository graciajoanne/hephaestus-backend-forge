# Posttest - Backend Quality: Testing, Peer Code Review & Observability

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari testing mindset, unit testing, peer code review, structured logging, correlation ID, dan PII safety.

## Instructions

- Jawab dengan singkat dan jelas.
- Total pertanyaan: 10.

1. Kenapa testing disebut risk reduction?

Jawaban:

```text
Karena testing membantu mengurangi risiko bug, regression, dan pelanggaran business rule sebelum aplikasi digunakan di production.
```

2. Apa perbedaan working code dan trusted code?

Jawaban:

```text
- Working code adalah kode yang terlihat berjalan saat diuji manual.
- Trusted code adalah kode yang perilaku pentingnya sudah diverifikasi melalui testing dan review sehingga lebih aman untuk diubah dan dikembangkan.
```

3. Jelaskan pola Given-When-Then.

Jawaban:

```text
Given menyiapkan kondisi awal.
When menjalankan aksi yang diuji.
Then memverifikasi hasil yang diharapkan.
Pola ini membuat test lebih mudah dibaca dan dipahami.
```

4. Kenapa service layer cocok untuk unit test?

Jawaban:

```text
Karena business rule dan logic utama biasanya berada di service layer. Dependency seperti repository dapat dimock sehingga pengujian fokus pada logic bisnis.
```

5. Apa peran JUnit 5 dan Mockito dalam unit test?

Jawaban:

```text
- JUnit 5 digunakan untuk membuat dan menjalankan test serta melakukan assertion.
- Mockito digunakan untuk membuat mock dependency sehingga test tidak bergantung pada database atau service eksternal.
```

6. Sebutkan 3 test case penting untuk `LoanApplicationService`.

Jawaban:

```text
1. Loan berhasil dibuat ketika customer ditemukan.
2. Loan gagal dibuat ketika customer tidak ditemukan.
3. Loan gagal dibuat ketika data tidak valid.
```

7. Apa tujuan peer code review?

Jawaban:

```text
Menemukan bug tersembunyi, menjaga standar kualitas kode, meningkatkan readability, dan memastikan implementasi sesuai business rule serta keamanan.
```

8. Apa itu structured logging dan kenapa penting?

Jawaban:

```text
Structured logging adalah pencatatan log dalam format terstruktur dengan field yang jelas. Penting karena memudahkan pencarian, filtering, monitoring, debugging, dan audit.
```

9. Apa fungsi `correlation_id` pada log dan error response?

Jawaban:

```text
Correlation_id digunakan untuk melacak satu request yang sama di seluruh log, error response, dan service lain sehingga proses tracing dan debugging menjadi lebih mudah.
```

10. Sebutkan minimal 5 data yang tidak boleh ditulis mentah di log.

Jawaban:

```text
1. Password
2. Access token & Refresh token
3. PIN
4. NIK
5. Nomor rekening, Nomor telepon, Email
6. OTP
7. JWT Token
8. Secret atau API key
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Testing bertujuan mengurangi risiko, bukan menjamin aplikasi bebas bug.
2. Unit test lebih efektif jika fokus pada business logic di service layer
3. Structured logging, correlation_id, dan PII safety sangat penting untuk observability
```

Apa 2 hal yang masih membingungkan?

```text
1. Kapan sebaiknya menggunakan unit test dibanding integration test pada kasus yang kompleks.
```

Apa 1 hal yang akan kamu cek saat melakukan code review?

```text
Apakah business rule dan access control sudah benar serta memiliki test yang relevan untuk mencegah regression.
```
