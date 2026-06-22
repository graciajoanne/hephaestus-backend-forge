package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerRequest {

    @NotBlank(message = "full_name is required")
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "nik is required")
    @Pattern(regexp="\\d{16}", message = "nik must consist of 16 digits")
    private String nik;

    @NotBlank(message = "email is required")
    @Email(message = "email is not valid")
    private String email;

    @NotBlank(message = "phone_number is required")
    @Size(min=10, max =13, message = "phone number must consist of 10-13 digits")
    @JsonProperty("phone_number")
    private String phoneNumber;
}