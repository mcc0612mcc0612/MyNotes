public class Example4_8 {
   public static void main(String args[]) {
       Circle circle = new Circle();            //������1��            
       circle.setRadius(10);                    //������2��
       Circular circular = new Circular();      //������3��
       System.out.println("circle������:"+circle);
       System.out.println("Բ׶��bottom������:"+circular.bottom); 
       circular.setHeight(5);             
       circular.setBottom(circle);              //������4��
       System.out.println("circle������:"+circle);
       System.out.println("Բ׶��bottom������:"+circular.bottom); 
       System.out.println("Բ׶�����:"+circular.getVolme());
       System.out.println("�޸�circle�İ뾶��bottom�İ뾶ͬ���仯");
       circle.setRadius(20);                      //������5��
       System.out.println("bottom�İ뾶:"+circular.getBottomRadius());
       System.out.println("���´���circle,cirlce�����ý������仯");
       circle = new Circle(); //���´���circle ������6��
       System.out.println("circle������:"+circle); 
       System.out.println("���ǲ�Ӱ��circular��bottom������");
       System.out.println("Բ׶��bottom������:"+circular.bottom); 
   }
}
