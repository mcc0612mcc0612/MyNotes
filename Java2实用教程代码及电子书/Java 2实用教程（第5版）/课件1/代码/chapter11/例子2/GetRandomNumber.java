import java.util.*;
public class GetRandomNumber {
   public static int [] getRandomNumber(int max,int amount) {
      //1-max之间的amount个不同随机整数
     int [] randomNumber = new int[amount];
     int index =0;
     randomNumber[0]= -1;
     Random random = new Random();
     while(index<amount){
       int number = random.nextInt(max)+1;
       boolean isInArrays=false;
       for(int m:randomNumber){//m依次取数组randomNumber元素的值（见3.7）
          if(m == number)
            isInArrays=true;  //number在数组里了
       }
       if(isInArrays==false){
           //如果number不在数组randomNumber中:
           randomNumber[index] = number;
           index++;   
       }
     }
     return  randomNumber;
   }
}