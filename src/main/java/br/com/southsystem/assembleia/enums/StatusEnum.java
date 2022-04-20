package br.com.southsystem.assembleia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
    ABLE_TO_VOTE("ABLE_TO_VOTE");

    private String status;
}
