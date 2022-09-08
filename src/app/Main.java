package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.CarroDao;
import model.dao.CategoriaDao;
import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.LocacaoDao;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.enums.Cor;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
		CarroDao carroDao = DaoFactory.createCarroDao();
		LocacaoDao locacaoDao = DaoFactory.createLocacaoDao();
		
		//CLIENTE--------------------------------------------------------------------------------------------------------------------------------------
//		System.out.println("--------------TESTE 1: INSERINDO UM CLIENTE--------------");
//		Cliente novoCliente = new Cliente(null, "Elian criado Java", "050.402.422-12", "emailJava@email.com", "(00) 0000-0000");
//		clienteDao.insert(novoCliente);
//		System.out.println("INSERIDO! Novo id: "+novoCliente.getId());
		

//		System.out.println("--------------TESTE 2: BUSCANDO UM CLIENTE--------------");
//		Cliente cliente = clienteDao.findById(5);
//		System.out.println(cliente);
		
//		System.out.println("--------------TESTE 3: ATUALIZANDO UM CLIENTE--------------");
//		cliente = clienteDao.findById(5);
//		cliente.setNome("Elian (deve ser del)");
//		clienteDao.update(cliente);
//		System.out.println("ATUALIZAÇÃO COMPLETADA");
		
//		System.out.println("--------------TESTE 4: BUSCANDO TODOS OS CLIENTES--------------");
//		List<Cliente> list = clienteDao.findAll();
//		for (Cliente clienteTodos : list) {
//			System.out.println(clienteTodos);
//		}
		
//		System.out.println("\n--------------TESTE 5: EXCLUINDO UM CLIENTE--------------");
//		System.out.println("Entre com o id a ser deletado: ");
//		int id = sc.nextInt();
//		clienteDao.deleteById(id);
//		System.out.println("DELETADO COM SUCESSO");
		//CLIENTE--------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		//CATEGORIA--------------------------------------------------------------------------------------------------------------------------------------
//		System.out.println("--------------TESTE 1: INSERINDO UM CATEGORIA--------------");
//		Categoria novaCategoria = new Categoria(null, "Automotor", 400.00);
//		categoriaDao.insert(novaCategoria);
//		System.out.println("INSERIDO! Novo id: "+novaCategoria.getId());
		
//		System.out.println("--------------TESTE 2: BUSCANDO UMA CATEGORIA--------------");
//		Categoria categoria = categoriaDao.findById(4);
//		System.out.println(categoria);
//		
//		System.out.println("--------------TESTE 3: ATUALIZANDO UMA CATEGORIA--------------");
//		categoria = categoriaDao.findById(4);
//		categoria.setDescricao("[DEVE SER DEL]");
//		categoriaDao.update(categoria);
//		System.out.println("ATUALIZAÇÃO COMPLETADA");
		
//		System.out.println("--------------TESTE 4: BUSCANDO TODAS AS CATEGORIAS--------------");
//		List<Categoria> list = categoriaDao.findAll();
//		for (Categoria categoriaTodos : list) {
//			System.out.println(categoriaTodos);
//		}
		
//		System.out.println("\n--------------TESTE 5: EXCLUINDO UMA CATEGORIA--------------");
//		System.out.println("Entre com o id a ser deletado: ");
//		int id = sc.nextInt();
//		categoriaDao.deleteById(id);
//		System.out.println("DELETADO COM SUCESSO");
		//CLIENTE--------------------------------------------------------------------------------------------------------------------------------------

		
		
		
		
		//CARRO--------------------------------------------------------------------------------------------------------------------------------------
//		System.out.println("--------------TESTE 1: INSERINDO UM CARRO--------------");
//		Categoria categoria = categoriaDao.findById(2);
//		Carro novoCarro = new Carro(null, "fusca", "DFS-123", Cor.valueOf("PRETA"), 2015, LocalDate.parse("11/08/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), categoria);
//		carroDao.insert(novoCarro);
//		System.out.println("INSERIDO! Novo id: "+novoCarro.getId());
		
//		System.out.println("--------------TESTE 2: BUSCANDO UM CARRO--------------");
//		Carro carro = carroDao.findById(1);
//		System.out.println(carro);

