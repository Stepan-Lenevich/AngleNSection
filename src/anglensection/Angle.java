package anglensection;

public class Angle { //in rads (-pi; pi)

    double rad = 180 / Math.PI;
    private double ab;
    private double ac;
    private double baseAngle;
    private double bac;

    public Angle(double[] x, double[] y) {
        ab = Math.atan2((y[0]-y[1]),(x[0]-x[1]));
        ac = Math.atan2((y[2]-y[1]),(x[2]-x[1])); 
        getBACandCWSide();
    }

    public Angle(double ab, double ac) {
        this.ab = ab;
        this.ac = ac;
        getBACandCWSide();
    }

    private void getBACandCWSide() {

        double deltaTmp = ((ab - ac) * rad + 360) % 360;

        if (deltaTmp > 180) { //abc is CCW in triangle

            baseAngle = ab;
            double tmpBACinDegrees = (ac - ab) * rad;
            if (tmpBACinDegrees < 0) {
                tmpBACinDegrees += 360;
            }
            bac = tmpBACinDegrees / rad;

        } else {
            baseAngle = ac;
            double tmpBACinDegrees = (ab - ac) * rad;
            if (tmpBACinDegrees < 0) {
                tmpBACinDegrees += 360;
            }
            bac = tmpBACinDegrees / rad;
        }
    }

    public double getNsectrissa(double fraction) {

        return ((baseAngle + bac * fraction + 180) % 360 - 180);
    }

}
