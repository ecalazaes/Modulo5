import conexao from "../database/conexao.js";
import res from "express/lib/response.js";

class AlunoRepository {

    // Criar um novo elemento
    create(){}

    // Buscar tudo
    findAll(){
        const sql = 'SELECT * FROM dbsenac.alunos';

        conexao.query(sql, (error, result) => {
            if (error) {
                return error;
                // return res.status(404).json({
                //     error: 'Erro ao buscar alunos'
                // });
            }
            // res.status(200).send(result);
            console.log(result);
            return result;
        });
    }

    // Buscar por id
    findById(req,res){}

    // Atualizar por id
    update(req,res){}

    // Deletar por id
    delete(req,res){}

}

export default new AlunoRepository();