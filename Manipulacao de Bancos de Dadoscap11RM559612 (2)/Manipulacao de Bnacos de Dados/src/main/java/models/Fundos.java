package models;

public class Fundos extends CarteiraInvest {
    private String instituicoesFinanceiras;
    private String fundosConservadores;
    private String fundosAltoRisco;
    private final float CONSERVATIVE_ANNUAL_RETURN = 0.08f; // 8% annual return for conservative funds
    private final float RISKY_ANNUAL_RETURN = 0.18f; // 18% annual return for high-risk funds

    // Constructors
    public Fundos() {
    }

    public Fundos(String idFundos, String instituicoesFinanceiras,
                  String fundosConservadores, String fundosAltoRisco) {
        super(idFundos, 0);
        this.instituicoesFinanceiras = instituicoesFinanceiras;
        this.fundosConservadores = fundosConservadores;
        this.fundosAltoRisco = fundosAltoRisco;
    }

    public Fundos(String idFundos, float balance, String instituicoesFinanceiras,
                  String fundosConservadores, String fundosAltoRisco) {
        super(idFundos, balance);
        this.instituicoesFinanceiras = instituicoesFinanceiras;
        this.fundosConservadores = fundosConservadores;
        this.fundosAltoRisco = fundosAltoRisco;
    }

    // Getters and Setters
    public String getIdFundos() {
        return getId();
    }

    public void setIdFundos(String idFundos) {
        setId(idFundos);
    }

    public String getInstituicoesFinanceiras() {
        return instituicoesFinanceiras;
    }

    public void setInstituicoesFinanceiras(String instituicoesFinanceiras) {
        this.instituicoesFinanceiras = instituicoesFinanceiras;
    }

    public String getFundosConservadores() {
        return fundosConservadores;
    }

    public void setFundosConservadores(String fundosConservadores) {
        this.fundosConservadores = fundosConservadores;
    }

    public String getFundosAltoRisco() {
        return fundosAltoRisco;
    }

    public void setFundosAltoRisco(String fundosAltoRisco) {
        this.fundosAltoRisco = fundosAltoRisco;
    }

    @Override
    public float calculateExpectedReturn(int months) {
        // Using an average between conservative and risky funds
        float avgAnnualReturn = (CONSERVATIVE_ANNUAL_RETURN + RISKY_ANNUAL_RETURN) / 2;
        float monthlyRate = (float)Math.pow(1 + avgAnnualReturn, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    public float calculateConservativeReturn(int months) {
        float monthlyRate = (float)Math.pow(1 + CONSERVATIVE_ANNUAL_RETURN, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    public float calculateRiskyReturn(int months) {
        float monthlyRate = (float)Math.pow(1 + RISKY_ANNUAL_RETURN, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    @Override
    public float applyFees(float amount) {
        // Funds typically have administration fees and performance fees
        float adminFee = 0.02f * amount; // 2% admin fee
        float performanceFee = 0.2f * (amount * 0.05f); // 20% performance fee on gains above 5%

        return amount - adminFee - performanceFee;
    }

    @Override
    public String toString() {
        return "models.Fundos{" +
                "idFundos='" + getId() + '\'' +
                ", balance=" + getBalance() + '\'' +
                ", instituicoesFinanceiras='" + instituicoesFinanceiras + '\'' +
                ", fundosConservadores='" + fundosConservadores + '\'' +
                ", fundosAltoRisco='" + fundosAltoRisco + '\'' +
                '}';
    }
}