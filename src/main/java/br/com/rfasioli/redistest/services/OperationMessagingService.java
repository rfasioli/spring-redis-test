package br.com.rfasioli.redistest.services;

import br.com.rfasioli.redistest.domains.Operation;
import br.com.rfasioli.redistest.dtos.OperationDTO;
import br.com.rfasioli.redistest.interfaces.OperationListenerUseCase;
import br.com.rfasioli.redistest.interfaces.OperationPublishUseCase;
import br.com.rfasioli.redistest.mappers.OperationDTOMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import messagers.MessagePublisher;
import messagers.RedisMessageSubscriber;
import org.springframework.stereotype.Service;

@Service
public class OperationMessagingService implements OperationListenerUseCase, OperationPublishUseCase {

    private final MessagePublisher publisher;
    private final OperationDTOMapper mapper;
    private final ObjectMapper objectMapper;

    public OperationMessagingService(MessagePublisher publisher,
                                     OperationDTOMapper mapper,
                                     ObjectMapper objectMapper) {
        this.publisher = publisher;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public Operation next() {
        String message = RedisMessageSubscriber.messageList.get(0);
        try {
            OperationDTO dto = objectMapper.readValue(message, OperationDTO.class);
            return mapper.toDomain(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro convertendo operação");
        }
    }

    @Override
    public Boolean hasMessage() {
        return !RedisMessageSubscriber.messageList.isEmpty();
    }

    @Override
    public Integer count() {
        return RedisMessageSubscriber.messageList.size();
    }

    @Override
    public void publish(Operation operation) {
        OperationDTO dto = mapper.fromDomain(operation);
        try {
            String message = objectMapper.writeValueAsString(dto);
            publisher.publish(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro convertendo operação");
        }
    }

}
