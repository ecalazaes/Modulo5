import express from 'express'
import conexao from "../infra/conexao.js";

const app = express()

// Indicar para o express usar o boby com json
app.use(express.json());

function buscarAlunoPorId(id) {
    return list.filter((item) => item.id == id)
}

// Criando uma rota padrão ou raiz.
app.get('/', (req, res) => {
    res.status(200).send('Aula sasasasa!')
})

// Buscar todos
app.get('/listas', (req, res) => {

    const sql = 'SELECT * FROM alunos';

    conexao.query(sql, (error, resultado) => {
        if (error) {
            res.status(404).json({ error: 'Erro ao buscar alunos' });
        } else {
            res.status(200).send(resultado);
        }
    })
})

// Buscar por Id
app.get('/listas/:id', (req, res) => {
    res.json(buscarAlunoPorId(req.params.id));
})

// Inserir
app.post('/listas', (req, res) => {
    list.push(req.body)
    res.status(201).send("Aluno cadastrado com sucesso!")
})

// Expor o objeto para outros módulos
export default app
