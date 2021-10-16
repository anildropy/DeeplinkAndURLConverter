package anil.trendyol.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
