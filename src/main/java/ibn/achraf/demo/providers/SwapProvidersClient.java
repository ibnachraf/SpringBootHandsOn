package ibn.achraf.demo.providers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class SwapProvidersClient {
  @Bean("WebClientFakeswap1")
  public WebClient initAsyncClientFakeswap1() {
    return WebClient.create();
  }

  @Bean("WebClientFakeswap2")
  public WebClient initAsyncClientFakeswap2() {
    return WebClient.create();
  }
}
