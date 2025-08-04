package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements InterfaceValidadorRegraDeNegocio{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return; //se não tem, segue, nem vai ser levado em consideração este metodo.
        }
        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo no sistema.");
        }

    }

}
