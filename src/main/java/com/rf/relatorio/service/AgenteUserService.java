package com.rf.relatorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.entity.AgenteUser;
import com.rf.relatorio.entity.Equipe;
import com.rf.relatorio.exception.AgenteUserNotFoundException;
import com.rf.relatorio.repository.AgenteUserRepository;
import com.rf.relatorio.repository.EquipeRepository;

@Service
public class AgenteUserService {

	private AgenteUserRepository agenteRepository;
	
    private EquipeRepository equipeRepository;
	
	@Autowired
	public AgenteUserService(AgenteUserRepository agenteRepository, EquipeRepository equipeRepository) {
		this.agenteRepository = agenteRepository;
		this.equipeRepository = equipeRepository;
	}
	
	public AgenteUser createAgenteUser(AgenteUser agenteUser) {
		return agenteRepository.save(agenteUser); 
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<AgenteUser> findAll() {
		return agenteRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public AgenteUser findById(Long id) {
		return agenteRepository.findById(id).orElseThrow(() -> new AgenteUserNotFoundException(id));
	}
	
    @Transactional
	public void delete(Long id) {
		findById(id);
		agenteRepository.deleteById(id);
	}
    
    @Transactional
	public AgenteUser update(Long id, AgenteUser agenteUser) {
    	AgenteUser agenteUserUpdate = findById(id);
    	agenteUserUpdate.setNome(agenteUser.getNome());
    	agenteUserUpdate.setFuncao(agenteUser.getFuncao());
    	agenteUserUpdate.setCodigo(agenteUser.getCodigo());

    	agenteRepository.save(agenteUserUpdate);
		return agenteUserUpdate;
	}
    
    public String adicionarAgenteAEquipe(Long agenteId, Long equipeId) {
        // Busca o AgenteUser pelo ID
        AgenteUser agenteUser = agenteRepository.findById(agenteId)
                .orElseThrow(() -> new RuntimeException("AgenteUser não encontrado"));

        // Busca a Equipe pelo ID
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

        // Adiciona o AgenteUser à equipe
        agenteUser.setEquipe(equipe);
        agenteRepository.save(agenteUser);

        return "AgenteUser " + agenteUser.getNome() + " adicionado à equipe " + equipe.getNome();
    }
    
    public String removerAgenteDeEquipe(Long agenteId) {
        // Busca o AgenteUser pelo ID
        AgenteUser agenteUser = agenteRepository.findById(agenteId)
                .orElseThrow(() -> new RuntimeException("AgenteUser não encontrado"));

        // Remove o AgenteUser da equipe (define equipe como null)
        agenteUser.setEquipe(null);
        agenteRepository.save(agenteUser);

        return "AgenteUser " + agenteUser.getNome() + " foi removido da equipe.";
    }
    
    public List<AgenteUser> buscarAgentesSemEquipe() {
        return agenteRepository.findByEquipeIsNull();
    }
}
