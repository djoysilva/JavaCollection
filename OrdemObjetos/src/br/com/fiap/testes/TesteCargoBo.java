package br.com.fiap.testes;

import java.sql.Connection;

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
			Cargo c = new Cargo();
			do{
				c = new Cargo();
				c.setCargo(JOptionPane.showInputDialog("Digite o cargo: "));
				c.setNivel(JOptionPane.showInputDialog("Digite o n�vel: "));
				c.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o salario: ")));
				CargoBO.novoCargo(c, con);
			}while(JOptionPane.showConfirmDialog(null,"Deseja adicionar mais um cargo?", "CARGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
		}catch (Exception e){
			throw new Excecao("Deu erro", e);
		}finally{
			CargoBO.listarCargos(con);
		}
	}
}
