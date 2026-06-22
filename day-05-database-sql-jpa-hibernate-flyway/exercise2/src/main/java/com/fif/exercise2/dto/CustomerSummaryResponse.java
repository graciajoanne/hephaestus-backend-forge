package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerSummaryResponse {

    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String nik;
    private String email;
}