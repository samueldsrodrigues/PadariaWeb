package br.com.padariaweb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.entity.Turno;
import br.com.padariaweb.service.ITurnoService;

@Service
@Transactional
public class TurnoService extends GenericoCRUDManager<Turno, Long> implements ITurnoService{

}
