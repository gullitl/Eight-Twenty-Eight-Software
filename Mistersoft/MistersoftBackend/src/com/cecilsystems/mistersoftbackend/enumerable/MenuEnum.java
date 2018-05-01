package com.cecilsystems.mistersoftbackend.enumerable;

/**
 * @author Plamedi L. Lusembo
 */
public enum MenuEnum {
    //0.Configurações
    CONFIGURACOES_MODULOS("CFG001", "MODULOS"),
    CONFIGURACOES_CONTA_USUARIO("CFG002", "MINHA CONTA"),
    //MODULOS("ARQ003", "MÓDULOS"),    
    //1.Cadastros
    CADASTRO_PECA("CAD001", "CADASTRO PECA"),
    CADASTRO_MONTAGEM("CAD002", "MONTAGEM PEÇA"),
    CADASTRO_MAO_OBRA("CAD003", "CADASTRO MÃO DE OBRA"),
    CADASTRO_UNIDADE_MEDIDA("CAD004", "CADASTRO UNIDADE MEDIDA"),
    CADASTRO_GRUPO_USUARIO("CAD005", "CADASTRO GRUPO USUÁRIO"),
    CADASTRO_USUARIO("CAD006", "CADASTRO DE USUÁRIO"),
    //3.Relatórios
    RELATORIO_PECAS("REL001", "RELATÓRIO PECAS"),
    RELATORIO_MONTAGEM_PECA("REL002", "RELATÓRIO MONTAGEM PECA"),
    //3.Suporte
    BACKUP_BANCO_DADOS("SUP001", "BACKUP BANCO DE DADOS"),
    //4.Sobre
    AJUDA_SOBRE("SBR001", "SOBRE");

    private final String id;
    private final String titulo;

    MenuEnum(String id, String title) {
        this.id = id;
        this.titulo = title;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

}
