package com.sinonc.job.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RefreshScope
public class OriginsDataConfig {

    @Value("${origins.cropSign}")
    private String cropSign;

    @Value("${origins.originsSign}")
    private String originsSign;
}
