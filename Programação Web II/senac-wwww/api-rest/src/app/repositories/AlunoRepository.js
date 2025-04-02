import {consulta} from "../database/conexao.js";

class AlunoRepository {

    //  Criar um aluno
    create(aluno) {
        const sql = 'INSERT INTO `dbsenac`.`alunos` SET ?;'
        return consulta(sql, aluno, "Não foi possível cadastrar o aluno")
    }


    // Buscar todos alunos
    findAll() {
        const sql = 'SELECT * FROM dbsenac.alunos';
        return consulta(sql, "", "Não foi possível localizar o aluno")
    }


    //  Buscar por id
    findById(id) {
        const sql = 'SELECT * FROM dbsenac.alunos WHERE aluno_id = ?;';
        return consulta(sql, id, "Erro ao buscar o aluno");
    }


// Atualizar por id
    update(aluno, id) {
        const sql = 'UPDATE dbsenac.alunos SET ? WHERE aluno_id = ?;';
        return consulta(sql, [aluno, id], "Erro ao atualizar o aluno");
    }

// Deletar por id
    delete(id) {
        const sql = 'DELETE FROM dbsenac.alunos WHERE aluno_id = ?;'
        return consuta(sql, id, "Erro ao deletar o aluno");
    }
}


export default new AlunoRepository();