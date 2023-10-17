package controller;

import dao.MotoristaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Motorista;

import java.sql.SQLException;
import java.util.Date;

public class MotoristaController {
    @FXML
    Button btnSalvar;
    @FXML
    TextField textNome;

    public void salvarCadastro() throws SQLException {
        //Criar um objeto motorista
        Motorista motorista = new Motorista();
        //Pegar dados da tela
        motorista.setNome(textNome.getText());
        motorista.setDataCadastro(new Date());
        motorista.setCnh("12345");
        motorista.setCpf("12345678901");
        motorista.setIdCidade(1);
        motorista.setIdVeiculo(1);
        //Chamar classe DAO para inserir no banco
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        motoristaDAO.inserir(motorista);

    }


}
