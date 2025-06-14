error id: file://<WORKSPACE>/src/main/java/ibn/achraf/demo/services/SwapController.java
file://<WORKSPACE>/src/main/java/ibn/achraf/demo/services/SwapController.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[31,5]

error in qdox parser
file content:
```java
offset: 991
uri: file://<WORKSPACE>/src/main/java/ibn/achraf/demo/services/SwapController.java
text:
```scala
package ibn.achraf.demo.services;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibn.achraf.demo.model.ProvidersEnum;
import ibn.achraf.demo.model.SwapResult;
import ibn.achraf.demo.providers.ISwapProvider;
import ibn.achraf.demo.providers.Pair;
import ibn.achraf.demo.providers.SwapErrors;
import reactor.core.publisher.Mono;

@RestController
public class SwapController {

    private ISwapProvider fakeswapP1;
    private ISwapProvider fakeswapP2;
    private Map<ProvidersEnum, ISwapProvider> providers;
    @Autowired
	private KafkaTemplate<Object, Object> template;
    pr

    @@@Autowired
    public SwapController(@Qualifier("fakeswap1") ISwapProvider fakeswap1Provider,
            @Qualifier("fakeswap2") ISwapProvider fakeswap2Provider) {
        this.fakeswapP1 = fakeswap1Provider;
        this.fakeswapP2 = fakeswap2Provider;
        this.providers = Map.of(ProvidersEnum.Fakeswap1, this.fakeswapP1, ProvidersEnum.Fakeswap2, this.fakeswapP2);
    }

    @GetMapping("/providers")
    public List<ProvidersEnum> providers() {
        return List.of(ProvidersEnum.values());
    }

    @GetMapping("/swap")
    public SwapResult swap(
            @RequestParam int a,
            @RequestParam int b,
            @RequestParam String pair,
            @RequestParam String provider) throws SwapErrors, URISyntaxException {
        ProvidersEnum p = ProvidersEnum.valueOf(provider);
        SwapResult result = providers.get(p).swap(a, b, pair);
        template.send(pair, pair)
        return result;
    }

    @GetMapping("/pairs")
    public Mono<List<Pair>> pairs() throws URISyntaxException {
        return fakeswapP1.pairs();
    }
}
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

QDox parse error in file://<WORKSPACE>/src/main/java/ibn/achraf/demo/services/SwapController.java