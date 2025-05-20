package models;

public abstract class CarteiraInvest {
    private String id;
    private float balance;

    public CarteiraInvest() {
    }

    public CarteiraInvest(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean validateCarteiraInvest(float amount) {
        return amount > 0;
    }


    public abstract float calculateExpectedReturn(int months);

    public abstract float applyFees(float amount);

    @Override
    public String toString() {
        return "models.CarteiraInvest{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}