package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements InterfaceValidadorRegraDeNegocio{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idPaciente() == null){
            return;
        }

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo no sistema.");
        }

    }



}
