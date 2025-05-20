package models;

public class Correntista extends Base {
    private String idCorrentista;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String endereco;
    private String dadosPessoais;
    private String idContaCorrente;
    private String password; // Added for authentication

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

    // Existing getters and setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID Correntista: " + idCorrentista);
        System.out.println("Nome Completo: " + nomeCompleto);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Endereço: " + endereco);
        System.out.println("Dados Pessoais: " + dadosPessoais);
    }
}