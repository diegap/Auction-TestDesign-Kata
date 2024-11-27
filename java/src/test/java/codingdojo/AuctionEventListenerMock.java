package codingdojo;

public class AuctionEventListenerMock implements AuctionEventListener {

    private String expectedCall;
    private String receivedCall;

    private int expectedPriceValue;
    private int receivedPriceValue;

    private int expectedIncrement;
    private int receivedIncrement;

    private String expectedBidder;
    private String receivedBidder;

    public void checkExpectations() throws Exception {
        if (!expectedCall.equals(receivedCall))
            throw new Exception("Expected call " + expectedCall + " but received call was " + receivedCall);

        if (expectedPriceValue != receivedPriceValue)
            throw new Exception("Expected price " + expectedPriceValue + " but received price was " + receivedPriceValue);

        if(expectedIncrement != receivedIncrement)
            throw new Exception("Expected expectedIncrement " + expectedIncrement + " but received increment was " + receivedIncrement);

        if(expectedBidder != null && !expectedBidder.equals(receivedBidder))
            throw new Exception("Expected bidder " + expectedBidder + " but received bidder was " + receivedBidder);
    }

    @Override
    public void closeAuction() {
        this.receivedCall = "CLOSE";
    }

    @Override
    public void incrementPrice(String bidder, int currentPrice, int increment) {
        this.receivedCall = "PRICE";
        this.receivedPriceValue = currentPrice;
        this.receivedIncrement = increment;
        this.receivedBidder = bidder;
    }

    @Override
    public void closeNoSaleAuction() {
        this.receivedCall = "NO_SALE";
    }

    public void expectCall(String call) {
        this.expectedCall = call;
    }

    public void expectCall(String call, int priceValue, int increment, String bidder) {
        this.expectedCall = call;
        this.expectedPriceValue = priceValue;
        this.expectedIncrement = increment;
        this.expectedBidder = bidder;
    }

}
