package br.com.rfasioli.redistest.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OperationDTO implements Serializable {

    private static final long serialVersionUID = 833002644087833827L;

    private Long id;
    private String uuid;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
