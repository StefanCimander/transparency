import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnChanges,
} from '@angular/core';

import * as d3 from 'd3';

import { HierarchyElement } from "../../models";

@Component({
  selector: 'app-edge-bundles',
  templateUrl: './edge-bundles.template.html',
  styleUrls: ['./edge-bundles.style.css'],
})
export class EdgeBundlesComponent implements AfterViewInit, OnChanges {
  @Input() hierarchy: HierarchyElement;
  @Input() diameter = 800;
  @Input() hierarchyWidth = 200;
  @Input() showFeatureNames = false;
  @Input() beta = 0.7;

  private selectedFeature: HierarchyElement;

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
  private arcs;

  private partition = d3.partition()
    .size([2 * Math.PI, this.radius]);

  private arc = d3.arc()
    .startAngle(function (d) { return d.x0})
    .endAngle(function (d) { return d.x1})
    .innerRadius(function (d) { return d.y0})
    .outerRadius(function (d) { return d.y1});


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
      .curve(d3.curveBundle.beta(this.beta))
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
    this.arcs = this.svg.append('g').selectAll('.arc');
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
      .filter( d => {
        if (this.selectedFeature == null) {
          return true;
        } else {
          return this.selectedFeature.id == d.target.data.id
            || this.selectedFeature.id == d.source.data.id;
        }
      })
      .attr('class', 'link')
      .attr('d', d => this.line(d.path))
      .classed('link--implicit', link => link.isImplicit);

    if (this.showFeatureNames) {
      this.nodes = this.nodes
        .data(root.leaves())
        .enter().append('text')
        .attr('class', 'node')
        .attr('dy', '0.31em')
        .attr('transform', d =>
          'rotate(' + (d.x - 90) + ')translate(' + (d.y + 16) + ')' + (d.x < 180 ? '' : 'rotate(180)'))
        .attr('text-anchor', d => d.x < 180 ? 'start' : 'end')
        .on('mouseover', d => this.mouseOver(d))
        .on('mouseout', d => this.mouseOuted())
        .on('click', d => this.mouseClicked(d))
        .text(d => d.data.name);
    } else {
      this.nodes = this.nodes
        .data(root.leaves())
        .enter().append('circle')
        .attr('r', '4')
        .attr('class', 'node')
        .attr('transform', d =>
          'rotate(' + (d.x - 90) + ')translate(' + (d.y + 8) + ')' + (d.x < 180 ? '' : 'rotate(180)'))
        .on('mouseover', d => this.mouseOver(d))
        .on('mouseout', d => this.mouseOuted())
        .on('click', d => this.mouseClicked(d));
    }

    const newRoot = d3.hierarchy(this.hierarchy, d => d.children)
      .sum(d => this.valueOfNode(d))
    this.partition(newRoot);

    /*
    this.arcs = this.arcs
      .data(newRoot.descendants())
      .enter().append('path')
      .attr('class', 'arc')
      .attr('d', this.arc)
      .attr('display', function (d) { return d.depth ? null : "none"; })
      .style('stroke', 'white')
    */
  }

  private valueOfNode(node: any) {
    if (node.children.length == 0) {
      return 1;
    }
    return 1 + node.children
      .map(c => this.valueOfNode(c))
      .reduce((a, b) => a + b, 0);
  }


  private mouseOver(d: any) {
    this.nodes.each(node => node.isTarget = node.isSource = false);

    this.links
      .each(link => { if (link.source === d && link.isImplicit) { link.source.isImplicit = true}} )
      .classed('link--target', link => { if (link.target === d) { return link.source.isSource = true; }})
      .classed('link--source', link => { if (link.source === d) { return link.target.isTarget = true; }})
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

  private mouseClicked(d: any) {
    if (this.selectedFeature == d.data) {
      this.selectedFeature = null;
    } else {
      this.selectedFeature = d.data;
    }
    this.redrawDependencyGraph();
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
