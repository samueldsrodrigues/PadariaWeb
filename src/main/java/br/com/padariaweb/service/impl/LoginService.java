package br.com.padariaweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.ILoginDao;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.service.ILoginService;
import br.com.padariaweb.util.Util;



@Service
@Transactional
public class LoginService extends GenericoCRUDManager<Funcionario, Integer> implements  ILoginService{

	@Autowired ILoginDao loginDao;
	
	
	public Funcionario verificaFuncionario(Funcionario func){
		Funcionario funcionario = loginDao.verificaFuncionario(func);
		if(funcionario != null){
			String senhaHash = Util.encriptar(func.getSenha(), funcionario.getSemente());
			if(funcionario.getSenha().equals(senhaHash)){
				return funcionario;
			}
		}
		return null;
	}
}