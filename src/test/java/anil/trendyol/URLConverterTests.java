package anil.trendyol;


import anil.trendyol.Service.URLConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class URLConverterTests {

    @Test
    void isProductPageWithBoutiqueAndMerchant() {
        URLConverter c = new URLConverter();
        assertTrue(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertTrue(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("ty://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=&boutiqueId="));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId="));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId="));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-?boutiqueId=439892&merchantId=105064"));
        assertFalse(c.isProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-"));
    }

    @Test
    void isProductPageWithBoutique() {
        URLConverter c = new URLConverter();
        assertTrue(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId="));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-?boutiqueId=439892"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-?boutiqueId="));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/saat-p-1925865?boutiqueId=439892"));
        assertFalse(c.isProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-"));
        assertFalse(c.isProductPageWithBoutique("ty://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
    }

    @Test
    void isProductPageWithMerchant() {
        URLConverter c = new URLConverter();
        assertTrue(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId="));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-?merchantId=439892"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-?merchantId="));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-"));
        assertFalse(c.isProductPageWithMerchant("ty://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
    }

    @Test
    void isProductPage() {
        URLConverter c = new URLConverter();
        assertTrue(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-1925865?"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-?merchantId=439892"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-?boutique="));
        assertFalse(c.isProductPage("https://www.trendyol.com/saat-p-1925865"));
        assertFalse(c.isProductPage("https://www.trendyol.com/casio/saat-p-"));
        assertFalse(c.isProductPage("ty://www.trendyol.com/casio/saat-p-1925865"));
    }

    @Test
    void isSearchPage() {
        URLConverter c = new URLConverter();
        assertTrue(c.isSearchPage("https://www.trendyol.com/sr?q=elbise"));
        assertTrue(c.isSearchPage("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC"));
        assertFalse(c.isSearchPage("https://www.trendyol.com/sr?q="));
        assertFalse(c.isSearchPage("https://www.trendyol.com/sr?"));
        assertFalse(c.isSearchPage("https://www.trendyol.com/sr"));
        assertFalse(c.isSearchPage("ty://www.trendyol.com/sr?q=%C3%BCt%C3%BC"));
        assertFalse(c.isSearchPage("ty://www.trendyol.com/sr?q=elbise"));
    }

    @Test
    void convertProductPageWithBoutiqueAndMerchant() throws Exception {
        URLConverter c = new URLConverter();
        assertEquals(c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"), "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
        assertEquals(c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=105064&boutiqueId=439892"), "ty://?Page=Product&ContentId=1925865&MerchantId=105064&CampaignId=439892");


        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("ty://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=&boutiqueId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-1925865"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/saat-p-1925865?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-?boutiqueId=439892&merchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutiqueAndMerchant("https://www.trendyol.com/casio/saat-p-"));
    }

    @Test
    void convertProductPageWithBoutique() throws Exception {
        URLConverter c = new URLConverter();
        assertEquals(c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"), "ty://?Page=Product&ContentId=1925865&CampaignId=439892");

        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-?boutiqueId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-?boutiqueId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/saat-p-1925865?boutiqueId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("https://www.trendyol.com/casio/saat-p-"));
        assertThrows(Exception.class, () -> c.convertProductPageWithBoutique("ty://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
    }

    @Test
    void convertProductPageWithMerchant() throws Exception {
        URLConverter c = new URLConverter();
        assertEquals(c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"), "ty://?Page=Product&ContentId=1925865&MerchantId=439892");

        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-1925865?merchantId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-?merchantId="));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/saat-p-1925865?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("https://www.trendyol.com/casio/saat-p-"));
        assertThrows(Exception.class, () -> c.convertProductPageWithMerchant("ty://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
    }

    @Test
    void convertProductPage() throws Exception {
        URLConverter c = new URLConverter();
        assertEquals(c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865"), "ty://?Page=Product&ContentId=1925865");
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892&boutiqueId=105064"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-1925865?"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-?merchantId=439892"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-?boutique="));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/saat-p-1925865"));
        assertThrows(Exception.class, () -> c.convertProductPage("https://www.trendyol.com/casio/saat-p-"));
        assertThrows(Exception.class, () -> c.convertProductPage("ty://www.trendyol.com/casio/saat-p-1925865"));
    }

    @Test
    void convertSearchPage() throws Exception {
        URLConverter c = new URLConverter();
        assertEquals(c.convertSearchPage("https://www.trendyol.com/sr?q=huawei"), "ty://?Page=Search&Query=huawei");
        assertEquals(c.convertSearchPage("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC"), "ty://?Page=Search&Query=%C3%BCt%C3%BC");

        assertThrows(Exception.class, () -> c.convertSearchPage("https://www.trendyol.com/sr?q="));
        assertThrows(Exception.class, () -> c.convertSearchPage("https://www.trendyol.com/sr?"));
        assertThrows(Exception.class, () -> c.convertSearchPage("https://www.trendyol.com/sr"));
        assertThrows(Exception.class, () -> c.convertSearchPage("ty://www.trendyol.com/sr?q=%C3%BCt%C3%BC"));
        assertThrows(Exception.class, () -> c.convertSearchPage("ty://www.trendyol.com/sr?q=elbise"));
    }

    @Test
    void convert() throws Exception {
        assertEquals(URLConverter.convert("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"), "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
        assertEquals(URLConverter.convert("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892"), "ty://?Page=Product&ContentId=1925865&CampaignId=439892");
        assertEquals(URLConverter.convert("https://www.trendyol.com/casio/saat-p-1925865?merchantId=439892"), "ty://?Page=Product&ContentId=1925865&MerchantId=439892");
        assertEquals(URLConverter.convert("https://www.trendyol.com/casio/saat-p-1925865"), "ty://?Page=Product&ContentId=1925865");
        assertEquals(URLConverter.convert("https://www.trendyol.com/sr?q=huawei"), "ty://?Page=Search&Query=huawei");
        assertEquals(URLConverter.convert("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC"), "ty://?Page=Search&Query=%C3%BCt%C3%BC");
        assertEquals(URLConverter.convert("https://www.trendyol.com/Hesabim/Favoriler"), "ty://?Page=Home");
        assertEquals(URLConverter.convert("https://www.trendyol.com/Hesabim/#/Siparislerim"), "ty://?Page=Home");
    }
}
