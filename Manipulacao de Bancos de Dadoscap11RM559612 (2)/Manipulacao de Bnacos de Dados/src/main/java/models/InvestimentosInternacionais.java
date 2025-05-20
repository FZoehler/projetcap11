package models;

public class InvestimentosInternacionais extends CarteiraInvest {
    private String euaS_P500;
    private String euaNasdaq;
    private String euaDowJones;
    private String europaDax;
    private String europaIbex35;
    private String europaEuroStoxx50;
    private final float EXCHANGE_RATE_VOLATILITY = 0.08f; // 8% exchange rate volatility
    private final float US_ANNUAL_RETURN = 0.10f; // 10% average US market return
    private final float EUROPE_ANNUAL_RETURN = 0.08f; // 8% average European market return

    // Constructors
    public InvestimentosInternacionais() {
    }

    public InvestimentosInternacionais(String idInvestimentosInternacionais, String euaS_P500,
                                       String euaNasdaq, String euaDowJones, String europaDax,
                                       String europaIbex35, String europaEuroStoxx50) {
        super(idInvestimentosInternacionais, 0);
        this.euaS_P500 = euaS_P500;
        this.euaNasdaq = euaNasdaq;
        this.euaDowJones = euaDowJones;
        this.europaDax = europaDax;
        this.europaIbex35 = europaIbex35;
        this.europaEuroStoxx50 = europaEuroStoxx50;
    }

    public InvestimentosInternacionais(String idInvestimentosInternacionais, float balance, String euaS_P500,
                                       String euaNasdaq, String euaDowJones, String europaDax,
                                       String europaIbex35, String europaEuroStoxx50) {
        super(idInvestimentosInternacionais, balance);
        this.euaS_P500 = euaS_P500;
        this.euaNasdaq = euaNasdaq;
        this.euaDowJones = euaDowJones;
        this.europaDax = europaDax;
        this.europaIbex35 = europaIbex35;
        this.europaEuroStoxx50 = europaEuroStoxx50;
    }

    // Getters and Setters
    public String getIdInvestimentosInternacionais() {
        return getId();
    }

    public void setIdInvestimentosInternacionais(String idInvestimentosInternacionais) {
        setId(idInvestimentosInternacionais);
    }

    public String getEuaS_P500() {
        return euaS_P500;
    }

    public void setEuaS_P500(String euaS_P500) {
        this.euaS_P500 = euaS_P500;
    }

    public String getEuaNasdaq() {
        return euaNasdaq;
    }

    public void setEuaNasdaq(String euaNasdaq) {
        this.euaNasdaq = euaNasdaq;
    }

    public String getEuaDowJones() {
        return euaDowJones;
    }

    public void setEuaDowJones(String euaDowJones) {
        this.euaDowJones = euaDowJones;
    }

    public String getEuropaDax() {
        return europaDax;
    }

    public void setEuropaDax(String europaDax) {
        this.europaDax = europaDax;
    }

    public String getEuropaIbex35() {
        return europaIbex35;
    }

    public void setEuropaIbex35(String europaIbex35) {
        this.europaIbex35 = europaIbex35;
    }

    public String getEuropaEuroStoxx50() {
        return europaEuroStoxx50;
    }

    public void setEuropaEuroStoxx50(String europaEuroStoxx50) {
        this.europaEuroStoxx50 = europaEuroStoxx50;
    }

    @Override
    public float calculateExpectedReturn(int months) {
        // Average of US and European markets
        float avgAnnualReturn = (US_ANNUAL_RETURN + EUROPE_ANNUAL_RETURN) / 2;
        float monthlyRate = (float)Math.pow(1 + avgAnnualReturn, 1.0/12) - 1;
        float baseReturn = getBalance() * monthlyRate * months;

        // Apply exchange rate effect (can be positive or negative)
        float exchangeRateEffect = (float)(Math.random() * 2 - 1) * EXCHANGE_RATE_VOLATILITY * baseReturn;

        return baseReturn + exchangeRateEffect;
    }
    public float calculateUSReturn(int months) {
        float monthlyRate = (float)Math.pow(1 + US_ANNUAL_RETURN, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    public float calculateEuropeReturn(int months) {
        float monthlyRate = (float)Math.pow(1 + EUROPE_ANNUAL_RETURN, 1.0/12) - 1;
        return getBalance() * monthlyRate * months;
    }

    @Override
    public float applyFees(float amount) {
        // International investments have higher fees due to exchange and foreign taxes
        float exchangeFee = 0.02f * amount; // 2% exchange fee
        float brokerageFee = 0.01f * amount; // 1% international brokerage
        float taxes = 0.15f * amount; // 15% taxes

        return amount - exchangeFee - brokerageFee - taxes;
    }

    @Override
    public String toString() {
        return "models.InvestimentosInternacionais{" +
                "idInvestimentosInternacionais='" + getId() + '\'' +
                ", balance=" + getBalance() + '\'' +
                ", euaS_P500='" + euaS_P500 + '\'' +
                ", euaNasdaq='" + euaNasdaq + '\'' +
                ", euaDowJones='" + euaDowJones + '\'' +
                ", europaDax='" + europaDax + '\'' +
                ", europaIbex35='" + europaIbex35 + '\'' +
                ", europaEuroStoxx50='" + europaEuroStoxx50 + '\'' +
                '}';
    }
}

