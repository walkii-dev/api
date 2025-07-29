package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank String logradouro,
        @NotBlank String uf,
        @NotBlank String cep,
        @NotBlank String bairro,
        @NotBlank String cidade,
        String numero,
        String complemento) {
}
