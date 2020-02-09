package br.com.rfasioli.redistest.interfaces;

public interface DomainParser<D, T> {
    D toDomain(T type);
    T fromDomain(D domain);
}
