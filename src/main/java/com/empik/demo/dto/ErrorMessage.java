package com.empik.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private HttpStatus httpStatus;
    @Builder.Default
    private Timestamp timestamp = Timestamp.from(ZonedDateTime.now().toInstant());
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
}

