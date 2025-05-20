package models;

public class RendaFixa extends CarteiraInvest {
    private String cdb;
    private String lci;
    private String cri;
    private String cra;
    private final float CDB_RATE = 1.10f; // 110% CDI
    private final float LCI_RATE = 1.00f; // 100% CDI
    private final float CRI_RATE = 0.12f; // 12% annual
    private final float CRA_RATE = 0.11f; // 11% annual

    // Constructors
    public RendaFixa() {
    }

    public RendaFixa(String idRendaFixa, String cdb, String lci, String cri, String cra) {
        super(idRendaFixa, 0);
        this.cdb = cdb;
        this.lci = lci;
        this.cri = cri;
        this.cra = cra;
    }

    public RendaFixa(String idRendaFixa, float balance, String cdb, String lci, String cri, String cra) {
        super(idRendaFixa, balance);
        this.cdb = cdb;
        this.lci = lci;
        this.cri = cri;
        this.cra = cra;
    }

    // Getters and Setters
    public String getIdRendaFixa() {
        return getId();
    }

    public void setIdRendaFixa(String idRendaFixa) {
        setId(idRendaFixa);
    }

    public String getCdb() {
        return cdb;
    }

    public void setCdb(String cdb) {
        this.cdb = cdb;
    }

    public String getLci() {
        return lci;
    }

    public void setLci(String lci) {
        this.lci = lci;
    }

    public String getCri() {
        return cri;
    }

    public void setCri(String cri) {
        this.cri = cri;
    }

    public String getCra() {
        return cra;
    }

    public void setCra(String cra) {
        this.cra = cra;
    }

    public float calculateCdbReturn(int months) {
        float cdiRate = 0.13f; // Assuming 13% CDI annual rate
        float monthlyRate = (float)Math.pow(1 + cdiRate, 1.0/12) - 1;
        return getBalance() * CDB_RATE * monthlyRate * months;
    }

    @Override
    public float calculateExpectedReturn(int months) {
        // Using CDB as the default calculation
        return calculateCdbReturn(months);
    }

    @Override
    public float applyFees(float amount) {
        // Fixed income typically has IR tax that varies according to term
        // Using a simplified model of 15% tax
        float tax = 0.15f * amount;
        return amount - tax;
    }

    @Override
    public String toString() {
        return "models.RendaFixa{" +
                "idRendaFixa='" + getId() + '\'' +
                ", balance=" + getBalance() + '\'' +
                ", cdb='" + cdb + '\'' +
                ", lci='" + lci + '\'' +
                ", cri='" + cri + '\'' +
                ", cra='" + cra + '\'' +
                '}';
    }
}

