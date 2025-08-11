FROM golang:1.24-alpine AS builder

WORKDIR /app

COPY go.mod go.sum .env ./
RUN go mod download

COPY . .
RUN CGO_ENABLED=0 GOOS=linux go build -o app ./

FROM alpine:latest

WORKDIR /root/

COPY --from=builder /app/app .
COPY --from=builder /app/.env .

RUN ls

CMD ["./app"]