package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.InterfaceValidadorCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.InterfaceValidadorRegraDeNegocio;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<InterfaceValidadorRegraDeNegocio> validadores;

    @Autowired
    private List<InterfaceValidadorCancelamentoConsulta> validadoresCancelamento;


    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        validadores.forEach(v-> v.validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);

        if (medico == null){
            throw new ValidacaoException("Não existe Médico disponível nesta data.");

        }
        var consulta = new Consulta (null, medico, paciente, dados.data() ,null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade não inserida!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade() ,dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v->v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

}
