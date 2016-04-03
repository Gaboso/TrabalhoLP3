package br.edu.ifms.lp3.util;

import br.edu.ifms.lp3.constant.Textual;
import br.edu.ifms.lp3.dao.ClienteDAO;
import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.helper.ScreenHelper;

import javax.swing.*;

/**
 * Classe com métodos auxiliares ao uso da classe Client
 */
public class ManagerClient extends ScreenHelper{

    /**
     * Método que cria um objeto de cliente com os dados capturados na tela
     *
     * @param address - Endereço do cliente
     * @param name    - Nome do cliente
     * @param phone   - Telefone do cliente
     * @param cpf     - CPF do cliente
     * @param sex     - Sexo do cliente
     * @return Retorna o objeto de cliente
     */
    private Cliente createClient(String address, String name, String phone, String cpf, char sex) {
        Cliente client = new Cliente();

        client.setNome(name);
        client.setEndereco(address);
        client.setTelefone(phone);
        client.setCpf(cpf);
        client.setSexo(sex);

        return client;
    }

    /**
     * Método para cadastrar os clientes
     *
     * @param client - Objeto do cliente
     * @param frame  - Frame atual
     * @return Retorna true se foi possível cadastrar e false caso contrário
     */
    public boolean recordClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();
        // Se ocorreu tudo bem e foi cadastrado
        if (clientDAO.save(client) != null) {
            showInformationMessage(frame, "efetuado");
            return true;
        } else {
            showMessageError(frame, Textual.CPF_JA_CADASTRADO);
            return false;
        }
    }

    /**
     * Método para atualizar um registro de cliente
     *
     * @param client - Objeto do cliente
     * @param frame  - Frame atual
     * @return Retorna true se foi possível atualizar e false caso contrário
     */
    public boolean updateClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();
        // Se ocorreu tudo bem e foi cadastrado
        if (clientDAO.update(client) != null) {
            showInformationMessage(frame, "atualizado");
            return true;
        } else {
            showMessageError(frame, Textual.IMPOSSIVEL_ATUALIZAR);
            return false;
        }
    }

    /**
     * Método para excluir cliente
     *
     * @param client - Objeto do cliente
     * @param frame  - Frame atual
     * @return Retorna true se foi possível remover e false caso contrário
     */
    public boolean removeClient(Cliente client, JFrame frame) {
        ClienteDAO clientDAO = new ClienteDAO();
        // Se ocorreu tudo bem e foi cadastrado
        if (clientDAO.remove(client)) {
            showInformationMessage(frame, "removido");
            return true;
        } else {
            showMessageError(frame, Textual.IMPOSSIVEL_REMOVER);
            return false;
        }
    }

    /**
     * Método para criar e validar um cliente
     *
     * @param name              - Nome do cliente
     * @param address           - Endereço do cliente
     * @param cpf               - CPF do cliente
     * @param phone             - Telefone do cliente
     * @param radioButtonFemale - Botão de radio feminino
     * @param radioButtonMale   - Botão de radio feminino masculino
     * @return Retorna objeto do cliente se foi possível cadastrar e null caso contrário
     */
    public Cliente createValidateClient(String name, String address, String cpf, String phone,
                                        JRadioButton radioButtonFemale, JRadioButton radioButtonMale) {
        char sex = ' ';

        Validator validator = new Validator();

        boolean error = validator.checkAll(address, name, cpf, phone, radioButtonMale, radioButtonFemale);

        if (!error) {
            if (radioButtonFemale.isSelected()) {
                sex = 'f';
            } else if (radioButtonMale.isSelected()) {
                sex = 'm';
            }

            return createClient(address, name, phone, cpf, sex);
        } else {
            return null;
        }
    }
}