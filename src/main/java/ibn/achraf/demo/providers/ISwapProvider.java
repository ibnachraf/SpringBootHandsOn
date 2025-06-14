package ibn.achraf.demo.providers;

import java.net.URISyntaxException;
import java.util.List;

import ibn.achraf.demo.model.ProvidersEnum;
import ibn.achraf.demo.model.SwapResult;
import reactor.core.publisher.Mono;
import ibn.achraf.demo.providers.Pair;

public interface ISwapProvider {
    public ProvidersEnum provider();
    public SwapResult swap(int a, int b, String pair) throws SwapErrors ;
    public Mono<List<Pair>> pairs() throws URISyntaxException;
}

