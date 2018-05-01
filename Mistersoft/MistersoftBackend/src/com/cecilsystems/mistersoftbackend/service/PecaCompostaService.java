package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.ItemPecaCompostaDao;
import com.cecilsystems.mistersoftbackend.dao.MaoDeObraPecaCompostaDao;
import com.cecilsystems.mistersoftbackend.dao.PecaCompostaDao;
import com.cecilsystems.mistersoftbackend.model.PecaComposta;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

public class PecaCompostaService {

    private static PecaCompostaService uniqueInstance;

    public PecaCompostaService() {

    }

    public static synchronized PecaCompostaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaCompostaService();
        }
        return uniqueInstance;
    }

    public List<PecaComposta> listaPecaComposta() throws ClassNotFoundException, SQLException {
        return PecaCompostaDao.getInstance().lista();
    }

    public PecaComposta selecionaPecaComposta(int codigoPecaComposta)
            throws ClassNotFoundException, SQLException {

        PecaComposta pecaComposta = PecaCompostaDao.getInstance().seleciona(codigoPecaComposta);

        pecaComposta.setLstItensPecaComposta(ItemPecaCompostaDao.getInstance()
                .lista(pecaComposta.getCdPeca()));

        pecaComposta.setVlTotalItens(new BigDecimal("0.00"));
        pecaComposta.getLstItensPecaComposta().stream().forEach((ipc) -> {
            pecaComposta.setVlTotalItens(pecaComposta.getVlTotalItens().add(ipc.getVlCustoTotal()));
        });
        pecaComposta.setVlTotalItens(pecaComposta.getVlTotalItens()
                .setScale(2, RoundingMode.HALF_UP));

        pecaComposta.setLstMaoDeObra(MaoDeObraPecaCompostaDao.getInstance()
                .lista(pecaComposta.getCdPeca()));

        pecaComposta.setVlTotalMaoDeObra(new BigDecimal("0.00"));
        pecaComposta.getLstMaoDeObra().stream().forEach((mdo) -> {
            pecaComposta.setVlTotalMaoDeObra(pecaComposta.getVlTotalMaoDeObra().add(mdo.getVlCustoTotal()));
        });
        pecaComposta.setVlTotalMaoDeObra(pecaComposta.getVlTotalMaoDeObra()
                .setScale(2, RoundingMode.HALF_UP));

        pecaComposta.setVlCusto(pecaComposta.getVlTotalItens().add(pecaComposta.getVlTotalMaoDeObra())
                .setScale(2, RoundingMode.HALF_UP));

        pecaComposta.setVlVenda(pecaComposta.getVlCusto().multiply(pecaComposta.getMarkup())
                .setScale(2, RoundingMode.HALF_UP));

        return pecaComposta;
    }

    public boolean salvaPecaComposta(PecaComposta pecaComposta, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? PecaCompostaDao.getInstance().salva(pecaComposta)
                : PecaCompostaDao.getInstance().atualiza(pecaComposta);
    }

    public boolean excluiPecaComposta(int codigoPecaComposta) throws ClassNotFoundException, SQLException {
        return PecaCompostaDao.getInstance().remove(codigoPecaComposta);
    }

}
