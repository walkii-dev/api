package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("")
    boolean existsByMedicoIdAndData(Long id, @NotNull @Future LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(@NotNull Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
