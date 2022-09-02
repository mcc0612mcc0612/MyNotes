public class Circle { 
    double radius,area;
    void setRadius(double r) {
        radius=r;
    } 
    double getRadius() {
        return radius;
    }
    double getArea(){
        area=3.14*radius*radius;
        return area;
    }
}
