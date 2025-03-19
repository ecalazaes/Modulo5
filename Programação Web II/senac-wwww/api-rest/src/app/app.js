import express from 'express'
import conexao from "../../infra/conexao.js";

const app = express()

// Indicar para o express usar o boby com json
app.use(express.json());

// function buscarAlunoPorId(id) {
//     return list.filter((item) => item.id == id)
// }

// Criando uma rota padrão ou raiz.
app.get('/', (req, res) => {
    res.status(200).send('Erick Calazães!')
})

// Buscar todos
app.get('/listas', (req, res) => {

    const sql = 'SELECT * FROM dbsenac.alunos';

    conexao.query(sql, (error, result) => {
        if (error) {
            res.status(404).json({ error: 'Erro ao buscar alunos' });
        } else {
            res.status(200).send(result);
        }
    })
})

// Buscar por Id
app.get('/listas/:id', (req, res) => {

    const id = req.params.id;
    const sql = 'SELECT * FROM dbsenac.alunos WHERE aluno_id = ?;'

    conexao.query(sql, id, (error, result) => {
        if (error) {
            res.status(400).json({ error: 'Erro ao buscar alunos' });
        } else {
            if (result == "") {{
                res.status(404).send('Aluno não encontrado');
            }} else {
                res.status(200).send(result);
            }
        }
    })
})

// Inserir
app.post('/listas', (req, res) => {

    const aluno = req.body;
    const sql = 'INSERT INTO `dbsenac`.`alunos` SET ?;'

    conexao.query(sql, aluno, (error, result) => {
        if (error) {
            res.status(400).json({ error: 'Erro ao cadastrar o aluno' });
        } else {
            res.status(201).json({ message: 'Aluno cadastrado com sucesso!', Id: result.insertId });
        }
    })
})

// Deletar por id
app.delete('/listas/:id', (req, res) => {

    const id = req.params.id;
    const sql = 'DELETE FROM dbsenac.alunos WHERE aluno_id = ?;'

    conexao.query(sql, id, (error, result) => {
        if (error) {
            res.status(400).json({ error: 'Erro ao buscar alunos' });
        } else {
            if (result == "") {{
                res.status(404).send('Aluno não encontrado');
            }} else {
                res.status(200).json({ message: 'Aluno deletado com sucesso!'});
            }
        }
    })
})

// Expor o objeto para outros módulos
export default app
