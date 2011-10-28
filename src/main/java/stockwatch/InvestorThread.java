package stockwatch;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import stockwatch.messages.ProtoMessageReader;
import stockwatch.messages.ProtoMessageWriter;
import stockwatch.messages.QuoteMessages.MsgWrapper;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.stockmarkets.WorldWideMarket;

public class InvestorThread extends Thread {
    private static final Logger logger = Logger.getLogger(InvestorThread.class);
    private Socket socket;
    private final WorldWideMarket worldWideMarket;
    private ProtoMessageReader protoMsgReader;
    private ProtoMessageWriter protoMsgWriter;
    private final int investorId;
    
    public InvestorThread(Socket socket, WorldWideMarket worldWideMarket, int threadId) {
        super("InvestorThread");
        this.socket = socket;
        this.worldWideMarket = worldWideMarket;
        this.protoMsgReader = new ProtoMessageReader();
        this.protoMsgWriter = new ProtoMessageWriter();
        this.investorId = threadId;
    }
    
    private void handleMessage(MsgWrapper msgWrapper) throws IOException {
        switch (msgWrapper.getType()) {
            case AVAILABLE_REQ: {
                QuoteList responseMsg = worldWideMarket.getQuotes();
                protoMsgWriter.writeTo(responseMsg, socket.getOutputStream());
                break;
            }
            case QUOTE_LIST: {
                QuoteList responseMsg = worldWideMarket.getQuotes(msgWrapper.getQuoteList());
                protoMsgWriter.writeTo(responseMsg, socket.getOutputStream());
                break;
            }
            default: {
                logger.error("Unsupported message type received!");
            }
        }
    }
    
    @Override
    public void run() {
        logger.debug("InvestorThread no.: " + investorId + " is running.");
        try {
            MsgWrapper msgWrapper = protoMsgReader.readFrom(socket.getInputStream());
            handleMessage(msgWrapper);
        } catch (IOException e) {
            logger.debug(e.getMessage() + e.getCause());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("Could not close socket! " + e.getMessage());
            }
        }
    }
    
    @Override
    public void finalize() {
        logger.debug("InvestorThread no.: " + investorId + " is gone.");
    }
}
