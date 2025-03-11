// Código necessário para a conexão com o Banco de Dados
import mysql from 'mysql'

const conexao = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    user: 'root',
    password: '',
    database: 'dbsenac'

})

export default conexao