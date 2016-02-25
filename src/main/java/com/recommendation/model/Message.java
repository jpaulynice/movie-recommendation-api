package com.recommendation.model;

import java.util.UUID;

/**
 * Object to show human readable message translation from the API.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public class Message {
    private int status;
    private String info;
    private final UUID requestId;

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

    /**
     * @param status the status to set
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(final String info) {
        this.info = info;
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
        result = prime * result + ((requestId == null) ? 0 : 
            requestId.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (requestId == null) {
            if (other.requestId != null)
                return false;
        } else if (!requestId.equals(other.requestId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Message [status=" + status + ", info=" + info + ", "
                + "requestId=" + requestId + "]";
    }
}
