
package models;

public class Saques extends Transaction {
    private String sourceAccountId;
    private float availableBalance;

    public Saques() {
    }

    public Saques(String id, float amount, String date, String description,
                  String sourceAccountId, float availableBalance) {
        super(id, amount, date, description);
        this.sourceAccountId = sourceAccountId;
        this.availableBalance = availableBalance;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public boolean validateTransaction() {
        return getAmount() > 0 && getAmount() <= availableBalance &&
                sourceAccountId != null && !sourceAccountId.isEmpty();
    }

    @Override
    public boolean processTransaction() {
        if (!validateTransaction()) {
            return false;
        }

        System.out.println("Processing models.Saques of " + getAmount() + " from account " + sourceAccountId);
        return true;
    }

    @Override
    public String toString() {
        return "models.Saques{" +
                "id='" + getId() + '\'' +
                ", amount=" + getAmount() +
                ", date='" + getDate() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", sourceAccountId='" + sourceAccountId + '\'' +
                ", availableBalance=" + availableBalance +
                '}';
    }
}


