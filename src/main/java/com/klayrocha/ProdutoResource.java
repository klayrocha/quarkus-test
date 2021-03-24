package com.klayrocha;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

	@GET
	public List<Produto> buscarTodosProdutos(){
		return Produto.listAll();
	}
	
	@POST
	@Transactional
	public void createProduto(ProdutoDTO dto){
		Produto p = new Produto();
		p.nome = dto.nome;
		p.valor = dto.valor;
		p.dataCriacao = new Date();
		p.dataAtualizacao = new Date();
		p.persist();
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void createProduto(@PathParam("id") Long id, ProdutoDTO dto){
		Optional<Produto> pOp = Produto.findByIdOptional(id);
		
		if(pOp.isPresent()) {
			Produto p = pOp.get();
			p.nome = dto.nome;
			p.valor = dto.valor;
			p.dataAtualizacao = new Date();
			p.persist();
		} else {
			throw new NotFoundException();
		}
		
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void createProduto(@PathParam("id") Long id){
		Optional<Produto> pOp = Produto.findByIdOptional(id);
		
		if(pOp.isPresent()) {
			Produto p = pOp.get();
			p.delete();
		} else {
			throw new NotFoundException();
		}
		
	}

}
