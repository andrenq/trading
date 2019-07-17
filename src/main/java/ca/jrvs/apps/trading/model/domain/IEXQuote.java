
package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "avgTotalVolume",
        "companyName",
        "iexRealtimePrice",
        "delayedPrice",
        "iexMarketPercent",
        "calculationPrice",
        "extendedChangePercent",
        "latestSource",
        "latestUpdate",
        "iexBidPrice",
        "previousClose",
        "high",
        "peRatio",
        "low",
        "delayedPriceTime",
        "extendedPrice",
        "extendedPriceTime",
        "week52Low",
        "closeTime",
        "changePercent",
        "week52High",
        "openTime",
        "close",
        "latestPrice",
        "marketCap",
        "iexRealtimeSize",
        "iexLastUpdated",
        "change",
        "latestVolume",
        "iexAskPrice",
        "ytdChange",
        "iexVolume",
        "iexAskSize",
        "lastTradeTime",
        "extendedChange",
        "latestTime",
        "open",
        "iexBidSize"
})
public class IEXQuote {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("avgTotalVolume")
    private Double avgTotalVolume;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("delayedPrice")
    private Double delayedPrice;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("extendedChangePercent")
    private Double extendedChangePercent;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestUpdate")
    private Double latestUpdate;
    @JsonProperty("iexBidPrice")
    private Double iexBidPrice;
    @JsonProperty("previousClose")
    private Double previousClose;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("delayedPriceTime")
    private Double delayedPriceTime;
    @JsonProperty("extendedPrice")
    private Double extendedPrice;
    @JsonProperty("extendedPriceTime")
    private Double extendedPriceTime;
    @JsonProperty("week52Low")
    private Double week52Low;
    @JsonProperty("closeTime")
    private Double closeTime;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("week52High")
    private Double week52High;
    @JsonProperty("openTime")
    private Double openTime;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("marketCap")
    private Double marketCap;
    @JsonProperty("iexRealtimeSize")
    private Double iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private Double iexLastUpdated;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("latestVolume")
    private Double latestVolume;
    @JsonProperty("iexAskPrice")
    private Double iexAskPrice;
    @JsonProperty("ytdChange")
    private Double ytdChange;
    @JsonProperty("iexVolume")
    private Double iexVolume;
    @JsonProperty("iexAskSize")
    private Double iexAskSize;
    @JsonProperty("lastTradeTime")
    private Double lastTradeTime;
    @JsonProperty("extendedChange")
    private Double extendedChange;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("open")
    private Double open;
    @JsonProperty("iexBidSize")
    private Double iexBidSize;

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("avgTotalVolume")
    public Double getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Double avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("delayedPrice")
    public Double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("extendedChangePercent")
    public Double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestUpdate")
    public Double getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Double latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("iexBidPrice")
    public Double getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Double iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("previousClose")
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("delayedPriceTime")
    public Double getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Double delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedPrice")
    public Double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedPriceTime")
    public Double getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Double extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("week52Low")
    public Double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("closeTime")
    public Double getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Double closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("openTime")
    public Double getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Double openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("marketCap")
    public Double getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("iexRealtimeSize")
    public Double getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Double iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexLastUpdated")
    public Double getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Double iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("latestVolume")
    public Double getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Double latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("iexAskPrice")
    public Double getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Double iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("iexVolume")
    public Double getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Double iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("iexAskSize")
    public Double getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Double iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("lastTradeTime")
    public Double getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Double lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("extendedChange")
    public Double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("open")
    public Double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Double open) {
        this.open = open;
    }

    @JsonProperty("iexBidSize")
    public Double getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Double iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "symbol='" + symbol + '\'' +
                ", avgTotalVolume=" + avgTotalVolume +
                ", companyName='" + companyName + '\'' +
                ", iexRealtimePrice=" + iexRealtimePrice +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}