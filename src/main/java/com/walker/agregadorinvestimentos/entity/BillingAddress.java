package com.walker.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddress { // BillingAddress = endereço de cobrança
    @Id
    @Column(name = "account_id") // indico que a primary key account_id da tabela tb_accounts também será a primary key da tabela tb_billingaddress
    private UUID id;

    @OneToOne // um endereço de cobrança se relaciona com uma conta -> relacionamento um para um
    @MapsId // indica que a primary dessa tabela parte da entidade Account
    @JoinColumn(name = "account_id") // definindo a foreign key da tabela tb_accounts
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, Integer number) {
        this.id = id;
        this.street = street;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
