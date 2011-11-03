package stockwatch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private final int investorId;
    
    public InvestorThread(Socket socket, WorldWideMarket worldWideMarket, int threadId) {
        super("InvestorThread");
        this.socket = socket;
        this.worldWideMarket = worldWideMarket;
        this.protoMsgReader = new ProtoMessageReader();
        this.protoMsgWriter = new ProtoMessageWriter();
        this.investorId = threadId;
    }
    
    @Override
    protected void finalize() {
        logger.debug("InvestorThread no.: " + investorId + " is gone.");
    }
    
    private void openStreams() {
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            logger.error("Could not open stream! " + e.getMessage());
        }
    }
    
    private void closeStreams() {
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            logger.error("Could not close stream! " + e.getMessage());
        }
    }
    private void handleMessage(MsgWrapper msgWrapper) throws IOException {
        switch (msgWrapper.getType()) {
            case AVAILABLE_REQ: {
                QuoteList responseMsg = worldWideMarket.getQuotes();
                protoMsgWriter.writeTo(responseMsg, outputStream);
                break;
            }
            case QUOTE_LIST: {
                QuoteList responseMsg = worldWideMarket.getQuotes(msgWrapper.getQuoteList());
                protoMsgWriter.writeTo(responseMsg, outputStream);
                break;
            }
            default: {
                logger.error("Unsupported message type received!");
                break;
            }
        }
    }
    
    @Override
    public void run() {
        logger.debug("InvestorThread no.: " + investorId + " is running.");
        try {
            openStreams();
            MsgWrapper msgWrapper = protoMsgReader.readFrom(inputStream);
            handleMessage(msgWrapper);
            closeStreams();
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
}
