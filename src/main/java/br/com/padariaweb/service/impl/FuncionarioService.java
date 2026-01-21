package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IFuncionarioDao;
import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IFuncionarioService;
import br.com.padariaweb.util.Util;

@Service
@Transactional
public class FuncionarioService extends GenericoCRUDManager<Funcionario, Long> implements IFuncionarioService {

	@Autowired
	IFuncionarioDao funcionarioDao;

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario filtro) {
		return funcionarioDao.verificaFuncionarioExistente(filtro);
	}

	@Override
	public List<Funcionario> pesquisarFuncionario(Funcionario filtro, Integer first, Integer maxPerPage) {

		return funcionarioDao.pesquisarFuncionario(filtro, first, maxPerPage);
	}

	public void salvar(Funcionario funcionario) throws ValidacaoException {
		List<Funcionario> funcionarios = funcionarioDao.verificaFuncionarioExistente(funcionario);
		// Caso seja inclusao de um novo funcionario
		if ((funcionario.getSqFuncionario() == null && !funcionarios.isEmpty())
				// Caso seja alteracao de usuarrio
				|| (funcionario.getSqFuncionario() != null && !funcionarios.isEmpty()
						&& !funcionario.getSqFuncionario().equals(funcionarios.get(0).getSqFuncionario())))
			throw new ValidacaoException("Funcionário já cadastrado na base de dados.");

		if (funcionario.getSqFuncionario() == null) {
			String senha = Util.gerarStringAleatoria();
			funcionario.setSemente(Util.gerarStringAleatoria());
			funcionario.setSenha(Util.encriptar(senha, funcionario.getSemente()));
		}

		if (funcionario.getSqFuncionario() != null)
			funcionarioDao.update(funcionario);
		else
			funcionarioDao.save(funcionario);
	}

	public Funcionario pesquisarFuncionario(Long funcionarioAlteracao) {
		Funcionario u = (Funcionario) funcionarioDao.findById(Funcionario.class, funcionarioAlteracao);
		funcionarioDao.evict(u);
		return u;
	}

	public void inativarFuncionario(Funcionario funcionarioInativar) {
		funcionarioDao.save(funcionarioInativar);

	}

//		private String getEmailPrimeiroAcesso(Funcionario funcionario, String senha){
//			StringBuilder s = new StringBuilder();
//			s.append("Prezado(a) ");
//			s.append(funcionario.getDsNome());
//			s.append("\n\n");
//			s.append("Você foi cadastrado no Sistema Controle de Brinquedoteca.\n");
//			s.append("Para acesso click no link abaixo e utilize a sua senha temporária para efetuar o login.\n");
//			s.append("http://appbrinq.com.br - Controle de Brinquedoteca\n\n");
//			s.append("Lembramos que esta é uma VERSÃO BETA 0.01 do sistema, então é possível que apresente alguns problemas.\n");
//			s.append("Pedimos a gentiliza de que caso ocorra algum problema, por favor informe ao Fernando para que possamos ajustar.\n");
//			s.append("Informamos também que este é um sistema para o acompanhamento das movimentações da sua loja, não será possível o cadastro de novas crianças, responsáveis ou visitas neste momento.\n");
//			s.append("Agredeçemos a compreensão.\n");
//			s.append("\n\n");
//			s.append("Senha temporária: ");
//			s.append(senha);
//			s.append("\n\n");
//			s.append("Este é um e-mail automático e não deve ser respondido.");
//			return s.toString();
//		}
//		private String getEmailRecuperarSenha(Funcionario funcionario, String senha){
//			StringBuilder s = new StringBuilder();
//			s.append("Prezado(a) ");
//			s.append(funcionario.getDsNome());
//			s.append("\n\n");
//			s.append("Sua senha no Sistema Controle de Brinquedoteca foi reiniciada.\n");
//			s.append("Para acesso click no link abaixo e utilize a sua senha temporária para efetuar o login.\n");
//			s.append("http://appbrinq.com.br - Controle de Brinquedoteca\n\n");
//			s.append("Senha temporária: ");
//			s.append(senha);
//			s.append("\n\n");
//			s.append("Este é um e-mail automático e não deve ser respondido.");
//			return s.toString();
//		}
//
//		public void salvarPrimeiroAcesso(Funcionario u) {
//			u.setDsSenha(Util.encriptar(u.getDsSenha(), u.getDsSemente()));
//			funcionarioDao.update(u);
//		}
//
//
//		public void recuperarSenha(String email) {
//			Funcionario u = funcionarioDao.findFirst("dsLogin",email);
//			if(u != null){
//				String senha = Util.gerarStringAleatoria();
//				u.setDsSemente(Util.gerarStringAleatoria());
//				u.setDsSenha(Util.encriptar(senha, u.getDsSemente()));
//				u.setPrimeiroAcesso(true);
//				
//				SendMail sendEmail = new SendMail();
//				sendEmail.sendMail(Constantes.emailSistema, u.getDsLogin(), "Recuperação de Senha - Sistema Controle de Brinquedoteca", getEmailRecuperarSenha(u, senha));
//			}
//			
//		}

	/**
	 * Recupera a senha da Identidade gerando uma senha aleatória e enviando-a para
	 * o e-mail da Identidade.
	 * 
	 * @param funcionarioSelecionado - usuário que será recuperado sua senha
	 */
//		@Override
//		public void recuperarSenha(Funcionario funcionarioSelecionado) {
//			String senha = this.criarSenha(funcionarioSelecionado);
//			funcionarioSelecionado.setPrimeiroAcesso(true);
//			funcionarioDao.update(funcionarioSelecionado);
//			
//			//Send e-mail to Funcionario
//			SendMail sendEmail = new SendMail();
//			sendEmail.sendMail(Constantes.emailSistema, funcionarioSelecionado.getDsLogin(), "Recuperação de Senha - Sistema Controle de Brinquedoteca", getEmailRecuperarSenha(funcionarioSelecionado, senha));
//		}

//		/**
//		 * Criação de uma senha inicial temporária no usuário fornecido.
//		 * 
//		 * @param funcionario - usuário a ter uma senha temporária definido
//		 * @return a senha gerada
//		 */
//		private String criarSenha(Funcionario funcionario) {
//			String senha = Util.gerarStringAleatoria();
//			funcionario.setSemente("temp");
//			funcionario.setSenha(Util.encriptar(senha, "temp"));
//			return senha;
//		}

}
