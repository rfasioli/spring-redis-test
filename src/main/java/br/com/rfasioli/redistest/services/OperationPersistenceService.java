package br.com.rfasioli.redistest.services;

import br.com.rfasioli.redistest.domains.Operation;
import br.com.rfasioli.redistest.entities.OperationEntity;
import br.com.rfasioli.redistest.interfaces.OperationGetUseCase;
import br.com.rfasioli.redistest.interfaces.OperationSaveUseCase;
import br.com.rfasioli.redistest.mappers.OperationEntityMapper;
import br.com.rfasioli.redistest.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationPersistenceService implements OperationGetUseCase, OperationSaveUseCase {

    private final OperationEntityMapper mapper;
    private final OperationRepository repository;

    public OperationPersistenceService(final OperationEntityMapper mapper,
                                       final OperationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Operation getById(String id) {
        OperationEntity entity = repository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Operação {} não encontrada", id))
        );
        return mapper.toDomain(entity);
    }

    @Override
    public List<Operation> getAll() {
        List<OperationEntity> operations = repository.findAll();
        return operations.stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Operation save(Operation operation) {
        OperationEntity entity = repository.save(mapper.fromDomain(operation));
        return mapper.toDomain(entity);
    }
}
