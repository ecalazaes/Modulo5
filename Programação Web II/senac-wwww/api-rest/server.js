import express from 'express'
const app = express()
const port = 8080

// Escutando a porta 8080
app.listen(port, () => {
    console.log(`Servidor rodando no endereço http://localhost:${port}`)
})

export default app