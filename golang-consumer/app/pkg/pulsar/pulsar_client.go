package pulsar

import (
	"log"
	"os"
	"time"

	"github.com/apache/pulsar-client-go/pulsar"
)

func NewClientPulsar() pulsar.Client {
	return clientPulsar()
}

func clientPulsar() pulsar.Client {
	pulsarClient, err := pulsar.NewClient(pulsar.ClientOptions{
		URL:               os.Getenv("PULSAR_URL"),
		OperationTimeout:  30 * time.Second,
		ConnectionTimeout: 30 * time.Second,
	})
	if err != nil {
		log.Fatalf("Could not create Pulsar client: %v", err)
	}
	return pulsarClient
}
