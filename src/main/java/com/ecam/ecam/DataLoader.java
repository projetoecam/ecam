package com.ecam.ecam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("====== VERIFICANDO CARGA INICIAL DE DADOS ======");

        // 1. Verifica se a tabela de municípios está vazia e insere um padrão (necessário para o bairro)
        Integer qtdMunicipios = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM municipios", Integer.class);
        if (qtdMunicipios == 0) {
            jdbcTemplate.execute("INSERT INTO municipios (nome) VALUES ('Município Padrão')");
            System.out.println("-> Município padrão inserido.");
        }

        // 2. Verifica se a tabela de macroterritórios está vazia
        Integer qtdMacros = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM macroterritorios", Integer.class);
        if (qtdMacros == 0) {
            jdbcTemplate.execute("INSERT INTO macroterritorios (nome, apelido, municipio_id) VALUES ('Macro Padrão', 'Macro', 1)");
            System.out.println("-> Macroterritório padrão inserido.");
        }

        // 3. Verifica se a tabela de bairros está vazia
        Integer qtdBairros = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bairros", Integer.class);
        if (qtdBairros == 0) {
            jdbcTemplate.execute("INSERT INTO bairros (nome, municipio_id, macroterritorio_id) VALUES ('Bairro Padrão', 1, 1)");
            System.out.println("-> Bairro padrão inserido.");
        }

        // 4. Verifica se a tabela de comunidades está vazia e insere com os campos obrigatórios (CHECK constraints)
        Integer qtdComunidades = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comunidades_microterritorios", Integer.class);
        if (qtdComunidades == 0) {
            jdbcTemplate.execute("INSERT INTO comunidades_microterritorios (nome, bairro_id, grau_prioridade, status_area, qtd_aproximada_moradores) " +
                    "VALUES ('Comunidade Padrão', 1, 'Média', 'Em expansão', 100)");
            System.out.println("-> Comunidade padrão inserida.");
        }

        // 5. Verifica se a tabela de usuários (operador) está vazia
        Integer qtdUsuarios = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM usuarios", Integer.class);
        if (qtdUsuarios == 0) {
            jdbcTemplate.execute("INSERT INTO usuarios (nome_completo, email, senha_hash, ativo) " +
                    "VALUES ('Administrador Padrão', 'admin@email.com', 'hash_senha_123', 1)");
            System.out.println("-> Usuário/Operador padrão inserido.");
        }

        System.out.println("====== CARGA INICIAL CONCLUÍDA COM SUCESSO ======");
    }
}