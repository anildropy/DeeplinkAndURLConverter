package anil.trendyol.Controller;

import anil.trendyol.Model.Log;
import anil.trendyol.Model.Request;
import anil.trendyol.Repository.LogRepository;
import anil.trendyol.Service.DeepLinkConverter;
import anil.trendyol.Service.URLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private LogRepository logRepository;

    /**
     * Handles requests that converting urls to deep-links.
     *
     * @return deep link
     */
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

    /**
     * Handles requests that converting deep-links urls.
     *
     * @return url
     */
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

    /**
     * Handles unexpected requests.
     * Users may want to request to indeterminate end-points.
     * And that is an exception.
     *
     * @return a proper information message
     */
    @ResponseBody
    @RequestMapping(value = "/**")
    public String handleUnexpectedRequest() {
        return "There are two possible end-points: /url-to-deep and /deep-to-url."
                .concat("\n")
                .concat("And they support just HTTP POST requests.")
                .concat("\n")
                .concat("You must send request body that contain 'link' key when requesting those end-points.")
                .concat("\n")
                .concat("{")
                .concat("\n\t")
                .concat("'link' : '...'")
                .concat("\n")
                .concat("}");
    }

    /**
     * If any request occurs database error this method will be performed.
     *
     * @return a proper information message
     */
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String handleDataBaseExceptions() {
        return "A database error occurred.";
    }

    /**
     * If any request occurs any type of error this method will be performed.
     *
     * @return a proper information message
     */
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions() {
        return "An unknown error occurred.";
    }

}
