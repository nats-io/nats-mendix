package natsconnectormodule.impl;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.logging.LogLevel;
import io.nats.client.*;
import io.nats.client.api.ServerInfo;
import io.nats.client.support.Status;

public class ErrorListenerMendixLogging implements ErrorListener {

    private static final ILogNode DEFAULT_LOGGER = Core.getLogger("NatsListener");

    private final ILogNode logger;
    private final boolean logInfos;
    private final boolean logWarns;
    private final boolean logErrors;

    public ErrorListenerMendixLogging() {
        this(DEFAULT_LOGGER, LogLevel.ERROR);
    }

    public ErrorListenerMendixLogging(LogLevel level) {
        this(DEFAULT_LOGGER, level);
    }

    public ErrorListenerMendixLogging(ILogNode logger) {
        this(logger, LogLevel.ERROR);
    }

    public ErrorListenerMendixLogging(ILogNode logger, LogLevel level) {
        this.logger = logger;
        switch (level) {
            case TRACE:
            case DEBUG:
            case INFO:
                logInfos = true;
                logWarns = true;
                logErrors = true;
                break;
            case WARNING:
                logInfos = false;
                logWarns = true;
                logErrors = true;
                break;
            case ERROR:
            case CRITICAL:
                logInfos = false;
                logWarns = false;
                logErrors = true;
                break;
            default:
                logInfos = false;
                logWarns = false;
                logErrors = false;
                break;
        }
    }

    private String buildMessage(String label, Connection conn, Consumer consumer, Subscription sub, Object... pairs) {
        StringBuilder sb = new StringBuilder(label);
        if (conn != null) {
            ServerInfo si = conn.getServerInfo();
            if (si != null) {
                sb.append(", Connection: ").append(conn.getServerInfo().getClientId());
            }
        }
        if (consumer != null) {
            sb.append(", Consumer: ").append(consumer.hashCode());
        }
        if (sub != null) {
            sb.append(", Subscription: ").append(sub.hashCode());
        }
        for (int x = 0; x < pairs.length; x++) {
            sb.append(", ").append(pairs[x]).append(pairs[++x]);
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void errorOccurred(final Connection conn, final String error) {
        if (logErrors) logger.error(buildMessage("errorOccurred", conn, null, null, "Error: ", error));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exceptionOccurred(final Connection conn, final Exception exp) {
        if (logErrors) logger.error(buildMessage("exceptionOccurred", conn, null, null, "Exception: ", exp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void slowConsumerDetected(final Connection conn, final Consumer consumer) {
        if (logWarns) logger.warn(buildMessage("slowConsumerDetected", conn, consumer, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageDiscarded(final Connection conn, final Message msg) {
        if (logInfos) logger.info(buildMessage("messageDiscarded", conn, null, null, "Message: ", msg));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heartbeatAlarm(final Connection conn, final JetStreamSubscription sub,
                               final long lastStreamSequence, final long lastConsumerSequence) {
        if (logErrors) logger.error(buildMessage("heartbeatAlarm", conn, null, sub, "lastStreamSequence: ", lastStreamSequence, "lastConsumerSequence: ", lastConsumerSequence));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unhandledStatus(final Connection conn, final JetStreamSubscription sub, final Status status) {
        if (logWarns) logger.warn(buildMessage("unhandledStatus", conn, null, sub, "Status:", status));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flowControlProcessed(Connection conn, JetStreamSubscription sub, String id, FlowControlSource source) {
        if (logInfos) logger.info(buildMessage("flowControlProcessed", conn, null, sub, "FlowControlSource:", source));
    }
}
