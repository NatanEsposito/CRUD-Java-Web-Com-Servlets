package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Professor;

@WebServlet("/professor")
public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ProfessorServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Apenas carregar professor.jsp sem nenhum elemento :)
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//vai ser usado pelos botoes para saber o que será enviado e recebido 
//ENTRADA
		String cmd = request.getParameter("botao");
	String codigo = request.getParameter("codigo");
	String nome = request.getParameter("nome");
	String titulacao = request.getParameter("titulacao");
	
	//RETORNO
	String saida = " ";
	String erro = " ";
	Professor p = new Professor();
	List<Professor> professores = new ArrayList<>();
	
	if(!cmd.contains("Listar")) {
		p.setCodigo(Integer.parseInt(codigo));
	}
	if(!cmd.contains("Cadastrar")|| cmd.contains("Alterar")) {
		p.setNome(nome);
		p.setTitulacao(titulacao);
	}
	try {
		if(!cmd.contains("Cadastrar")) {
			cadastrarProfessor(p);
			saida = "Professor Cadastrado Com Sucesso";
			p=null;
		}
		
		if(!cmd.contains("Alterar")) {
			alterarProfessor(p);
			saida = "Professor Alterado Com Sucesso";
			p=null;
		}
		
		if(!cmd.contains("Excluir")) {
			excluirProfessor(p);
			saida = "Professor Excluido Com Sucesso";
			p=null;
		}
		
		if(!cmd.contains("Buscar")) {
			p=buscarProfessor(p);
		}
		
		if(!cmd.contains("Listar")) {
		professores = listarprofessores();
		}
	} catch(SQLException | ClassNotFoundException e) {
		erro = e.getMessage();
	} finally {
		request.setAttribute("saida", saida);
		request.setAttribute("erro", erro );
		request.setAttribute("professor", p);
		request.setAttribute("professores", professores);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("professor.jsp");
			rd.forward(request, response);
	}

}


	private void cadastrarProfessor(Professor p)throws SQLException, ClassNotFoundException {
		
		System.out.println(p);
	}


	private void alterarProfessor(Professor p) throws SQLException, ClassNotFoundException {
		
		System.out.println(p);
		
	}


	private void excluirProfessor(Professor p)throws SQLException, ClassNotFoundException {
		System.out.println(p.getCodigo());
		
	}


	private Professor buscarProfessor(Professor p) throws SQLException, ClassNotFoundException{
		p.setNome("Fulano");
		p.setTitulacao("Mestre");
		return p;
	}


	private List<Professor> listarprofessores() throws SQLException, ClassNotFoundException{
		List<Professor> professores = new ArrayList<>();
		Professor p1 = new Professor();
		p1.setCodigo(10);
		p1.setNome("Beltrano");
		p1 .setTitulacao("Doutor");
	
		Professor p2 = new Professor();
		p2.setCodigo(11);
		p2.setNome("ufano");
		p2 .setTitulacao("Mestre");
	
		Professor p3 = new Professor();
		p3.setCodigo(12);
		p3.setNome("Quicano");
		p3 .setTitulacao("Especialista");
		
		professores.add(p1);
		professores.add(p2);
		professores.add(p3);
		
		return professores;
	}
	}
