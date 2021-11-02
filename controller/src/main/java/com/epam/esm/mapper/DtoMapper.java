package com.epam.esm.mapper;

import java.util.List;

/**
 * The GiftCertificateDtoMapper class
 */
public interface DtoMapper<D, R, M> {
  /**
   * Map DTO to Model to create new model
   *
   * @param dto data transfer object
   * @return model
   */
  M mapDtoToCreateModel(D dto);

  /**
   * Map DTO to Model to update model
   *
   * @param dto data transfer object
   * @param id  model id
   * @return model
   */
  M mapDtoToUpdateModel(long id, D dto);

  /**
   * Map Mdel to DTO
   *
   * @param model model
   * @return response DTO
   */
  R mapModelToResponseDto(M model);

  /**
   * Map list of models to list of response DTOs
   *
   * @param models list of models
   * @return list of response DTOs
   */
  List<R> mapModelListToDtoList(List<M> models);
}