//		System.out.println("--------------TESTE 3: ATUALIZANDO UM CARRO--------------");
//		Carro carro = carroDao.findById(3);
//		carro.setModelo("[DEVE SER DEL 2]");
//		carroDao.update(carro);
//		System.out.println("ATUALIZAÇÃO COMPLETADA");
//		
//		System.out.println("--------------TESTE 4: BUSCANDO TODOS OS CARROS--------------");
//		List<Carro> list = carroDao.findAll();
//		for (Carro carroTodos : list) {
//			System.out.println(carroTodos);
//		}
		
//		System.out.println("\n--------------TESTE 5: EXCLUINDO UM CARRO--------------");
//		System.out.println("Entre com o id a ser deletado: ");
//		int id = sc.nextInt();
//		carroDao.deleteById(id);
//		System.out.println("DELETADO COM SUCESSO");
		
//		System.out.println("\n=== TEST 6: BUSCANDO CARROS POR CATEGORIA ====="); 
//		Categoria categoria = new Categoria(2, null, null); 
//		List<Carro> carroCategorias = carroDao.findByIdCategoria(categoria); 
//		for (Carro obj : carroCategorias) {
//			System.out.println(obj); 
//		}
		//CLIENTE--------------------------------------------------------------------------------------------------------------------------------------
		
		
		//LOCACAO--------------------------------------------------------------------------------------------------------------------------------------
//		System.out.println("--------------TESTE 1: INSERINDO UMA LOCAÇÃO--------------");
//		LocacaoDiaria novaLocacaoDiaria = new LocacaoDiaria(null, LocalDate.parse("11/08/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("25/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Carro(1, null, null, null, null, null, null), new Cliente(1, null, null, null, null), 2);
//		LocacaoLongoPeriodo novaLocacaoLonga = new LocacaoLongoPeriodo(null, LocalDate.parse("11/08/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("25/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Carro(1, null, null, null, null, null, null), new Cliente(1, null, null, null, null), 0.05);
//		locacaoDao.insert(novaLocacaoDiaria, null, true); // <- Para locações diárias
//		//locacaoDao.insert(null, novaLocacaoLonga, false); // <- Para locações longas
//		System.out.println("INSERIDO! Novo id: "+novaLocacaoDiaria.getId());
//		//System.out.println("INSERIDO! Novo id: "+novaLocacaoLonga.getId());
		
//		System.out.println("--------------TESTE 2: BUSCANDO UMA LOCAÇÃO--------------");
		LocacaoDiaria locacaoDiaria = (LocacaoDiaria) locacaoDao.findById(5, true); //<- Para locações diárias
		LocacaoLongoPeriodo locacaoLongo = (LocacaoLongoPeriodo) locacaoDao.findById(3, false); //<- Para locações longo periodo
		System.out.println("locação encontrada: "+locacaoDiaria);
//		
		System.out.println("--------------TESTE 3: EXIBINDO AS DEVOLUÇÕES--------------");
		Carro carroDaLocacao = carroDao.findById(locacaoDiaria.getCarroId().getId()); //Necessário para pegar o valor das diarias
		Double valorDiariaBuscado = carroDaLocacao.getCategoriaId().getPrecoDiaria();
		
		Double valorDevolucaoDiaria = locacaoDao.Devolucao(locacaoDiaria, null, valorDiariaBuscado, true); //Devolução diaria
		Double valorDevolucaoLongo = locacaoDao.Devolucao(null, locacaoLongo, valorDiariaBuscado, false); //Devolução Longo
		System.out.println(valorDevolucaoLongo);
		
//		System.out.println("--------------TESTE 4: BUSCANDO TODOS OS LOCAÇÕES--------------");
//		FUNÇÃO INEXISTENTE
		
//		System.out.println("--------------TESTE 5: BUSCANDO TODOS OS CARROS POR CLIENTE--------------");
//		FUNÇÃO INEXISTENTE
		
//		System.out.println("--------------TESTE 4: EXCLUINDO UMA LOCAÇÃO--------------");
//		System.out.println("Entre com o id a ser deletado: ");
//		int id = sc.nextInt();
//		locacaoDao.deleteById(id);
//		System.out.println("DELETADO COM SUCESSO");
		
	}
}












