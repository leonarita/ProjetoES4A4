package br.com.ifsp.es4a4.projeto.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduledService {
	
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Scheduled(fixedRate = 1000, initialDelay = 1000)
	public void exampleSchedule() {
		log.info("SCHEDULE AT: " + simpleDateFormat.format(new Date()));
	}

}
