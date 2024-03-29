package stockwatch.messages;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import stockwatch.messages.QuoteMessages.QuoteList;

public class ProtoMessageWriter {
    private static final Logger logger = Logger.getLogger(ProtoMessageReader.class);
    
    public void writeTo(QuoteList responseMsg, OutputStream output) throws IOException {
        responseMsg.writeDelimitedTo(output);
        logger.debug("Message " + QuoteList.class.getSimpleName() + " sent! [" + responseMsg.getSerializedSize() + " bytes]");
    }
}
