package consumer

import (
	"context"
	"encoding/json"
	"log"
	"os"

	"com.danieldias/apache-pulsar/pkg/model"
	"github.com/apache/pulsar-client-go/pulsar"
)

func ConsumerMessages(client pulsar.Client) {
	consumer, err := client.Subscribe(pulsar.ConsumerOptions{
		Topic:            os.Getenv("PULSAR_TOPIC"),
		SubscriptionName: os.Getenv("PULSAR_SUBSCRIPTION"),
		Type:             pulsar.Shared,
	})

	if err != nil {
		log.Fatalf("Could not subscribe to topic: %v", err)
	}
	defer consumer.Close()

	log.Println("Started consuming messages...")

	for {
		msg, err := consumer.Receive(context.Background())
		if err != nil {
			log.Printf("Could not receive message: %v", err)
			//time.Sleep(1 * time.Second) // Evita loop rápido em caso de erro
			continue
		}

		log.Printf("Received message: %s", string(msg.Payload()))

		var character model.Character
		if err := json.Unmarshal(msg.Payload(), &character); err != nil {
			log.Printf("Could not unmarshal message payload: %v", err)
			consumer.Nack(msg)
			continue
		}

		log.Printf("Character Name: %s, Eikon: %s, Dominant: %t", character.Name, character.Eikon, character.Dominant)
		consumer.Ack(msg)
	}
}
