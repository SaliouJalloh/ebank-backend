package org.msd.ebankingbackend.infrastructure.mapper;

public interface AbstractEntityMapper<M, E> {

    M toModel(E entity);

    E toEntity(M model);
}
