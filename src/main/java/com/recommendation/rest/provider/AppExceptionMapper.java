package com.recommendation.rest.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.recommendation.exception.AbstractBaseException;
import com.recommendation.model.Message;

/**
 * Mapper class to map exceptions to readable messages
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Component
@Provider
public class AppExceptionMapper implements ExceptionMapper<RuntimeException> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final RuntimeException e) {
        Message message;
        if (e instanceof AbstractBaseException) {
            final AbstractBaseException ex = (AbstractBaseException) e;
            message = new Message(ex.getStatus(), ex.getMessage());
        } else if (e instanceof WebApplicationException) {
            message = webAppExMessage(e);
        } else {
            message = genericMessage();
        }

        logger.debug("An exception occurred: {}", message);
        return Response.status(message.getStatus()).entity(message).build();
    }

    private Message webAppExMessage(final RuntimeException e) {
        final WebApplicationException webEx = (WebApplicationException) e;
        final Response r = webEx.getResponse();
        return new Message(r.getStatus(), e.getMessage());
    }

    private Message genericMessage() {
        return new Message(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                "Internal server error");
    }
}
