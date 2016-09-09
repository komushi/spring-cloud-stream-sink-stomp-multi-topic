package io.pivotal.spring.cloud.stream.sink;

/**
 * Created by lei_xu on 6/26/16.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="stomp")
public class MultiStompSinkProperties {


    /**
     * The Websocket Path on which a StompSink consumer needs to connect
     */
    private String endpoint = "stomp";

    /**
     * The topic path to subscribe/publish
     */
    private String topicPath;

    /**
     * Whether to use SockJS
     */
    private Boolean withsockjs = false;

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

    public Boolean getWithsockjs() {
        return withsockjs;
    }

    public void setWithsockjs(Boolean withSockJS) {
        this.withsockjs = withSockJS;
    }

}
