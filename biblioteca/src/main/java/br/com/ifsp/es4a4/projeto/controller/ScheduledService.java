package br.com.ifsp.es4a4.projeto.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduledService {
		
	@Scheduled(fixedRate = 1000 * 60 * 60, initialDelay = 1000)
	public void exampleSchedule() {
		
		
	}

}
