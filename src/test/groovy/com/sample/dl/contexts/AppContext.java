package com.sample.dl.contexts;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan({"com.sample.dl.bdd","com.sample.dl.contexts.annotations"})
@Configuration
@ComponentScan(basePackages = {"com.sample.dl.bdd.cucumber.UI",
        "com.sample.dl.contexts.annotations",
        "com.sample.dl.bdd.utils"})
public class AppContext {
}
