package anil.trendyol.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class operates the url converting processes
 */
public class URLConverter {

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * \w+:                         Brand or category name may must contain at least one word character.
     * /:                           Passes brand or category name to product information section.
     * \w+:                         Product name may must contain at least one word character.
     * -p-:                         Passes product tag.
     * ([0-9]+):                    Product id must be numerical and has at least one number
     *                              the id will necessary in order to mount ContentId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * \?:                          Passes to query parameters.
     * boutiqueId=:                 Passes to boutique id.
     * ([0-9]+):                    Boutique id must be numerical and has at least one number
     *                              the id will necessary in order to mount CampaignId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &merchantId=:                Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount MerchantId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String formerPatternProductPageWithBoutiqueAndMerchant = "^https://www\\.trendyol\\.com/\\w+/\\w+-p-([0-9]+)\\?boutiqueId=([0-9]+)&merchantId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * \w+:                         Brand or category name may must contain at least one word character.
     * /:                           Passes brand or category name to product information section.
     * \w+:                         Product name may must contain at least one word character.
     * -p-:                         Passes product tag.
     * ([0-9]+):                    Product id must be numerical and has at least one number
     *                              the id will necessary in order to mount ContentId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * \?:                          Passes to query parameters.
     * merchantId=:                 Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount MerchantId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &boutiqueId=:                Passes to boutique id.
     * ([0-9]+):                    Boutique id must be numerical and has at least one number
     *                              the id will necessary in order to mount CampaignId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String latterPatternProductPageWithBoutiqueAndMerchant = "^https://www\\.trendyol\\.com/\\w+/\\w+-p-([0-9]+)\\?merchantId=([0-9]+)&boutiqueId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * \w+:                         Brand or category name may must contain at least one word character.
     * /:                           Passes brand or category name to product information section.
     * \w+:                         Product name may must contain at least one word character.
     * -p-:                         Passes product tag.
     * ([0-9]+):                    Product id must be numerical and has at least one number
     * the id will necessary in order to mount ContentId of deep-links
     * so if we want to take any part of match we have to enclose it with parentheses.
     * \?:                          Passes to query parameters.
     * boutiqueId=:                 Passes to boutique id.
     * ([0-9]+):                    Boutique id must be numerical and has at least one number
     * the id will necessary in order to mount CampaignId of deep-links
     * so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPageWithBoutique = "^https://www\\.trendyol\\.com/\\w+/\\w+-p-([0-9]+)\\?boutiqueId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * \w+:                         Brand or category name may must contain at least one word character.
     * /:                           Passes brand or category name to product information section.
     * \w+:                         Product name may must contain at least one word character.
     * -p-:                         Passes product tag.
     * ([0-9]+):                    Product id must be numerical and has at least one number
     *                              the id will necessary in order to mount ContentId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * \?:                          Passes to query parameters.
     * merchantId=:                 Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount MerchantId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPageWithMerchant = "^https://www\\.trendyol\\.com/\\w+/\\w+-p-([0-9]+)\\?merchantId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * \w+:                         Brand or category name may must contain at least one word character.
     * /:                           Passes brand or category name to product information section.
     * \w+:                         Product name may must contain at least one word character.
     * -p-:                         Passes product tag.
     * ([0-9]+):                    Product id must be numerical and has at least one number
     *                              the id will necessary in order to mount ContentId of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPage = "^https://www\\.trendyol\\.com/\\w+/\\w+-p-([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * https://www\.trendyol\.com:  Passes the domain.
     * /sr:                         Search path to query parameters section.
     * \?:                          Passes query parameters.
     * q=:                          Passes to the query value.
     * ([\S]+):                     Query value must contain at least one non-whitespace character
     *                              the value will necessary in order to mount Query of deep-links
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternSearchPage = "^https://www\\.trendyol\\.com/sr\\?q=([\\S]+)$";

    private final String ThrowMessage = "Passed URL that doesn't match the pattern.";

    /**
     * Converts given url to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when called methods thrown.
     */
    public static String convert(String url) throws Exception {
        URLConverter c = new URLConverter();

        if (c.isProductPageWithBoutiqueAndMerchant(url))
            return c.convertProductPageWithBoutiqueAndMerchant(url);
        else if (c.isProductPageWithBoutique(url))
            return c.convertProductPageWithBoutique(url);
        else if (c.isProductPageWithMerchant(url))
            return c.convertProductPageWithMerchant(url);
        else if (c.isProductPage(url))
            return c.convertProductPage(url);
        else if (c.isSearchPage(url))
            return c.convertSearchPage(url);
        else
            return "ty://?Page=Home";
    }

