# Trendyol Link Converter

This project helps to convert Trendyol.com links between mobile and web applications. Web applications use URLs and mobile applications use deeplinks. Both applications use links to redirect specific locations inside applications. When you want to redirect across applications, you should convert URLs to deeplinks or deeplinks to URLs.
Through the developed services in this project, the Web URL can be converted to deeplink for Mobile apps and the deeplinks can be converted to Web URLs.

## Required Configurations
- [Java SE Development Kit](https://www.oracle.com/tr/java/technologies/javase-jdk11-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or prefered IDE.

## Properties
This project uses postgresql to log request and response requests.

```   
spring.datasource.url=jdbc:postgresql://localhost:5432/trendyol
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=create
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## How To Build Project & Tests
If use IDE set configuration for this app, 
- To run project -> [TrendyolApplication](./src/main/java/anil.trendyol/TrendyolApplication)
- To run test cases to URLConverter -> [URLConverterTests](./src/test/java/anil.trendyol/URLConverterTests)
- To run test cases to DeepLinkConverterTests -> [DeepLinkConverterTests](./src/test/java/anil.trendyol/DeepLinkConverterTests)

Otherwise,
Open terminal reach project directory and type "./mvnw spring-boot:run" then, enter.
The Server Will run on port 8080.

## Flow

### A. URL To DeepLink

#### A.1. Controller layer
When a request is made to the POST "/url-to-deep" endpoint, this request is first handled by the ConvertToDeepLink method in the MainController.java class

```java   
    @ResponseBody
    @RequestMapping(value = "/url-to-deep", method = RequestMethod.POST)
    public String ConvertToDeepLink(@RequestBody Request request) throws Exception {
        String deepLink = URLConverter.convert(request.getLink());

        Log log = new Log();
        log.setRequest(request.getLink());
        log.setResponse(deepLink);
        logRepository.save(log);

        return deepLink;
    }
```
##### A.1.1.
This service accepts Request object in layer [Request](./src/main/java/anil.trendyol/Model/Request) as request body. It expects the "link" string value as it will be seen from the content of the object.
```java   

public class Request {
private String link;

    public Request() {
    }

    public Request(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
```
##### A.1.2.
The URLConverter.convert method is called with the "link" value received from the request to perform operations based on the specified conditions and then log the converted deepLink value. 

```method
String deepLink = URLConverter.convert(request.getLink());

        Log log = new Log();
        log.setRequest(request.getLink());
        log.setResponse(deepLink);
        logRepository.save(log);

        return deepLink;
```


###### A.1.2.1.
To log the id, the sent request as "request" and the response received by the service as "response", the model in the [Log](./src/main/java/anil.trendyol/Log) layer is created. Using the @Entity annotation, it is specified as a database table.

```java
@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    private String request;
    private String response;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Log(Long id, String request, String response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }

    public Log() {
    }
}
```

#### A.2. Service Layer
The URLConverter.convert method is called in order to convert the "link" value sent to the service and included in the request body to Web URL.
String parameters are defined using the Regex library, according to the pdf, which is located under the repository and explains the formats of the links sent.
Handle the sent "link" values according to which regex value they correspond to and convert operations are performed. For the value that does not comply with any regex, the homepage link is redirected.

```method
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
```

For prepares the Matcher instance for converting by given value, there is a function named prepareMatcher
```method
private Matcher prepareMatcher(String pattern, String url) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(url);
    }
```

#### A.3. Unit&Integration Tests
[URLConverterTests](./src/test/java/anil.trendyol/URLConverterTests)

Contains test cases such as 
- isProductPageWithBoutiqueAndMerchant, 
- isProductPageWithBoutique,
- isProductPageWithMerchant,
- isProductPage, 
- isSearchPage,
- convertProductPageWithBoutiqueAndMerchant,
- convertProductPageWithBoutique,
- convertProductPageWithMerchant, 
- convertProductPage, 
- convertSearchPage,
- convert.

### B. DeepLink To URL

#### B.1. Controller layer
When a request is made to the POST "/deep-to-url" endpoint, this request is first handled by the ConvertToURL method in the MainController.java class

```java   
    @ResponseBody
    @RequestMapping(value = "/deep-to-url", method = RequestMethod.POST)
    public String ConvertToURL(@RequestBody Request request) throws Exception {
        String URL = DeepLinkConverter.convert(request.getLink());

        Log log = new Log();
        log.setRequest(request.getLink());
        log.setResponse(URL);
        logRepository.save(log);

        return URL;
    }
```

##### B.1.1.
This service accepts Request object in layer [Request](./src/main/java/anil.trendyol/Model/Request) as request body. It expects the "link" string value as it will be seen from the content of the object.
```java   

public class Request {
private String link;

    public Request() {
    }

    public Request(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
```

##### B.1.2.
The DeepLinkConverter.convert method is called with the "link" value received from the request to perform operations based on the specified conditions and then log the converted url value. 

```method
String URL = DeepLinkConverter.convert(request.getLink());

        Log log = new Log();
        log.setRequest(request.getLink());
        log.setResponse(URL);
        logRepository.save(log);

        return URL;
```

###### B.1.2.1.
To log the id, the sent request as "request" and the response received by the service as "response", the model in the [Log](./src/main/java/anil.trendyol/Log) layer is created. Using the @Entity annotation, it is specified as a database table.

```java
@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    private String request;
    private String response;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Log(Long id, String request, String response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }

    public Log() {
    }
}
```













#### B.2. Service Layer
The DeepLinkConverter.convert method is called in order to convert the "link" value sent to the service and included in the request body to deeplink.
String parameters are defined using the Regex library, according to the pdf, which is located under the repository and explains the formats of the links sent.
Handle the sent "link" values according to which regex value they correspond to and convert operations are performed. For the value that does not comply with any regex, the homepage link is redirected.

```method
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
```

For prepares the Matcher instance for converting by given value, there is a function named prepareMatcher
```method
private Matcher prepareMatcher(String pattern, String deepLink) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(deepLink);
    }
```


#### B.3. Unit&Integration Tests
[DeepLinkConverterTests](./src/test/java/anil.trendyol/DeepLinkConverterTests)

Contains test cases such as
- isProductPageWithBoutiqueAndMerchant,
- isProductPageWithCampaign,
- isProductPageWithMerchant,
- isProductPage,
- isSearchPage,
- convertProductPageWithBoutiqueAndMerchant,
- convertProductPageWithCampaign,
- convertProductPageWithMerchant,
- convertProductPage,
- convertSearchPage,
- convert.
