package br.com.padariaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.padariaweb.dao.IProdutoDao;
import br.com.padariaweb.entity.Produto;
import br.com.padariaweb.exception.ValidacaoException;
import br.com.padariaweb.service.IProdutoService;

@Service
@Transactional
public class ProdutoService extends GenericoCRUDManager<Produto, Long> implements IProdutoService {

	@Autowired
	IProdutoDao produtoDao;

	@Override
	public List<Produto> pesquisarProduto(Produto filtro) {
		return produtoDao.verificaProdutoExistente(filtro);
	}

	@Override
	public List<Produto> pesquisarProduto(Produto filtro, Integer first, Integer maxPerPage) {

		return produtoDao.pesquisarProduto(filtro, first, maxPerPage);
	}

	public void salvar(Produto produto) throws ValidacaoException {
		List<Produto> produtos = produtoDao.verificaProdutoExistente(produto);
		// Caso seja inclusao de um novo produto
		if ((produto.getSqProduto() == null && !produtos.isEmpty())
				// Caso seja alteracao de produto
				|| (produto.getSqProduto() != null && !produtos.isEmpty()
						&& !produto.getSqProduto().equals(produtos.get(0).getSqProduto())))
			throw new ValidacaoException("Produto já cadastrado na base de dados.");

		

		if (produto.getSqProduto() != null)
			produtoDao.update(produto);
		else
			produtoDao.save(produto);
	}

	public Produto pesquisarProduto(Long produtoAlteracao) {
		Produto u = (Produto) produtoDao.findById(Produto.class, produtoAlteracao);
		produtoDao.evict(u);
		return u;
	}

	public void inativarProduto(Produto produtoInativar) {
		produtoDao.save(produtoInativar);

	}

}
