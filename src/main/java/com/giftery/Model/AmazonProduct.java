package com.giftery.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AmazonProduct
{
    @Getter
    @Setter
    @JsonProperty("app_sale_price")
    private double appSalePrice;

    @Getter
    @Setter
    @JsonProperty("app_sale_price_currency")
    private String appSalePriceCurrency;

    @Getter
    @Setter
    @JsonProperty("discount")
    private String discount;

    @Getter
    @Setter
    @JsonProperty("original_price")
    private String originalPrice;

    @Getter
    @Setter
    @JsonProperty("original_price_currency")
    private String originalPriceCurrency;

    @Getter
    @Setter
    @JsonProperty("product_detail_url")
    private String productDetailURL;

    @Getter
    @Setter
    @JsonProperty("product_id")
    private String productId;

    @Getter
    @Setter
    @JsonProperty("product_information_html")
    private List<String> productInformationHtml = new ArrayList<String>();

    @Getter
    @Setter
    @JsonProperty("product_main_image_url")
    private String productMainImageUrl;

    @Getter
    @Setter
    @JsonProperty("product_small_image_urls")
    private List<String> productSmallImageUrls = new ArrayList<String>();

    @Getter
    @Setter
    @JsonProperty("product_technical_spec")
    private Object productTechnicalSpec;

    @Getter
    @Setter
    @JsonProperty("product_title")
    private String productTitle;

    @Getter
    @Setter
    @JsonProperty("product_video_url")
    private String productVideoUrl;

    @Getter
    @Setter
    @JsonProperty("reviews_number")
    private String reviewsNumber;

    @Getter
    @Setter
    @JsonProperty("seller_information")
    private String sellerInformation;

    @Getter
    @Setter
    @JsonProperty("shop_url")
    private String shopUrl;
}
