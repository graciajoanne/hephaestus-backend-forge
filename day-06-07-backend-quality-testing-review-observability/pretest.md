# Pretest - Backend Quality: Testing, Peer Code Review & Observability

## Objective

Pretest ini digunakan untuk mengukur pemahaman awal peserta tentang testing mindset, unit testing, peer code review, structured logging, correlation ID, dan PII safety.

## Instructions

- Jawab dengan singkat dan jelas.
- Tidak perlu membuka dokumentasi.
- Tidak dinilai hanya dari benar atau salah, tetapi dari cara berpikir.
- Total pertanyaan: 15.
- Estimasi waktu: 15-20 menit.

1. Apa perbedaan working code dan trusted code?

Jawaban:

```text
Working Code adalah kode yang berjalan dan menghasilkan expected value.
Trusted Code adalah kode yang sudah memiliki validasi dan testing sehingga bisa dipercaya.
```

2. Kenapa testing disebut sebagai risk reduction?

Jawaban:

```text
Karena testing dapat membantu menemukan bug di awal sehinggan dapat mengurangi risiko error saat aplikasi digunakan user.
```

3. Apa itu Given-When-Then?

Jawaban:

```text
Given --> kondisi awal
When  --> aksi yang dilakukan
Then --> Kondisi akhir yang diharapkan
```

4. Apa perbedaan unit test dan integration test?

Jawaban:

```text
Unit test digunakan untuk menguji satu komponen secara tersendiri.
Integration test digunakan untuk menguji interaksi antar komponen.
```

5. Kenapa service layer biasanya cocok untuk unit test?

Jawaban:

```text
Karena service layer berisi business logic yang dapat diuji secara tersendiri.
```

6. Apa fungsi JUnit 5?

Jawaban:

```text
JUnit 5 digunakan untuk membuat dan menjalankan unit test pada aplikasi Java.
```

7. Apa fungsi Mockito?

Jawaban:

```text
Mockito digunakan untuk membuat mock object sehingga dependency dapat disimulasikan saat testing.
```

8. Kenapa dependency seperti repository sering dimock saat unit test?

Jawaban:

```text
Agar test fokus pada logic yang diuji dan tidak bergantung pada database atau sistem eksternal.
```

9. Apa contoh test case penting untuk `LoanApplicationService`?

Jawaban:

```text
- Pengajuan pinjaman berhasil saat data valid.
- Pengajuan ditolak jika nominal melebihi batas.
- Pengajuan ditolak jika data wajib tidak lengkap.
- Repository dipanggil dengan data yang benar.
```

10. Apa tujuan peer code review?

Jawaban:

```text
Untuk menemukan bug, meningkatkan kualitas kode, memastikan standar coding dipatuhi.
```

11. Area apa saja yang perlu dicek saat code review backend?

Jawaban:

```text
Business logic, readability, maintainability, rrror handling, validation, security, performance, testing
```

12. Apa itu structured logging?

Jawaban:

```text
Structured logging adalah pencatatan log dalam format terstruktur sehingga mudah dicari dan dianalisis.
```

13. Apa fungsi `correlation_id`?

Jawaban:

```text
Untuk melacak satu request yang sama di berbagai service atau log sehingga proses debugging dan tracing lebih mudah.
```

14. Kapan menggunakan log level `info`, `warn`, dan `error`?

Jawaban:

```text
`info`: Informasi proses normal aplikasi.
`warn`: Kondisi tidak normal tetapi aplikasi masih berjalan.
`error`: Terjadi kegagalan atau exception yang mempengaruhi proses.
```

15. Sebutkan data yang tidak boleh ditulis mentah di log.

Jawaban:

```text
- Password
- JWT token
- Access token atau refresh token
- API key
- Data pribadi sensitif
```

## Self Assessment

| Area | Score 1-5 |
|---|---|
| Testing mindset |3|
| Given-When-Then |2|
| JUnit 5 |1|
| Mockito |1|
| Service layer testing |2|
| Peer code review |2|
| Structured logging |1|
| Correlation ID |1|
| PII safety |1|


