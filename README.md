[![Build Status](https://travis-ci.com/StefanCimander/transparency.svg?token=f9aSHki9zHJ2Ax9f2WQC&branch=develop)](https://travis-ci.com/StefanCimander/transparency)

# Transparency

## Project Goal

The Transparency project visualizes feature dependencies in automotive software architectures. In this context, features refer to the modeled customer functions, that are finally implemented by the vehicels E/E architecture. As the degree of cross-linking between sensors and actuators constantly increases in the field of automotive development, also the abstract models of features are depending on each other more and more.

<img src="img/bundled-edges-graph.png" alt="Bundled Edges Graph" width="400" />

The graphical representation of these feature dependencies helps to get an overview and a better understanding of the designed E/E architecture, beginning at the top level feature model. The image shows a so called bundled edges graph. The features are arranged and grouped in a circle, links in between indicate the feature dependencies.

## E/E Architecture Model

The following domain model presents the relevant subjects of the E/E architecture:

![Domain Model](doc/domain-model.png)

## Setup and Configuration 

### IntelliJ IDEA

### Database with Sample Data

The schema of the database is described by the following entity relationship diagram:

![ER Diagram](doc/entity-relationship-diagram.png)

## Build and Run
