package org.msd.ebankingbackend.infrastructure.persistence.mapper;

public interface AbstractEntityMapper<M, E> {

    M toModel(E entity);

    E toEntity(M model);
}
