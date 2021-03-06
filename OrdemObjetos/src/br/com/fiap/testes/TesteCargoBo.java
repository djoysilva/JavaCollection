package br.com.fiap.testes;

import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.beans.Cargo;
import br.com.fiap.bo.CargoBO;
import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.excecao.Excecao;

public class TesteCargoBo {
	public static void main(String[] args) throws Exception{
		Connection con = null;
		try{
			//CargoBo bo = new CargoBO();
			con = ConexaoFactory.controlarInstancia().getConnection("", "");
			con.setAutoCommit(false);
			Cargo c = new Cargo();
			do{
				c = new Cargo();
				c.setCargo(JOptionPane.showInputDialog("Digite o cargo: "));
				c.setNivel(JOptionPane.showInputDialog("Digite o n�vel: "));
				c.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o salario: ")));
				CargoBO.novoCargo(c, con);
			}while(JOptionPane.showConfirmDialog(null,"Deseja adicionar mais um cargo?", "CARGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
			List<Cargo> lista = CargoBO.listarCargos(con);
			
			for (Cargo obj : lista){
				System.out.println(obj.getCargo() + "\n" + obj.getNivel() + "\n" + obj.getSalario());
			}
			
			String strSearch = JOptionPane.showInputDialog("Digite o cargo que deseja pesquisar");
			c = CargoBO.consultarCargo(strSearch, con);
			System.out.println(c.getCargo());
			//int teste = 10/0;
			con.commit();
			con.setAutoCommit(true);
		}catch (Exception e){
			con.rollback();
			throw new Excecao("Deu erro", e);
		}finally{
			try{
				con.close();
			}catch(Exception e){
				throw new Excecao("Falha");
			}
		}
	}
}
