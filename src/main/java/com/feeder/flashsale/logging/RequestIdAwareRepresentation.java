package com.feeder.flashsale.logging;

import org.slf4j.MDC;

import static com.feeder.flashsale.logging.RequestIdMdcFilter.REQUEST_ID;

public abstract class RequestIdAwareRepresentation {
    private final String requestId;

    public RequestIdAwareRepresentation() {
        this.requestId = MDC.get(REQUEST_ID);
    }

    public String getRequestId() {
        return requestId;
    }
}
