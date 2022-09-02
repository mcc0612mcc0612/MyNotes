abstract class MotorVehicles {
   abstract void brake();
}
interface MoneyFare {
   void charge();
}
interface ControlTemperature {
   void controlAirTemperature();
}
class Bus extends MotorVehicles implements MoneyFare { 
    void brake() {
        System.out.println("��������ʹ���ʽɲ������");
    }
    public void charge() {
        System.out.println("��������:һԪ/��,�����㹫����");
    }
} 
class Taxi extends MotorVehicles implements MoneyFare,ControlTemperature { 
    void brake() {
        System.out.println("���⳵ʹ����ʽɲ������");
    }
    public  void charge() {
        System.out.println("���⳵:2Ԫ/����,���3����");
    }
    public void  controlAirTemperature() { 
        System.out.println("���⳵��װ��Hair�յ�");
    }
}
class Cinema implements MoneyFare,ControlTemperature {
    public  void charge() {
        System.out.println("��ӰԺ:��Ʊ,ʮԪ/��");
    }
    public void controlAirTemperature() { 
       System.out.println("��ӰԺ��װ������յ�");
    }
}
public class Example6_3 {
   public static void main(String args[]) {
       Bus bus101 = new Bus();
       Taxi buleTaxi = new Taxi();
       Cinema redStarCinema = new Cinema();
       MoneyFare  fare;
       ControlTemperature temperature;
       fare = bus101;
       bus101.brake(); 
       fare.charge();
       fare = buleTaxi;
       temperature = buleTaxi;
       buleTaxi.brake();
       fare.charge();
       temperature.controlAirTemperature();
       fare = redStarCinema;
       temperature = redStarCinema;
       fare.charge();
       temperature.controlAirTemperature();
   }
}
