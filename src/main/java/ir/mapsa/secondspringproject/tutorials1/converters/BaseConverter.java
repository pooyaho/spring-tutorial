package ir.mapsa.secondspringproject.tutorials1.converters;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<D, E> {
    E convertDto(D d);

    D convertEntity(E e);

    default List<E> convertDto(List<D> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(i -> convertDto(i))
                    .collect(Collectors.toList());
        }
        return null;
    }

    default List<D> convertEntity(List<E> dList) {
        if (dList != null) {
            return dList.stream()
                    .map(i -> convertEntity(i))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
