package com.loja.arenajogos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {

    @Bean
    public ModelMapper initObjectMapper(){
        return new ModelMapper();
    }
}
