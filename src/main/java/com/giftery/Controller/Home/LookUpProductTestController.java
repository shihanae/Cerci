package com.giftery.Controller.Home;

import com.giftery.Model.AmazonProduct;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;

@Controller
@RequestMapping("/product-lookup")
public class LookUpProductTestController
{
    @Autowired
    private WebClient webClient;

    @Value("${x.rapid.api.key}")
    private String apiKey;

    @GetMapping({"", "/"})
    public String productLookUp()
    {
        return "product-lookup";
    }

    @GetMapping("/search")
    public String searchAsin(@RequestParam(name = "asin", required = true) String asin,
                             Model model)
    {
        // Host URL
        //String host = "https://amazon24.p.rapidapi.com/";
        //String host = "https://amazon24.p.rapidapi.com/api/product/";
        String host = "https://amazon24.p.rapidapi.com/api";

        // Headers for a request
        String x_rapidapi_host = "amazon24.p.rapidapi.com";
        String charset = "UTF-8";

        // Params
        String country = "US";

        // Format query for preventing encoding problems
        String query =  null;
        try
        {
            query = String.format("country=%s", URLEncoder.encode(country, charset));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            query = "country=" + country;
        }
        
     /*  try
        {
            HttpResponse<JsonNode> response = Unirest.get(host + asin + "?" + query)
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", x_rapidapi_host)
                .asJson();

            System.out.println("\n\n\nStatus: " + response.getStatus());
            System.out.println("Content-Type: " + response.getHeaders().get("Content-Type"));
            model.addAttribute("status", response.getStatus());
            model.addAttribute("contentType", response.getHeaders().get("Content-Type"));

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }*/


      /*  System.out.println("Started");
        // Building webClient - specify baseURl and defaultHeaders if any.
        WebClient webClient = WebClient.builder().baseUrl(host).defaultHeader(HttpHeaders.USER_AGENT, "WebClient")
               .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();*/


        AmazonProduct amazonProduct = webClient.get()
                .uri(host+"/product/"+asin+"?"+query)
                .accept(MediaType.APPLICATION_JSON)
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", x_rapidapi_host)
                .retrieve()
                .bodyToMono(AmazonProduct.class).block();


       /* Mono< AmazonProduct > amazonProductMono = webClient.get().uri(host + "/" + asin).retrieve().bodyToMono(AmazonProduct.class);
        amazonProductMono.subscribe(amazonProduct -> System.out.println("Mono without block:" + amazonProduct));*/

        model.addAttribute("amazonProduct", amazonProduct);




        return "product-details";
    }
}
