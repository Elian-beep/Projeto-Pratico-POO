package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.enums.Cor;

public class Program {

	public void areaCategoria() {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Program main = new Program();
		
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

		int opc = 0;
		while (opc != 5) {
			System.out.println("\nMENU CATEGORIA\n");
			System.out.println("Entre com a opção:");
			System.out.println("1- Cadastrar");
			System.out.println("2- Listar");
			System.out.println("3- Editar");
			System.out.println("4- Excluir");
			System.out.println("5- Voltar");
			System.out.printf("Sua opção: ");
			opc = sc.nextInt();
			sc.nextLine();
			main.limparConsole();

			switch (opc) {
			case 1: {
				System.out.println("--------------CADASTRANDO CATEGORIA--------------\n");
				
				System.out.printf("Descrição: ");
				String descricao = sc.nextLine();
				System.out.printf("Preço da Diária: ");
				double precoDiaria = sc.nextDouble();
				
				Categoria novaCategoria = new Categoria(null, descricao, precoDiaria);
				categoriaDao.insert(novaCategoria);
				System.out.println("\nINSERIDO! Novo id: "+novaCategoria.getId());
				break;
			}

			case 2: {
				System.out.println("--------------BUSCANDO TODAS AS CATEGORIAS--------------\n");
				List<Categoria> list = categoriaDao.findAll();
				for (Categoria categoriaTodos : list) {
					System.out.println(categoriaTodos);
				}
				break;
			}

			case 3: {
				System.out.println("--------------ATUALIZANDO CATEGORIA--------------\n");
				System.out.printf("Informe o ID da categoria a ser atualizada: ");
				int idCat = sc.nextInt();
				sc.nextLine();
				Categoria categoria = categoriaDao.findById(idCat);
				System.out.println(categoria);
				
				System.out.printf("\nNova descrição: ");
				String descricao = sc.nextLine();
				System.out.printf("Novo preço da Diária: ");
				double precoDiaria = sc.nextDouble();
				
				categoria.setDescricao(descricao);
				categoria.setPrecoDiaria(precoDiaria);
				categoriaDao.update(categoria);
				System.out.println("ATUALIZAÇÃO COMPLETADA");
				break;
			}

			case 4: {
				System.out.println("\n--------------EXCLUINDO UMA CATEGORIA--------------\n");
				System.out.println("Digite 999 para cancelar");
				System.out.println("Entre com o id a ser deletado: ");
				int idCat = sc.nextInt();
				
				if(idCat == 999)
					break;
				
				Categoria categoria = categoriaDao.findById(idCat);
				System.out.println("Categoria deletada: "+categoria);
				
				categoriaDao.deleteById(idCat);
				System.out.println("DELETADO COM SUCESSO");
				break;
			}

			case 5: {
				return;
			}

			default:
				System.out.println("\n\nComando inválido!\n\n");
				break;
			}
		}
	}

