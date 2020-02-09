package br.com.rfasioli.redistest.interfaces;

import br.com.rfasioli.redistest.domains.Operation;

import java.util.stream.Stream;

public interface OperationListenerUseCase {
    Operation next();
    Boolean hasMessage();
    Integer count();
}
