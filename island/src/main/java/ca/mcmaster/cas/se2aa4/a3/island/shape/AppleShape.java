package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class AppleShape{
    private final double x;
    private final double y;

    public AppleShape(double width, double height) {
        this.x = width / 2;
        this.y = height / 2;
    }
    public boolean contains(Coordinate coordinate) {
        double originX = coordinate.x-this.x;
        double originY = coordinate.y-this.y;

        boolean f1 = (originX)<=-1.685 && (originY)<=3.213 && originY >= 0.48*Math.pow((originX+2.03),2) + 0.01;
        boolean f2 = -1.685<=originX && originX<=1.68 && (originY)<=2.992 && originY >= (1/3)*Math.cos(originX)+0.1;
        boolean f3 = originX>=1.68 && originY<=2.992 && originY>=0.48*Math.pow((originX-2.03),2)+0.01;
        boolean f4 = originX>=0.7 && originY>=2.992 && originY<=7.537 && Math.pow((originX-5.58),2)+Math.pow((originY-5.34),2)>=6.63;
        boolean f5 = originX>=0.7 && originY>=7.537 && Math.pow((originX-1.95),2)+Math.pow((originY-5.56),2)<=9.14;
        boolean f6 = originX>=-1.4839 && originX<=0.7 && originY<=0.6*Math.sin(originX-1.4)+8.7;
        boolean f7 = originX<=-1.4839 && originY>=6.177 && Math.pow((originX+1.95),2)+Math.pow((originY-5.56),2)<=9.14;
        boolean f8 = originX<=-1.685 && originY<=6.177 && originY>=3.213 && Math.pow((originX-0.16),2)+Math.pow((originY-5.14),2)<=26.68;
        boolean f9 = originX>=-1.685 && originX<=1.68 && originY>=2.992 && originY<=6.177;
        boolean f10 = Math.pow((originX-2.31),2)+Math.pow((originY-8.77),2)<=6.35 && Math.pow((originX+0.23),2)+Math.pow((originY-11.02),2)<=5.58;

        return f1||f2||f3||f4||f5||f6||f7||f8||f9||f10;
    }
}
