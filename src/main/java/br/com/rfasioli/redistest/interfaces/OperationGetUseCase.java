package br.com.rfasioli.redistest.interfaces;

import br.com.rfasioli.redistest.domains.Operation;

import java.util.List;

public interface OperationGetUseCase {
    Operation getById(String id);
    List<Operation> getAll();
}
