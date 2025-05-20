package models;

public class Deposito extends Transaction {
    private String targetAccountId;

    public Deposito() {
    }

    public Deposito(String id, float amount, String date, String description, String targetAccountId) {
        super(id, amount, date, description);
        this.targetAccountId = targetAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    @Override
    public boolean validateTransaction() {
        // A deposit is valid if amount is positive and target account exists
        return getAmount() > 0 && targetAccountId != null && !targetAccountId.isEmpty();
    }

    @Override
    public boolean processTransaction() {
        // This would connect to a database to update the balance
        // For now, we'll just validate
        if (!validateTransaction()) {
            return false;
        }

        System.out.println("Processing deposit of " + getAmount() + " to account " + targetAccountId);
        return true;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id='" + getId() + '\'' +
                ", amount=" + getAmount() +
                ", date='" + getDate() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", targetAccountId='" + targetAccountId + '\'' +
                '}';
    }
}