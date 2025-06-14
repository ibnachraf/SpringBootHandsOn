package ibn.achraf.demo.providers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import ibn.achraf.demo.model.ProvidersEnum;
import ibn.achraf.demo.model.SwapResult;
import reactor.core.publisher.Mono;

public class SwapProvider implements ISwapProvider {

    private ProvidersEnum provider;
    private WebClient client;
    private String pairsUrl = "http://localhost:8080/v1/ledger/pairs";

    public SwapProvider(ProvidersEnum provider, WebClient client) {
        this.provider = provider;
        this.client = client;
    }

    @Override
    public SwapResult swap(int a, int b, String pair) throws SwapErrors {
        
        if (a < 2) {
            throw new SwapErrors.AmountTooLowError();
        }
        if (b > 5) {
            throw new SwapErrors.AmountTooHigh();
        }
        return new SwapResult(
                a + b,
                a,
                b,
                provider());
    }

    @Override
    public ProvidersEnum provider() {
        return this.provider;
    }

    @Override
    public Mono<List<Pair>> pairs() throws URISyntaxException {
        URI uri = new URI(pairsUrl);  
        Mono<List<Pair>> res = client.get()
                    .uri(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(Pair.class).collectList();
        return res;
    }

}


