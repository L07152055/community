package com.majiang.community.dto;

import lombok.Data;

@Data
public class AccessTokeDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
