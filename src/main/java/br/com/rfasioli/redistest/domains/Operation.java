package br.com.rfasioli.redistest.domains;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Operation {
    private String uuid;
    private Long id;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;

    private Operation(Builder builder) {
        uuid = builder.uuid;
        id = builder.id;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUuid() {
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class Builder {
        private String uuid;
        private Long id;
        private LocalDate createdAt;
        private LocalDateTime updatedAt;

        private Builder() {
        }

        public Builder withUuid(String val) {
            uuid = val;
            return this;
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withCreatedAt(LocalDate val) {
            createdAt = val;
            return this;
        }

        public Builder withUpdatedAt(LocalDateTime val) {
            updatedAt = val;
            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }
}
