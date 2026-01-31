package dev.rohitrai.Finance.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class PingOutput {

    @NonNull
    private String status;

}
