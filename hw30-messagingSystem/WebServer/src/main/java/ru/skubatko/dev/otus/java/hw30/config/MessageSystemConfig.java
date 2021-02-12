package ru.skubatko.dev.otus.java.hw30.config;

import ru.skubatko.dev.otus.java.hw30.messaging.db.GetUserListDataRequestHandler;
import ru.skubatko.dev.otus.java.hw30.messaging.db.SaveUserDataRequestHandler;
import ru.skubatko.dev.otus.java.hw30.messaging.front.GetUserListDataResponseHandler;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private final AppProperties appProperties;

    @Bean
    public MessageSystem messageSystem() {return new MessageSystemImpl();}

    @Bean
    public CallbackRegistry callbackRegistry() { return new CallbackRegistryImpl();}

    @Bean
    public MsClient frontendMsClient(
            SaveUserDataRequestHandler saveUserDataRequestHandler,
            GetUserListDataRequestHandler getUserListDataRequestHandler,
            GetUserListDataResponseHandler getUserListDataResponseHandler,
            MessageSystem messageSystem,
            CallbackRegistry callbackRegistry) {

        val requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, saveUserDataRequestHandler);
        requestHandlerDatabaseStore.addHandler(MessageType.USER_LIST_DATA, getUserListDataRequestHandler);
        val databaseMsClient = new MsClientImpl(appProperties.getDatabaseServiceClientName(),
                messageSystem, requestHandlerDatabaseStore, callbackRegistry);
        messageSystem.addClient(databaseMsClient);

        val requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_LIST_DATA, getUserListDataResponseHandler);

        val frontendMsClient = new MsClientImpl(appProperties.getFrontendServiceClientName(),
                messageSystem, requestHandlerFrontendStore, callbackRegistry);
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }
}
