package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaSimultanea implements InterfaceValidadorRegraDeNegocio{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){

        var medicoTemConsultaSimultanea = consultaRepository.existsByMedicoIdAndData(dados.idMedico(),dados.data());

        if (!medicoTemConsultaSimultanea){
            throw new ValidacaoException("Médico já possui uma consulta agendada neste horário.");
        }

    }
}
