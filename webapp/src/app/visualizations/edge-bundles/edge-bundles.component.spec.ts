import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EdgeBundlesComponent} from './edge-bundles.component';

describe('EdgeBundlesComponent', () => {
  let component: EdgeBundlesComponent;
  let fixture: ComponentFixture<EdgeBundlesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EdgeBundlesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EdgeBundlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
