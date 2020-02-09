package br.com.rfasioli.redistest.dtos;

import java.io.Serializable;

public class OperationDTO implements Serializable {

    private static final long serialVersionUID = 833002644087833827L;

    private Long id;
    private String uuid;

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

}
