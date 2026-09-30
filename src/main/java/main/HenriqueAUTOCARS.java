package main;
 
import DAO.ConexaoBD;
import view.TelaCliente;
 
/**
 *
 * @author eduar
 */
public class HenriqueAUTOCARS {
 
    public static void main(String[] args) {
        ConexaoBD.inicializarBanco();
        System.out.println("Banco de dados do HenriqueAUTOCARS!!!!");
 
        // Somente a tela de cliente (login) abre ao iniciar.
        // A ListadeCompra abre ao clicar em "Entrar" (ver TelaCliente).
        TelaCliente telaClien = new TelaCliente();
        telaClien.setVisible(true);
    }
 
}
