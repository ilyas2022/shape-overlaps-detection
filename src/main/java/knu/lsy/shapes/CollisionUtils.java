package knu.lsy.shapes;

import java.util.ArrayList;
import java.util.List;

public class CollisionUtils {

    // Helper class for Projection
    public static class Projection {
        double min, max;
        public Projection(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public boolean overlaps(Projection other) {
            // Overlap if they are not separated
            return this.max >= other.min && this.min <= other.max;
        }
    }

    // Polygon-Polygon collision using SAT
    public static boolean checkCollisionSAT(Shape poly1, Shape poly2) {
        List<Point> vertices1 = poly1.getVertices();
        List<Point> vertices2 = poly2.getVertices();

        if (vertices1 == null || vertices1.size() < 3 || vertices2 == null || vertices2.size() < 3) {
            return false; // Not valid polygons
        }

        List<Point> axes = new ArrayList<>();
        axes.addAll(getAxes(vertices1));
        axes.addAll(getAxes(vertices2));

        for (Point axis : axes) {
            Projection p1 = project(vertices1, axis);
            Projection p2 = project(vertices2, axis);

            if (!p1.overlaps(p2)) {
                return false; // Found a separating axis
            }
        }
        return true; // No separating axis found, polygons overlap
    }

    // Helper to get perpendicular axes for SAT (normals to edges)
    private static List<Point> getAxes(List<Point> vertices) {
        List<Point> axes = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Point p1 = vertices.get(i);
            Point p2 = vertices.get((i + 1) % vertices.size()); // Next vertex, wraps around

            double edgeX = p2.getX() - p1.getX();
            double edgeY = p2.getY() - p1.getY();

            // Perpendicular vector (normal) is (-edgeY, edgeX)
            axes.add(new Point(-edgeY, edgeX));
        }
        return axes;
    }

    // Helper to project polygon vertices onto an axis
    private static Projection project(List<Point> vertices, Point axis) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        
        // For dot product, axis doesn't strictly need to be unit, but results are scaled.
        // For consistency, it's fine as long as all projections use the same (scaled) axis.
        double axisX = axis.getX();
        double axisY = axis.getY();

        for (Point vertex : vertices) {
            // Dot product: (vertex.x * axis.x) + (vertex.y * axis.y)
            double projection = vertex.getX() * axisX + vertex.getY() * axisY;
            if (projection < min) {
                min = projection;
            }
            if (projection > max) {
                max = projection;
            }
        }
        return new Projection(min, max);
    }

    // Helper: Point in Polygon (Ray Casting algorithm)
    public static boolean isPointInPolygon(Point point, List<Point> vertices) {
        int n = vertices.size();
        if (n < 3) return false;

        boolean inside = false;
        double px = point.getX();
        double py = point.getY();

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Point vi = vertices.get(i);
            Point vj = vertices.get(j);
            
            double vix = vi.getX();
            double viy = vi.getY();
            double vjx = vj.getX();
            double vjy = vj.getY();

            // Check if the ray crosses the edge (vi, vj)
            // Condition 1: Is the point's y-coordinate between the edge's y-coordinates?
            // Condition 2: Is the point to the left of the edge?
            if (((viy > py) != (vjy > py)) &&
                (px < (vjx - vix) * (py - viy) / (vjy - viy) + vix)) {
                inside = !inside;
            }
        }
        return inside;
    }

    // Helper: Line Segment intersects Circle
    public static boolean lineSegmentIntersectsCircle(Point p1, Point p2, Point circleCenter, double radius) {
        double cx = circleCenter.getX();
        double cy = circleCenter.getY();

        double p1x = p1.getX();
        double p1y = p1.getY();
        double p2x = p2.getX();
        double p2y = p2.getY();

        // Vector from p1 to p2
        double dx = p2x - p1x;
        double dy = p2y - p1y;

        // Length squared of the segment p1p2
        double lenSq = dx * dx + dy * dy;

        // If the segment is just a point
        if (lenSq == 0.0) {
            return circleCenter.distanceTo(p1) <= radius;
        }

        // Parameter t for the projection of circleCenter onto the line defined by p1p2
        // t = dot((circleCenter - p1), (p2 - p1)) / |p2 - p1|^2
        double t = ((cx - p1x) * dx + (cy - p1y) * dy) / lenSq;

        Point closestPointOnLine;
        if (t < 0.0) {
            // Closest point on the line is p1 (beyond p1 end)
            closestPointOnLine = p1;
        } else if (t > 1.0) {
            // Closest point on the line is p2 (beyond p2 end)
            closestPointOnLine = p2;
        } else {
            // Projection is on the segment
            closestPointOnLine = new Point(p1x + t * dx, p1y + t * dy);
        }
        
        // Check if the distance from the circle center to this closest point is within the radius
        return circleCenter.distanceTo(closestPointOnLine) <= radius;
    }
} 