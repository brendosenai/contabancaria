package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaRequestDTO;
import com.senai.conta_bancaria.application.dto.ContaResponseDTO;
import com.senai.conta_bancaria.application.dto.DepositoRequestDTO;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.exception.ContaNaoEncontrada;
import com.senai.conta_bancaria.domain.exception.UsuarioNaoEncontradoException;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;

    public ContaResponseDTO cadastrarConta(ContaRequestDTO contaRequestDTO){
        return ContaResponseDTO.fromEntity(
                contaRepository.save(
                        contaRequestDTO.toEntity()
                )
        );
    }

    public List<ContaResponseDTO> listarConta(){
        return contaRepository.findAll()
                .stream().map(
                        ContaResponseDTO::fromEntity
                ).toList();
    }

    public ContaResponseDTO buscarContaPorId(Long id){
        return ContaResponseDTO.fromEntity(contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontrada(id))
        );
    }
    public ContaResponseDTO atualizarConta(Long id, ContaRequestDTO contaRequestDTO) {
        Conta contaAtualizado = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontrada(id));

        contaAtualizado.setAgencia(contaRequestDTO.agencia());
        contaAtualizado.setNumero(contaRequestDTO.numero());
        contaAtualizado.setTipo(contaRequestDTO.tipo());
        contaAtualizado.setSaldo(contaRequestDTO.saldo());
        contaAtualizado.setAtivo(contaRequestDTO.ativo());

        return ContaResponseDTO.fromEntity(contaRepository.save(contaAtualizado));
    }

    public void deletarConta(Long id) {

        if(!contaRepository.existsById(id)){
            throw new UsuarioNaoEncontradoException(id);
        }
        contaRepository.deleteById(id);
    }

    public Conta depositarConta(Long id, DepositoRequestDTO depositoRequestDTO){
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontrada(id));

        conta.depositar(depositoRequestDTO.valorDepositado());
        return contaRepository.save(conta);

    }
}
