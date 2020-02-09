package br.com.rfasioli.redistest.mappers;

import br.com.rfasioli.redistest.domains.Operation;
import br.com.rfasioli.redistest.dtos.OperationDTO;
import br.com.rfasioli.redistest.entities.OperationEntity;
import br.com.rfasioli.redistest.interfaces.DomainParser;
import org.springframework.stereotype.Component;

@Component
public class OperationDTOMapper implements DomainParser<Operation, OperationDTO> {
    @Override
    public Operation toDomain(OperationDTO dto) {
        return Operation.newBuilder()
                .withId(dto.getId())
                .withUuid(dto.getUuid())
                .build();
    }

    @Override
    public OperationDTO fromDomain(Operation domain) {
        OperationDTO entity = new OperationDTO();
        entity.setId(domain.getId());
        entity.setUuid(domain.getUuid());
        return entity;
    }
}
