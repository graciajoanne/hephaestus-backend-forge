package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class CustomerResponse {

    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String nik;
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}