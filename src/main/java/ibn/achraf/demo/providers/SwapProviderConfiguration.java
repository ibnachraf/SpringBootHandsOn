package ibn.achraf.demo.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import ibn.achraf.demo.model.ProvidersEnum;

@Configuration
public class SwapProviderConfiguration {
    @Bean("fakeswap1")
    public ISwapProvider initFakeswap1(@Qualifier("WebClientFakeswap1") WebClient client) {
        return new SwapProvider(ProvidersEnum.Fakeswap1, client);
    }

    @Bean("fakeswap2")
    public ISwapProvider initFakeswap2(@Qualifier("WebClientFakeswap2") WebClient client) {
        return new SwapProvider(ProvidersEnum.Fakeswap2, client);
    }

}
