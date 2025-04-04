import { Router } from "express";
import AlunoController from "./app/controllers/AlunoController.js";

const router = Router();

// Buscar todos os alunos
router.get('/', AlunoController.index);

// Buscar aluno por ID
router.get('/:id', AlunoController.show);

// Inserir aluno
router.post('/', AlunoController.store);

// Deletar aluno por id
router.delete('/:id', AlunoController.delete);

// Atualizar aluno por id
router.put('/:id', AlunoController.update);

export default router;