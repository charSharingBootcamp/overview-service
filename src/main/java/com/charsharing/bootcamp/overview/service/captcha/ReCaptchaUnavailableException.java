package com.charsharing.bootcamp.overview.service.captcha;

import org.springframework.web.client.RestClientException;

public final class ReCaptchaUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ReCaptchaUnavailableException() {
        super();
    }

    public ReCaptchaUnavailableException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ReCaptchaUnavailableException(final String message) {
        super(message);
    }

    public ReCaptchaUnavailableException(final Throwable cause) {
        super(cause);
    }

}
