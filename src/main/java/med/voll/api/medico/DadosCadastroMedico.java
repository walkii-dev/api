package med.voll.api.medico;

public record DadosCadastroMedico(String nome,
                                  String email,
                                  String crm,
                                  String cpf,
                                  Especialidade especialidade,
                                  DadosEndereco endereco) {
}
