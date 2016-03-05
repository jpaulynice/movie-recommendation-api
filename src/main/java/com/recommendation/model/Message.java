package com.recommendation.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object to show human readable message translation from the API.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@XmlRootElement
public class Message {
    @XmlElement
    private int status;

    @XmlElement
    private String info;

    @XmlElement
    private UUID requestId;

    public Message() {
        // nothing to see here
    }

    public Message(final int status, final String info) {
        requestId = UUID.randomUUID();
        this.status = status;
        this.info = info;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    public UUID getRequestId() {
        return requestId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((requestId == null) ? 0 : requestId.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (requestId == null) {
            if (other.requestId != null) {
                return false;
            }
        } else if (!requestId.equals(other.requestId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message [status=" + status + ", info=" + info + ", "
                + "requestId=" + requestId + "]";
    }
}
