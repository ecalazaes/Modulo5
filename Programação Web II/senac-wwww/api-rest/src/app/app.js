import express from 'express'
import AlunoController from "./controllers/AlunoController.js";
import router from "../router.js";

const app = express()

// Indicar para o express usar o boby com json
app.use(express.json());

// ROTAS //
app.use('/alunos', router)

export default app
