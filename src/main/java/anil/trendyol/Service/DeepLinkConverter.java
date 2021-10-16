package anil.trendyol.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class operates the url converting processes
 */
public class DeepLinkConverter {

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Product&ContentId=:
     *                              Pass to content id
     * ([0-9]+):                    Content id must be numerical and has at least one number
     *                              the id will necessary in order to mount product id of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &CampaignId=:                Passes to campaign id.
     * ([0-9]+):                    Campaign id must be numerical and has at least one number
     *                              the id will necessary in order to mount boutique of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &MerchantId=:                Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount merchant of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String formerPatternProductPageWithCampaignAndMerchant = "^ty://\\?Page=Product&ContentId=([0-9]+)&CampaignId=([0-9]+)&MerchantId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Product&ContentId=:
     *                              Pass to content id
     * ([0-9]+):                    Content id must be numerical and has at least one number
     *                              the id will necessary in order to mount product id of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &MerchantId=:                Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount merchant of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &CampaignId=:                Passes to campaign id.
     * ([0-9]+):                    Campaign id must be numerical and has at least one number
     *                              the id will necessary in order to mount boutique of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String latterPatternProductPageWithCampaignAndMerchant = "^ty://\\?Page=Product&ContentId=([0-9]+)&MerchantId=([0-9]+)&CampaignId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Product&ContentId=:
     *                              Pass to content id
     * ([0-9]+):                    Content id must be numerical and has at least one number
     *                              the id will necessary in order to mount product id of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &CampaignId=:                Passes to campaign id.
     * ([0-9]+):                    Campaign id must be numerical and has at least one number
     *                              the id will necessary in order to mount boutique of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPageWithCampaign = "^ty://\\?Page=Product&ContentId=([0-9]+)&CampaignId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Product&ContentId=:
     *                              Pass to content id
     * ([0-9]+):                    Content id must be numerical and has at least one number
     *                              the id will necessary in order to mount product id of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * &MerchantId=:                Passes to merchant id.
     * ([0-9]+):                    Merchant id must be numerical and has at least one number
     *                              the id will necessary in order to mount merchant of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPageWithMerchant = "^^ty://\\?Page=Product&ContentId=([0-9]+)&MerchantId=([0-9]+)$";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Product&ContentId=:
     *                              Pass to content id
     * ([0-9]+):                    Content id must be numerical and has at least one number
     *                              the id will necessary in order to mount product id of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternProductPage = "^^ty://\\?Page=Product&ContentId=([0-9]+)";

    /**
     * ^:                           Asserts that this pattern match beginning of the string.
     * ty://\?Page=Search&Query=:   Pass to Query value
     * ([\S]+):                     Query value must contain at least one non-whitespace character
     *                              the value will necessary in order to mount q of URLs
     *                              so if we want to take any part of match we have to enclose it with parentheses.
     * $:                           Asserts came up to the end of string.
     */
    private final String patternSearchPage = "^ty://\\?Page=Search&Query=([\\S]+)$";

    private final String ThrowMessage = "Passed deep-link that doesn't match the pattern.";

    /**
     * Converts given deep-link to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when called methods thrown.
     */
    public static String convert(String deepLink) throws Exception {
        DeepLinkConverter c = new DeepLinkConverter();

        if (c.isProductPageWithCampaignAndMerchant(deepLink))
            return c.convertProductPageWithCampaignAndMerchant(deepLink);
        else if (c.isProductPageWithCampaign(deepLink))
            return c.convertProductPageWithCampaign(deepLink);
        else if (c.isProductPageWithMerchant(deepLink))
            return c.convertProductPageWithMerchant(deepLink);
        else if (c.isProductPage(deepLink))
            return c.convertProductPage(deepLink);
        else if (c.isSearchPage(deepLink))
            return c.convertSearchPage(deepLink);
        else
            return "https://www.trendyol.com/";
    }

