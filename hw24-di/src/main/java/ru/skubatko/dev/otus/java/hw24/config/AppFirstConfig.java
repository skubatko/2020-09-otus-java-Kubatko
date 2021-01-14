package ru.skubatko.dev.otus.java.hw24.config;

import ru.skubatko.dev.otus.java.hw24.appcontainer.api.AppComponent;
import ru.skubatko.dev.otus.java.hw24.appcontainer.api.AppComponentsContainerConfig;
import ru.skubatko.dev.otus.java.hw24.services.EquationPreparer;
import ru.skubatko.dev.otus.java.hw24.services.GameProcessor;
import ru.skubatko.dev.otus.java.hw24.services.GameProcessorImpl;
import ru.skubatko.dev.otus.java.hw24.services.IOService;
import ru.skubatko.dev.otus.java.hw24.services.IOServiceConsole;
import ru.skubatko.dev.otus.java.hw24.services.PlayerService;

@AppComponentsContainerConfig(order = 1)
public class AppFirstConfig {

    @AppComponent(order = 2, name = "gameProcessor")
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }

    @AppComponent(order = 0, name = "ioService")
    public IOService ioService() {
        return new IOServiceConsole(System.out, System.in);
    }
}
