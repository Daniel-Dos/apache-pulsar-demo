package main

import (
	"com.danieldias/apache-pulsar/internal/config"
	"com.danieldias/apache-pulsar/pkg/consumer"
	"com.danieldias/apache-pulsar/pkg/pulsar"
)

func main() {
	config.LoadEnv()
	client := pulsar.NewClientPulsar()
	defer client.Close()
	consumer.ConsumerMessages(client)
}