	public void areaCarro() {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Program main = new Program();
		
		CarroDao carroDao = DaoFactory.createCarroDao();
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
		
		int opc = 0;
		while (opc != 6) {
			System.out.println("\nMENU CARRO\n");
			System.out.println("Entre com a opção:");
			System.out.println("1- Cadastrar");
			System.out.println("2- Listar todos");
			System.out.println("3- Listar por categoria");
			System.out.println("4- Editar");
			System.out.println("5- Excluir");
			System.out.println("6- Voltar");
			System.out.printf("Sua opção: ");
			opc = sc.nextInt();
			sc.nextLine();
			main.limparConsole();

			switch (opc) {
			case 1: {
				System.out.println("--------------CADASTRANDO UM CARRO--------------\n");
				
				System.out.println("Modelo: ");
				String modelo = sc.nextLine();
				System.out.println("Placa: ");
				String placa = sc.nextLine();
				System.out.println("Cor [BRANCA/PRETA/CINZA/VERMELHA]: ");
				String cor = sc.nextLine();
				System.out.println("Ano de fabricação: ");
				int ano = sc.nextInt();
				sc.nextLine();
				System.out.println("Data de Aquisição (dd/mm/yyyy): ");
				String dataAquisicao = sc.nextLine();
				System.out.println("Id da categoria: ");
				int idCategoria = sc.nextInt();
				sc.nextLine();
				Categoria categoria = categoriaDao.findById(idCategoria);
				
				Carro novoCarro = new Carro(null, modelo, placa, Cor.valueOf(cor), ano, LocalDate.parse(dataAquisicao, DateTimeFormatter.ofPattern("dd/MM/yyyy")), categoria);
				carroDao.insert(novoCarro);
				System.out.println("INSERIDO! Novo id: "+novoCarro.getId());
				break;
			}

			case 2: {
				System.out.println("--------------BUSCANDO TODOS OS CARROS--------------\n");
				List<Carro> list = carroDao.findAll();
				for (Carro carroTodos : list) {
					System.out.println(carroTodos);
				}
				break;
			}

			case 3: {
				System.out.println("--------------BUSCANDO CARROS POR CATEGORIA--------------\n"); 
				System.out.printf("Id da categoria: ");
				int idCategoria = sc.nextInt();
				sc.nextLine();
				Categoria categoria = new Categoria(idCategoria, null, null); 
				List<Carro> carroCategorias = carroDao.findByIdCategoria(categoria); 
				for (Carro obj : carroCategorias) {
					System.out.println(obj); 
				}
				break;
			}

			case 4: {
				System.out.println("--------------ATUALIZANDO UM CARRO--------------\n");
				
				System.out.printf("Informe o ID do carro a ser atualizada: ");
				int idCar = sc.nextInt();
				sc.nextLine();
				
				Carro carro = carroDao.findById(idCar);
				System.out.println(carro);
				
				System.out.println("Novo modelo: ");
				String modelo = sc.nextLine();
				System.out.println("Nova placa: ");
				String placa = sc.nextLine();
				System.out.println("Nova cor [BRANCA/PRETA/CINZA/VERMELHA]: ");
				String cor = sc.nextLine();
				System.out.println("Novo ano de fabricação: ");
				int ano = sc.nextInt();
				sc.nextLine();
				System.out.println("Nova data de Aquisição (dd/mm/yyyy): ");
				String dataAquisicao = sc.nextLine();
				System.out.println("Novo id da categoria: ");
				int idCategoria = sc.nextInt();
				sc.nextLine();
				Categoria categoria = categoriaDao.findById(idCategoria);
				
				carro.setModelo(modelo);
				carro.setPlaca(placa);
				carro.setCor(Cor.valueOf(cor));
				carro.setAno(ano);
				carro.setDataAquisicao(LocalDate.parse(dataAquisicao, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				carro.setCategoriaId(categoria);
				carroDao.update(carro);
				System.out.println("ATUALIZAÇÃO COMPLETADA");
				break;
			}

			case 5: {
				System.out.println("\n--------------EXCLUINDO UM CARRO--------------\n");
				System.out.println("Digite 999 para cancelar");
				System.out.println("Entre com o id a ser deletado: ");
				int idCar = sc.nextInt();
				
				if(idCar == 999)
					break;
				
				carroDao.deleteById(idCar);
				System.out.println("DELETADO COM SUCESSO");
				break;
			}

			case 6: {
				return;
			}

			default:
				System.out.println("\n\nComando inválido!\n\n");
				break;
			}
		}
	}

	public void areaCliente() {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Program main = new Program();

		ClienteDao clienteDao = DaoFactory.createClienteDao();

		int opc = 0;
		while (opc != 5) {
			System.out.println("\nMENU CLIENTE\n");
			System.out.println("Entre com a opção:");
			System.out.println("1- Cadastrar");
			System.out.println("2- Listar");
			System.out.println("3- Editar");
			System.out.println("4- Excluir");
			System.out.println("5- Voltar");
			System.out.printf("Sua opção: ");
			opc = sc.nextInt();
			sc.nextLine();
			main.limparConsole();

			switch (opc) {
			case 1: {
				System.out.println("--------------CADASTRANDO CLIENTE--------------\n");

				System.out.printf("Nome: ");
				String nome = sc.nextLine();
				System.out.printf("CPF [000.000.000-00]: ");
				String cpf = sc.nextLine();
				System.out.printf("Email: ");
				String email = sc.nextLine();
				System.out.printf("Telefone: ");
				String telefone = sc.nextLine();

				Cliente novoCliente = new Cliente(null, nome, cpf, email, telefone);
				clienteDao.insert(novoCliente);
				System.out.println("INSERIDO! Novo id: " + novoCliente.getId());
				break;
			}

			case 2: {
				System.out.println("--------------BUSCANDO TODOS OS CLIENTES--------------\n");
				List<Cliente> list = clienteDao.findAll();
				for (Cliente clienteTodos : list) {
					System.out.println(clienteTodos);
				}
				break;
			}

			case 3: {
				System.out.println("--------------ATUALIZANDO UM CLIENTE--------------\n");
				
				System.out.printf("Informe o ID do Cliente a ser atualizada: ");
				int idCli = sc.nextInt();
				sc.nextLine();
				Cliente cliente = clienteDao.findById(idCli);
				
				System.out.printf("Novi nome: ");
				String nome = sc.nextLine();
				System.out.printf("Novo CPF [000.000.000-00]: ");
				String cpf = sc.nextLine();
				System.out.printf("Novo email: ");
				String email = sc.nextLine();
				System.out.printf("Novo telefone: ");
				String telefone = sc.nextLine();
				
				cliente.setNome(nome);
				cliente.setCpf(cpf);
				cliente.setEmail(email);
				cliente.setTelefone(telefone);
				
				clienteDao.update(cliente);
				System.out.println("ATUALIZAÇÃO COMPLETADA");
				break;
			}

			case 4: {
				System.out.println("--------------EXCLUINDO UM CLIENTE--------------\n");
				System.out.println("Digite 999 para cancelar");
				System.out.println("Entre com o id a ser deletado: ");
				int idCli = sc.nextInt();
				
				if(idCli == 999)
					break;
				clienteDao.deleteById(idCli);
				System.out.println("DELETADO COM SUCESSO");
				break;
			}

			case 5: {
				return;
			}

			default:
				System.out.println("\n\nComando inválido!\n\n");
				break;
			}
		}
	}

	public void areaLocacao() {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Program main = new Program();
		
		LocacaoDao locacaoDao = DaoFactory.createLocacaoDao();
		CarroDao carroDao = DaoFactory.createCarroDao();
		
		int opc = 0;
		while (opc != 6) {
			System.out.println("\nMENU LOCAÇÃO\n");
			System.out.println("Entre com a opção:");
			System.out.println("1- Nova Locação");
			System.out.println("2- Devolução");
			System.out.println("3- Listar todas [NÃO FUNCIONA]");
			System.out.println("4- Listar locação por cliente [NÃO FUNCIONA]");
			System.out.println("5- Excluir");
			System.out.println("6- Voltar");
			System.out.printf("Sua opção: ");
			opc = sc.nextInt();
			sc.nextLine();
			main.limparConsole();

			switch (opc) {
			case 1: {
				System.out.println("--------------CADASTRANDO UMA LOCAÇÃO--------------\n");
				
				System.out.printf("Data de Retirada (dd/mm/aaaa): ");
				LocalDate dataRetirada = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				System.out.printf("Data de Devolução (dd/mm/aaaa): ");
				LocalDate dataDevolucao = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				System.out.printf("Id do Cliente: ");
				int idCliente = sc.nextInt();
				sc.nextLine();
				System.out.printf("Id do Carro: ");
				int idCarro = sc.nextInt();
				sc.nextLine();
				
				if (dataRetirada.until(dataDevolucao, ChronoUnit.DAYS) <= 10) {
					System.out.printf("Dias previstos: ");
					int diasPrevistos = sc.nextInt();
					sc.nextLine();
					
					LocacaoDiaria novaLocacaoDiaria = new LocacaoDiaria(null, dataRetirada, dataDevolucao, new Carro(idCarro, null, null, null, null, null, null), new Cliente(idCliente, null, null, null, null), diasPrevistos);
					locacaoDao.insert(novaLocacaoDiaria, null, true); // <- Para locações diárias
					System.out.println("INSERIDO! Novo id: "+novaLocacaoDiaria.getId());
				}else {	
					System.out.printf("Porcentagem de Desconto: ");
					double porcentagemDesconto = sc.nextDouble();
					sc.nextLine();
					
					LocacaoLongoPeriodo novaLocacaoLonga = new LocacaoLongoPeriodo(null, dataRetirada, dataDevolucao, new Carro(idCarro, null, null, null, null, null, null), new Cliente(idCliente, null, null, null, null), porcentagemDesconto);
					locacaoDao.insert(null, novaLocacaoLonga, false); // <- Para locações longas
					System.out.println("INSERIDO! Novo id: "+novaLocacaoLonga.getId());
				}
				
				break;
			}

			case 2: {
				System.out.println("--------------EXIBINDO AS DEVOLUÇÕES--------------");
				double valorDevolucao = 0.0;
				System.out.printf("Qual tipo de locação (1- Diária | 2- Longo Período): ");
				int opcTipo = sc.nextInt();
				sc.nextLine();
				
				System.out.printf("Id da locação: ");
				int idLocacao = sc.nextInt();
				sc.nextLine();
				
				if (opcTipo == 1) {
					
					LocacaoDiaria locacaoDiaria = (LocacaoDiaria) locacaoDao.findById(idLocacao, true); //<- Para locações diárias
					System.out.println("locação encontrada: "+locacaoDiaria);
					Carro carroDaLocacao = carroDao.findById(locacaoDiaria.getCarroId().getId()); //Necessário para pegar o valor das diarias
					Double valorDiariaBuscado = carroDaLocacao.getCategoriaId().getPrecoDiaria();
					valorDevolucao = locacaoDao.Devolucao(locacaoDiaria, null, valorDiariaBuscado, true); //Devolução diaria
				}else {
					LocacaoLongoPeriodo locacaoLongo = (LocacaoLongoPeriodo) locacaoDao.findById(idLocacao, false); //<- Para locações longo periodo
					System.out.println("locação encontrada: "+locacaoLongo);
					Carro carroDaLocacao = carroDao.findById(locacaoLongo.getCarroId().getId()); //Necessário para pegar o valor das diarias
					Double valorDiariaBuscado = carroDaLocacao.getCategoriaId().getPrecoDiaria();
					valorDevolucao = locacaoDao.Devolucao(null, locacaoLongo, valorDiariaBuscado, false); //Devolução Longo
				}
				
				System.out.println("Valor final da Devolução: "+String.format("%.2f", valorDevolucao));
				break;
			}

//			case 3: {
//				System.out.println("Listados...");
//				break;
//			}
//
//			case 4: {
//				System.out.println("Listados por clientes...");
//				break;
//			}

			case 5: {
				System.out.println("\n--------------EXCLUINDO UMA LOCAÇÃO--------------\n");
				System.out.println("Digite 999 para cancelar");
				System.out.println("Entre com o id a ser deletado: ");
				int idLoc = sc.nextInt();
				
				if(idLoc == 999)
					break;
				
				locacaoDao.deleteById(idLoc);
				System.out.println("DELETADO COM SUCESSO");
				break;
			}

			case 6: {
				return;
			}

			default:
				System.out.println("\n\nComando inválido!\n\n");
				break;
			}
		}
	}

	public void limparConsole() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Program main = new Program();

		main.limparConsole();
		int opcMaster = 0;

		while (opcMaster != 5) {
			System.out.println("\nSISTEMA DE LOCAÇÃO DE CARROS:\nMENU PRINCIPAL\n");
			System.out.println("Entre com a opção:");
			System.out.println("1- Categoria");
			System.out.println("2- Carro");
			System.out.println("3- Cliente");
			System.out.println("4- Locação");
			System.out.println("5- Sair");
			System.out.printf("Sua opção: ");
			opcMaster = sc.nextInt();
			main.limparConsole();
			sc.nextLine();

			switch (opcMaster) {
			case 1: {
				main.areaCategoria();
				break;
			}

			case 2: {
				main.areaCarro();
				break;
			}

			case 3: {
				main.areaCliente();
				break;
			}

			case 4: {
				main.areaLocacao();
				break;
			}

			case 5: {
				System.out.println("===========PROGRAMA ENCERRADO===========");
				return;
			}

			default:
				System.out.println("\n\nComando inválido!\n\n");
				break;
			}
		}
	}
}
