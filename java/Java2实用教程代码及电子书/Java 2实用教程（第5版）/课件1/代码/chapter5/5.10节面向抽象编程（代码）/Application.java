public class Application{
    public static void main(String args[]){
        Pillar pillar;
        Geometry bottom =null;
        pillar =new Pillar (bottom,100);  //null�׵�����
        System.out.println("���"+pillar.getVolume());
        bottom=new Rectangle(12,22);
        pillar =new Pillar (bottom,58);  //pillar�Ǿ��о��ε׵�����
        System.out.println("���"+pillar.getVolume());
        bottom=new Circle(10);
        pillar =new Pillar (bottom,58); //pillar�Ǿ���Բ�ε׵�����
        System.out.println("���"+pillar.getVolume());
    }
} 
