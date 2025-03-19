import app from "./src/app/app.js";
const port = 3000

// Subindo servidor na porta 3000
app.listen(port, () => {
    console.log(`Servidor rodando no endereço http://localhost:${port}`)
})
