# Cliente e Servidor UDP em Java

Este repositório contém um exemplo de um cliente e servidor UDP implementados em Java. O cliente envia mensagens para o servidor, que as recebe e envia uma confirmação de recebimento de volta ao cliente.

## Cliente UDP

### Descrição

O cliente UDP é implementado no arquivo `UDPClient.java`. Ele aceita dois argumentos da linha de comando: `<server_ip>` e `<server_port>`. O cliente lê mensagens do usuário a partir da entrada padrão e as envia para o servidor. As respostas do servidor são impressas no console.

### Funcionalidades

- Inicia uma comunicação com um servidor especificado.
- Envia mensagens para o servidor.
- Recebe e imprime respostas do servidor.
- Encerra a execução quando o usuário digita "exit".

## Servidor UDP

### Descrição

O servidor UDP é implementado no arquivo `UDPServer.java`. Ele aceita um argumento da linha de comando: `<server_port>`. O servidor cria um socket UDP na porta especificada e aguarda continuamente mensagens dos clientes. Quando uma mensagem é recebida, o servidor imprime informações sobre a mensagem e envia uma confirmação de recebimento de volta ao cliente.

### Funcionalidades

- Inicia um servidor UDP na porta especificada.
- Aguarda mensagens dos clientes.
- Extrai informações sobre os clientes (endereço IP e porta).
- Envia confirmações de recebimento para os clientes.
- Continua aguardando mensagens indefinidamente até ser interrompido.

## Como Executar

Para executar o cliente e o servidor, siga as instruções a seguir:

1. Compile os arquivos Java:
   ```bash
   javac UDPClient.java
   javac UDPServer.java
   ```

2. Inicie o servidor:
   ```bash
   java UDPServer <server_port>
   ```

3. Inicie o cliente:
   ```bash
   java UDPClient <server_ip> <server_port>
   ```

4. O cliente poderá enviar mensagens e receber respostas do servidor.

## Autor

Este código foi criado por [Ana Athayde] como parte de um projeto de comunicação UDP em Java.
