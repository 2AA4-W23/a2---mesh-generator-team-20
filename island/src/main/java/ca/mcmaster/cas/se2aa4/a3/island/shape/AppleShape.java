package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class AppleShape extends ShapeProvider{
    private final double x;
    private final double y;
    private final double radius;

    public AppleShape(double width, double height, double radius) {
        this.x = width / 2;
        this.y = height / 2;
        this.radius = radius;
    }
    public boolean contains(Coordinate coordinate) {
        double originX = coordinate.x-this.x;
        double originY = coordinate.y-this.y;

        boolean f1 = Math.pow((originX+0.4*radius),2) + Math.pow((originY),2) <= Math.pow(0.6*radius,2);
        boolean f2 = Math.pow((originX-0.4*radius),2) + Math.pow(originY,2) <= Math.pow(0.6*radius,2);
        boolean f3 = originY >= 0 && Math.pow(originX,2) + Math.pow(originY,2) <= Math.pow(radius,2);
        boolean f4 = Math.pow((originX-1.1*radius),2) + Math.pow(originY,2) >= Math.pow(0.5*radius,2);
        boolean f5 = Math.pow((originX-0.35*radius),2) + Math.pow((originY+0.8*radius),2) <= Math.pow(0.4*radius,2);
        boolean f6 = Math.pow((originX),2) + Math.pow((originY+1.1*radius),2) <= Math.pow(0.4*radius,2);

//        boolean f1 = (originX)<=-16.85 && (originY)<=32.13 && originY >= 4.8*Math.pow((originX+20.3),2) + 0.1;
//        boolean f2 = -16.85<=originX && originX<=16.8 && (originY)<=29.92 && originY >= (1/3)*Math.cos(originX)+1;
//        boolean f3 = originX>=16.8 && originY<=29.92 && originY>=4.8*Math.pow((originX-20.3),2)+0.1;
//        boolean f4 = originX>=7 && originY>=29.92 && originY<=75.37 && Math.pow((originX-55.8),2)+Math.pow((originY-53.4),2)>=66.3;
//        boolean f5 = originX>=7 && originY>=75.37 && Math.pow((originX-19.5),2)+Math.pow((originY-55.6),2)<=91.4;
//        boolean f6 = originX>=-14.839 && originX<=7 && originY<=6*Math.sin(originX-14)+87;
//        boolean f7 = originX<=-14.839 && originY>=61.77 && Math.pow((originX+19.5),2)+Math.pow((originY-55.6),2)<=91.4;
//        boolean f8 = originX<=-16.85 && originY<=61.77 && originY>=32.13 && Math.pow((originX-1.6),2)+Math.pow((originY-51.4),2)<=266.8;
//        boolean f9 = originX>=-16.85 && originX<=16.8 && originY>=29.92 && originY<=61.77;
//        boolean f10 = Math.pow((originX-23.1),2)+Math.pow((originY-87.7),2)<=63.5 && Math.pow((originX+2.3),2)+Math.pow((originY-110.2),2)<=55.8;
//        return f1||f2||f3||f4||f5||f6||f7||f8||f9||f10;
        return ((f1 || f2 || f3) && f4) || (f5 && f6) ;
    }
}
