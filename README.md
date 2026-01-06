# RabbitMQ Chat Application 💬

Aplicação Spring Boot simples com RabbitMQ para demonstrar sistema de mensageria com Producer e Consumer.

## 🚀 Como executar

### Opção 1: Usar imagem pronta do Docker Hub (Mais rápido) ⚡

Crie um arquivo `docker-compose.yml`:

```yaml
version: '3.8'

services:
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
    healthcheck:
      test: rabbitmq-diagnostics -q ping
      interval: 10s
      timeout: 5s
      retries: 5
    networks:
      - chat-network

  backend:
    image: raynersan/chat-backend:latest
    container_name: chat-backend
    ports:
      - "8080:8080"
    environment:
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_PORT: 5672
      SPRING_RABBITMQ_USERNAME: guest
      SPRING_RABBITMQ_PASSWORD: guest
    depends_on:
      rabbitmq:
        condition: service_healthy
    networks:
      - chat-network
    restart: on-failure

networks:
  chat-network:
    driver: bridge
```

Depois execute:

```bash
docker-compose up -d
```

### Opção 2: Build local (Para desenvolvimento)

Clone o repositório e execute:

```bash
docker-compose up -d --build
```

## 📝 Testando

```bash
# Enviar mensagem rápida
curl -X POST "http://localhost:8080/api/chat/send-fast?username=João&message=Olá"

# Ou via JSON
curl -X POST http://localhost:8080/api/chat/send \
  -H "Content-Type: application/json" \
  -d '{"username":"Maria","message":"Olá, mundo!"}'
```

## 👀 Ver as mensagens sendo processadas

```bash
docker-compose logs -f backend
```

Você verá algo como:
```
📩 Nova mensagem recebida:
   Usuário: João
   Mensagem: Olá
   Horário: 2026-01-06T18:30:45
```

## 🔗 Acessos

- **API**: http://localhost:8080
- **RabbitMQ Management**: http://localhost:15672 (guest/guest)

## ⏹️ Parar a aplicação

```bash
docker-compose down
```

## 📝 Endpoints disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/chat/send` | Enviar mensagem (JSON) |
| POST | `/api/chat/send-fast` | Enviar mensagem (query params) |
| GET | `/api/chat/test` | Teste rápido |

## 💡 Como funciona

1. Você envia uma mensagem via API REST
2. O Producer envia para a fila do RabbitMQ
3. O Consumer processa automaticamente e exibe nos logs

**Por isso você não vê mensagens no RabbitMQ** - elas são consumidas instantaneamente! ✅

## 🐛 Problemas?

```bash
# Ver logs de erro
docker-compose logs backend

# Reiniciar tudo
docker-compose down
docker-compose up -d --build
```
