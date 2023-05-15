package com.example.messageproducer.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author jeremy on 2023/5/10
 */
@Getter
@Setter
@ToString
@Builder
public class EmailMessage implements Serializable {
    private String to;
    private String subject;
    private String message;
}
