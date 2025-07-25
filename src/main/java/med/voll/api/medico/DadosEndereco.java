package med.voll.api.medico;

public record DadosEndereco(String logradouro,
                            String numero,
                            String complemento,
                            String bairro,
                            String cidade,
                            String uf,
                            String cep) {
}
