package br.com.rfasioli.redistest.controlers;

import br.com.rfasioli.redistest.domains.Operation;
import br.com.rfasioli.redistest.dtos.OperationDTO;
import br.com.rfasioli.redistest.interfaces.OperationPublishUseCase;
import br.com.rfasioli.redistest.mappers.OperationDTOMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    private final OperationPublishUseCase publisher;
    private final OperationDTOMapper mapper;

    public RedisTestController(final OperationPublishUseCase publisher,
                               final OperationDTOMapper mapper) {
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @PostMapping
    public void post(@RequestBody OperationDTO operationDTO) {
        Operation operation = mapper.toDomain(operationDTO);
        publisher.publish(operation);
    }

}
