package br.com.fiap.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.beans.Cargo;
import br.com.fiap.dao.CargoDAO;
import br.com.fiap.excecao.Excecao;

public abstract class CargoBO{
	public static void novoCargo(Cargo c, Connection conn) throws Exception{
		if(c.getCargo().length()<2){
			throw new Excecao("Caracteres insuficientes no Cargo");
		}
		if(c.getSalario()<700){
			throw new Excecao("Sal�rio n�o pode ser menor que 700");
		}
		new CargoDAO().gravar(c,conn);
	}
	public static List<Cargo> listarCargos(Connection conn) throws Exception{
		return new CargoDAO().getLista(conn);
	}
	
	public static Cargo consultarCargo(String pCargo, Connection conn) throws Exception{
		return new CargoDAO().getPesquisarCargo(pCargo.toUpperCase(), conn);
	}
	
	public static void deletar(String pCargo, Connection conn) throws Exception{
		if(pCargo.length()<2){
			throw new Excecao("Caracteres insuficientes no cargo");
		}
		new CargoDAO().deletar(pCargo, conn);
	}
	public static int atualizarSalario(double pAumento, Connection conn) throws Exception{
		return new CargoDAO().atualizar(pAumento, conn);
	}
}
