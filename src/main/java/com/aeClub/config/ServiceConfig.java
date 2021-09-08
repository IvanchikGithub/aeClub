package com.aeClub.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.aeClub.validator", "com.aeClub.service", "com.aeClub.controller", "com.aeClub.listener"})
public class ServiceConfig {

}