    /**
     * Prepares the Matcher instance for converting.
     *
     * @param pattern that will be used for Pattern.
     * @param url     that will be matcher.
     * @return Matcher instance.
     */
    private Matcher prepareMatcher(String pattern, String url) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(url);
    }

    /**
     * Tests the url whether presents a proper product page with boutique and merchant.
     *
     * @param url that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPageWithBoutiqueAndMerchant(String url) {
        if (Pattern.matches(formerPatternProductPageWithBoutiqueAndMerchant, url))
            return true;
        return Pattern.matches(latterPatternProductPageWithBoutiqueAndMerchant, url);
    }

    /**
     * Tests the url whether presents a proper product page with boutique.
     *
     * @param url that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPageWithBoutique(String url) {
        return Pattern.matches(patternProductPageWithBoutique, url);
    }

    /**
     * Tests the url whether presents a proper product page with merchant.
     *
     * @param url that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPageWithMerchant(String url) {
        return Pattern.matches(patternProductPageWithMerchant, url);
    }

    /**
     * Tests the url whether presents a proper product page.
     *
     * @param url that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPage(String url) {
        return Pattern.matches(patternProductPage, url);
    }

    /**
     * Tests the url whether presents a proper search page.
     *
     * @param url that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isSearchPage(String url) {
        return Pattern.matches(patternSearchPage, url);
    }

    /**
     * Converts the given url (product page with boutique and merchant) to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when the given url doesn't match proper pattern.
     */
    public String convertProductPageWithBoutiqueAndMerchant(String url) throws Exception {
        if (!isProductPageWithBoutiqueAndMerchant(url))
            throw new Exception(ThrowMessage);

        Pattern p;
        Matcher m;
        String contentId = "";
        String boutiqueId = "";
        String merchantId = "";

        if (Pattern.matches(formerPatternProductPageWithBoutiqueAndMerchant, url)) {

            m = prepareMatcher(formerPatternProductPageWithBoutiqueAndMerchant, url);
            if (m.matches()) {
                contentId = m.group(1);
                boutiqueId = m.group(2);
                merchantId = m.group(3);
            }
            return "ty://?Page=Product&ContentId="
                    .concat(contentId)
                    .concat("&")
                    .concat("CampaignId=")
                    .concat(boutiqueId)
                    .concat("&")
                    .concat("MerchantId=")
                    .concat(merchantId);

        } else {

            m = prepareMatcher(latterPatternProductPageWithBoutiqueAndMerchant, url);
            if (m.matches()) {
                contentId = m.group(1);
                merchantId = m.group(2);
                boutiqueId = m.group(3);
            }
            return "ty://?Page=Product&ContentId="
                    .concat(contentId)
                    .concat("&")
                    .concat("MerchantId=")
                    .concat(merchantId)
                    .concat("&")
                    .concat("CampaignId=")
                    .concat(boutiqueId);

        }

    }

    /**
     * Converts the given url (product page with boutique) to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when the given url doesn't match proper pattern.
     */
    public String convertProductPageWithBoutique(String url) throws Exception {
        if (!isProductPageWithBoutique(url))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPageWithBoutique, url);
        String contentId = "";
        String boutiqueId = "";

        if (m.matches()) {
            contentId = m.group(1);
            boutiqueId = m.group(2);
        }

        return "ty://?Page=Product&ContentId="
                .concat(contentId)
                .concat("&")
                .concat("CampaignId=")
                .concat(boutiqueId);
    }

    /**
     * Converts the given url (product page with merchant) to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when the given url doesn't match proper pattern.
     */
    public String convertProductPageWithMerchant(String url) throws Exception {
        if (!isProductPageWithMerchant(url))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPageWithMerchant, url);
        String contentId = "";
        String merchantId = "";

        if (m.matches()) {
            contentId = m.group(1);
            merchantId = m.group(2);
        }

        return "ty://?Page=Product&ContentId="
                .concat(contentId)
                .concat("&")
                .concat("MerchantId=")
                .concat(merchantId);
    }

    /**
     * Converts the given url (product page without anything) to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when the given url doesn't match proper pattern.
     */
    public String convertProductPage(String url) throws Exception {
        if (!isProductPage(url))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPage, url);
        String contentId = "";

        if (m.matches()) {
            contentId = m.group(1);
        }

        return "ty://?Page=Product&ContentId="
                .concat(contentId);
    }

    /**
     * Converts the given url (search page) to deep-link.
     *
     * @param url that will be converted.
     * @return converted deep-link.
     * @throws Exception when the given url doesn't match proper pattern.
     */
    public String convertSearchPage(String url) throws Exception {
        if (!isSearchPage(url))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternSearchPage, url);
        String queryValue = "";

        if (m.matches()) {
            queryValue = m.group(1);
        }

        return "ty://?Page=Search&Query="
                .concat(queryValue);
    }

}
