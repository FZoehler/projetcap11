package models;

public class Correntista  extends Base {
    private String idCorrentista;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String endereco;
    private String dadosPessoais;
    private String idContaCorrente;

    // Constructors
    public Correntista() {
    }

    public Correntista(String idCorrentista, String nomeCompleto, String telefone,
                       String email, String endereco, String dadosPessoais) {
        this.idCorrentista = idCorrentista;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dadosPessoais = dadosPessoais;
    }

    // Getters and Setters
    public String getIdCorrentista() {
        return idCorrentista;
    }

    public void setIdCorrentista(String idCorrentista) {
        this.idCorrentista = idCorrentista;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(String dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public String getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(String idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }
    @Override
    public void displayInfo() {
        System.out.println("ID models.Correntista: " + idCorrentista);
        System.out.println("Nome Completo: " + nomeCompleto);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Endereço: " + endereco);
        System.out.println("Dados Pessoais: " + dadosPessoais);
    }

}