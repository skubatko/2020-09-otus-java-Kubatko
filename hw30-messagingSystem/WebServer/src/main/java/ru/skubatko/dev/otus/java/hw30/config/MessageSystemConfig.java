package ru.skubatko.dev.otus.java.hw30.config;

import ru.skubatko.dev.otus.java.hw30.messaging.GetUserDataRequestHandler;
import ru.skubatko.dev.otus.java.hw30.messaging.GetUserDataResponseHandler;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.CallbackRegistryImpl;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;

@Configuration
@RequiredArgsConstructor
public class MessageSystemConfig {

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public MessageSystem messageSystem() {return new MessageSystemImpl();}

    @Bean
    public CallbackRegistry callbackRegistry() { return new CallbackRegistryImpl();}

    @Bean
    public HandlersStore requestHandlerDatabaseStore(GetUserDataRequestHandler getUserDataRequestHandler) {
        val handlersStore = new HandlersStoreImpl();
        handlersStore.addHandler(MessageType.USER_DATA, getUserDataRequestHandler);
        return handlersStore;
    }

    @Bean
    public MsClient databaseMsClient(
            MessageSystem messageSystem,
            HandlersStore requestHandlerDatabaseStore,
            CallbackRegistry callbackRegistry) {
        val databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public HandlersStore requestHandlerFrontendStore(GetUserDataResponseHandler getUserDataResponseHandler) {
        val requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, getUserDataResponseHandler);
        return requestHandlerFrontendStore;
    }

    @Bean
    public MsClient frontendMsClient(
            MessageSystem messageSystem,
            HandlersStore requestHandlerFrontendStore,
            CallbackRegistry callbackRegistry) {
        return new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
    }
}
