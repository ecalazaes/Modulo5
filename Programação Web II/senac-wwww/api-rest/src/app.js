import app from "../server.js"

// Criando uma rota padrão ou raiz.
app.get('/', (req, res) => {
    res.send('Aula Erick')
})

