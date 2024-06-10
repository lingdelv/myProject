package com.example.rental.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenVo {
    private String token;
    private Long expireTime;
}
