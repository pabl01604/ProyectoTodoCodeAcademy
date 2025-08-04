package com.bazar.Bazar.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean("mergerMapper")
/*  Esta anotacion se utiliza para indicar inequivocamente cual es el nombre
*   que tendrá un Bean. De esta manera podemos tener más de un Bean del mismo
*   tipo en el ApplicationContext.
* */
    public ModelMapper mergerMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return  objectMapper;
    }
}
