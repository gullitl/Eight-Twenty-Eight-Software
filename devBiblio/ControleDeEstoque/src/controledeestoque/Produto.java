/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controledeestoque;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class Produto {

    private int id;
    private char status;
    private String descricao;
    private int estoque_minimo;
    private int estoque_maximo;

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public int getEstoque_maximo() {
        return estoque_maximo;
    }

    public void setEstoque_maximo(int estoque_maximo) {
        this.estoque_maximo = estoque_maximo;
    }

}
