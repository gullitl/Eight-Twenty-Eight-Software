package service;

import java.util.List;
import model.Rendimento;

/**
 * @author Plamedi Luzolo Lusembo
 */
public class RendimentoService {

    private static RendimentoService uniqueInstance;

    public RendimentoService() {
    }

    public static synchronized RendimentoService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RendimentoService();
        }
        return uniqueInstance;
    }

    public boolean salvar(Rendimento rendimento) {
        return true;
    }

    public boolean excluir(String idDespesa) {
        return true;
    }

    public List<Rendimento> listar() {
        return null;
    }

}
