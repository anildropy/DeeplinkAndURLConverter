package anil.trendyol;


import anil.trendyol.Service.DeepLinkConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeepLinkConverterTests {

    @Test
    void isProductPageWithBoutiqueAndMerchant() {
        DeepLinkConverter c = new DeepLinkConverter();
        assertTrue(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertTrue(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=105064&CampaignId=439892"));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=&CampaignId=439892&MerchantId=105064"));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=&MerchantId="));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=105064"));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865"));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=439892"));
        assertFalse(c.isProductPageWithCampaignAndMerchant("ty://?Page=Product"));
    }

    @Test
    void isProductPageWithCampaign() {
        DeepLinkConverter c = new DeepLinkConverter();
        assertTrue(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId=439892"));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=&CampaignId=439892"));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId="));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&MerchantId=105064"));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&ContentId=1925865"));
        assertFalse(c.isProductPageWithCampaign("ty://?Page=Product&CampaignId=439892"));
    }

    @Test
    void isProductPageWithMerchant() {
        DeepLinkConverter c = new DeepLinkConverter();
        assertTrue(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=439892"));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=&MerchantId=439892"));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&MerchantId="));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=105064"));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&ContentId=1925865"));
        assertFalse(c.isProductPageWithMerchant("ty://?Page=Product&MerchantId=439892"));
    }

    @Test
    void isProductPage() {
        DeepLinkConverter c = new DeepLinkConverter();
        assertTrue(c.isProductPage("ty://?Page=Product&ContentId=1925865"));
        assertFalse(c.isProductPage("ty://?Page=Product&ContentId=1925865&MerchantId=439892"));
        assertFalse(c.isProductPage("ty://?Page=Product&ContentId=1925865&CampaignId=439892"));
        assertFalse(c.isProductPage("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertFalse(c.isProductPage("ty://?Page=Product&ContentId=1925865&MerchantId=105064&CampaignId=439892"));
        assertFalse(c.isProductPage("ty://?Page=Product&ContentId=1925865&"));
        assertFalse(c.isProductPage("ty://?Page=Product&CampaignId=439892&MerchantId=105064"));
        assertFalse(c.isProductPage("ty://?ContentId=1925865"));
    }

    @Test
    void isSearchPage() {
        DeepLinkConverter c = new DeepLinkConverter();
        assertTrue(c.isSearchPage("ty://?Page=Search&Query=huawei"));
        assertTrue(c.isSearchPage("ty://?Page=Search&Query=%C3%BCt%C3%BC"));
        assertFalse(c.isSearchPage("ty://?Page=Search&Query="));
        assertFalse(c.isSearchPage("ty://?Page=Search"));
    }

    @Test
    void convertProductPageWithBoutiqueAndMerchant() throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();
        assertEquals(c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"), "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
        assertEquals(c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=105064&CampaignId=439892"), "https://www.trendyol.com/brand/name-p-1925865?merchantId=105064&boutiqueId=439892");

        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=&CampaignId=439892&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=&MerchantId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaignAndMerchant("ty://?Page=Product"));
    }

    @Test
    void convertProductPageWithCampaign() throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();
        assertEquals(c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId=439892"), "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");

        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=&CampaignId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&CampaignId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=1925865&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&ContentId=1925865"));
        assertThrows(Exception.class, () -> c.convertProductPageWithCampaign("ty://?Page=Product&CampaignId=439892"));
    }

    @Test
    void convertProductPageWithMerchant() throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();
        assertEquals(c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=439892"), "https://www.trendyol.com/brand/name-p-1925865?merchantId=439892");

        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&MerchantId=439892&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=&MerchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&MerchantId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=1925865&CampaignId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&ContentId=1925865"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://?Page=Product&MerchantId=439892"));
    }

    @Test
    void convertProductPage() throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();
        assertEquals(c.convertProductPage("ty://?Page=Product&ContentId=1925865"), "https://www.trendyol.com/brand/name-p-1925865");

        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&ContentId=1925865&MerchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&ContentId=1925865&CampaignId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&ContentId=1925865&MerchantId=105064&CampaignId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&ContentId=1925865&"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?Page=Product&CampaignId=439892&MerchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://?ContentId=1925865"));
    }

    @Test
    void convertSearchPage() throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();
        assertEquals(c.convertSearchPage("ty://?Page=Search&Query=huawei"), "https://www.trendyol.com/sr?q=huawei");
        assertEquals(c.convertSearchPage("ty://?Page=Search&Query=%C3%BCt%C3%BC"), "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");

        assertThrows(Exception.class, () -> c.convertSearchPage("ty://?Page=Search&Query="));
        assertThrows(Exception.class, () -> c.convertSearchPage("ty://?Page=Search"));
    }

    @Test
    void convert() throws Exception {
        assertEquals(DeepLinkConverter.convert("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"), "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Product&ContentId=1925865&CampaignId=439892"), "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Product&ContentId=1925865&MerchantId=439892"), "https://www.trendyol.com/brand/name-p-1925865?merchantId=439892");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Product&ContentId=1925865"), "https://www.trendyol.com/brand/name-p-1925865");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Search&Query=huawei"), "https://www.trendyol.com/sr?q=huawei");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Search&Query=%C3%BCt%C3%BC"), "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Orders"), "https://www.trendyol.com/");
        assertEquals(DeepLinkConverter.convert("ty://?Page=Preferences&Id=9807654"), "https://www.trendyol.com/");
    }
}
