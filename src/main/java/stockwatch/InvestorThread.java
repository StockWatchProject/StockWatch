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
    private final int investorId;
    
    public InvestorThread(Socket socket, WorldWideMarket worldWideMarket, int threadId) {
        super("InvestorThread");
        this.socket = socket;
        this.worldWideMarket = worldWideMarket;
        this.investorId = threadId;
    }
    
    private void handleMessage(MsgWrapper msgWrapper) throws IOException {
        switch (msgWrapper.getType()) {
            case AVAILABLE_REQ: {
                QuoteList responseMsg = worldWideMarket.getQuotes();
                ProtoMessageWriter.writeTo(responseMsg, socket.getOutputStream());
                socket.close();
                break;
            }
            case QUOTE_LIST: {
                QuoteList responseMsg = worldWideMarket.getQuotes(msgWrapper.getQuoteList());
                ProtoMessageWriter.writeTo(responseMsg, socket.getOutputStream());
                socket.close();
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
            MsgWrapper msgWrapper = ProtoMessageReader.readFrom(socket.getInputStream());
            handleMessage(msgWrapper);
        } catch (IOException e) {
            logger.debug(e.getMessage() + e.getCause());
        }
    }
}
