package com.torreverde.pagamento_service.repository;

import com.torreverde.pagamento_service.model.RegistroItemDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroItemDoacaoRepository extends JpaRepository<RegistroItemDoacao, Long> {
}
