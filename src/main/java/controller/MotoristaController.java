package controller;

import dao.MotoristaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Motorista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MotoristaController {
    // Injeção dos componentes da interface gráfica definidos no arquivo FXML
    @FXML
    private Button btnSalvar, btnPesquisar, btnListar;
    @FXML
    private TextField textNome, textCPF, textCNH, textPesquisar;
    @FXML
    private TableView<Motorista> tabelaMotoristas;

    // Método inicializar, chamado após todos os componentes FXML terem sido carregados
    @FXML
    private void initialize() {
        // Configurar as colunas da tabela assim que a interface gráfica for carregada
        configurarTabela();
    }

    // Método para configurar as colunas da tabela de motoristas
    private void configurarTabela() {
        // Definindo as colunas e associando-as aos atributos do objeto Motorista
        TableColumn<Motorista, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Motorista, Integer> idCidadeCol = new TableColumn<>("ID Cidade");
        idCidadeCol.setCellValueFactory(new PropertyValueFactory<>("idCidade"));

        TableColumn<Motorista, Integer> idVeiculoCol = new TableColumn<>("ID Veículo");
        idVeiculoCol.setCellValueFactory(new PropertyValueFactory<>("idVeiculo"));

        TableColumn<Motorista, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Motorista, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Motorista, String> cnhCol = new TableColumn<>("CNH");
        cnhCol.setCellValueFactory(new PropertyValueFactory<>("cnh"));

        TableColumn<Motorista, Date> dataCadastroCol = new TableColumn<>("Data de Cadastro");
        dataCadastroCol.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));

        // Adicionando as colunas configuradas à tabela
        tabelaMotoristas.getColumns().addAll(idCol, idCidadeCol, idVeiculoCol, nomeCol, cpfCol, cnhCol, dataCadastroCol);
    }

    // Método para salvar um motorista no banco de dados
    @FXML
    private void salvarCadastro() throws SQLException {
        // Criando um novo objeto motorista e preenchendo com os dados da interface gráfica
        Motorista motorista = new Motorista();
        motorista.setNome(textNome.getText());
        motorista.setCpf(textCPF.getText());
        motorista.setCnh(textCNH.getText());
        motorista.setDataCadastro(new Date());  // Capturando a data atual para o cadastro
        motorista.setIdCidade(1);  // Você precisará definir como obter ou selecionar o ID da cidade
        motorista.setIdVeiculo(1); // Você precisará definir como obter ou selecionar o ID do veículo

        // Instanciando o DAO e salvando o motorista no banco de dados
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.inserir(motorista);
    }

    // Método para pesquisar um motorista no banco de dados
    @FXML
    private void pesquisar() throws SQLException {
        // Obtendo o ID a ser pesquisado a partir do campo de texto
        int id = Integer.parseInt(textPesquisar.getText());
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        Motorista motorista = motoristaDAO.pesquisar(id);

        // Atualizando os campos de texto da interface gráfica com os dados do motorista encontrado
        textNome.setText(motorista.getNome());
        textCPF.setText(motorista.getCpf());
        textCNH.setText(motorista.getCnh());
        // Você também pode querer exibir a data de cadastro, ID da cidade e ID do veículo
    }

    // Método para listar todos os motoristas e exibi-los na tabela
    @FXML
    private void listarMotoristas() throws SQLException {
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        List<Motorista> listaMotoristas = motoristaDAO.listar();
        ObservableList<Motorista> motoristas = FXCollections.observableArrayList(listaMotoristas);
        tabelaMotoristas.setItems(motoristas);  // Definindo os itens da tabela
    }
}
