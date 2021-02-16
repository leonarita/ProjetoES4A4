package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.TrabalhoAcademico;
import br.com.ifsp.es4a4.projeto.repository.TrabalhoAcademicoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrabalhoAcademicoService {
	
	private final TrabalhoAcademicoRepository trabalhoAcademicoRepository;
	
	public List<TrabalhoAcademico> findAll() {
		return this.trabalhoAcademicoRepository.findAll();
	}
	
	public TrabalhoAcademico findById(Long id) {
		return this.trabalhoAcademicoRepository.findById(id).orElse(null);
	}
	
	public TrabalhoAcademico create(TrabalhoAcademico trabalhoAcademico) {
		return this.trabalhoAcademicoRepository.save(trabalhoAcademico);
	}

	public void deleteById(Long id) {
		this.trabalhoAcademicoRepository.deleteById(id);
	}

}
