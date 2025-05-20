package models;

public class ContaCorrente extends Base {
    private String idContaCorrente;
    private float limiteDeCredito;
    private float cartoes;
    private float chequeEspecial;

    public ContaCorrente() {
    }

    public ContaCorrente(String idContaCorrente, float limiteDeCredito, float cartoes, float chequeEspecial) {
        this.idContaCorrente = idContaCorrente;
        this.limiteDeCredito = limiteDeCredito;
        this.cartoes = cartoes;
        this.chequeEspecial = chequeEspecial;
    }

    public String getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(String idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public float getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(float limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }

    public float getCartoes() {
        return cartoes;
    }

    public void setCartoes(float cartoes) {
        this.cartoes = cartoes;
    }

    public float getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(float chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID Conta Corrente: " + idContaCorrente);
        System.out.println("Limite de Crédito: " + limiteDeCredito);
        System.out.println("Cartões: " + cartoes);
        System.out.println("Cheque Especial: " + chequeEspecial);
    }
}
