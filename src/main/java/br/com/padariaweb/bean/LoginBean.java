package br.com.padariaweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.padariaweb.entity.Funcionario;
import br.com.padariaweb.service.ILoginService;
import br.com.padariaweb.util.Util;

@RequestScoped
@ManagedBean
public class LoginBean implements AuthenticationProvider, Serializable{
	
	private static final long serialVersionUID = 1210285979747117387L;
	@Autowired
	private ILoginService loginService;
	
//	Gerar semente de um usuario
	public static void main(String args[]) {
		String semente = Util.gerarStringAleatoria();
		String senhaHash = Util.encriptar("123", semente);
		
		System.out.println("Sua senha '123' tem uma semente: " + semente + " e uma senhaHash: " + senhaHash );
	}
	
	
	public Funcionario funcExist(Funcionario func){
		try {
			func = loginService.verificaFuncionario(func);
			if(func != null && !func.getEmail().isEmpty()){
				return func;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@SuppressWarnings("unused")
	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		String funcionario = arg0.getPrincipal().toString();
		String senha = arg0.getCredentials().toString();
		Funcionario func = new Funcionario();
		func.setEmail(funcionario);
		func.setSenha(senha);
		//Adiciona a lista de acessos
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
		Authentication customAuthentication = null;
		
		func = funcExist(func);
		if(func != null){
			autorizacoes.add(new SimpleGrantedAuthority(func.getCargo().getNome()));
			
			customAuthentication = new UsernamePasswordAuthenticationToken(func, senha, autorizacoes);
			return customAuthentication;
		}else if(func == null){
			throw new BadCredentialsException("Username or password incorrect!");
		}
		return arg0;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0));
	}

}

