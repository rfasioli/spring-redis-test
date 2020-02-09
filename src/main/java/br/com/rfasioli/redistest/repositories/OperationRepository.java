package br.com.rfasioli.redistest.repositories;

import br.com.rfasioli.redistest.entities.OperationEntity;
import br.com.rfasioli.redistest.interfaces.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CustomRepository<OperationEntity, String> {
}
