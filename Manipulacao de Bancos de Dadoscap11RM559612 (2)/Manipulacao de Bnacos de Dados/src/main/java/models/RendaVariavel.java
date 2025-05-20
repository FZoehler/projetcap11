package models;

public class RendaVariavel extends CarteiraInvest {
    private String bovespa;
    private String bmsf;
    private final float EXPECTED_ANNUAL_RETURN = 0.15f; // 15% average annual return
    private final float MARKET_VOLATILITY = 0.25f; // 25% standard deviation

    // Constructors
    public RendaVariavel() {
    }

    public RendaVariavel(String idRendaVariavel, String bovespa, String bmsf) {
        super(idRendaVariavel, 0);
        this.bovespa = bovespa;
        this.bmsf = bmsf;
    }

    public RendaVariavel(String idRendaVariavel, float balance, String bovespa, String bmsf) {
        super(idRendaVariavel, balance);
        this.bovespa = bovespa;
        this.bmsf = bmsf;
    }

    // Getters and Setters
    public String getIdRendaVariavel() {
        return getId();
    }

    public void setIdRendaVariavel(String idRendaVariavel) {
        setId(idRendaVariavel);
    }

    public String getBovespa() {
        return bovespa;
    }

    public void setBovespa(String bovespa) {
        this.bovespa = bovespa;
    }

    public String getBmsf() {
        return bmsf;
    }

    public void setBmsf(String bmsf) {
        this.bmsf = bmsf;
    }

    @Override
    public float calculateExpectedReturn(int months) {
        // Variable income is highly unpredictable, but we can provide an average estimate
        float monthlyRate = (float)Math.pow(1 + EXPECTED_ANNUAL_RETURN, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    public float[] calculateReturnRanges(int months) {
        float expected = calculateExpectedReturn(months);
        float pessimistic = expected * (1 - MARKET_VOLATILITY);
        float optimistic = expected * (1 + MARKET_VOLATILITY);

        return new float[]{pessimistic, expected, optimistic};
    }

    @Override
    public float applyFees(float amount) {
        // Variable income has brokerage fees and taxes
        float brokerageFee = 0.005f * amount; // 0.5% brokerage fee
        float tax = 0.15f * amount; // 15% capital gains tax

        return amount - brokerageFee - tax;
    }

    @Override
    public String toString() {
        return "models.RendaVariavel{" +
                "idRendaVariavel='" + getId() + '\'' +
                ", balance=" + getBalance() + '\'' +
                ", bovespa='" + bovespa + '\'' +
                ", bmsf='" + bmsf + '\'' +
                '}';
    }
}
