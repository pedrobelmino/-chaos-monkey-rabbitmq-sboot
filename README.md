---

# Chaos Monkey RabbitMQ Spring Boot

Este projeto demonstra a integração do Chaos Monkey para Spring Boot com um sistema de mensageria RabbitMQ. O objetivo é testar a resiliência e robustez dos microserviços, causando falhas intencionais e observando como o sistema as manipula.

## Funcionalidades

- **Chaos Monkey para Spring Boot**: Simula falhas em aplicações Spring Boot.
- **Integração com RabbitMQ**: Testa o tratamento de filas de mensagens sob condições de falha.
- **Teste de Resiliência**: Avalia a robustez da arquitetura de microserviços.

## Requisitos

- Java 11 ou superior
- Spring Boot
- RabbitMQ
- Docker (opcional, para configuração containerizada)

## Configuração

1. **Clone o repositório**:
    ```sh
    git clone https://github.com/pedrobelmino/chaos-monkey-rabbitmq-sboot.git
    cd chaos-monkey-rabbitmq-sboot
    ```

2. **Execute o RabbitMQ usando Docker** (opcional):
    ```sh
    docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    ```

3. **Compile o projeto**:
    ```sh
    ./mvnw clean install
    ```

4. **Execute a aplicação**:
    ```sh
    ./mvnw spring-boot:run
    ```

## Configuração

- **Chaos Monkey**: Configure o Chaos Monkey em `application.properties`:
    ```properties
    chaos.monkey.enabled=true
    chaos.monkey.assaults.latencyActive=true
    chaos.monkey.assaults.latencyRangeStart=1000
    chaos.monkey.assaults.latencyRangeEnd=3000
    chaos.monkey.assaults.exceptionsActive=true
    chaos.monkey.assaults.exceptionProbability=0.5
    ```

- **RabbitMQ**: Configure a conexão com RabbitMQ em `application.properties`:
    ```properties
    spring.rabbitmq.host=localhost
    spring.rabbitmq.port=5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    ```

## Uso

1. **Envie uma mensagem de teste**:
    ```sh
    curl -X POST http://localhost:8080/send -d "message=HelloChaosMonkey"
    ```

2. **Monitore os logs da aplicação** para observar como o Chaos Monkey afeta o processamento das mensagens.

## Contribuição

Contribuições são bem-vindas! Por favor, envie um pull request ou abra uma issue para discutir suas ideias.

## Licença

Este projeto é licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Para quaisquer dúvidas ou sugestões, sinta-se à vontade para entrar em contato com o proprietário do repositório.

---

Este README fornece uma visão geral, instruções de configuração, detalhes de configuração e diretrizes de uso para o projeto.
