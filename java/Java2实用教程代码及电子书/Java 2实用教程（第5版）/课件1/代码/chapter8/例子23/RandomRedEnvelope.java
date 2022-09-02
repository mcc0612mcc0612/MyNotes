import java.util.Random;
public class RandomRedEnvelope extends RedEnvelope { //������
    double minMoney;       //������������С���
    int integerRemainMoney;   //����е�Ǯ���ֱ�ʾ
    int randomMoney;          //�����û���Ǯ
    Random random;
    RandomRedEnvelope(double remainMoney,int remainPeople) {
       random = new Random();
       minMoney = 0.01;  //minMoney��ֵ��0.01����֤�û�����������0.01Բ
       this.remainMoney =  remainMoney; 
       this.remainPeople = remainPeople; 
       integerRemainMoney =(int)(remainMoney*100); //��Ǯ���ֱ�ʾ
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
      //�ý��randomMoney��[0,integerRemainMoney) ������
      if(randomMoney<(int)(minMoney*100)) {
         randomMoney = (int)(minMoney*100);      //��֤�û�����������1��
      }
      int leftOtherPeopleMoney =integerRemainMoney-randomMoney;
      //leftOtherPeopleMoney�ǵ�ǰ�û����������˵�Ǯ(��λ�Ƿ�)
      int otherPeopleNeedMoney = (remainPeople-1)*(int)(minMoney*100); 
      //otherPeopleNeedMoney�Ǳ�֤�����˻��ܼ����������ٽ��(��λ�Ƿ�)
      if(leftOtherPeopleMoney<otherPeopleNeedMoney) {
        randomMoney -=(otherPeopleNeedMoney-leftOtherPeopleMoney);
      }
      integerRemainMoney = integerRemainMoney - randomMoney;
      remainMoney = (double)(integerRemainMoney/100.0); //Ǯ�ĵ�λת��Բ
      remainPeople--;
      money = (double)(randomMoney/100.0);
      return money;   //�����û�������Ǯ����λ��Բ��
   }
}