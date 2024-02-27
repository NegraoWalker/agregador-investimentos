package com.walker.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @OneToOne(mappedBy = "account") // cada conta está relacionada a um endereço de cobrança;indicando que o relacionamento foi feito pelo campo account na tabela tb_billingaddress
    @PrimaryKeyJoinColumn // indico que a primary key account_id da tabela tb_accounts também será a primary key da tabela tb_billingaddress
    private BillingAddress billingAddress;

    @Column(name = "description")
    private String description; //descrição da conta

    @ManyToOne //várias contas pode pertencer a um usuário -> relacionamento muitos para um
    @JoinColumn(name = "user_id") //definindo a foreign key da tabela tb_users para a tb_accounts, garantindo o relacionamento
    private User user;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;

    public Account() {
    }

    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
