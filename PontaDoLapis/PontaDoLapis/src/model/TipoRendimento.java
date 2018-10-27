package model;

/**
 * @author Plamedi Luzolo Lusembo
 */
public class TipoRendimento {

    private String id;
    private String descricao;

    public TipoRendimento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static class TipoRendimentoBuilder {

        private final String id;
        private String descricao;

        public TipoRendimentoBuilder(String id) {
            this.id = id;
        }

        public TipoRendimentoBuilder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public TipoRendimento create() {
            TipoRendimento tipoRendimento = new TipoRendimento();
            tipoRendimento.setId(this.id);
            tipoRendimento.setDescricao(this.descricao);
            return tipoRendimento;
        }

    }

}
