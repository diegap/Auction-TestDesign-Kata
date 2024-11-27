package codingdojo;

public interface AuctionEventListener {
    void closeAuction();

    void incrementPrice(String bidder, int currentPrice, int increment);

    void closeNoSaleAuction();
}
