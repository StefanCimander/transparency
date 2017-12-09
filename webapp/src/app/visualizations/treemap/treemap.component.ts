import { Component, ElementRef, Input, OnChanges } from '@angular/core';

import * as d3 from 'd3';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-treemap',
  templateUrl: './treemap.teamplate.html',
  styleUrls: ['./treemap.style.css']
})
export class TreemapComponent implements OnChanges {

  @Input() hierarchy: HierarchyElement;
  @Input() width = 380;
  @Input() height = 380;
  @Input() showNames = false;
  @Input() direction = 'Incoming';
  @Input() threshold = 70;

  private htmlElement: HTMLElement;
  private host;
  private svg;
  private treemap;
  private incomingDependencies = {};

  constructor(private element: ElementRef) { }

  ngOnChanges() {
    this.redrawTreemap();
  }

  private redrawTreemap() {
    this.htmlElement = this.element.nativeElement;
    this.host = d3.select(this.htmlElement);

    this.incomingDependencies = {};
    this.calculateIncomingDependencies(this.hierarchy);
    this.buildSvg();
    this.populate();
  }

  private calculateIncomingDependencies(hierarchyElement: HierarchyElement) {
    hierarchyElement.dependencies.forEach(dep => {
      if (this.incomingDependencies[dep.name]) {
        this.incomingDependencies[dep.name] += 1;
      } else {
        this.incomingDependencies[dep.name] = 1;
      }
    });
    hierarchyElement.children.forEach(child => this.calculateIncomingDependencies(child));
  }

  private buildSvg() {
    this.host.html('');

    this.svg = this.host.append('svg')
      .attr('width', this.width)
      .attr('height', this.height)
      .attr('class', 'treemap')
      .append('g');

    this.treemap = d3.treemap()
      .size([this.width, this.height])
      .round(true)
      .paddingOuter(5)
      .paddingInner(1);
  }

  private dependencyCount(hierarchyElement: HierarchyElement): number {
    if (this.direction === 'Incoming') {
      return this.incomingDependencies[hierarchyElement.name];
    }
    return hierarchyElement.dependencies.length
  }

  private populate() {
    const root = d3.hierarchy(this.hierarchy)
      .eachBefore(d => { d.data.id = (d.parent ? d.parent.data.id + "." : "") + d.data.name })
      .sum(d => this.dependencyCount(d))
      .sort((a, b) =>  b.value - a.value);

    this.treemap(root);

    const cell = this.svg.selectAll("g")
      .data(root.leaves())
      .enter().append("g")
      .attr('transform', d => `translate(${d.x0}, ${d.y0})`);


    cell.append("rect")
      .attr('id', d => d.data.id)
      .attr('width', d => d.x1 - d.x0)
      .attr('height', d => d.y1 - d.y0)
      .attr('fill', d => {
        if (d.parent) {
          return this.dependencyCount(d.data) > this.threshold ? '#ff5858' : 'steelblue';
        }
        return 'white';
      });

    cell.append("clipPath")
      .attr('id', d => `clip-${d.data.id}`)
      .append('use')
      .attr('xlink:href', d => `#${d.data.id}`);

    if (this.showNames) {
      cell.append("text")
        .attr("clip-path", d => `url(#clip-${d.data.id})`)
        .selectAll('tspan')
        .data(d => d.data.name.split(/(?=[A-Z][^A-Z])/g))
        .enter().append('tspan')
        .attr("x", 4)
        .attr("y", (d, i) => 13 + i * 10)
        .attr('fill', 'white')
        .text(d => d);
    }

    cell.append("title")
      .text(d => d.data.id + "\n" +  d3.format(",d")(d.value) + " dependencies");
  }
}
