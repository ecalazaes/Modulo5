import AlunoRepository from "../repositories/AlunoRepository.js";


class AlunoController {

    // Listar tudo
    async index(req, res) {
        //  findAll retorna uma promise e não temos como garantir o tempo de resposta
        const row = await AlunoRepository.findAll();
        res.status(200).json(row)
    }

    // Listar por id
    async show(req, res) {
        const id = req.params.id;
        const row = await AlunoRepository.findById(id);
        res.status(200).json(row)
    }

    // Criar dados
    async store(req, res) {
        const aluno = req.body;
        const row = await AlunoRepository.create(aluno);
        res.status(201).send(row);

    }

    // Atualizar dados
    async update(req, res) {
        const id = req.params.id;
        const aluno = req.body;
        const row = await AlunoRepository.update(aluno, id);
        res.status(200).send(row);
    }

    // Remover dados
    async delete(req, res) {
        const id = req.params.id;
        const row = await AlunoRepository.delete(id);
        res.status(200).send(row);

    }
}

// padrao singleton
export default new AlunoController();