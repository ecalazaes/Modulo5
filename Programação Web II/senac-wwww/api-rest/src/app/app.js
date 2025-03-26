import express from 'express'
import conexao from "./database/conexao.js";
import AlunoController from "./controllers/AlunoController.js";

const app = express()

// Indicar para o express usar o boby com json
app.use(express.json());

// ROTAS //

// Listas todos alunos
app.get('/listas', AlunoController.index);

// Buscar aluno por Id
app.get('/listas/:id', AlunoController.show);

// Inserir aluno
app.post('/listas', AlunoController.store);

// Deletar por id
app.delete('/listas/:id', AlunoController.delete);

// Update aluno por id
app.put('/listas/:id', AlunoController.update);

export default app
