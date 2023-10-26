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

## Arquivos Java

### `UDPClient.java`

- **`main(String[] args)`:** Este é o método principal do cliente UDP. Ele é responsável por iniciar a execução do cliente. Aceita dois argumentos da linha de comando:
  - `<server_ip>`: O endereço IP do servidor para o qual o cliente enviará mensagens.
  - `<server_port>`: A porta do servidor na qual o cliente se conectará.

- **Configuração Inicial:** Antes de prosseguir, o código verifica se os argumentos da linha de comando são fornecidos corretamente. Caso contrário, exibe uma mensagem de erro e encerra a execução.

- **Buffer de Entrada:** O código cria um `BufferedReader` para ler as mensagens do usuário a partir da entrada padrão (teclado).

- **Socket UDP:** Um socket UDP é criado usando `DatagramSocket`. Esse socket será usado para a comunicação UDP com o servidor.

- **Conversão de Argumentos:** O endereço IP do servidor fornecido como argumento é convertido para um objeto `InetAddress`. A porta do servidor é armazenada em uma variável.

- **Loop de Comunicação:** O código entra em um loop que permite que o cliente envie mensagens ao servidor e receba respostas. Ele realiza o seguinte dentro do loop:
  - Lê uma mensagem da entrada padrão (usuário).
  - Converte essa mensagem em bytes.
  - Cria um pacote `DatagramPacket` contendo os dados da mensagem e as informações do servidor (endereço IP e porta).
  - Envia o pacote para o servidor usando o socket.
  - Aguarda uma resposta do servidor.
  - Exibe a resposta do servidor.

- **Finalização:** O loop continua até que o usuário digite "exit," momento em que o cliente encerra a execução.

### `UDPServer.java`

- **`main(String[] args)`:** Este é o método principal do servidor UDP. Ele é responsável por iniciar a execução do servidor. Aceita um argumento da linha de comando:
  - `<server_port>`: A porta na qual o servidor irá escutar.

- **Configuração Inicial:** O código verifica se o argumento da linha de comando é fornecido corretamente. Caso contrário, exibe uma mensagem de erro e encerra a execução. O servidor imprime a porta em que está escutando.

- **Socket UDP:** Um socket UDP é criado usando `DatagramSocket`. Esse socket será usado para receber mensagens dos clientes.

- **Loop de Recebimento:** O código entra em um loop infinito para aguardar mensagens dos clientes. Ele realiza o seguinte dentro do loop:
  - Aguarda a chegada de um pacote `DatagramPacket` contendo a mensagem de um cliente.
  - Extrai informações sobre o cliente, como o endereço IP e a porta de origem.
  - Converte os dados do pacote em uma mensagem legível.
  - Exibe informações sobre a mensagem e o cliente.
  - Envia uma confirmação de recebimento de volta ao cliente.

- **Finalização:** O servidor continua aguardando mensagens indefinidamente até ser interrompido manualmente.

## Autor

Este código foi criado por Ana Athayde como parte de um projeto de comunicação UDP em Java.
