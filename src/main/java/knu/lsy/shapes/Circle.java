package knu.lsy.shapes;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class Circle extends Shape {
    
    public Circle(Point center, double radius) {
        super(center, radius);
    }
    
    // TODO: 학생 과제 - 원의 겹침 감지 알고리즘 구현
    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Circle) {
            // Circle-Circle collision
            Circle otherCircle = (Circle) other;
            double distance = this.center.distanceTo(otherCircle.getCenter());
            return distance < (this.radius + otherCircle.getRadius());
        } else if (other instanceof RegularPolygon || other instanceof IrregularPolygon) {
            // Circle-Polygon collision
            List<Point> polygonVertices = other.getVertices();

            // 1. Check if any vertex of the polygon is inside the circle
            for (Point vertex : polygonVertices) {
                if (this.center.distanceTo(vertex) <= this.radius) {
                    return true;
                }
            }

            // 2. Check if the center of the circle is inside the polygon
            // (This handles cases where the circle is entirely contained within the polygon)
            if (CollisionUtils.isPointInPolygon(this.center, polygonVertices)) {
                return true;
            }

            // 3. Check if any edge of the polygon intersects the circle
            for (int i = 0; i < polygonVertices.size(); i++) {
                Point p1 = polygonVertices.get(i);
                Point p2 = polygonVertices.get((i + 1) % polygonVertices.size()); // Next vertex, wraps around
                if (CollisionUtils.lineSegmentIntersectsCircle(p1, p2, this.center, this.radius)) {
                    return true;
                }
            }
            return false;
        }
        return false; // Default for unknown shapes
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", "circle");
        json.put("id", id);
        json.put("center", center.toJSON());
        json.put("radius", radius);
        json.put("color", color);
        return json;
    }
    
    @Override
    public String getShapeType() {
        return "circle";
    }
    
    @Override
    public List<Point> getVertices() {
        // 원의 경계를 근사하는 점들 생성
        List<Point> vertices = new ArrayList<>();
        int numPoints = 32;
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            double x = center.getX() + radius * Math.cos(angle);
            double y = center.getY() + radius * Math.sin(angle);
            vertices.add(new Point(x, y));
        }
        return vertices;
    }
} 