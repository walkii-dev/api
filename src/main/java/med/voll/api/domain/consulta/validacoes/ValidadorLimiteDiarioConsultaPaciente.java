package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorLimiteDiarioConsultaPaciente implements InterfaceValidadorRegraDeNegocio{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteAtingiuLimiteDiarioConsulta = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);

        if(!pacienteAtingiuLimiteDiarioConsulta){
            throw new ValidacaoException("Paciente não pode mais marcar consultas na data informada.");
        }

    }
}
