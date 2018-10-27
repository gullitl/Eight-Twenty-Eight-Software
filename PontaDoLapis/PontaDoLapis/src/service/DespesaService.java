package service;

import java.util.List;
import model.Despesa;

/**
 * @author Plamedi Luzolo Lusembo
 */
public class DespesaService {

    private static DespesaService uniqueInstance;

    public DespesaService() {
    }

    public static synchronized DespesaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DespesaService();
        }
        return uniqueInstance;
    }

    public boolean salvar(Despesa despesa) {
        return true;
    }

    public boolean excluir(String idDespesa) {
        return true;
    }

    public List<Despesa> listar() {
        return null;
    }

}
