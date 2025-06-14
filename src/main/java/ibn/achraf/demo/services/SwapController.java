package ibn.achraf.demo.services;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
	private KafkaTemplate<String, Object> template;
    private String outputTopic;

    @Autowired
    public SwapController(@Qualifier("fakeswap1") ISwapProvider fakeswap1Provider,
            @Qualifier("fakeswap2") ISwapProvider fakeswap2Provider, @Value("${inputTopic}") String inputTopic) {
        this.fakeswapP1 = fakeswap1Provider;
        this.fakeswapP2 = fakeswap2Provider;
        this.providers = Map.of(ProvidersEnum.Fakeswap1, this.fakeswapP1, ProvidersEnum.Fakeswap2, this.fakeswapP2);
        this.outputTopic = inputTopic;
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
        template.send(outputTopic,pair ,result);
        return result;
    }

    @GetMapping("/pairs")
    public Mono<List<Pair>> pairs() throws URISyntaxException {
        return fakeswapP1.pairs();
    }
}