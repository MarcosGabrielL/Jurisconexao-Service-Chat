package br.com.jurisconexao.chat.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import br.com.jurisconexao.chat.models.Message;

/**
 * Essa classe cria um ProducerFactoryque sabe como criar produtores com base nas configurações que fornecemos.
 * @author Marcos
 *
 */

@EnableKafka
@Configuration
public class ProducerConfiguration {
	
	
    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    /**
     * BOOTSTRAP_SERVERS_CONFIGpara definir o endereço do servidor no qual o Kafka está sendo executado.
KEY_SERIALIZER_CLASS_CONFIGe VALUE_SERIALIZER_CLASS_CONFIGdesserializar a chave e o valor da fila Kafka.
     * @return
     */
    @Bean
    public Map<String, Object> producerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configurations;
    }

    /**
     * Também declaramos um KafkaTemplatebean para executar operações de alto nível em seu produtor. Em outras palavras, 
     * o modelo pode executar operações como enviar uma mensagem para um tópico e ocultar detalhes ocultos de você com eficiência.
     * @return
     */
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}