// Código necessário para a conexão com o Banco de Dados
import mysql from 'mysql'

const conexao = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    user: 'root',
    password: '',
    database: 'dbsenac'

})

// Estabelecer a conexão com o banco
conexao.connect((error) => {
    if (error) {
        return console.log(error)
    }
    console.log("Conexão realizada com sucesso")
})

export default conexao