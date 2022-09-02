import java.util.Random;
public class RandomRedEnvelope extends RedEnvelope { //随机红包
    double minMoney;       //可以抢到的最小金额
    int integerRemainMoney;   //红包中的钱按分表示
    int randomMoney;          //给抢用户的钱
    Random random;
    RandomRedEnvelope(double remainMoney,int remainPeople) {
       random = new Random();
       minMoney = 0.01;  //minMoney的值是0.01，保证用户至少能抢到0.01圆
       this.remainMoney =  remainMoney; 
       this.remainPeople = remainPeople; 
       integerRemainMoney =(int)(remainMoney*100); //把钱按分表示
       if(integerRemainMoney<remainPeople*(int)(minMoney*100)){
          integerRemainMoney = remainPeople*(int)(minMoney*100);
          this.remainMoney =(double)integerRemainMoney;
       }
    }
    public double giveMoney() {
      if(remainPeople<=0) {
         return 0;
      }
      if(remainPeople ==1) {
         money = remainMoney;
         remainPeople--;
         return money;
      }
      randomMoney = random.nextInt(integerRemainMoney);
      //该金额randomMoney在[0,integerRemainMoney) 区间内
      if(randomMoney<(int)(minMoney*100)) {
         randomMoney = (int)(minMoney*100);      //保证用户至少能抢到1分
      }
      int leftOtherPeopleMoney =integerRemainMoney-randomMoney;
      //leftOtherPeopleMoney是当前用户留给其余人的钱(单位是分)
      int otherPeopleNeedMoney = (remainPeople-1)*(int)(minMoney*100); 
      //otherPeopleNeedMoney是保证其他人还能继续抢的最少金额(单位是分)
      if(leftOtherPeopleMoney<otherPeopleNeedMoney) {
        randomMoney -=(otherPeopleNeedMoney-leftOtherPeopleMoney);
      }
      integerRemainMoney = integerRemainMoney - randomMoney;
      remainMoney = (double)(integerRemainMoney/100.0); //钱的单位转成圆
      remainPeople--;
      money = (double)(randomMoney/100.0);
      return money;   //返回用户抢到的钱（单位是圆）
   }
}