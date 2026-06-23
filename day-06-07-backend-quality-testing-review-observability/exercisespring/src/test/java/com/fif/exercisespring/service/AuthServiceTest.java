package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.LoginRequest;
import com.fif.exercisespring.dto.LoginResponse;
import com.fif.exercisespring.dto.UserResponse;
import com.fif.exercisespring.exception.UnauthorizedException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Menguji business logic AuthService. Karena kita ingin memastikan login bekerja sesuai requirement tanpa harus menjalankan aplikasi secara penuh.
// Yang diuji: login berhasil, login gagal, validasi token, auth/me
/* @Test yg harus dibuat:
1. `should_login_successfully_when_username_and_password_are_valid`
2. `should_throw_unauthorized_when_password_is_invalid`
3. `should_return_current_user_when_token_is_valid`
4. `should_throw_unauthorized_when_token_is_missing`
5. `should_throw_unauthorized_when_token_is_invalid`
 */
class AuthServiceTest {
    // Object yang akan diuji
    private AuthService authService;
    @BeforeEach
    void setUp() {
        //Method ini dijalankan sebelum setiap test.
        // Tujuan: Buat AuthService baru agar setiap test, biar kondisi awalnya bersih.
        authService = new AuthService();
    }

    @Test
    void should_login_successfully_when_username_and_password_are_valid() {
        // GIVEN
        // Menyiapkan data test
        LoginRequest request = new LoginRequest("admin", "admin123");

        // WHEN
        // Menjalankan method login
        LoginResponse response = authService.login(request);

        // THEN
        // Memastikan hasil sesuai
        assertNotNull(response);
        assertEquals("admin",response.getUsername());
        assertEquals("ADMIN",response.getRole());
        assertEquals("token-admin",response.getToken());
    }

    @Test
    void should_throw_unauthorized_when_password_is_invalid() {
        // GIVEN
        // Username benar, password salah
        LoginRequest request =new LoginRequest(
            "admin",
            "salah-password"
        );

        // WHEN + THEN
        // Kita berharap method login() melempar UnauthorizedException
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> authService.login(request));

        // Verifikasi isi exception
        assertEquals("Invalid username or password",exception.getMessage());
    }

    @Test
    void should_return_current_user_when_token_is_valid() {
        // given
        String token = "token-staff";
        // when
        UserResponse response = authService.me(token);
        // then
        assertNotNull(response);
        assertEquals("staff", response.getUsername());
        assertEquals("STAFF", response.getRole());
    }

    @Test
    void should_throw_unauthorized_when_token_is_missing() {
        // given
        String token = "";

        // when & then
        assertThrows(UnauthorizedException.class,
                () -> authService.me(token));
    }

    @Test
    void should_throw_unauthorized_when_token_is_invalid() {
        // given
        String token = "token-tidak-valid";

        // when & then
        UnauthorizedException exception = assertThrows(UnauthorizedException.class,
                () -> authService.me(token));

        assertNotNull(exception.getMessage());
    }
}