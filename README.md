# Spring boot chat application


Para iniciar o zookeeper, vá para o diretório bin e digite o comando abaixo.
./zookeeper-server-start.sh ../config/zookeeper.properties

Em seguida, para iniciar o corretor Kafka, execute o comando abaixo no mesmo diretório
./kafka-server-start.sh ../config/server.properties	

Obs: Certifique-se de que o zookeeper esteja em execução antes de iniciar o Kafka porque o Kafka recebe informações como informações de deslocamento mantidas nas partições do Zookeeper.

Criar um tópico Kafka
kafka-topics --create --topic kafka-chat --zookeeper localhost:2181 --replic

Como o Apache Kafka não pode enviar as mensagens do consumidor instantaneamente para o cliente com operações GET e POST clássicas.
Realizei essas operações usando WebSockets que fornecem comunicação bidirecional full-duplex 
