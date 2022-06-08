package com.sinonc.agriculture.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberBackgroundVo {

    @NotEmpty(message = "img is not null")
    private String img;
}
