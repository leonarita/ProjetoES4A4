package br.com.ifsp.es4a4.projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifsp.es4a4.projeto.model.Emprestimo;
import br.com.ifsp.es4a4.projeto.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
	
	private final EmprestimoRepository emprestimoRepository;
	
	public List<Emprestimo> findAll() {
		return this.emprestimoRepository.findAll();
	}
	
	public Emprestimo findById(Long id) {
		return this.emprestimoRepository.findById(id).orElse(null);
	}
	
	public Emprestimo create(Emprestimo emprestimo) {
		return this.emprestimoRepository.save(emprestimo);
	}

	public void deleteById(Long id) {
		this.emprestimoRepository.deleteById(id);
	}

}
