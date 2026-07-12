package com.ecam.ecam.login.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum PerfilAcesso {

    ADMINISTRADOR("Administrador", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR, 
            Permissao.DELETAR, Permissao.EXPORTAR, Permissao.COPIAR, Permissao.APROVAR_ALTERACAO)),

    COORDENADOR_GERAL("Coordenador Geral", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR, 
            Permissao.DELETAR, Permissao.EXPORTAR, Permissao.COPIAR, Permissao.APROVAR_ALTERACAO)),

    COORDENADOR_EQUIPE("Coordenador de Equipe", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR, 
            Permissao.DELETAR, Permissao.APROVAR_ALTERACAO)),

    OPERADOR_CADASTRO("Operador de Cadastro", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR)), // Edição será via tabela temporária

    OPERADOR_ATENDIMENTO("Operador de Atendimento", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR)),

    JURIDICO("Jurídico", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR)),

    PRESTACAO_CONTAS("Prestação de Contas", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR)),

    COMUNICACAO("Comunicação", Arrays.asList(
            Permissao.LER_DADOS, Permissao.CADASTRAR, Permissao.EDITAR));

    private final String nomePerfilDb;
    private final List<Permissao> permissoes;

    PerfilAcesso(String nomePerfilDb, List<Permissao> permissoes) {
        this.nomePerfilDb = nomePerfilDb;
        this.permissoes = permissoes;
    }

    public static PerfilAcesso buscarPorNome(String nome) {
        for (PerfilAcesso perfil : values()) {
            if (perfil.getNomePerfilDb().equalsIgnoreCase(nome)) {
                return perfil;
            }
        }
        return null; 
    }
}