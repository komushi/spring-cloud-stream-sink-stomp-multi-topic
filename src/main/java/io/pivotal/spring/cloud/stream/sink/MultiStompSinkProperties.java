package io.pivotal.spring.cloud.stream.sink;

/**
 * Created by lei_xu on 6/26/16.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="stomp")
public class MultiStompSinkProperties {


    public static final String DEFAULT_ENDPOINT = "stomp";


    /**
     * the websocketPath on which a StompSink consumer needs to connect. Default is <tt>/stomp</tt>
     */
    String endpoint = DEFAULT_ENDPOINT;

    String topicPath;

    Boolean withSockJS = false;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTopicPath() {
        return topicPath;
    }

    public void setTopicPath(String topicPath) {
        this.topicPath = topicPath;
    }

    public Boolean getWithSockJS() {
        return withSockJS;
    }

    public void setWithSockJS(Boolean withSockJS) {
        this.withSockJS = withSockJS;
    }

}
