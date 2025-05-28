# Shape Overlaps Detection System

A Spring Boot project to detect overlaps between various geometric shapes (Circles, Regular Polygons, Irregular Polygons).

## Project Overview

This project implements algorithms to determine if different shapes overlap in a 2D space. It includes:
- Shape classes: `Circle`, `RegularPolygon`, `IrregularPolygon`.
- Collision detection logic: 
    - Circle-Circle
    - Circle-Polygon
    - Polygon-Polygon (using Separating Axis Theorem - SAT)
- A Spring Boot backend to manage shapes and a simple web frontend for visualization.

## Core Technologies

- Java 17
- Spring Boot 3.2.1
- Gradle
- HTML, CSS, JavaScript (for frontend visualization)
- `org.json` for JSON processing

## How to Run

1. Clone the repository.
2. Ensure you have Java 17 and Gradle installed.
3. Navigate to the project root directory.
4. Run the application using Gradle: `./gradlew bootRun` (or `gradlew.bat bootRun` on Windows).
5. Open your web browser and go to `http://localhost:8080`.

## Student Assignment

This project is part of a student assignment that involves:
1. Implementing the `overlaps()` methods for the shape classes (Completed).
2. Documenting the project comprehensively as an online portfolio.

## Detailed Report / Portfolio

[Link to your detailed online portfolio (e.g., GitHub Pages, Notion) - To be added by student] 