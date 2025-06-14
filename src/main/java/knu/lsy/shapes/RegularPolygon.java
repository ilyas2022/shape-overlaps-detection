package knu.lsy.shapes;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class RegularPolygon extends Shape {
    private int sides;
    private double rotationAngle;
    private List<Point> vertices;
    
    public RegularPolygon(Point center, double radius, int sides, double rotationAngle) {
        super(center, radius);
        this.sides = sides;
        this.rotationAngle = rotationAngle;
        this.vertices = generateVertices();
    }
    
    private List<Point> generateVertices() {
        List<Point> points = new ArrayList<>();
        double angleStep = 2 * Math.PI / sides;
        
        for (int i = 0; i < sides; i++) {
            double angle = angleStep * i + rotationAngle;
            double x = center.getX() + radius * Math.cos(angle);
            double y = center.getY() + radius * Math.sin(angle);
            points.add(new Point(x, y));
        }
        
        return points;
    }
    
    // TODO: 학생 과제 - 정다각형의 겹침 감지 알고리즘 구현
    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Circle) {
            // Polygon-Circle collision: delegate to Circle's overlap method
            return other.overlaps(this);
        } else if (other instanceof RegularPolygon || other instanceof IrregularPolygon) {
            // Polygon-Polygon collision: use SAT
            return CollisionUtils.checkCollisionSAT(this, other);
        }
        return false; // Default for unknown shapes
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", "regularPolygon");
        json.put("id", id);
        json.put("center", center.toJSON());
        json.put("radius", radius);
        json.put("sides", sides);
        json.put("rotationAngle", rotationAngle);
        json.put("color", color);
        
        JSONArray verticesArray = new JSONArray();
        for (Point vertex : vertices) {
            verticesArray.put(vertex.toJSON());
        }
        json.put("vertices", verticesArray);
        
        return json;
    }
    
    @Override
    public String getShapeType() {
        return "regularPolygon";
    }
    
    @Override
    public List<Point> getVertices() {
        return new ArrayList<>(vertices);
    }
} 