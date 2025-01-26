package com.entrevista.compra_api.dto;

import java.time.LocalDateTime;

public class CompraDTO {
    private Integer id;
    private LocalDateTime data;
    private boolean pago;

    public CompraDTO(Integer id, LocalDateTime data, boolean pago) {
        this.id = id;
        this.data = data;
        this.pago = pago;
    }

    public CompraDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
