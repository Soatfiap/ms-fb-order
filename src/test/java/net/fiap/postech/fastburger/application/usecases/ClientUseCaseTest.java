package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientUseCaseTest {
    private SaveClientOutPutPort saveClientOutPutPort;
    private FindClientByCpfOutPutPort findClientByCpfOutPutPort;
    private ClientUseCase clientUseCase;

    @BeforeEach
    void setUp() {
        saveClientOutPutPort = mock(SaveClientOutPutPort.class);
        findClientByCpfOutPutPort = mock(FindClientByCpfOutPutPort.class);
        clientUseCase = new ClientUseCase(saveClientOutPutPort, findClientByCpfOutPutPort);
    }

    @Test
    void find() {
        String cpf = "12345678901";
        Client client = new Client();
        when(findClientByCpfOutPutPort.find(cpf)).thenReturn(client);

        Client result = clientUseCase.find(cpf);

        assertEquals(client, result);
        verify(findClientByCpfOutPutPort, times(1)).find(cpf);
    }

    @Test
    void save() {
        Client client = new Client();
        when(saveClientOutPutPort.save(client)).thenReturn(client);

        Client result = clientUseCase.save(client);

        assertEquals(client, result);
        verify(saveClientOutPutPort, times(1)).save(client);
    }
}