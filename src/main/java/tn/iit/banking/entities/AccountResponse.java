package tn.iit.banking.entities;

public class AccountResponse {
    private Long id;
    private String rib;
    private Double accountBalance;
    private String clientName;

    public AccountResponse(Long id, String rib, Double accountBalance, String clientName) {
        this.id = id;
        this.rib = rib;
        this.accountBalance = accountBalance;
        this.clientName = clientName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}