package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(@NotBlank String nome,
                                  @Email String email,
                                  @NotBlank String telefone,
                                  @Pattern(regexp = "\\d{4,6}") String crm,
                                  @Pattern(regexp = "\\d{8}") String cpf,
                                  @NotNull Especialidade especialidade,
                                  @Valid DadosEndereco endereco) {
}
