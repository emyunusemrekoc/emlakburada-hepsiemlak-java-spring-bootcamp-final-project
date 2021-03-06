package com.example.emlakburadapayment.utils.dtoConverter;

import java.util.List;

public interface DtoConverterService {
	
	<S,T> List<T> entityToDto(List<S> listTypeEntityClass, Class<T> targetDtoClass);
	
	<T> Object dtoToEntity(Object dtoClass,Class<T> targetEntityClass);

	<T> Object entityToDtoSingle(Object dtoClass,Class<T> targetEntityClass);

}
