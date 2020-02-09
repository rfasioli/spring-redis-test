package br.com.rfasioli.redistest.mappers;

import br.com.rfasioli.redistest.domains.Operation;
import br.com.rfasioli.redistest.entities.OperationEntity;
import br.com.rfasioli.redistest.interfaces.DomainParser;
import org.springframework.stereotype.Component;

@Component
public class OperationEntityMapper implements DomainParser<Operation, OperationEntity> {
    @Override
    public Operation toDomain(OperationEntity entity) {
        return Operation.newBuilder()
                .withId(entity.getId())
                .withUuid(entity.getUuid())
                .withCreatedAt(entity.getCreatedAt())
                .withUpdatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public OperationEntity fromDomain(Operation domain) {
        OperationEntity entity = new OperationEntity();
        entity.setId(domain.getId());
        entity.setUuid(domain.getUuid());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}
