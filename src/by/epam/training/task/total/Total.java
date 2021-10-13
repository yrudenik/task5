package by.epam.training.task.total;

public class Total implements BaseTotal {

    private double sumPrice;
    private int sumWeight;

    public double getSumPrice() {
        return sumPrice;
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

    public Total(double sumPrice, int sumWeight) {
        this.sumPrice = sumPrice;
        this.sumWeight = sumWeight;
    }

    @Override
    public void calculate() {

    }
}
