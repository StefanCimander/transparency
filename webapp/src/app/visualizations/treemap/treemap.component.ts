import { Component, ElementRef, Input, OnChanges } from '@angular/core';

import * as d3 from 'd3';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-treemap',
  templateUrl: './treemap.component.html',
  styleUrls: ['./treemap.component.css']
})
export class TreemapComponent implements OnChanges {

  @Input() hierarchy: HierarchyElement
  @Input() width = 380;
  @Input() height = 380;
  @Input() showNames = false;

  private htmlElement = this.element.nativeElement;
  private host = d3.select(this.htmlElement);
  private svg = this.host.append('svg')
    .attr('width', this.width)
    .attr('height', this.height)
    .attr('class', 'treemap')
    .append('g');

  private format = d3.format(",d");

  private treemap = d3.treemap()
    .size([this.width, this.height])
    .round(true)
    .paddingOuter(5)
    .paddingInner(1);

  constructor(private element: ElementRef) { }

  ngOnChanges() {
    this.redrawTreemap();
  }

  private redrawTreemap() {
    const root = d3.hierarchy(this.hierarchy)
      .eachBefore(d => { d.data.id = (d.parent ? d.parent.data.id + "." : "") + d.data.name })
      .sum(d => d.dependencies.length)
      .sort((a, b) =>  b.value - a.value); // b.height - a.height ||

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
          return d.value > 60 ? '#ff5858' : 'steelblue';
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
      .text(d => d.data.id + "\n" + this.format(d.value) + " dependencies");
  }
}
