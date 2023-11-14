package com.bits.pms.validators;

import com.bits.pms.constants.DataType;
import com.bits.pms.exception.ValidationException;
import com.bits.pms.models.CreateProductDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProductValidator {
    public static void validate(CreateProductDto createProductDto) {
        List<Error> errors = new ArrayList<>();
        if (createProductDto.getCharacteristics() != null) {
            createProductDto.getCharacteristics().forEach(characteristic -> {
                if (characteristic.getType().equals(DataType.DROPDOWN) && characteristic.getAllowedValues().isEmpty()) {
                    errors.add(new Error("allowedValues is mandatory for characteristic " + characteristic.getName()));
                }
            });
            if (!errors.isEmpty()) {
                throw new ValidationException(errors.stream().map(Throwable::getMessage).collect(Collectors.joining("|")));
            }
        }
    }
}
