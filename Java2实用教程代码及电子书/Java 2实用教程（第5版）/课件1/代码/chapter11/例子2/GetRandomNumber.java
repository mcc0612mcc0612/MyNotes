import java.util.*;
public class GetRandomNumber {
   public static int [] getRandomNumber(int max,int amount) {
      //1-max֮���amount����ͬ�������
     int [] randomNumber = new int[amount];
     int index =0;
     randomNumber[0]= -1;
     Random random = new Random();
     while(index<amount){
       int number = random.nextInt(max)+1;
       boolean isInArrays=false;
       for(int m:randomNumber){//m����ȡ����randomNumberԪ�ص�ֵ����3.7��
          if(m == number)
            isInArrays=true;  //number����������
       }
       if(isInArrays==false){
           //���number��������randomNumber��:
           randomNumber[index] = number;
           index++;   
       }
     }
     return  randomNumber;
   }
}