import { AfterViewInit, Component, ElementRef, Input, OnChanges } from '@angular/core';

import * as d3 from 'd3';
import {DependencySignal} from "../../models/dependency-signal.model";

@Component({
  selector: 'app-mapping-graph',
  templateUrl: 'mapping-graph.template.html',
  styleUrls: ['mapping-graph.style.css']
})
export class MappingGraphComponent implements AfterViewInit, OnChanges {

  private host;
  private svg;
  private width = 1000;
  private height = 500;

  @Input() public features: string[];
  @Input() public receivingFunctions: string[];
  @Input() public sendingFunctions: string[];
  @Input() public signals: DependencySignal[];

  constructor(private element: ElementRef) { }

  ngAfterViewInit() {
    if (this.receivingFunctions) {
      this.drawDependencyMappingGraph();
    }
  }

  ngOnChanges() {
    if (this.receivingFunctions) {
      this.drawDependencyMappingGraph();
    }
  }

  private drawDependencyMappingGraph() {
    this.host = d3.select(this.element.nativeElement);
    this.setupSVG();
    this.drawSignals();
    this.drawReceivingFunctions();
    this.drawSendingFunctions();
    this.drawFeatures();
  }

  private setupSVG() {
    this.height = Math.max(this.receivingFunctions.length, this.sendingFunctions.length) * 100
    this.host.html('');
    this.svg = this.host.append('svg')
      .attr('width', this.width)
      .attr('height', this.height)
      .append('g');
  }

  private drawFeatures() {
    this.svg.append('g')
      .selectAll('.feature')
      .data(this.features)
      .enter().append('circle')
      .attr('class', 'feature')
      .attr('cx', (d, i) => i * 600 + 200)
      .attr('cy', this.height / 2)
      .attr('r', 25)
      .attr('fill', 'white')
      .attr('stroke', 'steelblue')
      .attr('stroke-width', 2);

    this.svg.append('g')
      .selectAll('feature-name')
      .data(this.features)
      .enter().append('text')
      .attr('class', 'feature-name')
      .attr('x', (d, i) => i * 800 + 50)
      .attr('y', this.height / 2 + 45)
      .text(d => d);
  }

  private drawReceivingFunctions() {
    this.svg.append('g')
      .selectAll('receiving-function-mapping')
      .data(this.receivingFunctions)
      .enter().append('line')
      .attr('class', 'receiving-function-mapping')
      .attr('x1', 400)
      .attr('y1', (d, i) => i * 100 + 50)
      .attr('x2', 200)
      .attr('y2', this.height / 2)
      .attr('stroke', 'steelblue')
      .attr('stroke-width', 2);

    this.svg.append('g')
      .selectAll('receiving-function')
      .data(this.receivingFunctions)
      .enter().append('circle')
      .attr('class', 'receiving-function')
      .attr('cx', 400)
      .attr('cy', (d, i) => i * 100 + 50)
      .attr('r', 20)
      .attr('fill', 'white')
      .attr('stroke', '#ff5858')
      .attr('stroke-width', 2);

    this.svg.append('g')
      .selectAll('receiving-function-name')
      .data(this.receivingFunctions)
      .enter().append('text')
      .attr('class', 'receiving-function-name')
      .attr('x', 350)
      .attr('y', (d, i) => i * 100 + 95)
      .text(d => d);
  }

  private drawSendingFunctions() {
    this.svg.append('g')
      .selectAll('sending-function-mapping')
      .data(this.sendingFunctions)
      .enter().append('line')
      .attr('class', 'sending-function-mapping')
      .attr('x1', 600)
      .attr('y1', (d, i) => i * 100 + 50)
      .attr('x2', 800)
      .attr('y2', this.height / 2)
      .attr('stroke', 'steelblue')
      .attr('stroke-width', 2);

    this.svg.append('g')
      .selectAll('sending-function')
      .data(this.sendingFunctions)
      .enter().append('circle')
      .attr('class', 'sending-function')
      .attr('cx', 600)
      .attr('cy', (d, i) => i * 100 + 50)
      .attr('r', 20)
      .attr('fill', 'white')
      .attr('stroke', '#ff5858')
      .attr('stroke-width', 2);

    this.svg.append('g')
      .selectAll('sending-function-name')
      .data(this.sendingFunctions)
      .enter().append('text')
      .attr('class', 'sending-function-name')
      .attr('x', 550)
      .attr('y', (d, i) => i * 100 + 95)
      .text(d => d);
  }

  private drawSignals() {
    this.svg.append('g')
      .selectAll('signal')
      .data(this.signals)
      .enter().append('line')
      .attr('class', 'signal')
      .attr('x1', 400)
      .attr('y1', d => this.receivingFunctions.indexOf(d.receivingFunction) * 100 + 50)
      .attr('x2', 600)
      .attr('y2', d => this.sendingFunctions.indexOf(d.sendingFunction) * 100 + 50)
      .attr('stroke', '#ff5858')
      .attr('stroke-width', 2);
  }

}
