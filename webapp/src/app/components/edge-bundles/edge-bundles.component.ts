import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnChanges,
  ViewChild
} from '@angular/core';

import * as d3 from 'd3';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-edge-bundles',
  templateUrl: './edge-bundles.component.html',
  styleUrls: ['./edge-bundles.component.css'],
})
export class EdgeBundlesComponent implements AfterViewInit, OnChanges {
  @Input() hierarchy: HierarchyElement;

  @Input() diameter = 800;
  @Input() hierarchyWidth = 200;

  constructor(private element: ElementRef) { }

  private get radius() {
    return this.diameter / 2;
  }

  private get innerRadius() {
    return this.radius - this.hierarchyWidth;
  }

  private htmlElement: HTMLElement;
  private host;
  private svg;
  private cluster;
  private line;
  private nodes;
  private links;

  ngAfterViewInit() {
    this.redrawDependencyGraph();
  }

  ngOnChanges() {
    this.redrawDependencyGraph();
  }

  private redrawDependencyGraph() {
    this.htmlElement = this.element.nativeElement;
    this.host = d3.select(this.htmlElement);
    this.setup();
    this.buildSVG();
    this.populate();
  }

  private setup(): void {
    this.cluster = d3.cluster()
      .size([360, this.innerRadius]);

    this.line = d3.radialLine()
      .curve(d3.curveBundle.beta(0.95))
      .radius(d => d.y)
      .angle(d => d.x / 180 * Math.PI);
  }

  private buildSVG(): void {
    this.host.html('');
    this.svg = this.host.append('svg')
      .attr('width', this.diameter)
      .attr('height', this.diameter)
      .append('g')
      .attr('transform', 'translate(' + this.radius + ', ' + this.radius + ')');

    this.links = this.svg.append('g').selectAll('.link');
    this.nodes = this.svg.append('g').selectAll('.node');
  }

  private populate(): void {
    const root = d3.hierarchy(this.hierarchy, d => d.children);
    this.cluster(root);

    this.links = this.links
      .data(this.featureDependencies(root.leaves()))
      .enter().append('path')
      .each(d => { return d.source = d.path[0] })
      .each(d => { return d.target = d.path[d.path.length - 1]; })
      .each(d => { return d.isImplicit = d.type == 'LOGICAL_DEPENDENCY'})
      .attr('class', 'link')
      .attr('d', d => this.line(d.path));

    this.nodes = this.nodes
      .data(root.leaves())
      .enter().append('circle')
      .attr('r', '4')
      .attr('class', 'node')
      .attr('transform', d =>
        'rotate(' + (d.x - 90) + ')translate(' + (d.y + 8) + ')' + (d.x < 180 ? '' : 'rotate(180)'))
      .on('mouseover', d => this.mouseOver(d))
      .on('mouseout', d => this.mouseOuted())
  }

  private mouseOver(d: any) {
    this.nodes.each(node => node.isTarget = node.isSource = false);

    this.links
      .classed('link--target', link => { if (link.target === d) { return link.source.isSource = true; }})
      .classed('link--source', link => { if (link.source === d) { return link.target.isTarget = true; }})
      .classed('link--implicit', link => { if (link.source === d && link.isImplicit) { return link.target.isImplicit = true; }})
      .filter(link => link.target === d || link.source === d)
      .raise();

    this.nodes
      .classed('node--target', node => node.isTarget)
      .classed('node--source', node => node.isSource)
      .classed('node--implicit', node => node.isImplicit);
  }

  private mouseOuted() {
    this.links
      .classed('link--target', false)
      .classed('link--source', false);

    this.nodes
      .classed('node--target', false)
      .classed('node--source', false);
  }

  private featureDependencies(nodes: any[]) {
    const dependencies: { path: Path2D, type: string }[] = [];
    const namedNodes: Map<string, any> = new Map;
    nodes.forEach(node => namedNodes.set(node.data.name, node));
    nodes.forEach(node => {
      if (node.data.dependencies) {
        node.data.dependencies.forEach(dependency => {
          if (namedNodes.has(dependency.name)) {
            const path = namedNodes.get(node.data.name).path(namedNodes.get(dependency.name));
            dependencies.push({ path: path, type: dependency.type });
          }
        });
      }
    });
    return dependencies;
  }
}
