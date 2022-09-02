public abstract class RedEnvelope {
    public double remainMoney;    //红包当前金额
    public int remainPeople;      //当前参与抢红包的人数   
    public double money ;         //当前用户抢到的金额
    public abstract double giveMoney(); //抽象方法，具体怎么抢红包由子类完成
}

