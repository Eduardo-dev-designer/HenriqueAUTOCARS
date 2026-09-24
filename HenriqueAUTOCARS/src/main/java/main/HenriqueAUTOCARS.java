package main;

import DAO.ConexaoBD;
import view.TelaFuncionario;

/**
 *
 * @author eduar
 */
public class HenriqueAUTOCARS {

    public static void main(String[] args) {
        ConexaoBD.inicializarBanco();
        System.out.println("Banco de dados do HenriqueAUTOCARS!!!!");
        TelaFuncionario telaFunc = new TelaFuncionario();
        telaFunc.setVisible(true);
    }
}