import app from "./src/app.js";
import conexao from "./infra/conexao.js";

const port = 3000

// Estabelecer a conexão com o banco
conexao.connect((error) => {
    if (error) {
        console.log(error)
    } else {
        console.log("Conexão realizada com sucesso")

        // Subindo servidor na porta 3000
        app.listen(port, () => {
            console.log(`Servidor rodando no endereço http://localhost:${port}`)
        })
    }
})
