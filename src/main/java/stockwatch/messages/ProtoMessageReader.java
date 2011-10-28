package stockwatch.messages;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import stockwatch.messages.QuoteMessages.MsgWrapper;

public class ProtoMessageReader {
    private static final Logger logger = Logger.getLogger(ProtoMessageReader.class);
    
    public MsgWrapper readFrom(InputStream input) throws IOException {
        MsgWrapper msgWrapper = MsgWrapper.parseFrom(input);
        input.close();
        logger.debug("Message " + msgWrapper.getType() + " received! [" + msgWrapper.getSerializedSize() + "bytes].");
        return msgWrapper;
    }
}
