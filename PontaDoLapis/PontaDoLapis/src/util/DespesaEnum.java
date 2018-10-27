package util;

/**
 * @author Plamedi Luzolo Lusembo
 */
public enum DespesaEnum {
    HABITACAO(1, "Habitação"),
    SAUDE(2, "Saúde"),
    TRANSPORTE(3, "Transporte"),
    AUTOMOVEL(4, "Automóvel"),
    DESPESAS_PESSOAIS(5, "Despesas Pessoais"),
    CARTOES_CREDITO(6, "Cartões de crédito"),
    LAZER(7, "Lazer"),
    DEPENDENTES(8, "Dependentes");

    private final int codigo;
    private final String descricao;

    DespesaEnum(int id, String descricao) {
        this.codigo = id;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
