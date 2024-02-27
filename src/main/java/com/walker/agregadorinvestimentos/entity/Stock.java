package com.walker.agregadorinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stocks")
public class Stock {  //Stock = Armazenar; Entidade que representa  informações da ação da bolsa de valores

    @Id
    @Column(name = "stock_id")
    private String stockId; //Representa o código da ação da bolsa de valores. Exemplos: PETR4, MGLU4, etc...

    @Column(name = "description")
    private String description; //Representa a descrição da ação

    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
