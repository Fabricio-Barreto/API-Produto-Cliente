package com.mineirinho.mercado.services;

import com.mineirinho.mercado.dto.ProdutoDTO;
import com.mineirinho.mercado.model.Produto;
import com.mineirinho.mercado.model.exception.BadRequestException;
import com.mineirinho.mercado.model.exception.ResourceNotFoundException;
import com.mineirinho.mercado.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<ProdutoDTO> obterTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }

    /**
     * Método para retornar o produto encontrado pelo seu Id.
     * @param id do produto.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: "+ id + " Não encontrado!");
        }
        ProdutoDTO produtoDto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        return Optional.of(produtoDto);
    }

    /**
     * Método para adicionar um produto.
     * @param produtoDto
     * @return O produto adicionado.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        produtoDto.setId(null);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());

        return produtoDto;
    }

    /**
     * Método para remover um produto por Id.
     * @param id do produto
     */
    public void deletar(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado, O produto com id: " + id + " não foi encontrado para ser excluído.");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Método para Atualizar um produto por Id.
     * @param id
     * @param produtoDto
     * @return Retorna o produto atualizado.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado, O produto com id: " + id + " não foi encontrado para ser atualizado.");
        }
        produtoDto.setId(id);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);

        try {
            produtoRepository.save(produto);
        } catch (BadRequestException ex) {
            ex.getMessage();
        }


        return produtoDto;
    }


}
