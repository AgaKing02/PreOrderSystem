package com.CarSaleWebsite.Kolesa.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Code {

    @Min(4)
    @Max(4)
    @NotNull
    private String code;

    public Code(@Min(4) @Max(4) @NotNull @JsonProperty("code") String code) {
        this.code = code;
    }

    public Code() {
    }

    public String getCode() {
        return code;
    }
}
