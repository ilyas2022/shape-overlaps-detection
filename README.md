# Shape Overlaps Detection System

A Spring Boot application that detects overlaps between geometric shapes (Circles, Regular Polygons, Irregular Polygons) using computational geometry algorithms.

## 📋 Overview

This project implements collision detection algorithms to determine if different 2D shapes overlap. It includes a Spring Boot backend with shape management capabilities and a web frontend for visualization.

## ✨ Features

- **Shape Classes**: `Circle`, `RegularPolygon`, `IrregularPolygon`
- **Collision Detection**:
  - Circle-Circle overlap detection
  - Circle-Polygon overlap detection  
  - Polygon-Polygon overlap detection using Separating Axis Theorem (SAT)
- **Web Interface**: Simple frontend for shape visualization
- **REST API**: Backend endpoints for shape management

## 🛠 Technologies

- **Java 17**
- **Spring Boot 3.2.1**
- **Gradle** - Build tool
- **HTML, CSS, JavaScript** - Frontend visualization
- **org.json** - JSON processing

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Gradle (or use the included wrapper)

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone [your-repository-url]
   cd shape-overlaps-detection
   ```

2. **Run the application**
   ```bash
   # On Unix/Linux/MacOS
   ./gradlew bootRun
   
   # On Windows
   gradlew.bat bootRun
   ```

3. **Access the application**
   - Open your web browser and go to `http://localhost:8080`

## 🔬 Algorithm Implementation

### Separating Axis Theorem (SAT)
Used for polygon-polygon collision detection. The algorithm checks if two convex polygons are separated by projecting them onto various axes.

### Circle-Circle Detection
Distance-based approach comparing the distance between centers with the sum of radii.

### Circle-Polygon Detection
Combines distance calculations and geometric tests to determine overlap between circular and polygonal shapes.

## 📁 Project Structure

The project follows standard Spring Boot structure with:
- Shape model classes implementing overlap detection logic
- Spring Boot controllers for REST endpoints
- Static web resources for the visualization frontend

## 🎓 Academic Context

This project is part of a student assignment focusing on:
1. **Completed**: Implementation of `overlaps()` methods for all shape classes
2. **In Progress**: Comprehensive project documentation for online portfolio

## 📚 Future Documentation

A detailed online portfolio/report will be added with:
- Algorithm explanations and analysis
- Implementation details
- Testing strategies
- Performance considerations

---

**Note**: This is an educational project demonstrating computational geometry concepts and Spring Boot development.
