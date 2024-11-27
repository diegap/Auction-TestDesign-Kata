package codingdojo;

import org.junit.jupiter.api.Test;

public class AuctionMessageTranslatorTest {
    private static AuctionEventListenerMock eventListenerMock;
    private AuctionMessageTranslator translator;

    @Test
    public void notifiesAuctionClosedWhenCloseMessageReceived() throws Exception {
        givenAListenerWithExpectedCall("CLOSE");
        givenATranslator();

        whenTranslatorIsCalledWithMessage("SOLVersion: 1.1; Event: CLOSE;");

        thenListenerIsVerified();
    }

    @Test
    public void notifiesBidDetailsWhenPriceMessageReceived() throws Exception {
        givenAListenerWithExpectedCall("PRICE", 192, 7, "Someone else");
        givenATranslator();

        whenTranslatorIsCalledWithMessage("SOLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;");

        thenListenerIsVerified();
    }

    @Test
    public void notifiesCloseWithNoSaleMessageReceived() throws Exception {
        givenAListenerWithExpectedCall("NO_SALE");
        givenATranslator();

        whenTranslatorIsCalledWithMessage("SOLVersion: 1.1; Event: NO_SALE;");

        thenListenerIsVerified();
    }

    private static void thenListenerIsVerified() throws Exception {
        eventListenerMock.checkExpectations();
    }

    private void whenTranslatorIsCalledWithMessage(String message) {
        translator.processMessage(message);
    }

    private void givenATranslator() {
        translator = new AuctionMessageTranslator(eventListenerMock);
    }

    private void givenAListenerWithExpectedCall(String call) {
        eventListenerMock = new AuctionEventListenerMock();
        eventListenerMock.expectCall(call);
    }

    private void givenAListenerWithExpectedCall(String call, int price, int increment, String bidder) {
        eventListenerMock = new AuctionEventListenerMock();
        eventListenerMock.expectCall(call, price, increment, bidder);
    }
}

