import conexao from "../database/conexao.js";
import AlunoRepository from "../repositories/AlunoRepository.js";


class AlunoController {

    // Listar tudo
    index(req, res) {
        const row = AlunoRepository.findAll();
        res.json(row)
    }

    // Listar por id
    show(req, res) {

        const id = req.params.id;
        const sql = 'SELECT * FROM dbsenac.alunos WHERE aluno_id = ?;'

        conexao.query(sql, id, (error, result) => {
            if (error) {
                return res.status(400).json({
                    error: 'Erro ao buscar alunos'
                });
            }

            if (result == "") {
                return res.status(404).json({
                    message: `Aluno com id ${id} não encontrado`
                });
            }
            res.status(200).send(result);
        });
    }

    // Criar dados
    store(req, res) {

        const aluno = req.body;
        const sql = 'INSERT INTO `dbsenac`.`alunos` SET ?;'

        conexao.query(sql, aluno, (error, result) => {
            if (error) {
                return res.status(400).json({
                    error: 'Erro ao cadastrar o aluno'
                });
            }

            if (result.affectedRows >= 1) {
                return res.status(201).json({
                    message: `Aluno cadastrado com sucesso!`
                });
            }
        })
    }

    // Atualizar dados
    update(req, res){

        const id = req.params.id;
        const aluno = req.body;

        const sql = 'UPDATE dbsenac.alunos SET ? WHERE aluno_id = ?;';

        conexao.query(sql, [aluno, id], (error, result) => {
            if (error) {
                return res.status(400).json({
                    error: 'Erro ao atualizar o aluno'
                });
            }

            if (result.affectedRows === 0) {
                return res.status(404).json({
                    message: `Aluno com id ${id} não encontrado`
                });
            }

            return res.status(200).json({
                message: `Aluno com id ${id} atualizado com sucesso!`,
                affectedRows: result.affectedRows
            });

        });

    }

    // Remover dados
    delete(req, res){

        const id = req.params.id;
        const sql = 'DELETE FROM dbsenac.alunos WHERE aluno_id = ?;'

        conexao.query(sql, id, (error, result) => {
            if (error) {
                return res.status(400).json({
                    error: 'Erro ao deletar aluno'
                });
            }

            if (result.affectedRows === 0) {
                return res.status(404).json({
                    message: `Aluno com id ${id} não encontrado`
                });
            }

            res.status(200).json({
                message: `Aluno com id ${id} deletado com sucesso!`,
                affectedRows: result.affectedRows
            })
        });
    }
}

// padrao singleton
export default new AlunoController();