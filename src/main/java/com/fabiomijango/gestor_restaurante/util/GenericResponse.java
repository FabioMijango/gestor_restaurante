package com.fabiomijango.gestor_restaurante.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private String message;
    private Object data;

    @Builder.Default
    private String timestamp = LocalDateTime.now().toString();

    @Builder.Default
    @JsonIgnore
    private HttpStatus status = HttpStatus.OK;

    public ResponseEntity<GenericResponse> buildResponse() {
        return ResponseEntity.status(status).body(this);
    }

    @Data
    @Builder
    public static class ErrorResponse {
        private String path;
        private String method;
        private Map<String, String> errors;

        public ErrorResponse buildResponse() {
            return this;
        }
    }
}
