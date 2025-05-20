package models;

public class Transfer extends Transaction {
    private String sourceAccountId;
    private String targetAccountId;
    private float availableBalance;

    public Transfer() {
    }

    public Transfer(String id, float amount, String date, String description,
                    String sourceAccountId, String targetAccountId, float availableBalance) {
        super(id, amount, date, description);
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.availableBalance = availableBalance;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
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
                sourceAccountId != null && !sourceAccountId.isEmpty() &&
                targetAccountId != null && !targetAccountId.isEmpty() &&
                !sourceAccountId.equals(targetAccountId);
    }

    @Override
    public boolean processTransaction() {
        if (!validateTransaction()) {
            return false;
        }

        System.out.println("Processing transfer of " + getAmount() +
                " from account " + sourceAccountId +
                " to account " + targetAccountId);
        return true;
    }

    @Override
    public String toString() {
        return "models.Transfer{" +
                "id='" + getId() + '\'' +
                ", amount=" + getAmount() +
                ", date='" + getDate() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", sourceAccountId='" + sourceAccountId + '\'' +
                ", targetAccountId='" + targetAccountId + '\'' +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
