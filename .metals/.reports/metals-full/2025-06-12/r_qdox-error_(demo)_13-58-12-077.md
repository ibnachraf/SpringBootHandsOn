error id: file://<WORKSPACE>/src/main/java/ibn/achraf/demo/streams/competerStream.java
file://<WORKSPACE>/src/main/java/ibn/achraf/demo/streams/competerStream.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[43,1]

error in qdox parser
file content:
```java
offset: 1380
uri: file://<WORKSPACE>/src/main/java/ibn/achraf/demo/streams/competerStream.java
text:
```scala
package ibn.achraf.demo.streams;

import java.util.Map;
import java.util.HashMap;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;


@Configuration
public class competerStream {
    	private static final Logger logger = LoggerFactory.getLogger(competerStream.class);

    @Autowired
    @Bean(name = "swapBuilder")
    public StreamsBuilderFactoryBean swapFactory(TopologyConfig baseConfig) {
        return  new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(
            Map.of( 
            )));
    }

    @Autowired
    public void buildSwapTopology(StreamsBuilder builder, TopologyConfig config) {
        builder.stream(
            config.inputTopic,
            Consumed.with(Serdes.String(), Serdes.String())
        ).map((k, v) -> {
            logger.info("Key={}, Value={}", k, v);
            return new KeyValue<>(k, v);
        });
    }c


}@@

```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:489)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:587)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:584)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:619)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:617)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1306)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:584)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:904)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:687)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:467)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1570)
```
#### Short summary: 

QDox parse error in file://<WORKSPACE>/src/main/java/ibn/achraf/demo/streams/competerStream.java