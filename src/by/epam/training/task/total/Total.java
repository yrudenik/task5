package by.epam.training.task.total;

public class Total implements TotalInterface {

    private double sumPrice;
    private String currencyOfSumPrice;
    private int sumWeight;
    private String measureOfSumWeight;

    public double getSumPrice() {
        return sumPrice;
    }

    public String getCurrencyOfSumPrice() {
        return currencyOfSumPrice;
    }

    public void setCurrencyOfSumPrice(String currencyOfSumPrice) {
        this.currencyOfSumPrice = currencyOfSumPrice;
    }

    public String getMeasureOfSumWeight() {
        return measureOfSumWeight;
    }

    public void setMeasureOfSumWeight(String measureOfSumWeight) {
        this.measureOfSumWeight = measureOfSumWeight;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(int sumWeight) {
        this.sumWeight = sumWeight;
    }

//    public Total(double sumPrice, int sumWeight) {
//        this.sumPrice = sumPrice;
//        this.sumWeight = sumWeight;
//    }

    @Override
    public void calculate() {

    }
}
