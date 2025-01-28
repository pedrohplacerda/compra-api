package com.interview.purchase_api.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "entrevista", name = "tb_compra")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime data;
    @Column(nullable = false)
    private boolean pago;

    public Purchase() {
    }

    public Purchase(Integer id, LocalDateTime data, boolean pago) {
        this.id = id;
        this.data = data;
        this.pago = pago;
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
