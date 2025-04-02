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
    if (error) return console.log(error)
    console.log("Conexão realizada com sucesso")
})

/**
 *  Executa o codigo sql com ou sem valores
 * @param {string} sql instrução sql a ser executada
 * @param {string | Array} [params] params a serem passadas para o sql
 * @param {string} messageReject mensagem do Promise reject a ser exibida
 * @returns objeto da Promise
 */

export const consulta = (sql, params='', messageReject) => {
    return new Promise((resolve, reject) => {
        conexao.query(sql, params, (error, result) => {
            if (error) {
                return reject(messageReject);
            }

            const row = JSON.parse(JSON.stringify(result));
            resolve(row);
        })
    })
}

export default conexao