package com.mineirinho.mercado.repository;

import com.mineirinho.mercado.model.Produto;
import com.mineirinho.mercado.model.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository_old {

    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método para retornar o produto encontrado pelo seu Id.
     * @param id do produto.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }

    /**
     * Método para adicionar um produto.
     * @param produto
     * @return O produto adicionado.
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Método para remover um produto por Id.
     * @param id do produto
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método para Atualizar um produto por Id.
     * @param produto
     * @return Retorna o produto atualizado.
     */
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if(produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        deletar(produto.getId());
        produtos.add(produto);

        return produto;
    }

}
