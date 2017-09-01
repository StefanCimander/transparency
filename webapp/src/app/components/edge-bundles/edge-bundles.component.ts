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

  @ViewChild('container') element: ElementRef;

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
      .radius((d: any) => d.y)
      .angle((d: any) => d.x / 180 * Math.PI);
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
      .each(d => { return d.source = d[0], d.target = d[d.length - 1]; })
      .attr('class', 'link')
      .attr('d', this.line)

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

  private mouseOver(d: any) {
    this.nodes.each(node => {
      node.target = node.source = false;
    });
    this.links
      .classed('link--target', link => { if (link.target === d) { return link.source.source = true; }})
      .classed('link--source', link => { if (link.source === d) { return link.target.target = true; }})
      .filter(link => link.target === d || link.source === d)
      .raise();

    this.nodes
      .classed('node--target', node => node.target)
      .classed('node--source', node => node.source);
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
    // this.onFeatureSelected.emit(d.data);
  }

  private featureDependencies(nodes: any[]) {
    const map = {}, dependencies = [];

    // Compute a map from name to node.
    nodes.forEach(d => map[d.data.name] = d);

    // For each dependency, construct a link from the source to the target node.
    nodes.forEach(node => {
      if (node.data.dependencies) {
        node.data.dependencies.forEach(dependency => {
          if (map[dependency.name]) {
            const path = map[node.data.name].path(map[dependency.name]);
            path.type = dependency.type;
            dependencies.push(path);
          }
        });
      }
      /*
      if (this.selectedFeature && node.data.name === this.selectedFeature.name) {
        this.selectedFeature.dependencies.forEach(dependency => {

          if (map[dependency.name]) {
            const path = map[node.data.name].path(map[dependency.name]);
            path.type = dependency.type;
            dependencies.push(path);
          }
        });
      }
      */
    });
    return dependencies;
  }

}