    /**
     * Prepares the Matcher instance for converting.
     *
     * @param pattern that will be used for Pattern.
     * @param deepLink that will be matcher.
     * @return Matcher instance.
     */
    private Matcher prepareMatcher(String pattern, String deepLink) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(deepLink);
    }

    /**
     * Tests the deep-link whether presents a proper product page with campaign and merchant.
     *
     * @param deepLink that will be tested.
     * @return true if deep-link matches, false otherwise.
     */
    public boolean isProductPageWithCampaignAndMerchant(String deepLink) {
        if (Pattern.matches(formerPatternProductPageWithCampaignAndMerchant, deepLink))
            return true;
        return Pattern.matches(latterPatternProductPageWithCampaignAndMerchant, deepLink);
    }

    /**
     * Tests the deep-link whether presents a proper product page with campaign.
     *
     * @param deepLink that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPageWithCampaign(String deepLink) {
        return Pattern.matches(patternProductPageWithCampaign, deepLink);
    }

    /**
     * Tests the deep-link whether presents a proper product page with merchant.
     *
     * @param deepLink that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPageWithMerchant(String deepLink) {
        return Pattern.matches(patternProductPageWithMerchant, deepLink);
    }

    /**
     * Tests the deep-link whether presents a proper product page.
     *
     * @param deepLink that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isProductPage(String deepLink) {
        return Pattern.matches(patternProductPage, deepLink);
    }

    /**
     * Tests the deep-link whether presents a proper search page.
     *
     * @param deepLink that will be tested.
     * @return true if url matches, false otherwise.
     */
    public boolean isSearchPage(String deepLink) {
        return Pattern.matches(patternSearchPage, deepLink);
    }

    /**
     * Converts the given deep-link (product page with campaign and merchant) to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when the given deep-link doesn't match proper pattern.
     */
    public String convertProductPageWithCampaignAndMerchant(String deepLink) throws Exception {
        if (!isProductPageWithCampaignAndMerchant(deepLink))
            throw new Exception(ThrowMessage);

        Matcher m;
        String contentId = "";
        String campaignId = "";
        String merchantId = "";

        if (Pattern.matches(formerPatternProductPageWithCampaignAndMerchant, deepLink)) {

            m = prepareMatcher(formerPatternProductPageWithCampaignAndMerchant, deepLink);
            if (m.matches()) {
                contentId = m.group(1);
                campaignId = m.group(2);
                merchantId = m.group(3);
            }
            return "https://www.trendyol.com/brand/name-p-"
                    .concat(contentId)
                    .concat("?")
                    .concat("boutiqueId=")
                    .concat(campaignId)
                    .concat("&")
                    .concat("merchantId=")
                    .concat(merchantId);

        } else {

            m = prepareMatcher(latterPatternProductPageWithCampaignAndMerchant, deepLink);
            if (m.matches()) {
                contentId = m.group(1);
                merchantId = m.group(2);
                campaignId = m.group(3);
            }
            return "https://www.trendyol.com/brand/name-p-"
                    .concat(contentId)
                    .concat("?")
                    .concat("merchantId=")
                    .concat(merchantId)
                    .concat("&")
                    .concat("boutiqueId=")
                    .concat(campaignId);

        }

    }

    /**
     * Converts the given deep-link (product page with campaign) to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when the given deep-link doesn't match proper pattern.
     */
    public String convertProductPageWithCampaign(String deepLink) throws Exception {
        if (!isProductPageWithCampaign(deepLink))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPageWithCampaign, deepLink);
        String contentId = "";
        String campaignId = "";

        if (m.matches()) {
            contentId = m.group(1);
            campaignId = m.group(2);
        }

        return "https://www.trendyol.com/brand/name-p-"
                .concat(contentId)
                .concat("?")
                .concat("boutiqueId=")
                .concat(campaignId);
    }

    /**
     * Converts the given deep-link (product page with merchant) to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when the given deep-link doesn't match proper pattern.
     */
    public String convertProductPageWithMerchant(String deepLink) throws Exception {
        if (!isProductPageWithMerchant(deepLink))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPageWithMerchant, deepLink);
        String contentId = "";
        String merchantId = "";

        if (m.matches()) {
            contentId = m.group(1);
            merchantId = m.group(2);
        }

        return "https://www.trendyol.com/brand/name-p-"
                .concat(contentId)
                .concat("?")
                .concat("merchantId=")
                .concat(merchantId);
    }

    /**
     * Converts the given deep-link (product page without anything) to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when the given deep-link doesn't match proper pattern.
     */
    public String convertProductPage(String deepLink) throws Exception {
        if (!isProductPage(deepLink))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternProductPage, deepLink);
        String contentId = "";

        if (m.matches()) {
            contentId = m.group(1);
        }

        return "https://www.trendyol.com/brand/name-p-"
                .concat(contentId);
    }

    /**
     * Converts the given deep-link (search page) to URL.
     *
     * @param deepLink that will be converted.
     * @return converted URL.
     * @throws Exception when the given deep-link doesn't match proper pattern.
     */
    public String convertSearchPage(String deepLink) throws Exception {
        if (!isSearchPage(deepLink))
            throw new Exception(ThrowMessage);

        Matcher m = prepareMatcher(patternSearchPage, deepLink);
        String queryValue = "";

        if (m.matches()) {
            queryValue = m.group(1);
        }

        return "https://www.trendyol.com/sr?q="
                .concat(queryValue);
    }

}
