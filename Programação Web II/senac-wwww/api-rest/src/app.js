import express from 'express'
const app = express()

// Indicar para o express usar o boby com json
app.use(express.json());

// mock
const list = [
    {id: 1, Nome: "Erick", Grupo: "A"},
    {id: 2, Nome: "Daniele", Grupo: "A"},
    {id: 3, Nome: "Ronaldo", Grupo: "A"},
    {id: 4, Nome: "Calazães", Grupo: "A"}
]

function buscarAlunoPorId(id) {
    return list.filter((item) => item.id == id)
}

// Criando uma rota padrão ou raiz.
app.get('/', (req, res) => {
    res.status(200).send('Aula sasasasa!')
})

// Buscar todos
app.get('/listas', (req, res) => {
    res.status(200).send(list)
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
